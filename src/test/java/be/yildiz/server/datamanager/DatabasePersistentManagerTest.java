package be.yildiz.server.datamanager;

import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.database.NoPoolConnectionProvider;
import be.yildiz.server.generated.database.tables.Entities;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class DatabasePersistentManagerTest {

    public static class CreateDataForNewAccount {

        @Test
        public void happyFlow() throws SQLException, ClassNotFoundException {

            PersistentManager pm = new DatabasePersistentManager(
                    new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDbProperties()));
            pm.getAll(Entities.ENTITIES);

        }

    }
}
