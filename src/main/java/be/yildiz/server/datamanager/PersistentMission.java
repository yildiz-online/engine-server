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

    private static final MissionsStatus table = MissionsStatus.MISSIONS_STATUS.MISSIONS_STATUS;

    private static final TasksStatus taskTable = TasksStatus.TASKS_STATUS;

    private List<PlayerMissionStatus> missions = Lists.newList();

    private List<PlayerTaskStatus> tasks = Lists.newList();

    public PersistentMission(Connection c, MissionManager m) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {
            Optional.ofNullable(dsl.selectFrom(table))
                    .ifPresent(data -> data.forEach(
                                    value -> missions.add(new PlayerMissionStatus(
                                            new MissionId(value.getMisId().intValue()),
                                            PlayerId.get(value.getPlyId().intValue()),
                                            MissionStatus.valueOf(value.getStatus().intValue())
                                            ))));
            Optional.ofNullable(dsl.selectFrom(taskTable))
                    .ifPresent(data -> data.forEach(
                            value -> tasks.add(new PlayerTaskStatus(
                                            new TaskId(value.getTskId().intValue()),
                                            PlayerId.get(value.getPlyId().intValue()),
                                            new MissionId(value.getMisId().intValue()),
                                            value.getStatus()
                                            ))));
            this.missions.stream()
                    .filter(status -> status.status == MissionStatus.STARTED)
                    .forEach(status -> m.startMission(status.id, status.player));

            this.missions.stream()
                    .filter(status -> status.status == MissionStatus.WAITING_FOR_ACCEPTANCE)
                    .forEach(status -> m.prepareMission(status.id, status.player));

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
