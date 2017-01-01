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
import be.yildiz.server.generated.database.tables.Account;
import be.yildiz.server.generated.database.tables.records.AccountRecord;
import be.yildiz.shared.player.Player;
import be.yildiz.shared.player.PlayerManager;
import be.yildiz.shared.player.PlayerRight;
import org.jooq.Result;

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
    private static final Account table = Account.ACCOUNT;

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
        Result<AccountRecord> data = manager.getAll(table);
        for (AccountRecord r : data) {
            PlayerId id = PlayerId.get(r.getId().intValue());
            if (r.getActive()) {
                int right = r.getType().intValue();
                String name = r.getUsername();
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
    public void save(final Player data) {
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
     * @return <code>true</code> if the player has been successfully created.
     */
    public boolean createPlayer(final String login, final String pass, final String email) {
        PlayerId playerId = this.getFreeId();
        if (this.manager.createPlayer(login, pass, email, playerId)) {
            this.playerManager.createPlayer(playerId, login);
            return true;
        }
        return false;
    }

    @Override
    public void update(Player data) {
        // TODO Auto-generated method stub

    }
}
