/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.server.datamanager;

import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.network.protocol.MessageWrapper;
import be.yildiz.module.network.server.Session;
import be.yildiz.module.network.server.SessionListener;
import be.yildiz.shared.entity.action.Action;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Database implementation for the PersistenceManager.
 *
 * @author Grégory Van den Borre
 */
public final class DatabasePersistentManager implements PersistentManager, SessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePersistentManager.class);

    /**
     * Provide connection to the database.
     */
    private final DataBaseConnectionProvider provider;

    public DatabasePersistentManager(DataBaseConnectionProvider provider) {
        super();
        this.provider = provider;
    }

    @Override
    public void messageReceived(final Session player, final MessageWrapper message) {
    }

    @Override
    public void clientAuthenticated(final Session session) {
    }

    @Override
    public void sessionClosed(final Session session) {

    }


    @Override
    public void saveActionTask(List<Action> actionList) {
        // TODO Auto-generated method stub

    }

    @Override
    public long createNewLine(Table<?> t) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            create.insertInto(t).defaultValues().execute();
        } catch (SQLException e) {
            LOGGER.error("Create row error", e);
        }
        return 0;
    }

    public DataBaseConnectionProvider getProvider() {
        return provider;
    }


}
