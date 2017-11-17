/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database;


import be.yildiz.server.generated.database.tables.Entities;
import be.yildiz.server.generated.database.tables.Players;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
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
public class Yildizdatabase extends SchemaImpl {

    private static final long serialVersionUID = -1712288492;

    /**
     * The reference instance of <code>YILDIZDATABASE</code>
     */
    public static final Yildizdatabase YILDIZDATABASE = new Yildizdatabase();

    /**
     * The table <code>YILDIZDATABASE.ENTITIES</code>.
     */
    public final Entities ENTITIES = be.yildiz.server.generated.database.tables.Entities.ENTITIES;

    /**
     * The table <code>YILDIZDATABASE.PLAYERS</code>.
     */
    public final Players PLAYERS = be.yildiz.server.generated.database.tables.Players.PLAYERS;

    /**
     * No further instances allowed
     */
    private Yildizdatabase() {
        super("YILDIZDATABASE", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Entities.ENTITIES,
            Players.PLAYERS);
    }
}
