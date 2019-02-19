package be.yildizgames.engine.server.internal;

import be.yildizgames.common.authentication.Token;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.module.network.protocol.MessageWrapper;
import be.yildizgames.module.network.server.Session;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This session manager implementation automatically authenticate any incoming request.
 * @author Grégory Van den Borre
 */
class NoAuthenticationSessionManager extends BaseSessionManager {

    /**
     * Incremented each time a user connect, to provide a different id for everyone.
     */
    private AtomicInteger latest = new AtomicInteger();

    @Override
    protected final void authenticate(Session session, MessageWrapper messageWrapper) {
        int latestId = this.latest.incrementAndGet();
        Token token = Token.authenticated(PlayerId.valueOf(latestId), System.currentTimeMillis(), 5);
        session.sendMessage(this.generateAuthenticationMessage(token));
        this.setAuthenticated(session);
    }

    @Override
    public final void update() {

    }
}
