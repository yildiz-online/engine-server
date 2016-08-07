//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.server.game;

import be.yildiz.common.Version;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.log.Logger;
import be.yildiz.module.network.protocol.ServerResponse;
import be.yildiz.module.network.server.AuthenticationSessionManager;
import be.yildiz.module.network.server.Server;
import be.yildiz.module.network.server.SessionListener;
import be.yildiz.module.physics.AbstractPhysicEngine;
import be.yildiz.module.physics.CollisionListener;
import be.yildiz.server.physic.ServerPhysicEngine;
import be.yildiz.server.physic.ServerWorld;
import be.yildiz.shared.game.engine.AbstractGameEngine;
import be.yildiz.shared.game.engine.DataInitializer;
import be.yildiz.shared.game.engine.Initializable;
import lombok.Getter;

/**
 * Server side implementation for the game engine.
 *
 * @author Grégory Van den Borre
 */
public final class GameEngine extends AbstractGameEngine {

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
    @Getter
    private final AuthenticationSessionManager sessionManager;
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
     *
     * @effect Create a new engine.
     * @
     */
    public GameEngine(AbstractPhysicEngine physicEngine, AuthenticationSessionManager sessionManager, Server server, Version version) {
        super(version);
        Logger.info("Starting server game engine...");
        this.physicEngine = new ServerPhysicEngine(physicEngine);
        this.activeWorld = this.physicEngine.createWorld();
        this.initializer = new DataInitializer();
        this.sessionManager = sessionManager;
        server.startServer();
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
        Logger.info("Starting server data initialization...");
        Logger.info("Server data initialized.");
        Logger.info("Server game engine started.");
        this.setFrameLimiter(FPS);
        this.running = true;
        while (this.running) {
            this.runOneFrame();
        }
        Logger.info("Closing engine.");
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

    public final void sendMessage(final PlayerId player, final ServerResponse response) {
        this.sessionManager.getSessionByPlayer(player).sendMessage(response);
    }

    /**
     * Add a ghost listener to this active world.
     *
     * @param listener Listener to add.
     * @Requires listener != null
     * @Ensures this.activeWorld.listeners.contains(listener)
     */
    public final void addGhostListener(CollisionListener listener) {
        this.activeWorld.addGhostCollisionListener(listener);
    }
}
