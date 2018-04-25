/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.server.game;

import be.yildizgames.common.gameobject.CollisionListener;
import be.yildizgames.common.logging.LogFactory;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.common.model.Version;
import be.yildizgames.module.network.DecoderEncoder;
import be.yildizgames.module.network.protocol.NetworkMessage;
import be.yildizgames.module.network.server.Server;
import be.yildizgames.module.network.server.SessionListener;
import be.yildizgames.module.network.server.SessionManager;
import be.yildizgames.server.physic.ServerPhysicEngine;
import be.yildizgames.server.physic.ServerWorld;
import be.yildizgames.shared.game.engine.AbstractGameEngine;
import be.yildizgames.shared.game.engine.DataInitializer;
import be.yildizgames.shared.game.engine.Initializable;
import org.slf4j.Logger;

/**
 * Server side implementation for the game engine.
 *
 * @author Grégory Van den Borre
 */
public final class GameEngine extends AbstractGameEngine implements ResponseSender, AutoCloseable {

    private static final Logger LOGGER = LogFactory.getInstance().getLogger(GameEngine.class);

    /**
     * Frame limiter.
     */
    private static final int FPS = 50;

    /**
     * Physic engine.
     */
    private final ServerPhysicEngine physicEngine;

    /**
     * Class used to initialize the data.
     */
    private final DataInitializer initializer;

    private final SessionManager sessionManager;
    /**
     * Currently active world.
     */
    private ServerWorld activeWorld;

    /**
     * <code>true</code> if the engine is currently running, <code>false</code> otherwise.
     */
    private boolean running;

    private boolean check;

    /**
     * Full constructor, initialize the physic and the network engines, create the data manager.
     * Also add a shutdown hook to handle gracefully the termination signal.
     * @param sessionManager Session manager.
     * @param version Game version.
     */
    //@effect Create a new engine.
    public GameEngine(SessionManager sessionManager, Version version) {
        super(version);
        LOGGER.info("Starting server game engine...");
        this.physicEngine = new ServerPhysicEngine();
        this.activeWorld = this.physicEngine.createWorld();
        this.initializer = new DataInitializer();
        this.sessionManager = sessionManager;
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        Server
                .getEngine()
                .startServer("", 0, sessionManager, DecoderEncoder.WEBSOCKET);
    }

    /**
     * Add logic to be initialized before the engine starts.
     *
     * @param init Object to initialize before the engine starts.
     */
    public void addInitializable(final Initializable init) {
        this.initializer.addInitializable(init);
    }

    public void initialize() {
        this.initializer.initialize();
    }

    @Override
    public void start() {
        LOGGER.info("Starting server data initialization...");
        LOGGER.info("Server data initialized.");
        LOGGER.info("Server game engine started.");
        this.setFrameLimiter(FPS);
        this.running = true;
        while (this.running) {
            this.runOneFrame();
        }
        LOGGER.info("Closing engine.");
    }

    /**
     * Add a listener to be notified when collisions occurs between physic objects.
     *
     * @param listener Listener to notify when collision occurs.
     */
    public void addCollisionListener(final CollisionListener listener) {
        this.activeWorld.addCollisionListener(listener);
    }

    /**
     * Add a new session listener to the network engine. Listener will be notified when a client is successfully setAuthenticated and when he receives message.
     *
     * @param listener SessionListener to add.
     */
    public void addSessionListener(final SessionListener listener) {
        this.sessionManager.addSessionListener(listener);
    }

    /**
     * @return The currently active world.
     */
    public ServerWorld getActiveWorld() {
        return this.activeWorld;
    }

    @Override
    protected void runOneFrameImpl() {
        if (check) {
            this.physicEngine.update();
        }
        check = !check;
        this.sessionManager.update();
    }

    /**
     * Close the engine.
     */
    public void close() {
        this.running = false;
    }

    @Override
    public final void sendMessage(final PlayerId player, final NetworkMessage response) {
        this.sessionManager.getSessionByPlayer(player).sendMessage(response);
    }

    /**
     * Add a ghost listener to this active world.
     *
     * @param listener Listener to add.
     */
    //@Requires listener != null
    //@Ensures this.activeWorld.listeners.contains(listener)
    public final void addGhostListener(CollisionListener listener) {
        this.activeWorld.addGhostCollisionListener(listener);
    }

    //public SessionManager getSessionManager() {
    //    return sessionManager;
    //}
}
