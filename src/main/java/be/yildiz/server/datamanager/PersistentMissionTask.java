/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2017 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.data.PersistentData;
import be.yildiz.server.generated.database.tables.TasksStatus;
import be.yildiz.shared.mission.MissionId;
import be.yildiz.shared.mission.MissionManager;
import be.yildiz.shared.mission.task.TaskId;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class PersistentMissionTask implements PersistentData<PersistentMissionTask.PlayerTaskStatus, PersistentMissionTask.PlayerTaskStatus> {

    private static final TasksStatus TABLE = TasksStatus.TASKS_STATUS;

    private List<PlayerTaskStatus> tasks = Lists.newList();

    public PersistentMissionTask(Connection c, MissionManager manager) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {
            Optional.ofNullable(dsl.selectFrom(TABLE))
                    .ifPresent(data -> data.forEach(
                            value -> tasks.add(new PlayerTaskStatus(
                                            TaskId.valueOf(value.getTskId().intValue()),
                                            PlayerId.valueOf(value.getPlyId().intValue()),
                                            MissionId.valueOf(value.getMisId().intValue()),
                                            value.getStatus()
                                            ))));
            this.tasks
                    .forEach(task -> manager.updateTaskStatus(task.id, task.mission, task.player, task.status));

        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }

    @Override
    public PlayerTaskStatus save(PlayerTaskStatus data, Connection c) {
        try(DSLContext dsl = this.getDSL(c)) {
            dsl.insertInto(TABLE)
                    .set(TABLE.TSK_ID, UInteger.valueOf(data.id.value))
                    .set(TABLE.PLY_ID, UShort.valueOf(data.player.value))
                    .set(TABLE.MIS_ID, UInteger.valueOf(data.mission.value))
                    .set(TABLE.STATUS, data.status)
                    .execute();
        }
        return data;
    }

    @Override
    public void update(PlayerTaskStatus data, Connection c) {

    }

    public static class PlayerTaskStatus {

        private final TaskId id;

        private final PlayerId player;

        private final MissionId mission;

        private final String status;


        public PlayerTaskStatus(TaskId id, PlayerId player, MissionId mission, String status) {
            super();
            this.id = id;
            this.player = player;
            this.mission = mission;
            this.status = status;
        }
    }
}
