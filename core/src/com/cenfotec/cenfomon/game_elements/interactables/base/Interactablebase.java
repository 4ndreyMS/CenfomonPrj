package com.cenfotec.cenfomon.game_elements.interactables.base;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.ScreenObject;
import com.cenfotec.cenfomon.core.interfaces.ICollisionable;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;

public abstract class Interactablebase extends ScreenObject implements ICollisionable {
    protected String uniqueID;
    protected boolean requiresInput;

    protected Rectangle rectangle;
    protected MapProperties tileProperties;
    protected boolean playerInRange;

    protected World world;
    protected Body b2Body;

    public Interactablebase(World world, MapObject object) {
        this.world = world;
        this.rectangle = ((RectangleMapObject) object).getRectangle();
        this.tileProperties = object.getProperties();
        this.uniqueID = tileProperties.get("unique_id").toString();
        this.requiresInput = (boolean) tileProperties.get("requires_input");
        this.playerInRange = false;

        defineInteractable();
    }

    protected void defineInteractable() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(new Vector2(rectangle.x, rectangle.y).scl(1 / GameInstance.PIX_PER_MTR));

        this.b2Body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[] {
                new Vector2(0, 0).scl(1 / GameInstance.PIX_PER_MTR),
                new Vector2(rectangle.width, 0).scl(1 / GameInstance.PIX_PER_MTR),
                new Vector2(0, rectangle.height).scl(1 / GameInstance.PIX_PER_MTR),
                new Vector2(rectangle.width, rectangle.height).scl(1 / GameInstance.PIX_PER_MTR)
        };
        shape.set(vertices);

        fDef.shape = shape;
        fDef.isSensor = true;
        fDef.filter.categoryBits = PhysicsLayers.INTERACTABLE_BIT;
        fDef.filter.maskBits = PhysicsLayers.PLAYER_BIT;

        this.b2Body.createFixture(fDef).setUserData(this);
    }

    public abstract void interact();

    @Override
    public void setPosition(Vector2 position) {
        b2Body.setTransform(position, 0);
    }

    @Override
    public Vector2 getPosition() {
        return b2Body.getPosition();
    }
}
