package com.cenfotec.cenfomon.core.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.GameInstance;

public class ScreenCamera extends ScreenObject {
    public ScreenObject target;
    public OrthographicCamera camera;

    public boolean followX = true;
    public boolean followY = true;

    public float movSpeed = 5;
    public boolean smoothMovement = true;

    public ScreenCamera(ScreenObject p_target) {
        this.target = p_target;
        this.camera = new OrthographicCamera();
        GameInstance.mainCamera = this.camera;
    }

    @Override
    public void start() {
        if (target != null) {
            setPosition(target.getPosition());
        }
    }

    @Override
    public void update(float delta) {
        if (camera == null || target == null) return;

        if (followX) {
            if (smoothMovement)
                camera.position.x = MathUtils.lerp(camera.position.x, target.getPosition().x, delta * movSpeed);
            else
                camera.position.x = target.getPosition().x;
        }
        if (followY) {
            if (smoothMovement)
                camera.position.y = MathUtils.lerp(camera.position.y, target.getPosition().y, delta * movSpeed);
            else
                camera.position.y = target.getPosition().y;
        }

        camera.update();
    }

    @Override
    public void onDestroy() {
        return;
    }

    @Override
    public void setPosition(Vector2 p_position) {
        camera.position.x = p_position.x;
        camera.position.y = p_position.y;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(camera.position.x, camera.position.y);
    }

    public void setTarget(ScreenObject p_newTarget) {
        this.target = p_newTarget;
    }
}
