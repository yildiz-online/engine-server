package be.yildizgames.engine.server.internal;

import be.yildizgames.engine.server.PersistenceEngine;
import be.yildizgames.module.database.DataBaseConnectionProvider;
import be.yildizgames.module.database.DatabaseConnectionProviderFactory;
import be.yildizgames.module.database.DatabaseUpdater;
import be.yildizgames.module.database.DbProperties;
import be.yildizgames.module.database.LiquibaseDatabaseUpdater;

import java.sql.SQLException;

class DatabasePersistenceEngine implements PersistenceEngine {

    private DataBaseConnectionProvider connectionProvider;

    private boolean initialized;

    @Override
    public void initialize(DbProperties config) {
        try {
            this.connectionProvider = DatabaseConnectionProviderFactory.getInstance().create(config);
            this.initialized = true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
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
