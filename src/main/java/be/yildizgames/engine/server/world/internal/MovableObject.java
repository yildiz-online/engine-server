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

import be.yildizgames.engine.server.world.ServerGameObject;
import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.geometry.Quaternion;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.module.physics.AbstractMovableObject;
import be.yildizgames.module.physics.KinematicBody;

/**
 * Bullet implementation for a movable object server side. It is associated to a kinematic object in native bullet code.
 *
 * @author Grégory Van den Borre
 */
class MovableObject extends AbstractMovableObject implements ServerGameObject {

    /**
     * Physic body.
     */
    private final KinematicBody body;

    /**
     * Current scaling factor.
     */
    private Point3D scaleSize = Point3D.valueOf(1);

    /**
     * Full constructor.
     *
     * @param kinematicBody Bullet kinematic body.
     */
    MovableObject(final KinematicBody kinematicBody) {
        super();
        this.body = kinematicBody;
    }

    @Override
    public final EntityId getId() {
        return this.body.getId();
    }

    @Override
    public final void sleep(boolean b) {
        this.body.sleep(b);
    }

    @Override
    public final void delete() {
        this.body.delete();
    }

    @Override
    public final void rotate(float x, float y, float z, float w) {
        this.body.setOrientation(Quaternion.valueOf(w, x, y, z));
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
    public final Point3D getPosition() {
        return this.body.getPosition();
    }

    @Override
    public final Point3D getDirection() {
        return this.body.getDirection();
    }

    @Override
    public final void setPosition(float posX, float posY, float posZ) {
        this.body.setPosition(posX, posY, posZ);
    }

    @Override
    public final void setDirection(float dirX, float dirY, float dirZ) {
        this.body.setDirection(dirX, dirY, dirZ);
    }

    @Override
    public final Movable getInternal() {
        return this.body;
    }

}
