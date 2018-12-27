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

import be.yildizgames.engine.server.world.ServerGameObject;
import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.module.physics.AbstractStaticObject;
import be.yildizgames.module.physics.StaticBody;

/**
 * Bullet implementation for a static object server side. It is associated to a static object in native bullet code.
 *
 * @author Grégory Van den Borre
 */
class StaticObject extends AbstractStaticObject implements ServerGameObject {

    /**
     * Physic static body.
     */
    private final StaticBody body;

    /**
     * Current scaling factor.
     */
    private Point3D scaleSize = Point3D.valueOf(1);

    /**
     * Full constructor.
     *
     * @param body  Wrapped body.
     */
    StaticObject(final StaticBody body) {
        super(body.getPosition(), body.getDirection());
        this.body = body;
    }

    @Override
    public final void delete() {
        this.body.delete();
    }

    @Override
    public final EntityId getId() {
        return this.body.getId();
    }

    @Override
    public final void rotate(float x, float y, float z, float w) {
        //Does nothing
    }

    @Override
    public final Point3D getScaleSize() {
        return this.scaleSize;
    }

    @Override
    public final void scale(final float x, final float y, final float z) {
        this.scaleSize = Point3D.valueOf(x, y, z);
        this.body.scale(x, y, z);
    }

    @Override
    public final void sleep(boolean b) {
        this.body.sleep(b);
    }

    @Override
    public final void detachFromParent() {
        //Does nothing, static have no parent as it cannot move/rotate.
    }

    @Override
    public final void addOptionalChild(Movable child) {
        //TODO implements
    }

    @Override
    public final void removeChild(Movable child) {
        //TODO implements
    }

    @Override
    public final Movable getInternal() {
        return this.body;
    }
}
