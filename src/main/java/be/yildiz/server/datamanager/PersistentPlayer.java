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

import be.yildiz.common.collections.Sets;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.log.Logger;
import be.yildiz.server.generated.database.tables.Accounts;
import be.yildiz.server.generated.database.tables.records.AccountsRecord;
import be.yildiz.shared.player.Player;
import be.yildiz.shared.player.PlayerManager;
import be.yildiz.shared.player.PlayerRight;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.Set;

/**
 * Persistent data for player.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentPlayer implements PersistentData<Player> {

    /**
     * Persistent unit name where data must be retrieved.
     */
    private static final Accounts table = Accounts.ACCOUNTS;

    /**
     * List of unused player's id.
     */
    private final Set<PlayerId> freeId;

    /**
     * Persistent manager.
     */
    private final PersistentManager manager;

    /**
     * Manager for players.
     */
    private final PlayerManager playerManager;

    /**
     * Full constructor, retrieve data from persistent context.
     *
     * @param manager       Manager used with the persistent context.
     * @param playerManager Player manager.
     */
    public PersistentPlayer(final PersistentManager manager, final PlayerManager playerManager) {
        super();
        this.freeId = Sets.newSet();
        this.playerManager = playerManager;
        this.manager = manager;
        Result<AccountsRecord> data = manager.getAll(table);
        for (AccountsRecord r : data) {
            PlayerId id = PlayerId.get(r.getId().intValue());
            if (r.getActive()) {
                int right = r.getType().intValue();
                String name = r.getLogin();
                playerManager.createPlayer(id, name, PlayerRight.values()[right]);
            } else {
                this.freeId.add(id);
            }
        }
    }

    /**
     * Add a player to the list but will not persist it, persistence is done through external web app. This method is intended to be called AFTER player is persisted to keep data cohesion.
     *
     * @param data player to add.
     */
    @Override
    public void save(final Player data, Connection c) {
        // FIXME imlements
    }

    /**
     * @return A free Id for a newly created player.
     */
    PlayerId getFreeId() {
        if (this.freeId.isEmpty()) {
            throw new IndexOutOfBoundsException("FreeId list is empty");
        }
        PlayerId id = this.freeId.iterator().next();
        this.freeId.remove(id);
        return id;
    }

    /**
     * Create a new player.
     *
     * @param login Player's login.
     * @param pass  Player's password.
     * @param email Player's email.
     * @return The player if successfully created, null otherwise.
     */
    public Player createPlayer(final String login, final String pass, final String email, Connection c) {
        PlayerId playerId = this.getFreeId();
        try(DSLContext context = DSL.using(c)) {
            AccountsRecord playerToCreate = context.fetchOne(table, table.ID.equal(UShort.valueOf(playerId.value)));
            if (playerToCreate == null) {
                playerToCreate = context.newRecord(table);
                playerToCreate.setId(UShort.valueOf(playerId.value));
            }
            playerToCreate.setLogin(login);
            playerToCreate.setPassword(pass);
            playerToCreate.setEmail(email);
            playerToCreate.setActive(true);
            playerToCreate.setType(UByte.valueOf(0));
            playerToCreate.setMapId(UByte.valueOf(1));
            playerToCreate.setOnline(false);
            playerToCreate.store();
            return this.playerManager.createPlayer(playerId, login);
        } catch (Exception e) {
            Logger.error(e);
            return null;
        }
    }

    @Override
    public void update(Player data, Connection c) {
        // TODO Auto-generated method stub

    }
}
