package be.yildiz.server.datamanager;

import be.yildiz.common.log.Logger;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.shared.entity.BaseEntity;
import be.yildiz.shared.entity.EntityCreator;
import be.yildiz.shared.entity.EntityToCreate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class PersistentEntityCreator implements EntityCreator<BaseEntity> {

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
            Logger.error(ex);
            //FIXME shouldnt return null.
            return null;
        }
    }
}
