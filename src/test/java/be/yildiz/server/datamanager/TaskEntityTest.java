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
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.vector.Point3D;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

/**
 * @author Grégory Van den Borre
 */
public final class TaskEntityTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void testTaskEntity() {
        this.rule.expect(AssertionError.class);
        new TaskEntity(null, PlayerId.get(4), Point3D.ZERO, 1, 0);
    }

    @Test
    public void testTaskEntity2() {
        this.rule.expect(AssertionError.class);
        new TaskEntity(EntityId.get(1L), null, Point3D.ZERO, 1, 0);
    }

    @Test
    public void testTaskEntity3() {
        this.rule.expect(AssertionError.class);
        new TaskEntity(EntityId.get(1L), PlayerId.get(2), null, 1, 0);
    }

    @Test
    public void testTaskEntity4() {
        this.rule.expect(InvalidParameterException.class);
        new TaskEntity(EntityId.get(1L), PlayerId.get(2), Point3D.ZERO, -1, 0);
    }

    @Test
    public void testTaskEntity5() {
        this.rule.expect(InvalidParameterException.class);
        new TaskEntity(EntityId.get(1L), PlayerId.get(2), Point3D.ZERO, 5, -1);
    }

    @Test
    public void testTaskEntity6() {
        try {
            new TaskEntity(EntityId.get(1L), PlayerId.get(2), Point3D.ZERO, 5, 0);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetEntity() {
        TaskEntity te = new TaskEntity(EntityId.get(1L), PlayerId.get(2), new Point3D(5), 5, 0);
        Assert.assertEquals(EntityId.get(1L), te.getEntity());
        Assert.assertEquals(PlayerId.get(2), te.getOwner());
        Assert.assertEquals(new Point3D(5), te.getPosition());
        Assert.assertEquals(5, te.getType());
        Assert.assertEquals(0, te.getTimeLeft());
    }

}
