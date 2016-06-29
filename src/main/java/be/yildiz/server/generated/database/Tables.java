/**
 * This class is generated by jOOQ
 */
package be.yildiz.server.generated.database;


import be.yildiz.server.generated.database.tables.*;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in yildizdatabase
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.7.3"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

    /**
     * The table yildizdatabase.account
     */
    public static final Account ACCOUNT = be.yildiz.server.generated.database.tables.Account.ACCOUNT;

    /**
     * The table yildizdatabase.buildings
     */
    public static final Buildings BUILDINGS = be.yildiz.server.generated.database.tables.Buildings.BUILDINGS;

    /**
     * The table yildizdatabase.cities
     */
    public static final Cities CITIES = be.yildiz.server.generated.database.tables.Cities.CITIES;

    /**
     * The table yildizdatabase.entities
     */
    public static final Entities ENTITIES = be.yildiz.server.generated.database.tables.Entities.ENTITIES;

    /**
     * The table yildizdatabase.entity_modules
     */
    public static final EntityModules ENTITY_MODULES = be.yildiz.server.generated.database.tables.EntityModules.ENTITY_MODULES;

    /**
     * The table yildizdatabase.messages
     */
    public static final Messages MESSAGES = be.yildiz.server.generated.database.tables.Messages.MESSAGES;

    /**
     * The table yildizdatabase.researches
     */
    public static final Researches RESEARCHES = be.yildiz.server.generated.database.tables.Researches.RESEARCHES;

    /**
     * The table yildizdatabase.resources
     */
    public static final Resources RESOURCES = be.yildiz.server.generated.database.tables.Resources.RESOURCES;

    /**
     * The table yildizdatabase.task_build_building
     */
    public static final TaskBuildBuilding TASK_BUILD_BUILDING = be.yildiz.server.generated.database.tables.TaskBuildBuilding.TASK_BUILD_BUILDING;

    /**
     * The table yildizdatabase.task_build_entity
     */
    public static final TaskBuildEntity TASK_BUILD_ENTITY = be.yildiz.server.generated.database.tables.TaskBuildEntity.TASK_BUILD_ENTITY;

    /**
     * The table yildizdatabase.temp_account
     */
    public static final TempAccount TEMP_ACCOUNT = be.yildiz.server.generated.database.tables.TempAccount.TEMP_ACCOUNT;

    /**
     * The table yildizdatabase.web_lost_password
     */
    public static final WebLostPassword WEB_LOST_PASSWORD = be.yildiz.server.generated.database.tables.WebLostPassword.WEB_LOST_PASSWORD;

    /**
     * The table yildizdatabase.web_news
     */
    public static final WebNews WEB_NEWS = be.yildiz.server.generated.database.tables.WebNews.WEB_NEWS;
}
