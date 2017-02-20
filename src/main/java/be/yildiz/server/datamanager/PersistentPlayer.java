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
import be.yildiz.common.collections.Sets;
import be.yildiz.common.id.PlayerId;
import be.yildiz.server.generated.database.tables.Accounts;
import be.yildiz.server.generated.database.tables.TempAccounts;
import be.yildiz.server.generated.database.tables.records.AccountsRecord;
import be.yildiz.server.generated.database.tables.records.TempAccountsRecord;
import be.yildiz.shared.player.Player;
import be.yildiz.shared.player.PlayerManager;
import be.yildiz.shared.player.PlayerRight;
import be.yildiz.shared.player.PlayerToCreate;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Persistent data for player.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentPlayer implements PersistentData<PlayerToCreate, Player> {

    /**
     * Persistent unit name where data must be retrieved.
     */
    private static final Accounts table = Accounts.ACCOUNTS;

    /**
     * List of unused player's id.
     */
    private final Set<PlayerId> freeId;

    /**
     * Manager for players.
     */
    private final PlayerManager playerManager;

    private final TempAccountsMapper tempAccountsMapper = new TempAccountsMapper();

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param c SQL connection.
     * @param playerManager Player manager.
     */
    public PersistentPlayer(final Connection c, final PlayerManager playerManager) {
        super();
        this.freeId = Sets.newSet();
        this.playerManager = playerManager;
        try (DSLContext create = this.getDSL(c)) {
            Optional.ofNullable(create.selectFrom(table).fetch())
                    .ifPresent(data -> data.forEach(r -> {
                        PlayerId id = PlayerId.get(r.getId().intValue());
                        if (r.getActive()) {
                            int right = r.getType().intValue();
                            String name = r.getLogin();
                            playerManager.createPlayer(id, name, PlayerRight.values()[right]);
                        } else {
                            this.freeId.add(id);
                        }
                    }));
        }
    }

    /**
     * Add a player to the list but will not persist it, persistence is done through external web app. This method is intended to be called AFTER player is persisted to keep data cohesion.
     *
     * @param data player to add.
     */
    @Override
    public Player save(final PlayerToCreate data, Connection c) {
        PlayerId playerId = this.getFreeId(c);
        try(DSLContext context = this.getDSL(c)) {
            context.update(table)
                    .set(table.LOGIN, data.getLogin())
                    .set(table.PASSWORD, data.getPassword())
                    .set(table.EMAIL, data.getEmail())
                    .set(table.ACTIVE, true)
                    .set(table.TYPE, UByte.valueOf(0))
                    .set(table.ONLINE, false)
                    .set(table.MAP_ID, UByte.valueOf(1))
                    .where(table.ID.equal(UShort.valueOf(playerId.value)))
                    .execute();
            return this.playerManager.createPlayer(playerId, data.getLogin());
        }
    }

    /**
     * @return A free Id for a newly created player.
     */
    private PlayerId getFreeId(Connection c) {
        if (this.freeId.isEmpty()) {
            return this.createNewLine(c);
        }
        PlayerId id = this.freeId.iterator().next();
        this.freeId.remove(id);
        return id;
    }

    private PlayerId createNewLine(Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            create.insertInto(table).defaultValues().execute();
            AccountsRecord entity = create.fetchOne(table, table.ACTIVE.equal(false));
            return PlayerId.get(entity.getId().intValue());
        }
    }



    @Override
    public void update(Player data, Connection c) {
        // TODO Auto-generated method stub

    }

    public void deleteTempAccount(String login, Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            create.delete(TempAccounts.TEMP_ACCOUNTS)
                    .where(TempAccounts.TEMP_ACCOUNTS.LOGIN.equal(login))
                    .execute();
        }
    }

    public List<WaitingPlayer> getTempAccount(Connection c) {
        try (DSLContext create = this.getDSL(c)) {
            return Lists.newList(create.selectFrom(TempAccounts.TEMP_ACCOUNTS).fetch(this.tempAccountsMapper));
        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }

    private class TempAccountsMapper implements RecordMapper<TempAccountsRecord, WaitingPlayer> {

        @Override
        public WaitingPlayer map(TempAccountsRecord r) {
            return new WaitingPlayer(r.getLogin(), r.getPassword(), r.getEmail());
        }

    }
}
