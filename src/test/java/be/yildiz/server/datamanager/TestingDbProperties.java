package be.yildiz.server.datamanager;

import be.yildiz.module.database.DbProperties;

/**
 * @author Grégory Van den Borre
 */
public class TestingDbProperties implements DbProperties {
    @Override
    public String getDbUser() {
        return "";
    }

    @Override
    public int getDbPort() {
        return 0;
    }

    @Override
    public String getDbPassword() {
        return "";
    }

    @Override
    public String getDbHost() {
        return "";
    }

    @Override
    public String getDbName() {
        return "yildizdatabase";
    }
}
