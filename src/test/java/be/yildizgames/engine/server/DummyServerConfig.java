package be.yildizgames.engine.server;

import be.yildizgames.common.model.Version;
import be.yildizgames.engine.server.configuration.ServerConfiguration;

public class DummyServerConfig implements ServerConfiguration {

    @Override
    public int getApplicationPort() {
        return 0;
    }

    @Override
    public Version getVersion() {
        return Version.alpha(1,1,1,1);
    }

    @Override
    public String getAuthenticationMethod() {
        return "none";
    }

    @Override
    public String getBrokerHost() {
        return "localhost";
    }

    @Override
    public int getBrokerPort() {
        return 0;
    }

    @Override
    public String getBrokerDataFolder() {
        return "temp";
    }

    @Override
    public boolean getBrokerInternal() {
        return true;
    }
}
