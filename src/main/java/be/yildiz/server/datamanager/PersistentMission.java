package be.yildiz.server.datamanager;

import be.yildiz.server.generated.database.tables.Missions;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class PersistentMission {

    private static final Missions table = Missions.MISSIONS;

    public PersistentMission(Connection c) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {

        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }
}
