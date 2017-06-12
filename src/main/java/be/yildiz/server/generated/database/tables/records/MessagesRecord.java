/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Messages;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;


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
public class MessagesRecord extends UpdatableRecordImpl<MessagesRecord> implements Record6<UInteger, UShort, UShort, String, Boolean, Timestamp> {

    private static final long serialVersionUID = -1831584636;

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.MSG_ID</code>.
     */
    public void setMsgId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.MSG_ID</code>.
     */
    public UInteger getMsgId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.SENDER_ID</code>.
     */
    public void setSenderId(UShort value) {
        set(1, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.SENDER_ID</code>.
     */
    public UShort getSenderId() {
        return (UShort) get(1);
    }

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.RECEIVER_ID</code>.
     */
    public void setReceiverId(UShort value) {
        set(2, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.RECEIVER_ID</code>.
     */
    public UShort getReceiverId() {
        return (UShort) get(2);
    }

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.MESSAGE</code>.
     */
    public void setMessage(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.MESSAGE</code>.
     */
    public String getMessage() {
        return (String) get(3);
    }

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.READ</code>.
     */
    public void setRead(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.READ</code>.
     */
    public Boolean getRead() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>YILDIZDATABASE.MESSAGES.DATE</code>.
     */
    public void setDate(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>YILDIZDATABASE.MESSAGES.DATE</code>.
     */
    public Timestamp getDate() {
        return (Timestamp) get(5);
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
    public Row6<UInteger, UShort, UShort, String, Boolean, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UInteger, UShort, UShort, String, Boolean, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Messages.MESSAGES.MSG_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UShort> field2() {
        return Messages.MESSAGES.SENDER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UShort> field3() {
        return Messages.MESSAGES.RECEIVER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Messages.MESSAGES.MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field5() {
        return Messages.MESSAGES.READ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Messages.MESSAGES.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getMsgId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UShort value2() {
        return getSenderId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UShort value3() {
        return getReceiverId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value5() {
        return getRead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value1(UInteger value) {
        setMsgId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value2(UShort value) {
        setSenderId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value3(UShort value) {
        setReceiverId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value4(String value) {
        setMessage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value5(Boolean value) {
        setRead(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord value6(Timestamp value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessagesRecord values(UInteger value1, UShort value2, UShort value3, String value4, Boolean value5, Timestamp value6) {
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
     * Create a detached MessagesRecord
     */
    public MessagesRecord() {
        super(Messages.MESSAGES);
    }

    /**
     * Create a detached, initialised MessagesRecord
     */
    public MessagesRecord(UInteger msgId, UShort senderId, UShort receiverId, String message, Boolean read, Timestamp date) {
        super(Messages.MESSAGES);

        set(0, msgId);
        set(1, senderId);
        set(2, receiverId);
        set(3, message);
        set(4, read);
        set(5, date);
    }
}
