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

package be.yildizgames.engine.server.world;

import be.yildizgames.common.gameobject.CollisionListener;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.module.physics.GhostObject;
import be.yildizgames.module.physics.Gravity;
import be.yildizgames.module.physics.PhysicMesh;
import be.yildizgames.module.physics.RaycastResult;

/**
 * Bullet implementation for the ServerWorld_todelete.
 *
 * @author Grégory Van den Borre
 */
public interface ServerWorld {

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
    ServerGameObject createStaticObject(final EntityId id, final Box box, final Point3D position, final Point3D direction);

    /**
     * Create a static physic box, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param box      Box to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    ServerGameObject createStaticObject(final EntityId id, final Box box, final Point3D position) ;

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
    ServerGameObject createStaticObject(final EntityId id, final Sphere sphere, final Point3D position, final Point3D direction);

    /**
     * Create a static physic sphere, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param sphere   Sphere to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    ServerGameObject createStaticObject(final EntityId id, final Sphere sphere, final Point3D position);

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
    ServerGameObject createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position, final Point3D direction);

    /**
     * Create a static physic object, it a given Id, cannot move, and will not be affected in any way by physics dynamic, but is collidable. The direction is the default one. It is usually used to
     * represent collidable object like buildings, trees...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param mesh     Mesh to use physic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     */
    ServerGameObject createStaticObject(final EntityId id, final PhysicMesh mesh, final Point3D position);

    /**
     * Create a movable physic box, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param box      Box to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    ServerGameObject createMovableObject(final EntityId id, final Box box, final Point3D position);

    /**
     * Create a movable physic sphere, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param sphere   Sphere to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    ServerGameObject createMovableObject(final EntityId id, final Sphere sphere, final Point3D position);

    /**
     * Create a movable physic object, it has a given Id and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or movable object like
     * characters, vehicles...
     *
     * @param id       Id to retrieve when object is selected, has collisions...
     * @param mesh     Mesh to use physic resource.
     * @param position Object initial position.
     * @return The built object.
     */
    ServerGameObject createMovableObject(final EntityId id, final PhysicMesh mesh, final Point3D position);

    RaycastResult throwRay(final Point3D origin, final Point3D destination);

    EntityId throwSimpleRay(final Point3D origin, final Point3D destination);

    Point3D getGravity();

    ServerWorld setGravity(final Gravity newGravity);

    void setGravity(final float gravityX, final float gravityY, final float gravityZ);

    GhostObject createGhostObject(final EntityId id, final Box box, final Point3D position);

    GhostObject createGhostObject(final EntityId id, final Sphere sphere, final Point3D position);

    ServerGameObject createStaticDoodad(final Point3D position, final Point3D direction);

    ServerGameObject createStaticDoodad(final EntityId id, final Point3D position, final Point3D direction);

    void addCollisionListener(final CollisionListener c);

    void addGhostCollisionListener(final CollisionListener c);
}
