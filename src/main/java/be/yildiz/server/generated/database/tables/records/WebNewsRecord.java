/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database.tables.records;


import be.yildiz.server.generated.database.tables.WebNews;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class WebNewsRecord extends UpdatableRecordImpl<WebNewsRecord> implements Record8<Integer, String, String, String, Timestamp, String, String, String> {

    private static final long serialVersionUID = 698931342;

    /**
     * Create a detached WebNewsRecord
     */
    public WebNewsRecord() {
        super(WebNews.WEB_NEWS);
    }

    /**
     * Create a detached, initialised WebNewsRecord
     */
    public WebNewsRecord(Integer id, String titleEn, String titleFr, String author, Timestamp date, String image, String contentEn, String contentFr) {
        super(WebNews.WEB_NEWS);

        setValue(0, id);
        setValue(1, titleEn);
        setValue(2, titleFr);
        setValue(3, author);
        setValue(4, date);
        setValue(5, image);
        setValue(6, contentEn);
        setValue(7, contentFr);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.id</code>.
     */
    public Integer getId() {
        return (Integer) getValue(0);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.id</code>.
     */
    public void setId(Integer value) {
        setValue(0, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.title_en</code>.
     */
    public String getTitleEn() {
        return (String) getValue(1);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.title_en</code>.
     */
    public void setTitleEn(String value) {
        setValue(1, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.title_fr</code>.
     */
    public String getTitleFr() {
        return (String) getValue(2);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.title_fr</code>.
     */
    public void setTitleFr(String value) {
        setValue(2, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.author</code>.
     */
    public String getAuthor() {
        return (String) getValue(3);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.author</code>.
     */
    public void setAuthor(String value) {
        setValue(3, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.date</code>.
     */
    public Timestamp getDate() {
        return (Timestamp) getValue(4);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.date</code>.
     */
    public void setDate(Timestamp value) {
        setValue(4, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.image</code>.
     */
    public String getImage() {
        return (String) getValue(5);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.image</code>.
     */
    public void setImage(String value) {
        setValue(5, value);
    }

    /**
     * Getter for <code>yildizdatabase.web_news.content_en</code>.
     */
    public String getContentEn() {
        return (String) getValue(6);
    }

    /**
     * Setter for <code>yildizdatabase.web_news.content_en</code>.
     */
    public void setContentEn(String value) {
        setValue(6, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>yildizdatabase.web_news.content_fr</code>.
     */
    public String getContentFr() {
        return (String) getValue(7);
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>yildizdatabase.web_news.content_fr</code>.
     */
    public void setContentFr(String value) {
        setValue(7, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, String, Timestamp, String, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, String, Timestamp, String, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return WebNews.WEB_NEWS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return WebNews.WEB_NEWS.TITLE_EN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return WebNews.WEB_NEWS.TITLE_FR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return WebNews.WEB_NEWS.AUTHOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return WebNews.WEB_NEWS.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return WebNews.WEB_NEWS.IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return WebNews.WEB_NEWS.CONTENT_EN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return WebNews.WEB_NEWS.CONTENT_FR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTitleEn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTitleFr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getAuthor();
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
    public String value6() {
        return getImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getContentEn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getContentFr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value2(String value) {
        setTitleEn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value3(String value) {
        setTitleFr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value4(String value) {
        setAuthor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value5(Timestamp value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value6(String value) {
        setImage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value7(String value) {
        setContentEn(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord value8(String value) {
        setContentFr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebNewsRecord values(Integer value1, String value2, String value3, String value4, Timestamp value5, String value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }
}
