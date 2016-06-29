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

package be.yildiz.server.physic;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.shape.Box;
import be.yildiz.common.vector.Point3D;
import be.yildiz.module.physics.CollisionListener;
import be.yildiz.module.physics.CollisionResult;
import be.yildiz.module.physics.DummyPhysicEngine;
import be.yildiz.module.physics.GhostObject;
import be.yildiz.server.gameobject.ServerGameEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
@Ignore
public class ServerWorldTest {

    private ServerPhysicEngine engine;

    private CollisionListenerTestImpl collisionListener;

    private ServerWorld world;

    @Test
    public void updateGhostFindResultTest() throws InterruptedException {
        givenPhysicEngineWithWorldAndGhostCollisionListener();
        GhostObject go = this.world.createGhostObject(EntityId.get(1L), new Box(100), Point3D.xyz(100, 100, 100));
        ServerGameEntity se = this.world.createMovableObject(EntityId.get(2L), new Box(10), Point3D.xyz(100, 100, 100));
        whenEngineUpdate(20);
        Assert.assertEquals(go.getId(), this.collisionListener.object1.get());
        Assert.assertEquals(se.getId(), this.collisionListener.object2.get());
    }

    @Test
    public void updateGhostLoseResultTest() throws InterruptedException {
        givenPhysicEngineWithWorldAndGhostCollisionListener();
        GhostObject go = this.world.createGhostObject(EntityId.get(1L), new Box(100), Point3D.xyz(100, 100, 100));
        ServerGameEntity se = this.world.createMovableObject(EntityId.get(2L), new Box(10), Point3D.xyz(100, 100, 100));
        whenEngineUpdate(20);
        Assert.assertEquals(go.getId(), this.collisionListener.object1.get());
        Assert.assertEquals(se.getId(), this.collisionListener.object2.get());
        go.setPosition(1500, 500, 500);
        whenEngineUpdate(20);
        Assert.assertFalse(this.collisionListener.object1.isPresent());
        Assert.assertFalse(this.collisionListener.object2.isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void addNullListenerTest() {
        this.world.addCollisionListener(null);
    }

    @Test(expected = NullPointerException.class)
    public void addNullGhostListenerTest() {
        this.world.addGhostCollisionListener(null);
    }

    @Ignore(value = "the collision between objects seems not working, fix.")
    @Test
    public void updateCollisionFindResultTest() throws InterruptedException {
        givenPhysicEngineWithWorldAndCollisionListener();
        ServerGameEntity se = this.world.createMovableObject(EntityId.get(2L), new Box(100), Point3D.xyz(100));
        whenEngineUpdate(20);
        Assert.assertFalse(this.collisionListener.object1.isPresent());
        Assert.assertFalse(this.collisionListener.object2.isPresent());
        ServerGameEntity se2 = this.world.createMovableObject(EntityId.get(3L), new Box(100), Point3D.xyz(100));
        whenEngineUpdate(20);
        Assert.assertEquals(se.getId(), this.collisionListener.object1.get());
        Assert.assertEquals(se2.getId(), this.collisionListener.object2.get());
    }

    @Ignore(value = "the collision between objects seems not working, fix.")
    @Test
    public void updateCollisionLoseResultTest() throws InterruptedException {
        givenPhysicEngineWithWorldAndCollisionListener();
        ServerGameEntity se = this.world.createMovableObject(EntityId.get(2L), new Box(10), Point3D.xyz(100));
        ServerGameEntity se2 = this.world.createMovableObject(EntityId.get(3L), new Box(10), Point3D.xyz(100));
        whenEngineUpdate(20);
        Assert.assertEquals(se.getId(), this.collisionListener.object1.get());
        Assert.assertEquals(se2.getId(), this.collisionListener.object2.get());
        se2.setPosition(1500, 500, 500);
        whenEngineUpdate(20);
        Assert.assertFalse(this.collisionListener.object1.isPresent());
        Assert.assertFalse(this.collisionListener.object2.isPresent());
    }

    private void givenPhysicEngineWithWorldAndGhostCollisionListener() {
        this.engine = new ServerPhysicEngine(new DummyPhysicEngine());
        this.world = engine.createWorld();
        this.collisionListener = new CollisionListenerTestImpl();
        this.world.addGhostCollisionListener(this.collisionListener);
    }

    private void givenPhysicEngineWithWorldAndCollisionListener() {
        this.engine = new ServerPhysicEngine(new DummyPhysicEngine());
        this.world = engine.createWorld();
        this.collisionListener = new CollisionListenerTestImpl();
        this.world.addCollisionListener(this.collisionListener);
    }

    private void whenEngineUpdate(long timeToWait) throws InterruptedException {
        // FIXME need some time to be effective
        this.engine.update();
        Thread.sleep(timeToWait);
        this.engine.update();
    }

    private static final class CollisionListenerTestImpl implements CollisionListener {

        private Optional<EntityId> object1 = Optional.empty();

        private Optional<EntityId> object2 = Optional.empty();

        @Override
        public void newCollision(CollisionResult r) {
            this.object1 = Optional.of(r.object1);
            this.object2 = Optional.of(r.object2);
        }

        @Override
        public void lostCollision(CollisionResult r) {
            this.object1 = Optional.empty();
            this.object2 = Optional.empty();
        }
    }
}
