package com.cenfotec.cenfomon.core.physics;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.cenfotec.cenfomon.core.interfaces.ICollisionable;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CollisionListenerEmitter implements ICollisionable {
    private ArrayList<Fixture> collisions;

    //Use it connect events to this collisions detector
    public Consumer<Fixture> onCollisionEnterEvent;
    public Consumer<Fixture> onCollisionExitEvent;

    //Constructors
    public CollisionListenerEmitter() {
        this.collisions = new ArrayList<Fixture>();
    }

    public boolean isColliding() {
        return collisions.size() > 0;
    }

    public ArrayList<Fixture> getCollisions() {
        return collisions;
    }
    public Fixture getLastCollision() {
        if (collisions.size() > 0) {
            return collisions.get(collisions.size() - 1);
        }
        return null;
    }
    public Fixture getCollisionByIndex(int p_index) {
        if (p_index < collisions.size()) {
            return collisions.get(p_index);
        }
        return null;
    }

    @Override
    public void onCollisionEnter(Fixture p_fixture) {
        if (!collisions.contains(p_fixture)) {
            collisions.add(p_fixture);
        }

        //Event trigger
        if (onCollisionEnterEvent != null) {
            onCollisionEnterEvent.accept(p_fixture);
        }
    }

    @Override
    public void onCollisionExit(Fixture p_fixture) {
        if (collisions.contains(p_fixture)) {
            collisions.remove(p_fixture);
        }

        //EventTrigger
        if (onCollisionExitEvent != null) {
            onCollisionExitEvent.accept(p_fixture);
        }
    }
}
