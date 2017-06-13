/*
 * This file is generated by jOOQ.
*/
package be.yildiz.server.generated.database;


import be.yildiz.server.generated.database.tables.Accounts;
import be.yildiz.server.generated.database.tables.Buildings;
import be.yildiz.server.generated.database.tables.Cities;
import be.yildiz.server.generated.database.tables.Databasechangeloglock;
import be.yildiz.server.generated.database.tables.Entities;
import be.yildiz.server.generated.database.tables.Messages;
import be.yildiz.server.generated.database.tables.Missions;
import be.yildiz.server.generated.database.tables.Researches;
import be.yildiz.server.generated.database.tables.Resources;
import be.yildiz.server.generated.database.tables.TasksStatus;
import be.yildiz.server.generated.database.tables.TempAccounts;
import be.yildiz.server.generated.database.tables.records.AccountsRecord;
import be.yildiz.server.generated.database.tables.records.BuildingsRecord;
import be.yildiz.server.generated.database.tables.records.CitiesRecord;
import be.yildiz.server.generated.database.tables.records.DatabasechangeloglockRecord;
import be.yildiz.server.generated.database.tables.records.EntitiesRecord;
import be.yildiz.server.generated.database.tables.records.MessagesRecord;
import be.yildiz.server.generated.database.tables.records.MissionsRecord;
import be.yildiz.server.generated.database.tables.records.ResearchesRecord;
import be.yildiz.server.generated.database.tables.records.ResourcesRecord;
import be.yildiz.server.generated.database.tables.records.TasksStatusRecord;
import be.yildiz.server.generated.database.tables.records.TempAccountsRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;


/**
 * A class modelling foreign key relationships between tables of the <code>YILDIZDATABASE</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountsRecord, UShort> IDENTITY_ACCOUNTS = Identities0.IDENTITY_ACCOUNTS;
    public static final Identity<EntitiesRecord, UInteger> IDENTITY_ENTITIES = Identities0.IDENTITY_ENTITIES;
    public static final Identity<MessagesRecord, UInteger> IDENTITY_MESSAGES = Identities0.IDENTITY_MESSAGES;
    public static final Identity<MissionsRecord, UInteger> IDENTITY_MISSIONS = Identities0.IDENTITY_MISSIONS;
    public static final Identity<ResearchesRecord, UShort> IDENTITY_RESEARCHES = Identities0.IDENTITY_RESEARCHES;
    public static final Identity<TempAccountsRecord, UShort> IDENTITY_TEMP_ACCOUNTS = Identities0.IDENTITY_TEMP_ACCOUNTS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountsRecord> KEY_ACCOUNTS_PRIMARY = UniqueKeys0.KEY_ACCOUNTS_PRIMARY;
    public static final UniqueKey<BuildingsRecord> KEY_BUILDINGS_PRIMARY = UniqueKeys0.KEY_BUILDINGS_PRIMARY;
    public static final UniqueKey<CitiesRecord> KEY_CITIES_PRIMARY = UniqueKeys0.KEY_CITIES_PRIMARY;
    public static final UniqueKey<DatabasechangeloglockRecord> KEY_DATABASECHANGELOGLOCK_PRIMARY = UniqueKeys0.KEY_DATABASECHANGELOGLOCK_PRIMARY;
    public static final UniqueKey<EntitiesRecord> KEY_ENTITIES_PRIMARY = UniqueKeys0.KEY_ENTITIES_PRIMARY;
    public static final UniqueKey<MessagesRecord> KEY_MESSAGES_PRIMARY = UniqueKeys0.KEY_MESSAGES_PRIMARY;
    public static final UniqueKey<MissionsRecord> KEY_MISSIONS_PRIMARY = UniqueKeys0.KEY_MISSIONS_PRIMARY;
    public static final UniqueKey<ResearchesRecord> KEY_RESEARCHES_PRIMARY = UniqueKeys0.KEY_RESEARCHES_PRIMARY;
    public static final UniqueKey<ResourcesRecord> KEY_RESOURCES_PRIMARY = UniqueKeys0.KEY_RESOURCES_PRIMARY;
    public static final UniqueKey<TasksStatusRecord> KEY_TASKS_STATUS_PRIMARY = UniqueKeys0.KEY_TASKS_STATUS_PRIMARY;
    public static final UniqueKey<TempAccountsRecord> KEY_TEMP_ACCOUNTS_PRIMARY = UniqueKeys0.KEY_TEMP_ACCOUNTS_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<AccountsRecord, UShort> IDENTITY_ACCOUNTS = createIdentity(Accounts.ACCOUNTS, Accounts.ACCOUNTS.ID);
        public static Identity<EntitiesRecord, UInteger> IDENTITY_ENTITIES = createIdentity(Entities.ENTITIES, Entities.ENTITIES.ID);
        public static Identity<MessagesRecord, UInteger> IDENTITY_MESSAGES = createIdentity(Messages.MESSAGES, Messages.MESSAGES.MSG_ID);
        public static Identity<MissionsRecord, UInteger> IDENTITY_MISSIONS = createIdentity(Missions.MISSIONS, Missions.MISSIONS.MIS_ID);
        public static Identity<ResearchesRecord, UShort> IDENTITY_RESEARCHES = createIdentity(Researches.RESEARCHES, Researches.RESEARCHES.RES_ID);
        public static Identity<TempAccountsRecord, UShort> IDENTITY_TEMP_ACCOUNTS = createIdentity(TempAccounts.TEMP_ACCOUNTS, TempAccounts.TEMP_ACCOUNTS.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<AccountsRecord> KEY_ACCOUNTS_PRIMARY = createUniqueKey(Accounts.ACCOUNTS, "KEY_ACCOUNTS_PRIMARY", Accounts.ACCOUNTS.ID);
        public static final UniqueKey<BuildingsRecord> KEY_BUILDINGS_PRIMARY = createUniqueKey(Buildings.BUILDINGS, "KEY_BUILDINGS_PRIMARY", Buildings.BUILDINGS.CITY_ID);
        public static final UniqueKey<CitiesRecord> KEY_CITIES_PRIMARY = createUniqueKey(Cities.CITIES, "KEY_CITIES_PRIMARY", Cities.CITIES.ID);
        public static final UniqueKey<DatabasechangeloglockRecord> KEY_DATABASECHANGELOGLOCK_PRIMARY = createUniqueKey(Databasechangeloglock.DATABASECHANGELOGLOCK, "KEY_DATABASECHANGELOGLOCK_PRIMARY", Databasechangeloglock.DATABASECHANGELOGLOCK.ID);
        public static final UniqueKey<EntitiesRecord> KEY_ENTITIES_PRIMARY = createUniqueKey(Entities.ENTITIES, "KEY_ENTITIES_PRIMARY", Entities.ENTITIES.ID);
        public static final UniqueKey<MessagesRecord> KEY_MESSAGES_PRIMARY = createUniqueKey(Messages.MESSAGES, "KEY_MESSAGES_PRIMARY", Messages.MESSAGES.MSG_ID);
        public static final UniqueKey<MissionsRecord> KEY_MISSIONS_PRIMARY = createUniqueKey(Missions.MISSIONS, "KEY_MISSIONS_PRIMARY", Missions.MISSIONS.MIS_ID);
        public static final UniqueKey<ResearchesRecord> KEY_RESEARCHES_PRIMARY = createUniqueKey(Researches.RESEARCHES, "KEY_RESEARCHES_PRIMARY", Researches.RESEARCHES.RES_ID);
        public static final UniqueKey<ResourcesRecord> KEY_RESOURCES_PRIMARY = createUniqueKey(Resources.RESOURCES, "KEY_RESOURCES_PRIMARY", Resources.RESOURCES.CITY_ID);
        public static final UniqueKey<TasksStatusRecord> KEY_TASKS_STATUS_PRIMARY = createUniqueKey(TasksStatus.TASKS_STATUS, "KEY_TASKS_STATUS_PRIMARY", TasksStatus.TASKS_STATUS.TSK_ID);
        public static final UniqueKey<TempAccountsRecord> KEY_TEMP_ACCOUNTS_PRIMARY = createUniqueKey(TempAccounts.TEMP_ACCOUNTS, "KEY_TEMP_ACCOUNTS_PRIMARY", TempAccounts.TEMP_ACCOUNTS.ID);
    }
}
