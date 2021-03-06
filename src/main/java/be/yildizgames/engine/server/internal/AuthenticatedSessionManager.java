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
import be.yildizgames.common.authentication.protocol.Queues;
import be.yildizgames.common.authentication.protocol.mapper.TokenMapper;
import be.yildizgames.module.messaging.Broker;
import be.yildizgames.module.messaging.BrokerMessage;
import be.yildizgames.module.messaging.BrokerMessageDestination;
import be.yildizgames.module.messaging.BrokerMessageHeader;
import be.yildizgames.module.messaging.BrokerMessageProducer;
import be.yildizgames.module.network.protocol.MessageWrapper;
import be.yildizgames.module.network.server.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Session manager implementation requiring to be authenticated against an authentication server.
 *
 * @author Grégory Van den Borre
 */
class AuthenticatedSessionManager extends BaseSessionManager {

    private final BrokerMessageProducer producer;

    private final Map<String, Session> sessionByCorrelationId = new HashMap<>();

    AuthenticatedSessionManager(Broker broker) {
        super();
        Objects.requireNonNull(broker);
        BrokerMessageDestination destinationProducer = broker.registerQueue(Queues.AUTHENTICATION_REQUEST.getName());
        BrokerMessageDestination destinationConsumer = broker.registerQueue(Queues.AUTHENTICATION_RESPONSE.getName());
        destinationConsumer.createConsumer(this::authenticationResponse);
        this.producer = destinationProducer.createProducer();
    }

    @Override
    protected void authenticate(Session session, MessageWrapper message) {
        String uid = UUID.randomUUID().toString();
        this.sessionByCorrelationId.put(uid, session);
        this.producer.sendMessage(message.message, BrokerMessageHeader.correlationId(uid));
    }

    @Override
    public void update() {

    }

    private void authenticationResponse(BrokerMessage m) {
        Token token = TokenMapper.getInstance().from(m.getText());
        Optional.ofNullable(this.sessionByCorrelationId.remove(m.getCorrelationId()))
                .ifPresent(s -> {
                    if (token.isAuthenticated() && s.isConnected()) {
                        s.setAuthenticated();
                        s.setPlayer(token.getId());
                        s.sendMessage(this.generateAuthenticationMessage(token));
                    } else {
                        s.sendMessage(this.generateAuthenticationMessage(Token.authenticationFailed()));
                    }
                });
    }
}
