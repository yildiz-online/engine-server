/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.DatabasechangelogRecord;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class Databasechangelog extends TableImpl<DatabasechangelogRecord> {

    private static final long serialVersionUID = -1333974914;

    /**
     * The reference instance of <code>YILDIZDATABASE.DATABASECHANGELOG</code>
     */
    public static final Databasechangelog DATABASECHANGELOG = new Databasechangelog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangelogRecord> getRecordType() {
        return DatabasechangelogRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.ID</code>.
     */
    public final TableField<DatabasechangelogRecord, String> ID = createField("ID", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.AUTHOR</code>.
     */
    public final TableField<DatabasechangelogRecord, String> AUTHOR = createField("AUTHOR", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.FILENAME</code>.
     */
    public final TableField<DatabasechangelogRecord, String> FILENAME = createField("FILENAME", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    public final TableField<DatabasechangelogRecord, Timestamp> DATEEXECUTED = createField("DATEEXECUTED", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    public final TableField<DatabasechangelogRecord, Integer> ORDEREXECUTED = createField("ORDEREXECUTED", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.EXECTYPE</code>.
     */
    public final TableField<DatabasechangelogRecord, String> EXECTYPE = createField("EXECTYPE", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.MD5SUM</code>.
     */
    public final TableField<DatabasechangelogRecord, String> MD5SUM = createField("MD5SUM", org.jooq.impl.SQLDataType.VARCHAR.length(35), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.COMMENTS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> COMMENTS = createField("COMMENTS", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.TAG</code>.
     */
    public final TableField<DatabasechangelogRecord, String> TAG = createField("TAG", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LIQUIBASE = createField("LIQUIBASE", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.CONTEXTS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> CONTEXTS = createField("CONTEXTS", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.LABELS</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LABELS = createField("LABELS", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>YILDIZDATABASE.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DEPLOYMENT_ID = createField("DEPLOYMENT_ID", org.jooq.impl.SQLDataType.VARCHAR.length(10), this, "");

    /**
     * Create a <code>YILDIZDATABASE.DATABASECHANGELOG</code> table reference
     */
    public Databasechangelog() {
        this("DATABASECHANGELOG", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.DATABASECHANGELOG</code> table reference
     */
    public Databasechangelog(String alias) {
        this(alias, DATABASECHANGELOG);
    }

    private Databasechangelog(String alias, Table<DatabasechangelogRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangelog(String alias, Table<DatabasechangelogRecord> aliased, Field<?>[] parameters) {
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
    public Databasechangelog as(String alias) {
        return new Databasechangelog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangelog rename(String name) {
        return new Databasechangelog(name, null);
    }
}
