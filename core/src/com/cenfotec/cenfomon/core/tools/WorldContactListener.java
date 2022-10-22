package com.cenfotec.cenfomon.core.tools;

import com.badlogic.gdx.physics.box2d.*;
import com.cenfotec.cenfomon.core.interfaces.ICollisionable;

public class WorldContactListener implements ContactListener {
    //Gets called when 2 fixtures collide
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() != null) {
            if (ICollisionable.class.isAssignableFrom(fixA.getUserData().getClass())) {
                ((ICollisionable) fixA.getUserData()).onCollisionEnter(fixB);
            }
        }

        if (fixB.getUserData() != null) {
            if (ICollisionable.class.isAssignableFrom(fixB.getUserData().getClass())) {
                ((ICollisionable) fixB.getUserData()).onCollisionEnter(fixA);
            }
        }
    }

    //Gets called when 2 fixtures stop colliding
    @Override
    public void endContact(Contact contact) {
        //Gdx.app.log("End contact", "");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() != null) {
            if (ICollisionable.class.isAssignableFrom(fixA.getUserData().getClass())) {
                ((ICollisionable) fixA.getUserData()).onCollisionExit(fixB);
            }
        }

        if (fixB.getUserData() != null) {
            if (ICollisionable.class.isAssignableFrom(fixB.getUserData().getClass())) {
                ((ICollisionable) fixB.getUserData()).onCollisionExit(fixA);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
