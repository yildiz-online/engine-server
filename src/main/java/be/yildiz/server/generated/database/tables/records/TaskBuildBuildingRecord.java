/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.TaskBuildBuilding;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TaskBuildBuildingRecord extends UpdatableRecordImpl<TaskBuildBuildingRecord> implements Record6<UInteger, UByte, UByte, UInteger, UShort, UByte> {

    private static final long serialVersionUID = 700423872;

    /**
     * Setter for <code>yildizdatabase.task_build_building.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>yildizdatabase.task_build_building.position</code>.
     */
    public void setPosition(UByte value) {
        set(1, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.position</code>.
     */
    public UByte getPosition() {
        return (UByte) get(1);
    }

    /**
     * Setter for <code>yildizdatabase.task_build_building.type</code>.
     */
    public void setType(UByte value) {
        set(2, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.type</code>.
     */
    public UByte getType() {
        return (UByte) get(2);
    }

    /**
     * Setter for <code>yildizdatabase.task_build_building.time_left</code>.
     */
    public void setTimeLeft(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.time_left</code>.
     */
    public UInteger getTimeLeft() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>yildizdatabase.task_build_building.staff</code>.
     */
    public void setStaff(UShort value) {
        set(4, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.staff</code>.
     */
    public UShort getStaff() {
        return (UShort) get(4);
    }

    /**
     * Setter for <code>yildizdatabase.task_build_building.level</code>.
     */
    public void setLevel(UByte value) {
        set(5, value);
    }

    /**
     * Getter for <code>yildizdatabase.task_build_building.level</code>.
     */
    public UByte getLevel() {
        return (UByte) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UInteger, UByte, UByte, UInteger, UShort, UByte> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UInteger, UByte, UByte, UInteger, UShort, UByte> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field2() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.POSITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field3() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.TIME_LEFT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UShort> field5() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.STAFF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field6() {
        return TaskBuildBuilding.TASK_BUILD_BUILDING.LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
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
    public UInteger value4() {
        return getTimeLeft();
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
    public UByte value6() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value2(UByte value) {
        setPosition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value3(UByte value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value4(UInteger value) {
        setTimeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value5(UShort value) {
        setStaff(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord value6(UByte value) {
        setLevel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskBuildBuildingRecord values(UInteger value1, UByte value2, UByte value3, UInteger value4, UShort value5, UByte value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TaskBuildBuildingRecord
     */
    public TaskBuildBuildingRecord() {
        super(TaskBuildBuilding.TASK_BUILD_BUILDING);
    }

    /**
     * Create a detached, initialised TaskBuildBuildingRecord
     */
    public TaskBuildBuildingRecord(UInteger id, UByte position, UByte type, UInteger timeLeft, UShort staff, UByte level) {
        super(TaskBuildBuilding.TASK_BUILD_BUILDING);

        set(0, id);
        set(1, position);
        set(2, type);
        set(3, timeLeft);
        set(4, staff);
        set(5, level);
    }
}
