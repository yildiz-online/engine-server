package be.yildiz.server.datamanager;

import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.shared.entity.BaseEntity;
import be.yildiz.shared.entity.EntityCreator;
import be.yildiz.shared.entity.EntityToCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class PersistentEntityCreator implements EntityCreator<BaseEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentEntityCreator.class);

    private final PersistentEntity persistentEntity;

    private final DataBaseConnectionProvider provider;

    public PersistentEntityCreator(PersistentEntity persistentEntity, DataBaseConnectionProvider provider) {
        this.persistentEntity = persistentEntity;
        this.provider = provider;
    }

    @Override
    public BaseEntity create(EntityToCreate e) {
        try(Connection c = provider.getConnection()) {
            return this.persistentEntity.save(e, c);
        } catch (SQLException ex) {
            LOGGER.error("Sql error:", ex);
            //FIXME should'nt return null.
            return null;
        }
    }
}
