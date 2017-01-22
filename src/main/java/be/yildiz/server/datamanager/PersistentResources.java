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
import be.yildiz.server.generated.database.tables.Resources;
import be.yildiz.server.generated.database.tables.records.ResourcesRecord;
import be.yildiz.shared.resources.ResourceValue;
import be.yildiz.shared.resources.ResourcesProducer;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Persistent data for the game resources.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentResources implements PersistentData<ResourcesProducer, ResourcesProducer> {

    /**
     * Database table containing the data.
     */
    private static final Resources table = Resources.RESOURCES;

    /**
     * Full constructor.
     *
     * @param manager Manager used to retrieve or persist the data.
     * @param entityManager Entity manager.
     */
    public PersistentResources(final PersistentManager manager, final ServerCityManager entityManager) {
        super();
        // FIXME game related
        //Create an object ResourceModel injected in the engine at construction
        //this object contains the different fields for the resource
        //this object will be responsible to instantiate new ResourceValue
        //the database will have columns name res_0, res_1... instead of game related values.
        Optional.ofNullable(manager.getAll(table))
                .ifPresent(data -> data.forEach(r -> {
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
                }));
    }

    @Override
    public void save(final ResourcesProducer data, Connection c) {
        try (DSLContext create = DSL.using(c)) {
            create.insertInto(Resources.RESOURCES, Resources.RESOURCES.CITY_ID, Resources.RESOURCES.LAST_TIME_COMPUTED, Resources.RESOURCES.METAL, Resources.RESOURCES.ENERGY,
                    Resources.RESOURCES.MONEY, Resources.RESOURCES.RESEARCH, Resources.RESOURCES.INHABITANT)
                    .values(UInteger.valueOf(data.getCity().value), new Timestamp(data.getLastUpdate()), (int) data.getResource(0), (int) data.getResource(1),
                            (int) data.getResource(2), (int) data.getResource(3), UShort.valueOf((int) data.getResource(4))).execute();
        }
    }

    @Override
    public void update(ResourcesProducer data, Connection c) {
        try (DSLContext create = DSL.using(c)) {
            ResourcesRecord resources = create.fetchOne(table, table.CITY_ID.equal(UInteger.valueOf(data.getCity().value)));
            resources.setMetal((int) data.getResource(0));
            resources.setEnergy((int) data.getResource(1));
            resources.setMoney((int) data.getResource(2));
            resources.setResearch((int) data.getResource(3));
            resources.setInhabitant(UShort.valueOf((int) data.getResource(4)));
            resources.store();
        }
    }
}
