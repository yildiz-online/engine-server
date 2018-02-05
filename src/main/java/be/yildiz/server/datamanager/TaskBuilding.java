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

package be.yildiz.server.datamanager;

import be.yildizgames.common.model.EntityId;

/**
 * Simple container for a building construction task data.
 *
 * @author Grégory Van den Borre
 */
public final class TaskBuilding {

    /**
     * BaseCity's id.
     */
    private final EntityId city;

    /**
     * Building position.
     */
    private final int position;

    /**
     * Building type.
     */
    private final int type;

    /**
     * Building level.
     */
    private final int level;

    /**
     * Building allocated staff.
     */
    private final int staff;

    /**
     * Time left before building is completed.
     */
    private final long timeLeft;

    public TaskBuilding(EntityId city, int position, int type, int level, int staff, long timeLeft) {
        super();
        this.city = city;
        this.position = position;
        this.type = type;
        this.level = level;
        this.staff = staff;
        this.timeLeft = timeLeft;
    }


    public EntityId getCity() {
        return city;
    }

    public int getPosition() {
        return position;
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getStaff() {
        return staff;
    }

    public long getTimeLeft() {
        return timeLeft;
    }
}
