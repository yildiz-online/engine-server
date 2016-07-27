/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Messages;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UShort;

import javax.annotation.Generated;
import java.sql.Timestamp;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MessagesRecord extends UpdatableRecordImpl<MessagesRecord> implements Record6<UShort, UShort, UShort, String, Boolean, Timestamp> {

	private static final long serialVersionUID = -14233777;

	/**
	 * Setter for <code>yildizdatabase.messages.id</code>.
	 */
	public void setId(UShort value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.id</code>.
	 */
	public UShort getId() {
		return (UShort) getValue(0);
	}

	/**
	 * Setter for <code>yildizdatabase.messages.sender</code>.
	 */
	public void setSender(UShort value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.sender</code>.
	 */
	public UShort getSender() {
		return (UShort) getValue(1);
	}

	/**
	 * Setter for <code>yildizdatabase.messages.receiver</code>.
	 */
	public void setReceiver(UShort value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.receiver</code>.
	 */
	public UShort getReceiver() {
		return (UShort) getValue(2);
	}

	/**
	 * Setter for <code>yildizdatabase.messages.message</code>.
	 */
	public void setMessage(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.message</code>.
	 */
	public String getMessage() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>yildizdatabase.messages.is_read</code>.
	 */
	public void setIsRead(Boolean value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.is_read</code>.
	 */
	public Boolean getIsRead() {
		return (Boolean) getValue(4);
	}

	/**
	 * Setter for <code>yildizdatabase.messages.date</code>.
	 */
	public void setDate(Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>yildizdatabase.messages.date</code>.
	 */
	public Timestamp getDate() {
		return (Timestamp) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<UShort> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<UShort, UShort, UShort, String, Boolean, Timestamp> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<UShort, UShort, UShort, String, Boolean, Timestamp> valuesRow() {
		return (Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UShort> field1() {
		return Messages.MESSAGES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UShort> field2() {
		return Messages.MESSAGES.SENDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UShort> field3() {
		return Messages.MESSAGES.RECEIVER;
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
		return Messages.MESSAGES.IS_READ;
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
	public UShort value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UShort value2() {
		return getSender();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UShort value3() {
		return getReceiver();
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
		return getIsRead();
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
	public MessagesRecord value1(UShort value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessagesRecord value2(UShort value) {
		setSender(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessagesRecord value3(UShort value) {
		setReceiver(value);
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
		setIsRead(value);
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
	public MessagesRecord values(UShort value1, UShort value2, UShort value3, String value4, Boolean value5, Timestamp value6) {
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
	public MessagesRecord(UShort id, UShort sender, UShort receiver, String message, Boolean isRead, Timestamp date) {
		super(Messages.MESSAGES);

		setValue(0, id);
		setValue(1, sender);
		setValue(2, receiver);
		setValue(3, message);
		setValue(4, isRead);
		setValue(5, date);
	}
}
