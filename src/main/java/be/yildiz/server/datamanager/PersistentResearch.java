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

import be.yildiz.common.id.PlayerId;
import be.yildiz.common.util.Pair;
import be.yildiz.common.util.StringUtil;
import be.yildiz.server.generated.database.tables.Researches;
import be.yildiz.server.generated.database.tables.records.ResearchesRecord;
import be.yildiz.shared.player.Player;
import be.yildiz.shared.player.PlayerManager;
import be.yildiz.shared.research.Research;
import be.yildiz.shared.research.ResearchManager;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.Optional;
import java.util.Set;

/**
 * Persistent data for researches.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentResearch implements PersistentData<Pair<PlayerId, Set<Research>>, Pair<PlayerId, Set<Research>>> {

    /**
     * Database table containing the data.
     */
    private static final Researches table = Researches.RESEARCHES;

    /**
     * Full constructor.
     *
     * @param manager       Manager used to retrieve or persist the data.
     * @param playerManager Player manager.
     * @param researchManager Research manager.
     */
    public PersistentResearch(final PersistentManager manager, final PlayerManager playerManager, final ResearchManager researchManager) {
        super();
        Optional
                .ofNullable(manager.getAll(table))
                .ifPresent(data -> {
                    data.forEach(r -> {
                        Player player = playerManager.findFromId(PlayerId.get(r.getPlayerId().intValue()));
                        if(!r.getName().isEmpty()) {
                            String[] researches = r.getName().split(",");
                            for (String s : researches) {
                                researchManager.addResearch(Research.get(s), player);
                            }
                        }
                    });
                });
    }

    @Override
    public Pair<PlayerId, Set<Research>> save(final Pair<PlayerId, Set<Research>> data, Connection c) {
        try (DSLContext create = DSL.using(c)) {
            ResearchesRecord research = create.fetchOne(table, table.PLAYER_ID.equal(UShort.valueOf(data.getObject1().value)));
            research.setName(StringUtil.toString(data.getObject2()));
            research.store();
            return data;
        }
    }

    @Override
    public void update(Pair<PlayerId, Set<Research>> data, Connection c) {

    }
}
