/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Maps;
import be.yildiz.common.collections.Sets;
import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.id.WorldId;
import be.yildiz.common.vector.Point3D;
import be.yildiz.server.generated.database.tables.Cities;
import be.yildiz.server.generated.database.tables.Entities;
import be.yildiz.server.generated.database.tables.records.EntitiesRecord;
import be.yildiz.shared.construction.entity.EntityFactory;
import be.yildiz.shared.data.EntityType;
import be.yildiz.shared.entity.*;
import be.yildiz.shared.entity.module.ModuleGroup;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Persistent data for entities.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentEntity implements PersistentData<EntityToCreate, BaseEntity>, RecordMapper<EntitiesRecord, BaseEntity> {

    /**
     * Persistent unit where data must be retrieved.
     */
    private static final Entities table = Entities.ENTITIES;

    /**
     * List of Id not used.
     */
    private final Set<EntityId> freeId = Sets.newInsertionOrderedSet();
    private final EntityInConstructionFactory constructionFactory = new EntityInConstructionFactorySimple();;
    private final EntityFactory<BaseEntity> entityFactory;

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param c SQL connection.
     * @param factory Factory to build entities.
     */
    public PersistentEntity(Connection c,
                            final EntityFactory<BaseEntity> factory) {
        super();
        this.entityFactory = factory;
        Map<EntityId, String> names = Maps.newMap();
        try (DSLContext create = this.getDSL(c)) {
            Optional.ofNullable(create.selectFrom(Cities.CITIES).fetch())
                    .ifPresent(citiesRecords ->
                            citiesRecords.forEach(r -> names.put(EntityId.get(r.getId().longValue()), r.getName())));
            //        faire le set a la reception du message entity info response dans le client

            Optional.ofNullable(create.selectFrom(table).fetch())
                    .ifPresent(data ->
                            data.forEach(r -> {
                                EntityId id = EntityId.get(r.getId().longValue());
                                if (r.getActive()) {
                                    PlayerId player = PlayerId.get(r.getOwnerId().intValue());
                                    EntityType type = EntityType.get(r.getType().intValue());
                                    ModuleGroup m = new ModuleGroup.ModuleGroupBuilder()
                                            .withHull(ActionId.get(r.getModuleHull().intValue()))
                                            .withEnergy(ActionId.get(r.getModuleEnergy().intValue()))
                                            .withDetector(ActionId.get(r.getModuleDetector().intValue()))
                                            .withMove(ActionId.get(r.getModuleMove().intValue()))
                                            .withInteraction(ActionId.get(r.getModuleInteraction().intValue()))
                                            .withAdditional1(ActionId.get(r.getModuleAdditional_1().intValue()))
                                            .withAdditional2(ActionId.get(r.getModuleAdditional_2().intValue()))
                                            .withAdditional3(ActionId.get(r.getModuleAdditional_3().intValue()))
                                            .build();
                                    Point3D pos = Point3D.xyz(r.getPositionX().floatValue(), r.getPositionY().floatValue(), r.getPositionZ().floatValue());
                                    Point3D dir = Point3D.xyz(r.getDirectionX().floatValue(), r.getDirectionY().floatValue(), r.getDirectionZ().floatValue());
                                    EntityInConstruction eic = constructionFactory.build(
                                            type,
                                            id,
                                            names.getOrDefault(id, type.name),
                                            m,
                                            player,
                                            pos,
                                            dir,
                                            r.getHitPoint().intValue(),
                                            r.getEnergyPoint().intValue());

                                    factory.createEntity(eic);
                                } else {
                                    this.freeId.add(id);
                                }
                            }));
        }
    }

    @Override
    public BaseEntity save(final EntityToCreate data, Connection c) {
        EntityId id = this.getFreeId(c);
        try(DSLContext context = this.getDSL(c)) {
            context.update(table)
                    .set(table.TYPE, UByte.valueOf(data.getType().type))
                    .set(table.OWNER_ID, UShort.valueOf(data.getOwner().value))
                    .set(table.MODULE_MOVE, UByte.valueOf(data.getModules().getMove().value))
                    .set(table.MODULE_INTERACTION, UByte.valueOf(data.getModules().getInteraction().value))
                    .set(table.MODULE_HULL, UByte.valueOf(data.getModules().getHull().value))
                    .set(table.MODULE_ENERGY, UByte.valueOf(data.getModules().getEnergy().value))
                    .set(table.MODULE_DETECTOR, UByte.valueOf(data.getModules().getDetector().value))
                    .set(table.MODULE_ADDITIONAL_1, UByte.valueOf(data.getModules().getAdditional1().value))
                    .set(table.MODULE_ADDITIONAL_2, UByte.valueOf(data.getModules().getAdditional2().value))
                    .set(table.MODULE_ADDITIONAL_3, UByte.valueOf(data.getModules().getAdditional3().value))
                    .set(table.MAP_ID, UByte.valueOf(1))
                    .set(table.ACTIVE, true)
                    .set(table.HIT_POINT, UShort.valueOf(0)) //FIXME find value
                    .set(table.ENERGY_POINT, UShort.valueOf(0))
                    .set(table.POSITION_X, Double.valueOf(data.getPosition().x))
                    .set(table.POSITION_Y, Double.valueOf(data.getPosition().y))
                    .set(table.POSITION_Z, Double.valueOf(data.getPosition().z))
                    .set(table.DIRECTION_X, Double.valueOf(data.getDirection().x))
                    .set(table.DIRECTION_Y, Double.valueOf(data.getDirection().y))
                    .set(table.DIRECTION_Z, Double.valueOf(data.getDirection().z))
                    .where(table.ID.equal(UInteger.valueOf(id.value)))
                    .execute();
            DefaultEntityInConstruction eic = constructionFactory.build(id, data);
            return entityFactory.createEntity(eic);
        }
    }

    /**
     * @return An id ready to be used to build a new object.
     */
    private EntityId getFreeId(Connection c) {
        if (this.freeId.isEmpty()) {
            return this.createNewLine(c);
        }
        EntityId id = this.freeId.iterator().next();
        this.freeId.remove(id);
        return id;
    }

    /**
     * Add a new line in entity table with unique id, it will if used if no free id can be done.
     *
     * @return the created id.
     */
    private EntityId createNewLine(Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            create.insertInto(table).defaultValues().execute();
            EntitiesRecord entity = create.fetchOne(table, table.ACTIVE.equal(false));
            return EntityId.get(entity.getId().longValue());
        }
    }

    /**
     * Delete an entity.
     *
     * @param id Id of the entity to delete.
     */
    public void delete(final EntityId id, Connection c) {
        this.freeId.add(id);
        try (DSLContext create = this.getDSL(c)) {
            EntitiesRecord entity = create.fetchOne(table, table.ID.equal(UInteger.valueOf(id.value)));
            entity.setActive(false);
            entity.store();
        }
    }

    @Override
    public void update(final BaseEntity data, Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            EntitiesRecord entity = create.fetchOne(table, table.ID.equal(UInteger.valueOf(data.getId().value)));
            entity.setMapId(UByte.valueOf(1));
            entity.setType(UByte.valueOf(data.getType().type));
            entity.setOwnerId(UShort.valueOf(data.getOwner().value));
            entity.setPositionX(Double.valueOf(data.getPosition().x));
            entity.setPositionY(Double.valueOf(data.getPosition().y));
            entity.setPositionZ(Double.valueOf(data.getPosition().z));
            entity.setDirectionX(Double.valueOf(data.getDirection().x));
            entity.setDirectionY(Double.valueOf(data.getDirection().y));
            entity.setDirectionZ(Double.valueOf(data.getDirection().z));
            ModuleGroup modules = data.getModules();
            entity.setModuleHull(UByte.valueOf(modules.getHull().value));
            entity.setModuleEnergy(UByte.valueOf(modules.getEnergy().value));
            entity.setModuleInteraction(UByte.valueOf(modules.getInteraction().value));
            entity.setModuleMove(UByte.valueOf(modules.getMove().value));
            entity.setModuleDetector(UByte.valueOf(modules.getDetector().value));
            entity.setModuleAdditional_1(UByte.valueOf(modules.getAdditional1().value));
            entity.setModuleAdditional_2(UByte.valueOf(modules.getAdditional2().value));
            entity.setModuleAdditional_3(UByte.valueOf(modules.getAdditional3().value));
            entity.setMapId(UByte.valueOf(WorldId.WORLD.value));
            entity.setHitPoint(UShort.valueOf(data.getHitPoints()));
            entity.setEnergyPoint(UShort.valueOf(data.getEnergyPoints()));

            entity.setActive(true);

            // FIXME use right types.
            // Deducted from the modules.
            // entity.setMaxhp(UShort.valueOf(data.getMaxHitPoints()));
            // entity.setMaxenergy(UShort.valueOf(data.getMaxEnergyPoints()));
            entity.store();
        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }

    @Override
    public BaseEntity map(EntitiesRecord entitiesRecord) {
        return null;
    }
}
