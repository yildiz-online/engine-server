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
package be.yildizgames.engine.server.world.internal;

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.gameobject.CollisionListener;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.engine.server.world.ServerGameObjectBuilder;
import be.yildizgames.engine.server.world.ServerWorld;
import be.yildizgames.module.physics.Gravity;
import be.yildizgames.module.physics.PhysicWorld;
import be.yildizgames.module.physics.RaycastResult;
import be.yildizgames.module.physics.World;

/**
 * @author Grégory Van den Borre
 */
public class EnginePhysicWorld implements ServerWorld, World {

    /**
     * Physic world used to build and manage server world objects.
     */
    private final PhysicWorld physicWorld;

    public EnginePhysicWorld(PhysicWorld physicWorld) {
        super();
        ImplementationException.throwForNull(physicWorld);
        this.physicWorld = physicWorld;
    }

    @Override
    public ServerGameObjectBuilder createObjectBuilder() {
        return new ServerObjectBuilder(this.physicWorld.createObject());
    }

    @Override
    public final RaycastResult throwRay(final Point3D origin, final Point3D destination) {
        return this.physicWorld.throwRay(origin, destination);
    }

    @Override
    public final EntityId throwSimpleRay(final Point3D origin, final Point3D destination) {
        return this.physicWorld.throwSimpleRay(origin, destination);
    }

    @Override
    public final Point3D getGravity() {
        return this.physicWorld.getGravity();
    }

    @Override
    public final EnginePhysicWorld setGravity(final Gravity newGravity) {
        this.physicWorld.setGravity(newGravity);
        return this;
    }

    @Override
    public final void setGravity(final float gravityX, final float gravityY, final float gravityZ) {
        this.physicWorld.setGravity(gravityX, gravityY, gravityZ);
    }

    @Override
    public final void addCollisionListener(final CollisionListener c) {
        this.physicWorld.addCollisionListener(c);
    }

    @Override
    public final void addGhostCollisionListener(final CollisionListener c) {
        this.physicWorld.addGhostCollisionListener(c);
    }
}
