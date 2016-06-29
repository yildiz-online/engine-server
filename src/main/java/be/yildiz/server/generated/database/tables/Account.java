/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.AccountRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.UByte;
import org.jooq.types.UShort;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.7.3"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Account extends TableImpl<AccountRecord> {

    /**
     * The reference instance of <code>yildizdatabase.account</code>
     */
    public static final Account ACCOUNT = new Account();
    private static final long serialVersionUID = -219967033;
    /**
     * The column <code>yildizdatabase.account.id</code>.
     */
    public final TableField<AccountRecord, UShort> ID = createField("id", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>yildizdatabase.account.username</code>.
     */
    public final TableField<AccountRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false).defaulted(true), this, "");
    /**
     * The column <code>yildizdatabase.account.password</code>.
     */
    public final TableField<AccountRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(40).nullable(false).defaulted(true), this, "");
    /**
     * The column <code>yildizdatabase.account.type</code>. 0 for regular player, 1 for GM, 2 for admin, 3 for webapp
     */
    public final TableField<AccountRecord, UByte> TYPE = createField("type", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false).defaulted(true), this, "0 for regular player, 1 for GM, 2 for admin, 3 for webapp");
    /**
     * The column <code>yildizdatabase.account.online</code>.
     */
    public final TableField<AccountRecord, Boolean> ONLINE = createField("online", org.jooq.impl.SQLDataType.BIT.nullable(false).defaulted(true), this, "");
    /**
     * The column <code>yildizdatabase.account.map</code>.
     */
    public final TableField<AccountRecord, UByte> MAP = createField("map", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false).defaulted(true), this, "");
    /**
     * The column <code>yildizdatabase.account.active</code>. 0 if the player is deleted, 1 if exist in game
     */
    public final TableField<AccountRecord, Boolean> ACTIVE = createField("active", org.jooq.impl.SQLDataType.BIT.nullable(false).defaulted(true), this, "0 if the player is deleted, 1 if exist in game");
    /**
     * The column <code>yildizdatabase.account.email</code>.
     */
    public final TableField<AccountRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false).defaulted(true), this, "");
    /**
     * The column <code>yildizdatabase.account.last_connection</code>.
     */
    public final TableField<AccountRecord, Timestamp> LAST_CONNECTION = createField("last_connection", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

    /**
     * Create a <code>yildizdatabase.account</code> table reference
     */
    public Account() {
        this("account", null);
    }

    /**
     * Create an aliased <code>yildizdatabase.account</code> table reference
     */
    public Account(String alias) {
        this(alias, ACCOUNT);
    }

    private Account(String alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(String alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, Yildizdatabase.YILDIZDATABASE, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AccountRecord, UShort> getIdentity() {
        return Keys.IDENTITY_ACCOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.KEY_ACCOUNT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccountRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountRecord>>asList(Keys.KEY_ACCOUNT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account as(String alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    public Account rename(String name) {
        return new Account(name, null);
    }
}
