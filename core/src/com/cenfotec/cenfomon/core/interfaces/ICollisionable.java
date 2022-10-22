package com.cenfotec.cenfomon.core.interfaces;

import com.badlogic.gdx.physics.box2d.Fixture;

public interface ICollisionable {
    public void onCollisionEnter(Fixture p_fixture);
    public void onCollisionExit(Fixture p_fixture);
}
