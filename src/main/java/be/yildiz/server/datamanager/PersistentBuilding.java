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

import be.yildiz.common.id.EntityId;
import be.yildiz.common.log.Logger;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.server.generated.database.tables.Buildings;
import be.yildiz.server.generated.database.tables.records.BuildingsRecord;
import be.yildiz.shared.building.BaseBuilding;
import be.yildiz.shared.building.GameBuildingData;
import be.yildiz.shared.city.City;
import be.yildiz.shared.city.CityManager;
import be.yildiz.shared.construction.building.BuildingConstructionListener;
import be.yildiz.shared.construction.building.BuildingFactory;
import be.yildiz.shared.data.BuildingPosition;
import be.yildiz.shared.data.EntityType;
import be.yildiz.shared.data.Level;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Persistent data for buildings.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentBuilding implements PersistentData<BaseBuilding>, RecordMapper<BuildingsRecord, BaseBuilding> {

    /**
     * Persistent unit where data must be retrieved.
     */
    private static final Buildings table = Buildings.BUILDINGS;

    /**
     * Manager to retrieve or persist buildings in persistent context.
     */
    private final DataBaseConnectionProvider provider;

    /**
     * Associated city manager.
     */
    private CityManager<BaseBuilding, GameBuildingData, City<BaseBuilding, GameBuildingData>> cityManager;

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param manager  Manager used with the persistent context.
     * @param factory  Factory to build entities.
     * @param listener Listener to notify for a new building.
     */
    public PersistentBuilding(final PersistentManager manager, final BuildingFactory<BaseBuilding> factory, final CityManager<BaseBuilding, GameBuildingData, City<BaseBuilding, GameBuildingData>> em, final BuildingConstructionListener<BaseBuilding, GameBuildingData, City<BaseBuilding, GameBuildingData>> listener) {
        super();
        this.provider = manager.getProvider();
        this.cityManager = em;
        Result<BuildingsRecord> data = manager.getAll(table);
        data.map(this).stream().filter(b -> b.getLevel().isNotZero()).forEach(b -> {
            factory.createBuilding(b);
            City<BaseBuilding, GameBuildingData> c = em.getCityById(b.getCity());
            listener.buildingComplete(c, b);
        });
        em.getCities().forEach(City::initializeProducer);
    }

    @Override
    public void save(final BaseBuilding data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            create.insertInto(table, table.BASE_ID, table.POSITION, table.TYPE, table.LEVEL, table.STAFF)
                    .values(UInteger.valueOf(data.getCity().value), UByte.valueOf(data.getBuildingPosition().value), UByte.valueOf(data.getType().type), UByte.valueOf(data.getLevel().value),
                            UShort.valueOf(data.getStaff())).execute();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void update(final BaseBuilding data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            BuildingsRecord building = create.fetchOne(table, table.BASE_ID.equal(UInteger.valueOf(data.getCity().value)).and(table.POSITION.equal(UByte.valueOf(data.getBuildingPosition().value))));
            building.setBaseId(UInteger.valueOf(data.getCity().value));
            building.setType(UByte.valueOf(data.getType().type));
            building.setLevel(UByte.valueOf(data.getLevel().value));
            building.setStaff(UShort.valueOf(data.getStaff()));
            building.store();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public BaseBuilding map(BuildingsRecord r) {
        EntityId id = EntityId.get(r.getBaseId().longValue());
        BuildingPosition pos = new BuildingPosition(r.getPosition().intValue());
        EntityType type = EntityType.get(r.getType().intValue());
        Level level = new Level(r.getLevel().intValue());
        int staff = r.getStaff().intValue();
        return new BaseBuilding(id, cityManager.getData(type), pos, level, staff);
    }
}
