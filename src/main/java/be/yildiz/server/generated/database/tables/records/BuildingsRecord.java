/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Buildings;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BuildingsRecord extends UpdatableRecordImpl<BuildingsRecord> implements Record5<UInteger, UByte, UByte, UByte, UShort> {

    private static final long serialVersionUID = -110164758;

    /**
     * Setter for <code>YILDIZDATABASE.BUILDINGS.BASE_ID</code>.
     */
    public void setBaseId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.BUILDINGS.BASE_ID</code>.
     */
    public UInteger getBaseId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>YILDIZDATABASE.BUILDINGS.POSITION</code>. between 0 and 8
     */
    public void setPosition(UByte value) {
        set(1, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.BUILDINGS.POSITION</code>. between 0 and 8
     */
    public UByte getPosition() {
        return (UByte) get(1);
    }

    /**
     * Setter for <code>YILDIZDATABASE.BUILDINGS.TYPE</code>.
     */
    public void setType(UByte value) {
        set(2, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.BUILDINGS.TYPE</code>.
     */
    public UByte getType() {
        return (UByte) get(2);
    }

    /**
     * Setter for <code>YILDIZDATABASE.BUILDINGS.LEVEL</code>.
     */
    public void setLevel(UByte value) {
        set(3, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.BUILDINGS.LEVEL</code>.
     */
    public UByte getLevel() {
        return (UByte) get(3);
    }

    /**
     * Setter for <code>YILDIZDATABASE.BUILDINGS.STAFF</code>.
     */
    public void setStaff(UShort value) {
        set(4, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.BUILDINGS.STAFF</code>.
     */
    public UShort getStaff() {
        return (UShort) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<UInteger, UByte> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<UInteger, UByte, UByte, UByte, UShort> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<UInteger, UByte, UByte, UByte, UShort> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Buildings.BUILDINGS.BASE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field2() {
        return Buildings.BUILDINGS.POSITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field3() {
        return Buildings.BUILDINGS.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field4() {
        return Buildings.BUILDINGS.LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UShort> field5() {
        return Buildings.BUILDINGS.STAFF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getBaseId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value2() {
        return getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value3() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value4() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UShort value5() {
        return getStaff();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord value1(UInteger value) {
        setBaseId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord value2(UByte value) {
        setPosition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord value3(UByte value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord value4(UByte value) {
        setLevel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord value5(UShort value) {
        setStaff(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingsRecord values(UInteger value1, UByte value2, UByte value3, UByte value4, UShort value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BuildingsRecord
     */
    public BuildingsRecord() {
        super(Buildings.BUILDINGS);
    }

    /**
     * Create a detached, initialised BuildingsRecord
     */
    public BuildingsRecord(UInteger baseId, UByte position, UByte type, UByte level, UShort staff) {
        super(Buildings.BUILDINGS);

        set(0, baseId);
        set(1, position);
        set(2, type);
        set(3, level);
        set(4, staff);
    }
}
