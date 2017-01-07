/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.TempAccountsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class TempAccounts extends TableImpl<TempAccountsRecord> {

    private static final long serialVersionUID = -470909573;

    /**
     * The reference instance of <code>YILDIZDATABASE.TEMP_ACCOUNTS</code>
     */
    public static final TempAccounts TEMP_ACCOUNTS = new TempAccounts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TempAccountsRecord> getRecordType() {
        return TempAccountsRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.LOGIN</code>.
     */
    public final TableField<TempAccountsRecord, String> LOGIN = createField("LOGIN", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.PASSWORD</code>.
     */
    public final TableField<TempAccountsRecord, String> PASSWORD = createField("PASSWORD", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.EMAIL</code>.
     */
    public final TableField<TempAccountsRecord, String> EMAIL = createField("EMAIL", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.CHECK_VALUE</code>.
     */
    public final TableField<TempAccountsRecord, String> CHECK_VALUE = createField("CHECK_VALUE", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.DATE</code>.
     */
    public final TableField<TempAccountsRecord, Timestamp> DATE = createField("DATE", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>YILDIZDATABASE.TEMP_ACCOUNTS.COMPLETE</code>.
     */
    public final TableField<TempAccountsRecord, Boolean> COMPLETE = createField("COMPLETE", org.jooq.impl.SQLDataType.BIT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("b'0'", org.jooq.impl.SQLDataType.BIT)), this, "");

    /**
     * Create a <code>YILDIZDATABASE.TEMP_ACCOUNTS</code> table reference
     */
    public TempAccounts() {
        this("TEMP_ACCOUNTS", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.TEMP_ACCOUNTS</code> table reference
     */
    public TempAccounts(String alias) {
        this(alias, TEMP_ACCOUNTS);
    }

    private TempAccounts(String alias, Table<TempAccountsRecord> aliased) {
        this(alias, aliased, null);
    }

    private TempAccounts(String alias, Table<TempAccountsRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<TempAccountsRecord> getPrimaryKey() {
        return Keys.KEY_TEMP_ACCOUNTS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TempAccountsRecord>> getKeys() {
        return Arrays.<UniqueKey<TempAccountsRecord>>asList(Keys.KEY_TEMP_ACCOUNTS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccounts as(String alias) {
        return new TempAccounts(alias, this);
    }

    /**
     * Rename this table
     */
    public TempAccounts rename(String name) {
        return new TempAccounts(name, null);
    }
}