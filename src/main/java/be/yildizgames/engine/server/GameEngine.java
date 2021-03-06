/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
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
package be.yildizgames.engine.server;

import be.yildizgames.engine.server.world.ServerWorld;
import be.yildizgames.module.physics.PhysicEngine;
import be.yildizgames.shared.game.engine.Initializable;

/**
 * Game engine for a server.
 *
 * @author Grégory Van den Borre
 */
public interface GameEngine {

    /**
     * Add logic to be initialized before the engine starts.
     *
     * @param init Object to initialize before the engine starts.
     */
    void addInitializable(Initializable init);

    /**
     * Provide the physic engine.
     * @return The physic engine.
     */
    PhysicEngine getPhysicEngine();

    /**
     * Provide the network engine.
     * @return The network engine.
     */
    NetworkEngine getNetworkEngine();

    /**
     * Provide the persistence engine.
     * @return The persistence engine.
     */
    PersistenceEngine getPersistenceEngine();

    /**
     * Create a new world.
     * @return The created world.
     */
    ServerWorld createWorld();

    /**
     * Start the engine.
     */
    void start();

    /**
     * Close the engine.
     */
    void close();
}
