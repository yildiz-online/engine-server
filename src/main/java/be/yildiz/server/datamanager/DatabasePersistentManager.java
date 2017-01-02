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
import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.log.Logger;
import be.yildiz.common.vector.Point3D;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.module.network.protocol.MessageWrapper;
import be.yildiz.module.network.server.Session;
import be.yildiz.module.network.server.SessionListener;
import be.yildiz.server.generated.database.tables.*;
import be.yildiz.server.generated.database.tables.records.*;
import be.yildiz.shared.building.BaseBuilding;
import be.yildiz.shared.construction.building.WaitingBuilding;
import be.yildiz.shared.construction.entity.WaitingEntity;
import be.yildiz.shared.entity.action.Action;
import be.yildiz.shared.player.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
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
    public void open() {
    }

    @Override
    public void close() {
    }

//    @Override
//    public void addModuleConfiguration(ModuleConfiguration config) {
//        try (Connection c = this.provider.getConnection();) {
//            DSLContext create = DSL.using(c, this.provider.getDialect());
//            PlayerModulesConfigurations table = PlayerModulesConfigurations.PLAYER_MODULES_CONFIGURATIONS;
//            PlayerModulesConfigurationsRecord record = create.newRecord(table);
//            record.setPlayerId(UShort.valueOf(config.getPlayer().value));
//            record.setEntityType(UByte.valueOf(config.getType().type));
//            record.setConfigurationName(config.getName());
//            record.setMove(UShort.valueOf(config.getModules().getMove().value));
//            record.setInteraction(UShort.valueOf(config.getModules().getInteraction().value));
//            record.setProtect(UShort.valueOf(config.getModules().getHull().value));
//            record.setOther_1(UShort.valueOf(config.getModules().getModules().getOr(0, ActionId.get(0)).value));
//            record.setOther_2(UShort.valueOf(config.getModules().getModules().getOr(1, ActionId.get(0)).value));
//            record.setOther_3(UShort.valueOf(config.getModules().getModules().getOr(2, ActionId.get(0)).value));
//            record.store();
//        } catch (SQLException e) {
//            Logger.error(e);
//        }
//    }

    @Override
    public boolean createDataForNewAccount(final String login, final String hashedPass, final String email, final PlayerId player) {
        Connection c = null;
        DSLContext context = null;
        try {
            c = this.provider.getConnection();
            context = DSL.using(c, this.provider.getDialect());
            Account table = Account.ACCOUNT;
            Researches researchTable = Researches.RESEARCHES;
            TempAccount tempAccountTable = TempAccount.TEMP_ACCOUNT;
            Entities entitiesTable = Entities.ENTITIES;
            c.setAutoCommit(false);

            AccountRecord playerToCreate = context.fetchOne(table, table.ID.equal(UShort.valueOf(player.value)));
            playerToCreate.setUsername(login);
            playerToCreate.setPassword(hashedPass);
            playerToCreate.setEmail(email);
            playerToCreate.setActive(true);
            playerToCreate.setType(UByte.valueOf(0));
            playerToCreate.setMap(UByte.valueOf(1));
            playerToCreate.setOnline(false);
            playerToCreate.store();

            ResearchesRecord recordToCreate = context.newRecord(researchTable);
            recordToCreate.setPlayerId(UShort.valueOf(player.value));
            recordToCreate.setResearchesName("");
            recordToCreate.store();

            EntitiesRecord entityToCreate = context.newRecord(entitiesTable);

            context.delete(tempAccountTable).where(tempAccountTable.LOGIN.equal(login)).execute();
            c.commit();
            return true;
        } catch (SQLException e) {
            Logger.error("Create player query", e);
            try {
                if(c!=null) {
                    Logger.warning("Player creation for " + login + " failed, rolling back.");
                    c.rollback();
                    Logger.warning("Player creation for " + login + " roll back successful.");
                }

            } catch (SQLException e1) {
                Logger.error(e1);
            }
            return false;
        } finally {
            try {
                if(context != null) {
                    context.close();
                }
                if (c != null) {
                    c.setAutoCommit(true);
                    c.close();
                }
            } catch (SQLException e) {
                Logger.error(e);
            }
        }
    }

    @Override
    public List<WaitingPlayer> getPlayerWaiting() {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            return Lists.newList(create.selectFrom(TempAccount.TEMP_ACCOUNT).fetch(new TempAccountMapper()));
        } catch (SQLException e) {
            Logger.error(e);
        }
        return Collections.emptyList();
    }

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
            Account table = Account.ACCOUNT;
            AccountRecord account = create.fetchOne(table, table.ID.equal(UShort.valueOf(id.value)));
            account.setOnline(true);
            account.store();
        } catch (SQLException e) {
            Logger.error("Set connected query", e);
        }
    }

    @Override
    public void saveBuildingTask(final List<WaitingBuilding<BaseBuilding>> buildingList) {
        final long now = System.currentTimeMillis();
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            TaskBuildBuilding table = TaskBuildBuilding.TASK_BUILD_BUILDING;
            create.delete(table).execute();
            for (WaitingBuilding<BaseBuilding> wb : buildingList) {
                TaskBuildBuildingRecord record = create.newRecord(table);
                record.setId(UInteger.valueOf(wb.getB().getCity().value));
                record.setPosition(UByte.valueOf(wb.getB().getBuildingPosition().value));
                record.setType(UByte.valueOf(wb.getB().getType().type));
                record.setTimeLeft(UInteger.valueOf(wb.getTime() - now));
                record.setStaff(UShort.valueOf(wb.getB().getStaff()));
                record.setLevel(UByte.valueOf(wb.getB().getLevel().value));
                record.store();
            }
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public void saveEntityTask(final List<WaitingEntity> entityList) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            TaskBuildEntity table = TaskBuildEntity.TASK_BUILD_ENTITY;
            create.delete(table).execute();
            for (WaitingEntity we : entityList) {
                create.insertInto(table, table.ID, table.OWNER_ID, table.POSITION, table.TYPE, table.TIME_LEFT)
                        .values(UInteger.valueOf(we.entity.getId().value), UShort.valueOf(we.entity.getOwner().value), we.entity.getPosition().toString(), UByte.valueOf(we.entity.getType().type),
                                UInteger.valueOf(we.representation.getTime())).execute();
            }
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public List<TaskBuilding> retrieveBuildingTask() {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            TaskBuildBuilding table = TaskBuildBuilding.TASK_BUILD_BUILDING;
            return Lists.newList(create.selectFrom(table).fetch(new TaskBuildBuildingMapper()));
        } catch (SQLException e) {
            Logger.error("Get entity creation task", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TaskEntity> retrieveEntityTask() {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            TaskBuildEntity table = TaskBuildEntity.TASK_BUILD_ENTITY;
            return Lists.newList(create.selectFrom(table).fetch(new TaskBuildEntityMapper()));
        } catch (SQLException e) {
            Logger.error("Get entity creation task", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Message> retrieveMessage(final PlayerId player) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            Messages table = Messages.MESSAGES;
            return Lists.newList(create.selectFrom(table).where(table.RECEIVER.equal(UShort.valueOf(player.value))).fetch(new MessageMapper()));
        } catch (SQLException e) {
            Logger.error("Get message list", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void persistMessage(final Message message) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            Messages table = Messages.MESSAGES;
            create.insertInto(table, table.SENDER, table.RECEIVER, table.MESSAGE, table.IS_READ, table.DATE)
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
    public <T extends Record> Result<T> getAll(Table<T> t) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            return create.selectFrom(t).fetch();
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
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

    private class TempAccountMapper implements RecordMapper<TempAccountRecord, WaitingPlayer> {

        @Override
        public WaitingPlayer map(TempAccountRecord r) {
            return new WaitingPlayer(r.getLogin(), r.getPassword(), r.getEmail());
        }

    }

    private class TaskBuildBuildingMapper implements RecordMapper<TaskBuildBuildingRecord, TaskBuilding> {

        @Override
        public TaskBuilding map(TaskBuildBuildingRecord r) {
            return new TaskBuilding(EntityId.get(r.getId().longValue()), r.getPosition().intValue(), r.getType().intValue(), r.getLevel().intValue(), r.getStaff().intValue(), r.getTimeLeft()
                    .longValue());
        }

    }

    private class TaskBuildEntityMapper implements RecordMapper<TaskBuildEntityRecord, TaskEntity> {

        @Override
        public TaskEntity map(TaskBuildEntityRecord r) {
            return new TaskEntity(EntityId.get(r.getId().longValue()), PlayerId.get(r.getOwnerId().intValue()), new Point3D(r.getPosition()), r.getType().intValue(), r.getTimeLeft().longValue());
        }

    }

    private class MessageMapper implements RecordMapper<MessagesRecord, Message> {

        @Override
        public Message map(MessagesRecord r) {
            return new Message(PlayerId.get(r.getSender().intValue()), PlayerId.get(r.getReceiver().intValue()), r.getMessage(), new Date(r.getDate().getTime()), r.getIsRead());
        }

    }
}
