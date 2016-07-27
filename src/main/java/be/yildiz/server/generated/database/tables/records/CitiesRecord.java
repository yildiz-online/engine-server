/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.Cities;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UShort;

import javax.annotation.Generated;


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
public class CitiesRecord extends UpdatableRecordImpl<CitiesRecord> implements Record2<UShort, String> {

	private static final long serialVersionUID = 15665832;

	/**
	 * Setter for <code>yildizdatabase.cities.id</code>.
	 */
	public void setId(UShort value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>yildizdatabase.cities.id</code>.
	 */
	public UShort getId() {
		return (UShort) getValue(0);
	}

	/**
	 * Setter for <code>yildizdatabase.cities.name</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>yildizdatabase.cities.name</code>.
	 */
	public String getName() {
		return (String) getValue(1);
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
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<UShort, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<UShort, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UShort> field1() {
		return Cities.CITIES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Cities.CITIES.NAME;
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
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CitiesRecord value1(UShort value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CitiesRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CitiesRecord values(UShort value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CitiesRecord
	 */
	public CitiesRecord() {
		super(Cities.CITIES);
	}

	/**
	 * Create a detached, initialised CitiesRecord
	 */
	public CitiesRecord(UShort id, String name) {
		super(Cities.CITIES);

		setValue(0, id);
		setValue(1, name);
	}
}
