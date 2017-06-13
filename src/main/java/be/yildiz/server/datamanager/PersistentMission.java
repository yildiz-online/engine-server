package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.server.generated.database.tables.Missions;
import be.yildiz.shared.mission.MissionId;
import be.yildiz.shared.mission.MissionStatus;
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

    private static final Missions table = Missions.MISSIONS;

    private List<PlayerMissionStatus> missions = Lists.newList();

    public PersistentMission(Connection c) {
        super();
        try(DSLContext dsl = this.getDSL(c)) {
            Optional.ofNullable(dsl.selectFrom(table))
                    .ifPresent(data -> data.forEach(
                                    value -> missions.add(new PlayerMissionStatus(
                                            new MissionId(value.getMisId().intValue()),
                                            PlayerId.get(value.getPlyId().intValue()),
                                            MissionStatus.valueOf(value.getStatus().intValue())
                                            ))));
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
}
