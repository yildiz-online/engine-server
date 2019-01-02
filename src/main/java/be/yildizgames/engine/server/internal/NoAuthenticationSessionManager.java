package be.yildizgames.engine.server.internal;

import be.yildizgames.module.network.protocol.MessageWrapper;
import be.yildizgames.module.network.server.Session;

class NoAuthenticationSessionManager extends BaseSessionManager {

    @Override
    protected void authenticate(Session session, MessageWrapper messageWrapper) {
        this.setAuthenticated(session);
    }

    @Override
    public void update() {

    }
}
