package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.server.generated.database.tables.MissionsStatus;
import be.yildiz.server.generated.database.tables.TasksStatus;
import be.yildiz.shared.mission.MissionId;
import be.yildiz.shared.mission.MissionManager;
import be.yildiz.shared.mission.MissionStatus;
import be.yildiz.shared.mission.task.TaskId;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class PersistentMission {

    private List<PlayerMissionStatus> missions = Lists.newList();

    private List<PlayerTaskStatus> tasks = Lists.newList();

    public PersistentMission(Connection c, MissionManager manager) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {
            MissionsStatus table = MissionsStatus.MISSIONS_STATUS;
            Optional.ofNullable(dsl.selectFrom(table))
                    .ifPresent(data -> data.forEach(
                                    value -> missions.add(new PlayerMissionStatus(
                                            new MissionId(value.getMisId().intValue()),
                                            PlayerId.valueOf(value.getPlyId().intValue()),
                                            MissionStatus.valueOf(value.getStatus().intValue())
                                            ))));
            TasksStatus taskTable = TasksStatus.TASKS_STATUS;
            Optional.ofNullable(dsl.selectFrom(taskTable))
                    .ifPresent(data -> data.forEach(
                            value -> tasks.add(new PlayerTaskStatus(
                                            new TaskId(value.getTskId().intValue()),
                                            PlayerId.valueOf(value.getPlyId().intValue()),
                                            new MissionId(value.getMisId().intValue()),
                                            value.getStatus()
                                            ))));
            this.missions.stream()
                    .filter(mission -> mission.status == MissionStatus.STARTED)
                    .forEach(mission -> manager.startMission(mission.id, mission.player));

            this.missions.stream()
                    .filter(mission -> mission.status == MissionStatus.WAITING_FOR_ACCEPTANCE)
                    .forEach(mission -> manager.prepareMission(mission.id, mission.player));

            this.tasks
                    .forEach(task -> manager.updateTaskStatus(task.id, task.mission, task.player, task.status));

        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }

    private class PlayerMissionStatus {

        private final MissionId id;

        private final PlayerId player;

        private final MissionStatus status;

        private PlayerMissionStatus(MissionId id, PlayerId player, MissionStatus status) {
            this.id = id;
            this.player = player;
            this.status = status;
        }
    }

    private class PlayerTaskStatus {

        private final TaskId id;

        private final PlayerId player;

        private final MissionId mission;

        private final String status;


        private PlayerTaskStatus(TaskId id, PlayerId player, MissionId mission, String status) {
            super();
            this.id = id;
            this.player = player;
            this.mission = mission;
            this.status = status;
        }
    }
}
