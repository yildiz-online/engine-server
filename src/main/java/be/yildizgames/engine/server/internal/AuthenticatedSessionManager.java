/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.server.internal;

import be.yildizgames.common.authentication.Token;
import be.yildizgames.common.authentication.protocol.mapper.TokenMapper;
import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.module.messaging.Broker;
import be.yildizgames.module.messaging.BrokerMessageDestination;
import be.yildizgames.module.messaging.Header;
import be.yildizgames.module.messaging.JmsMessageProducer;
import be.yildizgames.module.messaging.Message;
import be.yildizgames.module.network.protocol.MessageWrapper;
import be.yildizgames.module.network.server.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Session manager implementation requiring to be authenticated against an authentication server.
 * @author Grégory Van den Borre
 */
class AuthenticatedSessionManager extends BaseSessionManager {

    private final JmsMessageProducer producer;

    private final Map<String, Session> sessionByCorrelationId = new HashMap<>();

    AuthenticatedSessionManager(Broker broker) {
        super();
        ImplementationException.throwForNull(broker);
        BrokerMessageDestination destination = broker.registerQueue("authenticate");
        destination.createConsumer(this::authenticationResponse);
        this.producer = destination.createProducer();
    }

    @Override
    protected void authenticate(Session session, MessageWrapper message) {
        String uid = UUID.randomUUID().toString();
        this.sessionByCorrelationId.put(uid, session);
        this.producer.sendMessage(message.message, Header.correlationId(uid));
    }

    @Override
    public void update() {

    }

    private void authenticationResponse(Message m) {
        Token token = TokenMapper.getInstance().from(m.getText());
        Session s = this.sessionByCorrelationId.remove(m.getCorrelationId());
        if (token.isAuthenticated() && s != null && s.isConnected()) {
            s.setAuthenticated();
            s.setPlayer(token.getId());
            s.sendMessage(this.generateAuthenticationMessage(token));
        } else {
            s.sendMessage(this.generateAuthenticationMessage(Token.authenticationFailed()));
        }
    }
}
