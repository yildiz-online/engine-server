/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.server.physic;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.shape.Box;
import be.yildiz.common.shape.Sphere;
import be.yildiz.common.vector.Point3D;
import be.yildiz.module.physics.*;
import be.yildiz.server.gameobject.ServerGameEntity;

/**
 * Bullet implementation for the ServerWorld_todelete.
 *
 * @author Grégory Van den Borre
 */
public final class ServerWorld implements World {

    /**
     * Physic world used to build and manage server world objects.
     */
    private final PhysicWorld physicWorld;

    public ServerWorld(PhysicWorld physicWorld) {
        super();
        assert  physicWorld != null;
        this.physicWorld = physicWorld;
    }

    /**
     * Create a static physic box, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent collidable object like
     * buildings, trees...
     *
     * @param id        Id to retrieve when object is selected, has collisions...
     * @param box       Box to use physic resource.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final Box box, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    /**
     * Create a static physic box, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param box      Box to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final Box box, final Point3D position) {
        return this.createStaticObject(id, box, position, Point3D.BASE_DIRECTION);
    }

    /**
     * Create a static physic sphere, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent collidable object like
     * buildings, trees...
     *
     * @param id        Id to retrieve when object is selected, has collisions...
     * @param sphere    Sphere to use physic resource.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final Sphere sphere, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    /**
     * Create a static physic sphere, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param sphere   Sphere to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final Sphere sphere, final Point3D position) {
        return this.createStaticObject(id, sphere, position, Point3D.BASE_DIRECTION);
    }

    /**
     * Create a static physic object, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent collidable object like
     * buildings, trees...
     *
     * @param id        Id to retrieve when object is selected, has collisions...
     * @param mesh      Mesh to use physic resource.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position, final Point3D direction) {
        StaticBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(mesh)
                .atPosition(position)
                .withDirection(direction)
                .buildStatic();
        return new StaticObject(body, position, direction);
    }

    /**
     * Create a static physic object, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param mesh     Mesh to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    public ServerGameEntity createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position) {
        return this.createStaticObject(id, mesh, position, Point3D.BASE_DIRECTION);
    }

    /**
     * Create a movable physic box, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param box      Box to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    public ServerGameEntity createMovableObject(final EntityId id, final Box box, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body, position);
    }

    /**
     * Create a movable physic sphere, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param sphere   Sphere to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    public ServerGameEntity createMovableObject(final EntityId id, final Sphere sphere, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body, position);
    }

    /**
     * Create a movable physic object, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param mesh     Mesh to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    public ServerGameEntity createMovableObject(final EntityId id, final PhysicMesh mesh, final Point3D position) {
        KinematicBody body = this.physicWorld
                .createObject()
                .withId(id)
                .withShape(mesh)
                .atPosition(position)
                .buildKinematic();
        return new MovableObject(body, position);
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
    public void setGravity(final Gravity newGravity) {
        this.physicWorld.setGravity(newGravity);
    }

    @Override
    public void setGravity(final float gravityX, final float gravityY, final float gravityZ) {
        this.physicWorld.setGravity(gravityX, gravityY, gravityZ);
    }

    public GhostObject createGhostObject(final EntityId id, final Box box, final Point3D position) {
        return this.physicWorld
                .createObject()
                .withId(id)
                .withShape(box)
                .atPosition(position)
                .buildGhost();
    }

    public GhostObject createGhostObject(final EntityId id, final Sphere sphere, final Point3D position) {
        return this.physicWorld
                .createObject()
                .withId(id)
                .withShape(sphere)
                .atPosition(position)
                .buildGhost();
    }

    public ServerGameEntity createStaticDoodad(final Point3D position, final Point3D direction) {
        return new StaticDoodad(position, direction);
    }

    public ServerGameEntity createStaticDoodad(final EntityId id, final Point3D position, final Point3D direction) {
        // FIXME id ignored?
        return this.createStaticDoodad(position, direction);
    }

    public void addCollisionListener(final CollisionListener c) {
        this.physicWorld.addCollisionListener(c);
    }

    public void addGhostCollisionListener(final CollisionListener c) {
        this.physicWorld.addGhostCollisionListener(c);
    }
}
