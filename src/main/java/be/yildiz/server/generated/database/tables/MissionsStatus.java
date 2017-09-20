/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database.tables;


import be.yildiz.server.generated.database.Keys;
import be.yildiz.server.generated.database.Yildizdatabase;
import be.yildiz.server.generated.database.tables.records.MissionsStatusRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MissionsStatus extends TableImpl<MissionsStatusRecord> {

    private static final long serialVersionUID = -1365615262;

    /**
     * The reference instance of <code>YILDIZDATABASE.MISSIONS_STATUS</code>
     */
    public static final MissionsStatus MISSIONS_STATUS = new MissionsStatus();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MissionsStatusRecord> getRecordType() {
        return MissionsStatusRecord.class;
    }

    /**
     * The column <code>YILDIZDATABASE.MISSIONS_STATUS.MST_ID</code>.
     */
    public final TableField<MissionsStatusRecord, UInteger> MST_ID = createField("MST_ID", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>YILDIZDATABASE.MISSIONS_STATUS.MIS_ID</code>.
     */
    public final TableField<MissionsStatusRecord, UInteger> MIS_ID = createField("MIS_ID", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>YILDIZDATABASE.MISSIONS_STATUS.PLY_ID</code>.
     */
    public final TableField<MissionsStatusRecord, UShort> PLY_ID = createField("PLY_ID", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED, this, "");

    /**
     * The column <code>YILDIZDATABASE.MISSIONS_STATUS.STATUS</code>.
     */
    public final TableField<MissionsStatusRecord, UByte> STATUS = createField("STATUS", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.TINYINTUNSIGNED)), this, "");

    /**
     * Create a <code>YILDIZDATABASE.MISSIONS_STATUS</code> table reference
     */
    public MissionsStatus() {
        this("MISSIONS_STATUS", null);
    }

    /**
     * Create an aliased <code>YILDIZDATABASE.MISSIONS_STATUS</code> table reference
     */
    public MissionsStatus(String alias) {
        this(alias, MISSIONS_STATUS);
    }

    private MissionsStatus(String alias, Table<MissionsStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private MissionsStatus(String alias, Table<MissionsStatusRecord> aliased, Field<?>[] parameters) {
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
    public Identity<MissionsStatusRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_MISSIONS_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MissionsStatusRecord> getPrimaryKey() {
        return Keys.KEY_MISSIONS_STATUS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MissionsStatusRecord>> getKeys() {
        return Arrays.<UniqueKey<MissionsStatusRecord>>asList(Keys.KEY_MISSIONS_STATUS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MissionsStatus as(String alias) {
        return new MissionsStatus(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MissionsStatus rename(String name) {
        return new MissionsStatus(name, null);
    }
}
