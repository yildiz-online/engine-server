/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Databasechangeloglock;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DatabasechangeloglockRecord extends UpdatableRecordImpl<DatabasechangeloglockRecord> implements Record4<Integer, Boolean, Timestamp, String> {

    private static final long serialVersionUID = 446299589;

    /**
     * Setter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public void setLocked(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public Boolean getLocked() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public void setLockgranted(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public Timestamp getLockgranted() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public void setLockedby(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public String getLockedby() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Boolean, Timestamp, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Boolean, Timestamp, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Databasechangeloglock.DATABASECHANGELOGLOCK.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field2() {
        return Databasechangeloglock.DATABASECHANGELOGLOCK.LOCKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Databasechangeloglock.DATABASECHANGELOGLOCK.LOCKGRANTED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Databasechangeloglock.DATABASECHANGELOGLOCK.LOCKEDBY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value2() {
        return getLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getLockgranted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getLockedby();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabasechangeloglockRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabasechangeloglockRecord value2(Boolean value) {
        setLocked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabasechangeloglockRecord value3(Timestamp value) {
        setLockgranted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabasechangeloglockRecord value4(String value) {
        setLockedby(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabasechangeloglockRecord values(Integer value1, Boolean value2, Timestamp value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord() {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);
    }

    /**
     * Create a detached, initialised DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord(Integer id, Boolean locked, Timestamp lockgranted, String lockedby) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        set(0, id);
        set(1, locked);
        set(2, lockgranted);
        set(3, lockedby);
    }
}