/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.TasksRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


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
public class Tasks extends TableImpl<TasksRecord> {

    private static final long serialVersionUID = 860893608;

    /**
     * The reference instance of <code>YILDIZDATABASE.TASKS</code>
     */
    public static final Tasks TASKS = new Tasks();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TasksRecord> getRecordType() {
        return TasksRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.TASKS.TSK_ID</code>.
     */
    public final TableField<TasksRecord, UInteger> TSK_ID = createField("TSK_ID", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * Create a <code>YILDIZDATABASE.TASKS</code> table reference
     */
    public Tasks() {
        this("TASKS", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.TASKS</code> table reference
     */
    public Tasks(String alias) {
        this(alias, TASKS);
    }

    private Tasks(String alias, Table<TasksRecord> aliased) {
        this(alias, aliased, null);
    }

    private Tasks(String alias, Table<TasksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Yildizdatabase.YILDIZDATABASE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TasksRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_TASKS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TasksRecord> getPrimaryKey() {
        return Keys.KEY_TASKS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TasksRecord>> getKeys() {
        return Arrays.<UniqueKey<TasksRecord>>asList(Keys.KEY_TASKS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tasks as(String alias) {
        return new Tasks(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Tasks rename(String name) {
        return new Tasks(name, null);
    }
}
