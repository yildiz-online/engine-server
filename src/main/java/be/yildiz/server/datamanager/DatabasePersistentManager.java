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

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.log.Logger;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.network.protocol.MessageWrapper;
import be.yildiz.module.network.server.Session;
import be.yildiz.module.network.server.SessionListener;
import be.yildiz.server.generated.database.tables.Accounts;
import be.yildiz.server.generated.database.tables.Messages;
import be.yildiz.server.generated.database.tables.records.AccountsRecord;
import be.yildiz.server.generated.database.tables.records.MessagesRecord;
import be.yildiz.shared.entity.action.Action;
import be.yildiz.shared.player.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Database implementation for the PersistenceManager.
 *
 * @author Grégory Van den Borre
 */
@AllArgsConstructor
public final class DatabasePersistentManager implements PersistentManager, SessionListener {

    /**
     * Provide connection to the database.
     */
    @Getter
    private final DataBaseConnectionProvider provider;

    @Override
    public void messageReceived(final Session player, final MessageWrapper message) {
    }

    @Override
    public void clientAuthenticated(final Session session) {
        this.setConnected(session.getPlayer(), true);
    }

    @Override
    public void sessionClosed(final Session session) {
        // FIXME session is null when client crash and exception occurs(this
        // method is called twice, second time with null session)
        if (session != null && session.hasPlayer()) {
            this.setConnected(session.getPlayer(), false);
        }
    }

    private void setConnected(final PlayerId id, final boolean connected) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            Accounts table = Accounts.ACCOUNTS;
            AccountsRecord account = create.fetchOne(table, table.ID.equal(UShort.valueOf(id.value)));
            account.setOnline(true);
            account.store();
        } catch (SQLException e) {
            Logger.error("Set connected query", e);
        }
    }

    @Override
    public List<Message> retrieveMessage(final PlayerId player) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            Messages table = Messages.MESSAGES;
            return Lists.newList(create.selectFrom(table).where(table.RECEIVER_ID.equal(UShort.valueOf(player.value))).fetch(new MessageMapper()));
        } catch (SQLException e) {
            Logger.error("Get message list", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void persistMessage(final Message message) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            Messages table = Messages.MESSAGES;
            create.insertInto(table, table.SENDER_ID, table.RECEIVER_ID, table.MESSAGE, table.READ, table.DATE)
                    .values(UShort.valueOf(message.getSender().value), UShort.valueOf(message.getReceiver().value), message.getMessage(), message.isRead(), new Timestamp(message.getDate().getTime()))
                    .execute();
        } catch (SQLException e) {
            Logger.error(e);
        }
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
            Logger.error(e);
        }
        return 0;
    }

    private class MessageMapper implements RecordMapper<MessagesRecord, Message> {

        @Override
        public Message map(MessagesRecord r) {
            return new Message(PlayerId.get(r.getSenderId().intValue()), PlayerId.get(r.getReceiverId().intValue()), r.getMessage(), new Date(r.getDate().getTime()), r.getRead());
        }

    }
}
