/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.ResourcesRecord;

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
public class Resources extends TableImpl<ResourcesRecord> {

    private static final long serialVersionUID = 570659245;

    /**
     * The reference instance of <code>YILDIZDATABASE.resources</code>
     */
    public static final Resources RESOURCES = new Resources();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ResourcesRecord> getRecordType() {
        return ResourcesRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.resources.city_id</code>.
     */
    public final TableField<ResourcesRecord, UInteger> CITY_ID = createField("city_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.last_time_computed</code>.
     */
    public final TableField<ResourcesRecord, Timestamp> LAST_TIME_COMPUTED = createField("last_time_computed", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.metal</code>.
     */
    public final TableField<ResourcesRecord, Integer> METAL = createField("metal", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.energy</code>.
     */
    public final TableField<ResourcesRecord, Integer> ENERGY = createField("energy", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.money</code>.
     */
    public final TableField<ResourcesRecord, Integer> MONEY = createField("money", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.research</code>.
     */
    public final TableField<ResourcesRecord, Integer> RESEARCH = createField("research", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>YILDIZDATABASE.resources.inhabitant</code>.
     */
    public final TableField<ResourcesRecord, UShort> INHABITANT = createField("inhabitant", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false), this, "");

    /**
     * Create a <code>YILDIZDATABASE.resources</code> table reference
     */
    public Resources() {
        this("resources", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.resources</code> table reference
     */
    public Resources(String alias) {
        this(alias, RESOURCES);
    }

    private Resources(String alias, Table<ResourcesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Resources(String alias, Table<ResourcesRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<ResourcesRecord> getPrimaryKey() {
        return Keys.KEY_RESOURCES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ResourcesRecord>> getKeys() {
        return Arrays.<UniqueKey<ResourcesRecord>>asList(Keys.KEY_RESOURCES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resources as(String alias) {
        return new Resources(alias, this);
    }

    /**
     * Rename this table
     */
    public Resources rename(String name) {
        return new Resources(name, null);
    }
}
