package be.yildiz.server.datamanager;

import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.database.TestingDatabaseInit;
import be.yildiz.server.generated.database.tables.Accounts;
import be.yildiz.server.generated.database.tables.Researches;
import be.yildiz.server.generated.database.tables.records.AccountsRecord;
import be.yildiz.server.generated.database.tables.records.ResearchesRecord;
import org.jooq.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class DatabasePersistentManagerTest {

    public static class CreateDataForNewAccount {

        @Test
        public void happyFlow() {
            try(DataBaseConnectionProvider dbcp = new TestingDatabaseInit().init("empty_db.xml")) {

                PersistentManager pm = new DatabasePersistentManager(dbcp);

                pm.createDataForNewAccount("myLogin", "myPassword", "myEmail", PlayerId.get(5));
                Result<AccountsRecord> a = pm.getAll(Accounts.ACCOUNTS);
                Assert.assertEquals(1, a.size());
                AccountsRecord result = a.get(0);
                Assert.assertEquals(5, result.getId().intValue());
                Assert.assertEquals("myLogin", result.getLogin());
                Assert.assertEquals("myPassword", result.getPassword());
                Assert.assertEquals("myEmail", result.getEmail());

                Result<ResearchesRecord> r = pm.getAll(Researches.RESEARCHES);
                Assert.assertEquals(1, r.size());
                ResearchesRecord researchResult = r.get(0);
                Assert.assertEquals(5, researchResult.getPlayerId().intValue());
                Assert.assertEquals("", researchResult.getName());
                //FIXME insert in temp account and then check if empty
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        }

        @Test
        public void researchesInsertionFail() {
            try(DataBaseConnectionProvider dbcp = new TestingDatabaseInit().init("researches-failure.xml")) {

                PersistentManager pm = new DatabasePersistentManager(dbcp);

                pm.createDataForNewAccount("myLogin", "myPassword", "myEmail", PlayerId.get(3));

                Result<AccountsRecord> a = pm.getAll(Accounts.ACCOUNTS);
                Assert.assertTrue(a.isEmpty());
                Result<ResearchesRecord> r = pm.getAll(Researches.RESEARCHES);
                Assert.assertTrue(r.isEmpty());
                //FIXME insert in temp account and then check propertly restored
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        }
    }
}
