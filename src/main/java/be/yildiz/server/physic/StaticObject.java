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

import be.yildiz.module.physics.AbstractStaticObject;
import be.yildiz.module.physics.StaticBody;
import be.yildiz.server.gameobject.ServerGameEntity;
import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;

/**
 * Bullet implementation for a static object server side. It is associated to a static object in native bullet code.
 *
 * @author Grégory Van den Borre
 */
public final class StaticObject extends AbstractStaticObject implements ServerGameEntity {

    /**
     * Physic static body.
     */
    private final StaticBody body;

    /**
     * Current scaling factor.
     */
    protected Point3D scaleSize = Point3D.valueOf(1);

    /**
     * Full constructor.
     *
     * @param initialPosition  Immutable object position.
     * @param initialDirection Immutable object direction.
     */
    StaticObject(final StaticBody body, final Point3D initialPosition, final Point3D initialDirection) {
        super(initialPosition, initialDirection);
        this.body = body;
    }

    @Override
    public void delete() {
        this.body.delete();
    }

    @Override
    public EntityId getId() {
        return this.body.getId();
    }

    @Override
    public void rotate(float x, float y, float z, float w) {
        //Does nothing
    }

    @Override
    public Point3D getScaleSize() {
        return this.scaleSize;
    }

    @Override
    public void scale(final float x, final float y, final float z) {
        this.scaleSize = Point3D.valueOf(x, y, z);
        this.body.scale(x, y, z);
    }

    @Override
    public void sleep(boolean b) {
        this.body.sleep(b);
    }

    @Override
    public void detachFromParent() {
        //TODO implements
    }

    @Override
    public void addOptionalChild(Movable child) {
        //TODO implements
    }

    @Override
    public void removeChild(Movable child) {
        //TODO implements
    }

    @Override
    public Movable getInternal() {
        return this.body;
    }
}
