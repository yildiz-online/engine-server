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

/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Resources;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import javax.annotation.Generated;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResourcesRecord extends UpdatableRecordImpl<ResourcesRecord> implements Record7<UInteger, Timestamp, Integer, Integer, Integer, Integer, UShort> {

    private static final long serialVersionUID = 898476618;

    /**
     * Setter for <code>yildizdatabase.resources.city_id</code>.
     */
    public void setCityId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.city_id</code>.
     */
    public UInteger getCityId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>yildizdatabase.resources.last_time_computed</code>.
     */
    public void setLastTimeComputed(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.last_time_computed</code>.
     */
    public Timestamp getLastTimeComputed() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>yildizdatabase.resources.metal</code>.
     */
    public void setMetal(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.metal</code>.
     */
    public Integer getMetal() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>yildizdatabase.resources.energy</code>.
     */
    public void setEnergy(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.energy</code>.
     */
    public Integer getEnergy() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>yildizdatabase.resources.money</code>.
     */
    public void setMoney(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.money</code>.
     */
    public Integer getMoney() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>yildizdatabase.resources.research</code>.
     */
    public void setResearch(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.research</code>.
     */
    public Integer getResearch() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>yildizdatabase.resources.inhabitant</code>.
     */
    public void setInhabitant(UShort value) {
        set(6, value);
    }

    /**
     * Getter for <code>yildizdatabase.resources.inhabitant</code>.
     */
    public UShort getInhabitant() {
        return (UShort) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, Timestamp, Integer, Integer, Integer, Integer, UShort> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, Timestamp, Integer, Integer, Integer, Integer, UShort> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Resources.RESOURCES.CITY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Resources.RESOURCES.LAST_TIME_COMPUTED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Resources.RESOURCES.METAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Resources.RESOURCES.ENERGY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Resources.RESOURCES.MONEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Resources.RESOURCES.RESEARCH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UShort> field7() {
        return Resources.RESOURCES.INHABITANT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getCityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getLastTimeComputed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getMetal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getEnergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getMoney();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getResearch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UShort value7() {
        return getInhabitant();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value1(UInteger value) {
        setCityId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value2(Timestamp value) {
        setLastTimeComputed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value3(Integer value) {
        setMetal(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value4(Integer value) {
        setEnergy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value5(Integer value) {
        setMoney(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value6(Integer value) {
        setResearch(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord value7(UShort value) {
        setInhabitant(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourcesRecord values(UInteger value1, Timestamp value2, Integer value3, Integer value4, Integer value5, Integer value6, UShort value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ResourcesRecord
     */
    public ResourcesRecord() {
        super(Resources.RESOURCES);
    }

    /**
     * Create a detached, initialised ResourcesRecord
     */
    public ResourcesRecord(UInteger cityId, Timestamp lastTimeComputed, Integer metal, Integer energy, Integer money, Integer research, UShort inhabitant) {
        super(Resources.RESOURCES);

        set(0, cityId);
        set(1, lastTimeComputed);
        set(2, metal);
        set(3, energy);
        set(4, money);
        set(5, research);
        set(6, inhabitant);
    }
}
