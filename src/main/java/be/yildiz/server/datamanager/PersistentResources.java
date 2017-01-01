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
import be.yildiz.common.log.Logger;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.server.city.ServerCity;
import be.yildiz.server.city.ServerCityManager;
import be.yildiz.server.generated.database.tables.Resources;
import be.yildiz.server.generated.database.tables.records.ResourcesRecord;
import be.yildiz.shared.resources.ResourceValue;
import be.yildiz.shared.resources.ResourcesProducer;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Persistent data for the game resources.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentResources implements PersistentData<ResourcesProducer> {

    /**
     * Database table containing the data.
     */
    private static final Resources table = Resources.RESOURCES;

    /**
     * Manager to retrieve or persist resources in persistent context.
     */
    private final DataBaseConnectionProvider provider;

    /**
     * Full constructor.
     *
     * @param manager Manager used to retrieve or persist the data.
     * @param entityManager Entity manager.
     */
    public PersistentResources(final PersistentManager manager, final ServerCityManager entityManager) {
        super();
        this.provider = manager.getProvider();
        Result<ResourcesRecord> data = manager.getAll(table);
        // FIXME game related
        //Create an object ResourceModel injected in the engine at construction
        //this object contains the different fields for the resource
        //this object will be responsible to instantiate new ResourceValue
        //the database will have columns name res_0, res_1... instead of game related values.
        for (ResourcesRecord r : data) {
            EntityId cityId = EntityId.get(r.getValue(table.CITY_ID).longValue());
            ServerCity city = entityManager.getCityById(cityId);
            long time = r.getValue(table.LAST_TIME_COMPUTED).getTime();
            float[] values = new float[5];
            values[0] = r.getMetal().floatValue();
            values[1] = r.getEnergy().floatValue();
            values[2] = r.getMoney().floatValue();
            values[3] = r.getResearch().floatValue();
            values[4] = r.getInhabitant().floatValue();
            city.getProducer().setNewValues(time, new ResourceValue(values));
        }
    }

    @Override
    public void save(final ResourcesProducer data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            create.insertInto(Resources.RESOURCES, Resources.RESOURCES.CITY_ID, Resources.RESOURCES.LAST_TIME_COMPUTED, Resources.RESOURCES.METAL, Resources.RESOURCES.ENERGY,
                    Resources.RESOURCES.MONEY, Resources.RESOURCES.RESEARCH, Resources.RESOURCES.INHABITANT)
                    .values(UInteger.valueOf(data.getCity().value), new Timestamp(data.getLastUpdate()), Integer.valueOf((int) data.getResource(0)), Integer.valueOf((int) data.getResource(1)),
                            Integer.valueOf((int) data.getResource(2)), Integer.valueOf((int) data.getResource(3)), UShort.valueOf((int) data.getResource(4))).execute();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public void update(ResourcesProducer data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            ResourcesRecord resources = create.fetchOne(table, table.CITY_ID.equal(UInteger.valueOf(data.getCity().value)));
            resources.setMetal(Integer.valueOf((int) data.getResource(0)));
            resources.setEnergy(Integer.valueOf((int) data.getResource(1)));
            resources.setMoney(Integer.valueOf((int) data.getResource(2)));
            resources.setResearch(Integer.valueOf((int) data.getResource(3)));
            resources.setInhabitant(UShort.valueOf((int) data.getResource(4)));
            resources.store();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
