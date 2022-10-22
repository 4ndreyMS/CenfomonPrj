package com.cenfotec.cenfomon.game_elements.interactables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.game_elements.interactables.base.Interactablebase;
import com.cenfotec.cenfomon.managers.ScreensManager;

public class ScreenChanger extends Interactablebase {
    public ScreenChanger(World world, MapObject object) {
        super(world, object);
    }

    @Override
    public void start() {
        return;
    }

    @Override
    public void update(float delta) {
        return;
    }

    @Override
    public void onDestroy() {
        return;
    }

    @Override
    public void onCollisionEnter(Fixture fixture) {
        if (fixture.getFilterData().categoryBits == PhysicsLayers.PLAYER_BIT) {
            interact();
        }
    }

    @Override
    public void onCollisionExit(Fixture fixture) {
        return;
    }

    @Override
    public void interact() {
        ScreensManager.instance.changeScreen(uniqueID);
    }
}
