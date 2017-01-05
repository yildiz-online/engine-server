package be.yildiz.server.datamanager;

import be.yildiz.module.database.DataBaseConnectionProvider;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class TestingDatabaseInit {

    public void init(DataBaseConnectionProvider c) throws LiquibaseException, SQLException {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c.getConnection()));
        Liquibase liquibase = new Liquibase("empty_db.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("test");
    }
}
