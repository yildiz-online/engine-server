//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.server.datamanager;

import be.yildiz.common.id.EntityId;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Simple container for a building construction task data.
 *
 * @author Grégory Van den Borre
 */
@AllArgsConstructor
@Getter
public final class TaskBuilding {

    /**
     * City's id.
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
}
