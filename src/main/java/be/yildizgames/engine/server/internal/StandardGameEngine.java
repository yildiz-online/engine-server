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

package be.yildizgames.engine.server.internal;

import be.yildizgames.common.util.Util;
import be.yildizgames.engine.server.GameEngine;
import be.yildizgames.engine.server.NetworkEngine;
import be.yildizgames.engine.server.PersistenceEngine;
import be.yildizgames.engine.server.configuration.ServerConfiguration;
import be.yildizgames.engine.server.world.ServerWorld;
import be.yildizgames.engine.server.world.internal.EnginePhysicWorld;
import be.yildizgames.module.messaging.Broker;
import be.yildizgames.module.network.DecoderEncoder;
import be.yildizgames.module.network.server.Server;
import be.yildizgames.module.physics.BasePhysicEngine;
import be.yildizgames.module.physics.PhysicEngine;
import be.yildizgames.shared.game.engine.AbstractGameEngine;
import be.yildizgames.shared.game.engine.DataInitializer;
import be.yildizgames.shared.game.engine.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server side implementation for the game engine.
 *
 * @author Grégory Van den Borre
 */
public final class StandardGameEngine extends AbstractGameEngine implements AutoCloseable, GameEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardGameEngine.class);

    /**
     * Frame limiter.
     */
    private static final int FPS = 50;

    /**
     * Physic engine.
     */
    private final BasePhysicEngine physicEngine;

    /**
     * Class used to initialize the data.
     */
    private final DataInitializer initializer;

    private final BaseSessionManager sessionManager;

    /**
     * <code>true</code> if the engine is currently running, <code>false</code> otherwise.
     */
    private boolean running;

    private boolean check;

    private final PersistenceEngine persistenceEngine;

    /**
     * Full constructor, initialize the physic and the network engines, create the data manager.
     * Also add a shutdown hook to handle gracefully the termination signal.
     * @param config Server configuration.
     */
    //@effect Create a new engine.
    public StandardGameEngine(ServerConfiguration config) {
        super(config.getVersion());
        LOGGER.info("Starting server game engine(PID:{})...", Util.getPid());
        this.physicEngine = BasePhysicEngine.getEngine();
        this.persistenceEngine = new DatabasePersistenceEngine();
        this.initializer = new DataInitializer();
        switch(config.getAuthenticationMethod()) {
            case "authentication-server-async":
                this.sessionManager = new AuthenticatedSessionManager(Broker.getBroker(config));
                break;
            case "none":
                this.sessionManager = new NoAuthenticationSessionManager();
                break;
            default:
                this.sessionManager = new AuthenticatedSessionManager(Broker.getBroker(config));
                break;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        Server
                .getEngine()
                .startServer(config.getApplicationPort(), this.sessionManager, DecoderEncoder.WEBSOCKET);
    }

    @Override
    public void addInitializable(final Initializable init) {
        this.initializer.addInitializable(init);
    }

    @Override
    public void start() {
        LOGGER.info("initializing server game engine...");
        this.initializer.initialize();
        LOGGER.info("Server game engine initialized.");
        LOGGER.info("Server game engine started.");
        this.setFrameLimiter(FPS);
        this.running = true;
        while (this.running) {
            this.runOneFrame();
        }
        LOGGER.info("Closing server game engine.");
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
    public PhysicEngine getPhysicEngine() {
        return this.physicEngine;
    }

    @Override
    public NetworkEngine getNetworkEngine() {
        return this.sessionManager;
    }

    @Override
    public PersistenceEngine getPersistenceEngine() {
        return this.persistenceEngine;
    }

    public ServerWorld createWorld() {
        return new EnginePhysicWorld(this.physicEngine.createWorld());
    }
}
