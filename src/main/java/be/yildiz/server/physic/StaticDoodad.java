/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildiz.server.physic;

import be.yildiz.module.physics.AbstractStaticObject;
import be.yildiz.server.gameobject.ServerGameEntity;
import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;

/**
 * A static doodad is an an object not moving and not meant to have any interaction with the game entities.
 *
 * @author Grégory Van den Borre
 */
final class StaticDoodad extends AbstractStaticObject implements ServerGameEntity {

    /**
     * Current scaling.
     */
    private Point3D scaleSize = Point3D.valueOf(1);

    /**
     * Create a new doodad.
     *
     * @param pos Immutable position.
     * @param dir Immutable direction.
     * requires true.
     */
    StaticDoodad(final Point3D pos, final Point3D dir) {
        super(pos, dir);
    }

    @Override
    public void detachFromParent() {
        //TODO need to be done?
    }

    @Override
    public void setPosition(final float posX, final float posY, final float posZ) {
        //no set position as static
    }

    @Override
    public void setDirection(final float dirX, final float dirY, final float dirZ) {
        //no set direction as static
    }

    @Override
    public void addOptionalChild(Movable child) {
        //TODO need to be done?
    }

    @Override
    public void removeChild(Movable child) {
        //TODO need to be done?
    }

    @Override
    public Movable getInternal() {
        //TODO correct?
        return this;
    }

    @Override
    public void sleep(final boolean b) {
        //no sleep as static
    }

    @Override
    public void scale(final float x, final float y, final float z) {
        //no scale as static
    }

    @Override
    public void rotate(final float x, final float y, final float z, final float w) {
        //no rotate as static
    }

    @Override
    public void delete() {
        //FIXME implements
    }

    /**
     * Game entity id, world value as it has no interaction with the game..
     */
    @Override
    public EntityId getId() {
        return EntityId.WORLD;
    }

    @Override
    public Point3D getScaleSize() {
        return scaleSize;
    }
}
