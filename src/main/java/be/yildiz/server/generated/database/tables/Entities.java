/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.EntitiesRecord;

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
public class Entities extends TableImpl<EntitiesRecord> {

    private static final long serialVersionUID = -1758480462;

    /**
     * The reference instance of <code>YILDIZDATABASE.ENTITIES</code>
     */
    public static final Entities ENTITIES = new Entities();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EntitiesRecord> getRecordType() {
        return EntitiesRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.ID</code>.
     */
    public final TableField<EntitiesRecord, UInteger> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.TYPE</code>.
     */
    public final TableField<EntitiesRecord, UByte> TYPE = createField("TYPE", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("9", org.jooq.impl.SQLDataType.TINYINTUNSIGNED)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.OWNER_ID</code>.
     */
    public final TableField<EntitiesRecord, UShort> OWNER_ID = createField("OWNER_ID", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MAP_ID</code>.
     */
    public final TableField<EntitiesRecord, UByte> MAP_ID = createField("MAP_ID", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.TINYINTUNSIGNED)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.HIT_POINT</code>.
     */
    public final TableField<EntitiesRecord, UShort> HIT_POINT = createField("HIT_POINT", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.ENERGY_POINT</code>.
     */
    public final TableField<EntitiesRecord, UShort> ENERGY_POINT = createField("ENERGY_POINT", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_HULL</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_HULL = createField("MODULE_HULL", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_ENERGY</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_ENERGY = createField("MODULE_ENERGY", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_DETECTOR</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_DETECTOR = createField("MODULE_DETECTOR", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_MOVE</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_MOVE = createField("MODULE_MOVE", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_INTERACTION</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_INTERACTION = createField("MODULE_INTERACTION", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_ADDITIONAL_1</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_ADDITIONAL_1 = createField("MODULE_ADDITIONAL_1", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_ADDITIONAL_2</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_ADDITIONAL_2 = createField("MODULE_ADDITIONAL_2", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.MODULE_ADDITIONAL_3</code>.
     */
    public final TableField<EntitiesRecord, UByte> MODULE_ADDITIONAL_3 = createField("MODULE_ADDITIONAL_3", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.ACTIVE</code>.
     */
    public final TableField<EntitiesRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("b'0'", org.jooq.impl.SQLDataType.BIT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.POSITION_X</code>.
     */
    public final TableField<EntitiesRecord, Double> POSITION_X = createField("POSITION_X", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.POSITION_Y</code>.
     */
    public final TableField<EntitiesRecord, Double> POSITION_Y = createField("POSITION_Y", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.POSITION_Z</code>.
     */
    public final TableField<EntitiesRecord, Double> POSITION_Z = createField("POSITION_Z", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.DIRECTION_X</code>.
     */
    public final TableField<EntitiesRecord, Double> DIRECTION_X = createField("DIRECTION_X", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.DIRECTION_Y</code>.
     */
    public final TableField<EntitiesRecord, Double> DIRECTION_Y = createField("DIRECTION_Y", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * The column <code>YILDIZDATABASE.ENTITIES.DIRECTION_Z</code>.
     */
    public final TableField<EntitiesRecord, Double> DIRECTION_Z = createField("DIRECTION_Z", org.jooq.impl.SQLDataType.FLOAT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("-1", org.jooq.impl.SQLDataType.FLOAT)), this, "");

    /**
     * Create a <code>YILDIZDATABASE.ENTITIES</code> table reference
     */
    public Entities() {
        this("ENTITIES", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.ENTITIES</code> table reference
     */
    public Entities(String alias) {
        this(alias, ENTITIES);
    }

    private Entities(String alias, Table<EntitiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entities(String alias, Table<EntitiesRecord> aliased, Field<?>[] parameters) {
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
    public Identity<EntitiesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_ENTITIES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EntitiesRecord> getPrimaryKey() {
        return Keys.KEY_ENTITIES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EntitiesRecord>> getKeys() {
        return Arrays.<UniqueKey<EntitiesRecord>>asList(Keys.KEY_ENTITIES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entities as(String alias) {
        return new Entities(alias, this);
    }

    /**
     * Rename this table
     */
    public Entities rename(String name) {
        return new Entities(name, null);
    }
}
