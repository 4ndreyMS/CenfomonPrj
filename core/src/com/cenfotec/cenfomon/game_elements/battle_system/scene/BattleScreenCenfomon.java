package com.cenfotec.cenfomon.game_elements.battle_system.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.ScreenObject;
import com.cenfotec.cenfomon.core.interfaces.IRenderizable;

public class BattleScreenCenfomon extends ScreenObject implements IRenderizable {

    public enum States {
        IDLE,
        ATTACK,
        HURT,
        APPEAR,
        DISAPPEAR
    }

    private final float SCALE = 2.0f;

    private Sprite _sprite;
    private boolean _flipX;
    private boolean _flipY;
    private boolean _visible;

    private States _curState;
    private float _stateTime;

    private float idleTime = 0.0f;
    private Vector2 _startPos;
    private int dir = 1;

    public BattleScreenCenfomon() {
        //Define sprite bounds
        this._sprite = new Sprite();
        this._sprite.setBounds(0, 0, 32 / GameInstance.PIX_PER_MTR, 32 / GameInstance.PIX_PER_MTR);
        this._sprite.setScale(2.0f, 2.0f);
    }

    public void setSprite(String p_cenfomonName) {
        TextureRegion region = GameInstance.cenfomonsAtlas.findRegion(p_cenfomonName);
        checkFlips(region);
        this._sprite.setRegion(region);
    }
    public void setFlip(boolean p_x, boolean p_y) {
        this._flipX = p_x;
        this._flipY = p_y;
        dir = this._flipX ? -1 : 1;
    }
    public boolean isFlipX()
    {
        return this._flipX;
    }
    public boolean isFlipY()
    {
        return this._flipY;
    }

    public void setVisible(boolean p_visible) { this._visible = p_visible; }
    public boolean isVisible() { return this.visible; }

    public void setStartPos(Vector2 p_pos) {
        _startPos = p_pos;
        setPosition(_startPos);
    }

    //Should the last region flip in any axis?
    public void checkFlips(TextureRegion p_region) {
        if (p_region.isFlipX() != _flipX) {
            p_region.flip(true, false);
        }
        if (p_region.isFlipY() != _flipY) {
            p_region.flip(false, true);
        }
    }

    @Override
    public void start() {
        _startPos = getPosition();
        changeState(States.IDLE);
    }

    @Override
    public void update(float delta) {
        updateState(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            changeState(States.ATTACK);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            changeState(States.HURT);
        }
    }

    public void updateState(float delta) {
        switch (_curState) {
            case IDLE:
                onIdle(delta);
                break;
            case ATTACK:
                onAttack(delta);
                break;
            case HURT:
                onReceiveHit(delta);
                break;
            case APPEAR:
                onAppear(delta);
                break;
            case DISAPPEAR:
                onDisappear(delta);
                break;
        }

        _stateTime += delta;
    }

    public void changeState(States p_state) {
        _curState = p_state;
        _stateTime = 0.0f;
        _sprite.setScale(SCALE, SCALE);
        idleTime = 0.0f;
        _visible = true;
        _sprite.setColor(1, 1, 1, 1);
    }

    private void onIdle(float delta) {
        //Idle animation
        idleTime += delta * 5;
        if (idleTime >= MathUtils.PI2) idleTime = 0.0f;
        _sprite.setScale(SCALE, MathUtils.sin(idleTime) * 0.1f + SCALE);
    }

    private void onAttack(float delta) {
        if (_stateTime < 0.2f) {
            _sprite.setPosition(_sprite.getX() + (20 * dir) * delta, _sprite.getY());
        }
        if (_stateTime >= 0.3f && _stateTime < 0.5f) {
            _sprite.setPosition(_sprite.getX() - (20 * dir) * delta, _sprite.getY());
        }

        if (_stateTime >= 0.5f && _stateTime < 0.7f) {
            setPosition(_startPos);
        }

        if (_stateTime >= 0.7f) {
            changeState(States.IDLE);
        }
    }

    private void onReceiveHit(float delta) {
        //Blink
        if (_stateTime > 0.2f && _stateTime < 0.8f) {
            _visible = MathUtils.cos(_stateTime * 25) > 0;
            _sprite.setColor(1f, 0.3f, 0.3f, 1);
        }
        if (_stateTime >= 0.8f) {
            changeState(States.IDLE);
        }
    }

    private void onAppear(float delta) {

    }

    private void onDisappear(float delta) {

    }
    @Override
    public void onDestroy() {

    }

    @Override
    public void setPosition(Vector2 position) {
        _sprite.setPosition(position.x, position.y);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(_sprite.getX(), _sprite.getY());
    }

    @Override
    public void render(SpriteBatch p_batch) {
        if (!_visible) return;
        _sprite.draw(p_batch);
    }
}
