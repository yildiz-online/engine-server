package be.yildiz.server.datamanager;

import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.database.NoPoolConnectionProvider;
import liquibase.exception.LiquibaseException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class DatabasePersistentManagerTest {

    public static class CreateDataForNewAccount {

        @Test
        public void happyFlow() throws SQLException, ClassNotFoundException, LiquibaseException {
            DataBaseConnectionProvider dbcp = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDbProperties());
            new TestingDatabaseInit().init(dbcp);

            PersistentManager pm = new DatabasePersistentManager(dbcp);

            pm.createDataForNewAccount("myLogin", "myPassword", "myEmail", PlayerId.get(5));
        }
    }
}
