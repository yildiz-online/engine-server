/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
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

package be.yildizgames.engine.server.world;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.module.physics.GhostObject;
import be.yildizgames.module.physics.PhysicMesh;

/**
 * Create server game object.
 * @author Grégory Van den Borre
 */
public interface ServerGameObjectBuilder {

    /**
     * Provide an id to the object.
     * @param id Id to set.
     * @return The builder for chaining.
     */
    ServerGameObjectBuilder withId(EntityId id);

    /**
     * Provide a shape to the object.
     * @param box Box to use as shape.
     * @return The builder for chaining.
     */
    ServerGameObjectBuilder withShape(Box box);

    /**
     * Provide a shape to the object.
     * @param sphere Sphere to use as shape.
     * @return The builder for chaining.
     */
    ServerGameObjectBuilder withShape(Sphere sphere);

    /**
     * Provide a shape to the object.
     * @param mesh Mesh to use as shape.
     * @return The builder for chaining.
     */
    ServerGameObjectBuilder withShape(PhysicMesh mesh);

    /**
     * Provide a position to the object.
     * @param position Object initial position.
     * @return The builder for chaining.
     */
    ServerGameObjectBuilder atPosition(Point3D position);

    /**
     * Create a movable object.
     * @return the created object.
     */
    ServerGameObject buildMovable();

    /**
     * Create a static object.
     * @return the created object.
     */
    ServerGameObject buildStatic();

    /**
     * Create a ghost object.
     * @return the created object.
     */
    GhostObject buildGhost();
}
