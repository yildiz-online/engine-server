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

package be.yildizgames.engine.server.world.internal;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.engine.server.world.ServerGameObject;
import be.yildizgames.engine.server.world.ServerGameObjectBuilder;
import be.yildizgames.module.physics.GhostObject;
import be.yildizgames.module.physics.PhysicMesh;
import be.yildizgames.module.physics.PhysicObjectBuilder;

class ServerObjectBuilder implements ServerGameObjectBuilder {

    private final PhysicObjectBuilder builder;

    ServerObjectBuilder(PhysicObjectBuilder builder) {
        this.builder = builder;
    }

    @Override
    public final ServerObjectBuilder withId(EntityId id) {
        this.builder.withId(id);
        return this;
    }

    @Override
    public final ServerObjectBuilder withShape(Box box) {
        this.builder.withShape(box);
        return this;
    }

    @Override
    public final ServerObjectBuilder withShape(Sphere sphere) {
        this.builder.withShape(sphere);
        return this;
    }

    @Override
    public final ServerObjectBuilder withShape(PhysicMesh mesh) {
        this.builder.withShape(mesh);
        return this;
    }

    @Override
    public final ServerObjectBuilder atPosition(Point3D position) {
        this.builder.atPosition(position);
        return this;
    }

    @Override
    public final ServerGameObject buildMovable() {
        return new MovableObject(this.builder.buildKinematic());
    }

    @Override
    public final ServerGameObject buildStatic() {
        return new StaticObject(this.builder.buildStatic());
    }

    @Override
    public final GhostObject buildGhost() {
        return this.builder.buildGhost();
    }

}
