//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.server.datamanager;

import be.yildiz.common.id.PlayerId;
import be.yildiz.common.log.Logger;
import be.yildiz.common.util.Pair;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.server.generated.database.tables.Researches;
import be.yildiz.server.generated.database.tables.records.ResearchesRecord;
import be.yildiz.shared.player.Player;
import be.yildiz.shared.player.PlayerManager;
import be.yildiz.shared.research.Research;
import be.yildiz.shared.research.ResearchManager;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * Persistent data for researches.
 *
 * @author Grégory Van den Borre
 */
public final class PersistentResearch implements PersistentData<Pair<PlayerId, Set<Research>>> {

    /**
     * Database table containing the data.
     */
    private static final Researches table = Researches.RESEARCHES;

    /**
     * Manager to retrieve or persist researches in persistent context.
     */
    private final DataBaseConnectionProvider provider;

    /**
     * Full constructor.
     *
     * @param manager       Manager used to retrieve or persist the data.
     * @param playerManager Player manager.
     */
    public PersistentResearch(final PersistentManager manager, final PlayerManager playerManager, final ResearchManager researchManager) {
        super();
        this.provider = manager.getProvider();
        Result<ResearchesRecord> data = manager.getAll(table);
        for (ResearchesRecord r : data) {
            Player player = playerManager.findFromId(PlayerId.get(r.getPlayerId().intValue()));
            String[] researches = r.getResearchesName().split(",");
            if (!"".equals(researches[0])) {
                for (String s : researches) {
                    researchManager.addResearch(Research.get(s), player);
                }
            }
        }
    }

    @Override
    public void save(final Pair<PlayerId, Set<Research>> data) {
        try (Connection c = this.provider.getConnection(); DSLContext create = DSL.using(c, this.provider.getDialect())) {
            ResearchesRecord research = create.fetchOne(table, table.PLAYER_ID.equal(UShort.valueOf(data.getObject1().value)));
            research.setResearchesName(StringUtil.toString(data.getObject2()));
            research.store();
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
    public void update(Pair<PlayerId, Set<Research>> data) {
        // TODO Auto-generated method stub

    }
}
