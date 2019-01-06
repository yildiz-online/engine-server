package be.yildizgames.engine.server;

import be.yildizgames.module.database.DataBaseConnectionProvider;
import be.yildizgames.module.database.DbProperties;

public interface PersistenceEngine {

    void initialize(DbProperties config);

    void update(String configurationFile);

    DataBaseConnectionProvider getConnectionProvider();
}
