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

package be.yildiz.server.datamanager;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public class TaskMoveTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void testTaskMove1() {
        this.rule.expect(NullPointerException.class);
        new TaskMove(null, Point3D.ZERO, 0);
    }

    @Test
    public void testTaskMove2() {
        this.rule.expect(NullPointerException.class);
        new TaskMove(EntityId.get(5L), null, 0);
    }

    @Test
    public void testTaskMove() {
        try {
            new TaskMove(EntityId.get(5L), Point3D.ZERO, 0);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testGet() {
        TaskMove tm = new TaskMove(EntityId.get(2L), new Point3D(1), 12);
        Assert.assertEquals(EntityId.get(2L), tm.getEntity());
        Assert.assertEquals(new Point3D(1), tm.getDestination());
        Assert.assertEquals(12, tm.getSpeed(), 0.001);
    }
}
