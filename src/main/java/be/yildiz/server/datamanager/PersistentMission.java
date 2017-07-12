package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.server.generated.database.tables.MissionsStatus;
import be.yildiz.shared.mission.MissionId;
import be.yildiz.shared.mission.MissionManager;
import be.yildiz.shared.mission.MissionStatus;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class PersistentMission implements PersistentData<PersistentMission.PlayerMissionStatus, PersistentMission.PlayerMissionStatus>{

    private static final MissionsStatus TABLE = MissionsStatus.MISSIONS_STATUS;

    private List<PlayerMissionStatus> missions = Lists.newList();

    public PersistentMission(Connection c, MissionManager manager) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {
            Optional.ofNullable(dsl.selectFrom(TABLE))
                    .ifPresent(data -> data.forEach(
                                    value -> missions.add(new PlayerMissionStatus(
                                            MissionId.valueOf(value.getMisId().intValue()),
                                            PlayerId.valueOf(value.getPlyId().intValue()),
                                            MissionStatus.valueOf(value.getStatus().intValue())
                                            ))));
            this.missions.stream()
                    .filter(mission -> mission.status == MissionStatus.STARTED)
                    .forEach(mission -> manager.startMission(mission.id, mission.player));

            this.missions.stream()
                    .filter(mission -> mission.status == MissionStatus.WAITING_FOR_ACCEPTANCE)
                    .forEach(mission -> manager.prepareMission(mission.id, mission.player));
        }
    }

    private DSLContext getDSL(Connection c) {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);
        return DSL.using(c, settings);
    }

    @Override
    public PlayerMissionStatus save(PlayerMissionStatus data, Connection c) {
        try(DSLContext dsl = this.getDSL(c)) {
            dsl.insertInto(TABLE)
                    .set(TABLE.MIS_ID, UInteger.valueOf(data.id.value))
                    .set(TABLE.PLY_ID, UShort.valueOf(data.player.value))
                    .set(TABLE.STATUS, UByte.valueOf(data.status.value))
                    .newRecord();
        }
        return data;
    }

    @Override
    public void update(PlayerMissionStatus data, Connection c) {

    }

    public static class PlayerMissionStatus {

        private final MissionId id;

        private final PlayerId player;

        private final MissionStatus status;

        private PlayerMissionStatus(MissionId id, PlayerId player, MissionStatus status) {
            this.id = id;
            this.player = player;
            this.status = status;
        }
    }
}
