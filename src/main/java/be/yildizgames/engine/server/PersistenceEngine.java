package be.yildizgames.engine.server;

import be.yildizgames.module.database.DataBaseConnectionProvider;
import be.yildizgames.module.database.DbProperties;

/**
 * Engine to handle the persistence.
 * @author Grégory Van den Borre
 */
public interface PersistenceEngine {

    /**
     * Initialize the engine.
     * @param config Configuration for the persistence unit.
     */
    void initialize(DbProperties config);

    /**
     * Update the persistence metamodel.
     * @param configurationFile Configuration file.
     */
    void update(String configurationFile);

    /**
     * Get the connection provider.
     * @return The connection provider.
     */
    DataBaseConnectionProvider getConnectionProvider();
}
