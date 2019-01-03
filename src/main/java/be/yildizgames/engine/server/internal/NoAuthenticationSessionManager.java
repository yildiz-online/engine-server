package be.yildizgames.engine.server.internal;

import be.yildizgames.common.authentication.Token;
import be.yildizgames.common.authentication.protocol.mapper.TokenMapper;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.module.network.protocol.MessageWrapper;
import be.yildizgames.module.network.protocol.NetworkMessage;
import be.yildizgames.module.network.server.Session;

class NoAuthenticationSessionManager extends BaseSessionManager {

    @Override
    protected void authenticate(Session session, MessageWrapper messageWrapper) {
        Token token = Token.authenticated(PlayerId.WORLD, System.currentTimeMillis(), 5);
        session.sendMessage(this.generateAuthenticationMessage(token));
        this.setAuthenticated(session);
    }

    @Override
    public void update() {

    }
}
