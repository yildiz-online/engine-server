//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.collections.Maps;
import be.yildiz.common.collections.Sets;
import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.id.WorldId;
import be.yildiz.common.log.Logger;
import be.yildiz.common.vector.Point3D;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.server.generated.database.tables.Cities;
import be.yildiz.server.generated.database.tables.Entities;
import be.yildiz.server.generated.database.tables.EntityModules;
import be.yildiz.server.generated.database.tables.records.CitiesRecord;
import be.yildiz.server.generated.database.tables.records.EntitiesRecord;
import be.yildiz.server.generated.database.tables.records.EntityModulesRecord;
import be.yildiz.shared.construction.entity.EntityFactory;
import be.yildiz.shared.data.EntityType;
import be.yildiz.shared.entity.*;
import be.yildiz.shared.entity.module.ModuleGroup;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Persistent data for entities.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentEntity implements PersistentData<BaseEntity> {

    /**
     * Persistent unit where data must be retrieved.
     */
    private static final Entities table = Entities.ENTITIES;

    /**
     * Manager to retrieve or persist entities in persistent context.
     */
    private final DataBaseConnectionProvider provider;

    /**
     * List of Id not used.
     */
    private final Set<EntityId> freeId = Sets.newInsertionOrderedSet();

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param manager Manager used with the persistent context.
     * @param factory Factory to build entities.
     */
    public PersistentEntity(final PersistentManager manager, final EntityInConstructionFactory constructionFactory, final EntityManager<BaseEntity, GameEntityData> entityManager, final EntityFactory<BaseEntity> factory) {
        super();
        this.provider = manager.getProvider();
        Result<EntitiesRecord> data = manager.getAll(table);

        Result<EntityModulesRecord> moduleRecords = manager.getAll(EntityModules.ENTITY_MODULES);

        Map<Integer, ModuleGroup> entityModules = Maps.newMap();

        for (EntityModulesRecord r : moduleRecords) {
            List<ActionId> modules = Lists.newList();
            modules.add(ActionId.get(r.getMove().intValue()));
            modules.add(ActionId.get(r.getInteraction().intValue()));
            modules.add(ActionId.get(r.getProtect().intValue()));
            modules.add(ActionId.get(r.getEnergy().intValue()));
            Short o1 = r.getOther_1();
            Short o2 = r.getOther_2();
            Short o3 = r.getOther_3();
            if (o1 != null) {
                modules.add(ActionId.get(o1));
            }
            if (o2 != null) {
                modules.add(ActionId.get(o2));
            }
            if (o3 != null) {
                modules.add(ActionId.get(o3));
            }
            entityModules.put(r.getId().intValue(), new ModuleGroup(modules));
        }


        Result<CitiesRecord> citiesRecords = manager.getAll(Cities.CITIES);


//        faire le set a la reception du message entityinforesponse dans le client

        Map<EntityId, String> names = Maps.newMap();
        for (CitiesRecord r : citiesRecords) {
            names.put(EntityId.get(r.getId().longValue()), r.getName());
        }

        for (EntitiesRecord r : data) {
            EntityId id = EntityId.get(r.getId().longValue());
            if (r.getActive()) {
                PlayerId player = PlayerId.get(r.getOwner().intValue());
                EntityType type = EntityType.get(r.getType().intValue());
                ModuleGroup modules = entityModules.get(r.getModules().intValue());
                Point3D pos = Point3D.xyz(r.getPositionX().floatValue(), r.getPositionY().floatValue(), r.getPositionZ().floatValue());
                Point3D dir = Point3D.xyz(r.getDirectionX().floatValue(), r.getDirectionY().floatValue(), r.getDirectionZ().floatValue());
                EntityInConstruction eic = constructionFactory.build(type, id, names.getOrDefault(id, type.name), modules, player, pos, dir, r.getHp().intValue(), r.getEnergy().intValue());

                factory.createEntity(eic);
            } else {
                this.freeId.add(id);
            }
        }
    }

    @Override
    public void save(final BaseEntity data) {
        this.update(data);
    }

    /**
     * @return An id ready to be used to build a new object.
     */
    public EntityId getFreeId() {
        if (this.freeId.isEmpty()) {
            return this.createNewLine();
        }
        EntityId id = this.freeId.iterator().next();
        this.freeId.remove(id);
        return id;
    }

    /**
     * Add a new line in entity table with unique id, it will if used if no free id can be done.
     *
     * @return
     */
    private EntityId createNewLine() {
        try (Connection c = this.provider.getConnection()) {
            DSLContext create = this.getDSL(c);
            create.insertInto(table, table.ACTIVE).values(false).execute();

            EntitiesRecord entity = create.fetchOne(table, table.ACTIVE.equal(false));
            return EntityId.get(entity.getId().longValue());
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }

    /**
     * Delete an entity.
     *
     * @param id Id of the entity to delete.
     */
    public void delete(final EntityId id) {
        this.freeId.add(id);
        try (Connection c = this.provider.getConnection(); DSLContext create = this.getDSL(c)) {
            EntitiesRecord entity = create.fetchOne(table, table.ID.equal(UInteger.valueOf(id.value)));
            entity.setActive(false);
            entity.store();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public void update(final BaseEntity data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = this.getDSL(c)) {
            EntitiesRecord entity = create.fetchOne(table, table.ID.equal(UInteger.valueOf(data.getId().value)));
            entity.setType(UByte.valueOf(data.getType().type));
            entity.setOwner(UShort.valueOf(data.getOwner().value));
            entity.setPositionX(Double.valueOf(data.getPosition().x));
            entity.setPositionY(Double.valueOf(data.getPosition().y));
            entity.setPositionZ(Double.valueOf(data.getPosition().z));
            entity.setDirectionX(Double.valueOf(data.getDirection().x));
            entity.setDirectionY(Double.valueOf(data.getDirection().y));
            entity.setDirectionZ(Double.valueOf(data.getDirection().z));
            entity.setMap(UByte.valueOf(WorldId.WORLD.value));
            entity.setHp(UShort.valueOf(data.getHitPoints()));
            entity.setEnergy(UShort.valueOf(data.getEnergyPoints()));
            //FIXME use list of ids
            //entity.setModules(UShort.valueOf(data.getModuleIds().value));
            entity.setActive(true);

            // FIXME use right types.
            // Deducted from the modules.
            // entity.setMaxhp(UShort.valueOf(data.getMaxHitPoints()));
            // entity.setMaxenergy(UShort.valueOf(data.getMaxEnergyPoints()));
            entity.store();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, this.provider.getDialect(), settings);
    }
}
