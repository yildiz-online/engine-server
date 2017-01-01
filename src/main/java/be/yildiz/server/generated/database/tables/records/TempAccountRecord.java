/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.TempAccount;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import java.sql.Timestamp;


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
public class TempAccountRecord extends UpdatableRecordImpl<TempAccountRecord> implements Record6<String, String, String, String, Timestamp, Byte> {

    private static final long serialVersionUID = 1498349461;

    /**
     * Setter for <code>yildizdatabase.temp_account.login</code>.
     */
    public void setLogin(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.login</code>.
     */
    public String getLogin() {
        return (String) get(0);
    }

    /**
     * Setter for <code>yildizdatabase.temp_account.password</code>.
     */
    public void setPassword(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.password</code>.
     */
    public String getPassword() {
        return (String) get(1);
    }

    /**
     * Setter for <code>yildizdatabase.temp_account.email</code>.
     */
    public void setEmail(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.email</code>.
     */
    public String getEmail() {
        return (String) get(2);
    }

    /**
     * Setter for <code>yildizdatabase.temp_account.check_value</code>.
     */
    public void setCheckValue(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.check_value</code>.
     */
    public String getCheckValue() {
        return (String) get(3);
    }

    /**
     * Setter for <code>yildizdatabase.temp_account.date</code>.
     */
    public void setDate(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.date</code>.
     */
    public Timestamp getDate() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>yildizdatabase.temp_account.complete</code>.
     */
    public void setComplete(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>yildizdatabase.temp_account.complete</code>.
     */
    public Byte getComplete() {
        return (Byte) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Timestamp, Byte> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Timestamp, Byte> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TempAccount.TEMP_ACCOUNT.LOGIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TempAccount.TEMP_ACCOUNT.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TempAccount.TEMP_ACCOUNT.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TempAccount.TEMP_ACCOUNT.CHECK_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return TempAccount.TEMP_ACCOUNT.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field6() {
        return TempAccount.TEMP_ACCOUNT.COMPLETE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getCheckValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value6() {
        return getComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value1(String value) {
        setLogin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value2(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value3(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value4(String value) {
        setCheckValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value5(Timestamp value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord value6(Byte value) {
        setComplete(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TempAccountRecord values(String value1, String value2, String value3, String value4, Timestamp value5, Byte value6) {
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
     * Create a detached TempAccountRecord
     */
    public TempAccountRecord() {
        super(TempAccount.TEMP_ACCOUNT);
    }

    /**
     * Create a detached, initialised TempAccountRecord
     */
    public TempAccountRecord(String login, String password, String email, String checkValue, Timestamp date, Byte complete) {
        super(TempAccount.TEMP_ACCOUNT);

        set(0, login);
        set(1, password);
        set(2, email);
        set(3, checkValue);
        set(4, date);
        set(5, complete);
    }
}
