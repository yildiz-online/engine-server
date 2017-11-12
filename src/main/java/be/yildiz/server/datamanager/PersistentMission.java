package be.yildiz.server.datamanager;

import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.data.PersistentData;
import be.yildiz.server.generated.database.tables.MissionsStatus;
import be.yildiz.shared.mission.MissionId;
import be.yildiz.shared.mission.MissionManager;
import be.yildiz.shared.mission.MissionStatus;
import be.yildiz.shared.mission.PlayerMissionStatus;
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
public class PersistentMission implements PersistentData<PlayerMissionStatus, PlayerMissionStatus> {

    private static final MissionsStatus TABLE = MissionsStatus.MISSIONS_STATUS;

    private List<PlayerMissionStatus> missions = Lists.newList();

    public PersistentMission(Connection c, MissionManager manager) {
        super();
        try (DSLContext dsl = this.getDSL(c)) {
            Optional.ofNullable(dsl.selectFrom(TABLE))
                    .ifPresent(data -> data.forEach(
                            value -> manager.initialize(
                                    new PlayerMissionStatus(
                                            MissionId.valueOf(value.getMisId().intValue()),
                                            PlayerId.valueOf(value.getPlyId().intValue()),
                                            MissionStatus.valueOf(value.getStatus().intValue())
                            ))));
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
                    .execute();
        }
        return data;
    }

    @Override
    public void update(PlayerMissionStatus data, Connection c) {

    }


}
