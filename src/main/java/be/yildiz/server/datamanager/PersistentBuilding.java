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

import be.yildiz.common.id.EntityId;
import be.yildiz.server.city.ServerCity;
import be.yildiz.server.city.ServerCityManager;
import be.yildiz.server.generated.database.tables.Buildings;
import be.yildiz.server.generated.database.tables.records.BuildingsRecord;
import be.yildiz.shared.building.BaseBuilding;
import be.yildiz.shared.building.GameBuildingData;
import be.yildiz.shared.city.City;
import be.yildiz.shared.construction.building.BuildingConstructionManager;
import be.yildiz.shared.data.BuildingPosition;
import be.yildiz.shared.data.EntityType;
import be.yildiz.shared.data.Level;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.Optional;

/**
 * Persistent data for buildings.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentBuilding implements PersistentData<BaseBuilding, BaseBuilding>, RecordMapper<BuildingsRecord, BaseBuilding> {

    /**
     * Persistent unit where data must be retrieved.
     */
    private static final Buildings table = Buildings.BUILDINGS;

    /**
     * Associated city manager.
     */
    private ServerCityManager cityManager;

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param constructionManager Construction manager.
     * @param em City manager.
     */
    public PersistentBuilding(final Connection c, BuildingConstructionManager<BaseBuilding, GameBuildingData, ServerCity> constructionManager, final ServerCityManager em) {
        super();
        this.cityManager = em;
        try (DSLContext create = this.getDSL(c)) {
            Optional
                    .ofNullable(create.selectFrom(table).fetch())
                    .ifPresent(data -> data.map(this)
                            .stream()
                            .filter(BaseBuilding::exists)
                            .forEach(constructionManager::createBuilding));
            em.getCities().forEach(City::initializeProducer);
        }

    }

    @Override
    public BaseBuilding save(final BaseBuilding data, Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            create.insertInto(table, table.BASE_ID, table.POSITION, table.TYPE, table.LEVEL, table.STAFF)
                    .values(
                            UInteger.valueOf(data.getCity().value),
                            UByte.valueOf(data.getBuildingPosition().value),
                            UByte.valueOf(data.getType().type),
                            UByte.valueOf(data.getLevel().value),
                            UShort.valueOf(data.getStaff()))
                    .execute();
            return data;
        }
    }

    @Override
    public void update(final BaseBuilding data, Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            BuildingsRecord building = create.fetchOne(table, table.BASE_ID.equal(UInteger.valueOf(data.getCity().value)).and(table.POSITION.equal(UByte.valueOf(data.getBuildingPosition().value))));
            building.setBaseId(UInteger.valueOf(data.getCity().value));
            building.setType(UByte.valueOf(data.getType().type));
            building.setLevel(UByte.valueOf(data.getLevel().value));
            building.setStaff(UShort.valueOf(data.getStaff()));
            building.store();
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

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }
}
