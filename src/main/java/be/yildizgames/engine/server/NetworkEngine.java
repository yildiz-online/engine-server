/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.engine.server;

import be.yildizgames.common.model.PlayerId;
import be.yildizgames.module.network.server.Session;
import be.yildizgames.module.network.server.SessionListener;

import java.util.List;
import java.util.Set;

/**
 * Network engine to manage session and send messages.
 * @author Grégory Van den Borre
 */
public interface NetworkEngine {

    /**
     * Retrieve the session for a given player, if the player is offline, it will be a disconnected session.
     * @param player Id of the player to get the session.
     * @return The session for the given player.
     */
    Session getSessionByPlayer(PlayerId player);

    /**
     * Disconnect a session.
     * @param session Session to disconnect.
     */
    void disconnectSession(Session session);

    /**
     * Add a session listener to be notified of the message received and other session events.
     * @param listener Listener to register.
     */
    void addSessionListener(SessionListener listener);

    /**
     * Retrieve all connected players.
     * @return A set of connected players.
     */
    Set<PlayerId> getActivePlayers();

    /**
     * Retrieve all connected sessions.
     * @return A list of connected sessions.
     */
    List<Session> getActiveSessions();
}
