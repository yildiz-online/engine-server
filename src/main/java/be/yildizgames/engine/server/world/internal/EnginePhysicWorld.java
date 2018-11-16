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
package be.yildizgames.engine.server.world.internal;

import be.yildizgames.common.gameobject.CollisionListener;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.engine.server.world.ServerGameObject;
import be.yildizgames.engine.server.world.ServerWorld;
import be.yildizgames.module.physics.GhostObject;
import be.yildizgames.module.physics.Gravity;
import be.yildizgames.module.physics.KinematicBody;
import be.yildizgames.module.physics.PhysicMesh;
import be.yildizgames.module.physics.PhysicWorld;
import be.yildizgames.module.physics.RaycastResult;
import be.yildizgames.module.physics.StaticBody;
import be.yildizgames.module.physics.World;

public class EnginePhysicWorld implements ServerWorld, World {

    /**
     * Physic world used to build and manage server world objects.
     */
    private final PhysicWorld physicWorld;

    public EnginePhysicWorld(PhysicWorld physicWorld) {
        super();
        assert  physicWorld != null;
        this.physicWorld = physicWorld;
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final Box box, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final Box box, final Point3D position) {
        return this.createStaticObject(id, box, position, Point3D.BASE_DIRECTION);
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final Sphere sphere, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final Sphere sphere, final Point3D position) {
        return this.createStaticObject(id, sphere, position, Point3D.BASE_DIRECTION);
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(mesh)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    @Override
    public ServerGameObject createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position) {
        return this.createStaticObject(id, mesh, position, Point3D.BASE_DIRECTION);
    }

    @Override
    public ServerGameObject createMovableObject(final EntityId id, final Box box, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body);
    }

    @Override
    public ServerGameObject createMovableObject(final EntityId id, final Sphere sphere, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body);
    }

    @Override
    public ServerGameObject createMovableObject(final EntityId id, final PhysicMesh mesh, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(mesh)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body);
    }

    @Override
    public RaycastResult throwRay(final Point3D origin, final Point3D destination) {
        return this.physicWorld.throwRay(origin, destination);
    }

    @Override
    public EntityId throwSimpleRay(final Point3D origin, final Point3D destination) {
        return this.physicWorld.throwSimpleRay(origin, destination);
    }

    @Override
    public Point3D getGravity() {
        return this.physicWorld.getGravity();
    }

    @Override
    public EnginePhysicWorld setGravity(final Gravity newGravity) {
        this.physicWorld.setGravity(newGravity);
        return this;
    }

    @Override
    public void setGravity(final float gravityX, final float gravityY, final float gravityZ) {
        this.physicWorld.setGravity(gravityX, gravityY, gravityZ);
    }

    @Override
    public GhostObject createGhostObject(final EntityId id, final Box box, final Point3D position) {
        return this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .buildGhost();
    }

    @Override
    public GhostObject createGhostObject(final EntityId id, final Sphere sphere, final Point3D position) {
        return this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .buildGhost();
    }

    @Override
    public ServerGameObject createStaticDoodad(final Point3D position, final Point3D direction) {
        return new StaticDoodad(position, direction);
    }

    @Override
    public ServerGameObject createStaticDoodad(final EntityId id, final Point3D position, final Point3D direction) {
        // FIXME id ignored?
        return this.createStaticDoodad(position, direction);
    }

    @Override
    public void addCollisionListener(final CollisionListener c) {
        this.physicWorld.addCollisionListener(c);
    }

    @Override
    public void addGhostCollisionListener(final CollisionListener c) {
        this.physicWorld.addGhostCollisionListener(c);
    }
}
