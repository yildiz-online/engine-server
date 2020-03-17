package be.yildizgames.engine.server.internal;

import be.yildizgames.engine.server.PersistenceEngine;
import be.yildizgames.module.database.DataBaseConnectionProvider;
import be.yildizgames.module.database.DatabaseConnectionProviderFactory;
import be.yildizgames.module.database.DbProperties;
import be.yildizgames.module.database.updater.DatabaseUpdater;
import be.yildizgames.module.database.updater.LiquibaseDatabaseUpdater;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
class DatabasePersistenceEngine implements PersistenceEngine {

    private DataBaseConnectionProvider connectionProvider;

    private boolean initialized;

    @Override
    public void initialize(DbProperties config) {
        this.connectionProvider = DatabaseConnectionProviderFactory.getInstance().create(config);
        this.initialized = true;
    }

    @Override
    public void update(String configurationFile) {
        if(!initialized) {
            throw new PersistenceException("Database engine not initialized.");
        }
        DatabaseUpdater databaseUpdater = LiquibaseDatabaseUpdater.fromConfigurationPath(configurationFile);
        try {
            databaseUpdater.update(connectionProvider);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public DataBaseConnectionProvider getConnectionProvider() {
        return this.connectionProvider;
    }
}
