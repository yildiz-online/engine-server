/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.WebLostPasswordRecord;

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
public class WebLostPassword extends TableImpl<WebLostPasswordRecord> {

    private static final long serialVersionUID = 469813724;

    /**
     * The reference instance of <code>YILDIZDATABASE.web_lost_password</code>
     */
    public static final WebLostPassword WEB_LOST_PASSWORD = new WebLostPassword();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WebLostPasswordRecord> getRecordType() {
        return WebLostPasswordRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.web_lost_password.id</code>.
     */
    public final TableField<WebLostPasswordRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.web_lost_password.email</code>.
     */
    public final TableField<WebLostPasswordRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>YILDIZDATABASE.web_lost_password.check_value</code>.
     */
    public final TableField<WebLostPasswordRecord, String> CHECK_VALUE = createField("check_value", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * Create a <code>YILDIZDATABASE.web_lost_password</code> table reference
     */
    public WebLostPassword() {
        this("web_lost_password", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.web_lost_password</code> table reference
     */
    public WebLostPassword(String alias) {
        this(alias, WEB_LOST_PASSWORD);
    }

    private WebLostPassword(String alias, Table<WebLostPasswordRecord> aliased) {
        this(alias, aliased, null);
    }

    private WebLostPassword(String alias, Table<WebLostPasswordRecord> aliased, Field<?>[] parameters) {
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
    public Identity<WebLostPasswordRecord, Integer> getIdentity() {
        return Keys.IDENTITY_WEB_LOST_PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WebLostPasswordRecord> getPrimaryKey() {
        return Keys.KEY_WEB_LOST_PASSWORD_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WebLostPasswordRecord>> getKeys() {
        return Arrays.<UniqueKey<WebLostPasswordRecord>>asList(Keys.KEY_WEB_LOST_PASSWORD_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebLostPassword as(String alias) {
        return new WebLostPassword(alias, this);
    }

    /**
     * Rename this table
     */
    public WebLostPassword rename(String name) {
        return new WebLostPassword(name, null);
    }
}
