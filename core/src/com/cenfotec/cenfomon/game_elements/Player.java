package com.cenfotec.cenfomon.game_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.AnimatedSprite;
import com.cenfotec.cenfomon.core.game.ScreenObject;
import com.cenfotec.cenfomon.core.interfaces.IRenderizable;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.screens.GameLevelScreen;

public class Player extends ScreenObject implements IRenderizable {
    public enum States {
        IDLE,
        WALK
    }

    private final float MOV_SPEED = 3.0f;

    public Player.States curState;
    private float stateTimer;

    public World world;
    public Body b2body;

    private TextureRegion upRegion;
    private TextureRegion downRegion;
    private TextureRegion leftRegion;
    private TextureRegion rightRegion;

    private Sprite sprite;
    private AnimatedSprite animator;

    private Vector2 lastDir;
    private Vector2 movVec;

    private int columns = 1;
    private int rows = 1;

    public float getStateTimer() {
        return stateTimer;
    }
    public Vector2 getLastDir() {
        return this.lastDir;
    }
    public States getCurState() {
        return this.curState;
    }

    public Player(GameLevelScreen screen) {
        this.world = screen.getWorld();

        //States
        curState = States.IDLE;
        lastDir = new Vector2(0, -1);
        movVec = new Vector2(0, 0);

        //Sprite Regions
        upRegion = GameInstance.charactersAtlas.findRegion("FemalePlayerWalkUp");
        downRegion = GameInstance.charactersAtlas.findRegion("FemalePlayerWalkDown");
        leftRegion = GameInstance.charactersAtlas.findRegion("FemalePlayerWalkLeft");
        rightRegion = GameInstance.charactersAtlas.findRegion("FemalePlayerWalkRight");

        //Animations
        sprite = new Sprite();
        animator = new AnimatedSprite(sprite, columns, rows);
        animator.createAnimation("Walk_Up", upRegion, true, 32, 32, new int[]{1, 0, 2, 0}, 0.15f);
        animator.createAnimation("Walk_Down", downRegion, true, 32, 32, new int[]{1, 0, 2, 0}, 0.15f);
        animator.createAnimation("Walk_Left", leftRegion, true, 32, 32, new int[]{1, 0, 2, 0}, 0.15f);
        animator.createAnimation("Walk_Right", rightRegion, true, 32, 32, new int[]{1, 0, 2, 0}, 0.15f);

        animator.createAnimation("Idle_Up", upRegion, true, 32, 32, 0, 1, 0.2f);
        animator.createAnimation("Idle_Down", downRegion, true, 32, 32, 0, 1, 0.2f);
        animator.createAnimation("Idle_Left", leftRegion, true, 32, 32, 0, 1, 0.2f);
        animator.createAnimation("Idle_Right", rightRegion, true, 32, 32, 0, 1, 0.2f);

        //Define sprite bounds
        sprite.setBounds(0, 0, 32 / GameInstance.PIX_PER_MTR, 32 / GameInstance.PIX_PER_MTR);
    }

    @Override
    public void start() {
        addTag("Player");
        definePlayer();

        GameInstance.playerRef = this;
        animator.play("Idle_Down");
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        handleState(delta);
        animator.update(delta);
        sprite.setPosition(b2body.getPosition().x - sprite.getWidth() / 2, b2body.getPosition().y - (16 / GameInstance.PIX_PER_MTR) / 2);
    }

    private void handleState(float delta) {
        switch (curState) {
            case IDLE:
                onIdle(delta);
                break;
            case WALK:
                onWalk(delta);
                break;
        }

        //Apply movement
        b2body.setGravityScale(0.0f);
        b2body.setLinearVelocity(new Vector2(movVec.x * MOV_SPEED, movVec.y * MOV_SPEED));
    }

    public void changeState(States p_newState) {
        this.curState = p_newState;
        stateTimer = 0.0f;
    }

    private void onIdle(float delta) {
        if (movVec.x != 0 || movVec.y != 0) {
            changeState(States.WALK);
            return;
        }

        //Animations
        if (lastDir.y == 1) {
            animator.play("Idle_Up");
        }
        else if (lastDir.y == -1) {
            animator.play("Idle_Down");
        }
        else if (lastDir.x == -1) {
            animator.play("Idle_Left");
        }
        else if (lastDir.x == 1) {
            animator.play("Idle_Right");
        }
    }

    private void onWalk(float delta) {
        if (movVec.x == 0 && movVec.y == 0) {
            changeState(States.IDLE);
            return;
        }

        lastDir = new Vector2(movVec.x, movVec.y);

        //Animations
        if (lastDir.y == 1) {
            animator.play("Walk_Up");
        }
        if (lastDir.y == -1) {
            animator.play("Walk_Down");
        }
        if (lastDir.x == -1) {
            animator.play("Walk_Left");
        }
        if (lastDir.x == 1) {
            animator.play("Walk_Right");
        }
    }

    private static boolean canMove = true;

    /**metoth that make the player static
     *
     * delays the movement for 1 second
     *
     *neeeds the handleInput metoth to work
     * **/
    public void makeStaticPlayer1S(){
        canMove = false;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canMove = true;
            }
        }, 1f);

    }
    /**metoth that make the player static
     *
     * delays the movement for 1.5 seconds
     *
     *neeeds the handleInput metoth to work
     * **/
    public void makeStaticPlayer2S(){
        canMove = false;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canMove = true;
            }
        }, 1.5f);

    }

    public void handleInput(float delta) {

        if (!canMove){
            movVec.set(0,0);
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movVec.set(-1, 0);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movVec.set(1, 0);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            movVec.set(0, 1);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            movVec.set(0, -1);
        }
        else {
            movVec.set(0, 0);
        }
    }

    public void definePlayer() {
        Vector2 currentPosition = GameInstance.playerData.getLastPosition();
        lastDir = GameInstance.playerData.getLastDirVec();

        BodyDef bDef = new BodyDef();
        bDef.position.set(currentPosition);
        bDef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / GameInstance.PIX_PER_MTR);
        fDef.filter.categoryBits = PhysicsLayers.PLAYER_BIT;
        fDef.filter.maskBits = PhysicsLayers.DEFAULT_BIT |
                PhysicsLayers.BLOCK_BIT |
                PhysicsLayers.ENEMY_BIT |
                PhysicsLayers.ENEMY_HEAD_BIT |
                PhysicsLayers.ITEM_BIT |
                PhysicsLayers.INTERACTABLE_BIT;

        fDef.shape = shape;

        b2body.createFixture(fDef).setUserData(this);
    }

    @Override
    public void onDestroy() {
        return;
    }

    @Override
    public void setPosition(Vector2 position) {
        b2body.setTransform(position, 0);
    }

    @Override
    public Vector2 getPosition() {
        return b2body.getPosition();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}

