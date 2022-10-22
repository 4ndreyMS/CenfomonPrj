package com.cenfotec.cenfomon.core.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.ScreenObject;
import com.cenfotec.cenfomon.core.game.TileMapScreen;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.game_elements.interactables.HighGrass;
import com.cenfotec.cenfomon.game_elements.interactables.ScreenChanger;
import com.cenfotec.cenfomon.game_elements.interactables.DialogueTrigger;

public class B2WorldCreator {
    private int[] STATIC_OBJECT_LAYERS = new int[5];

    public B2WorldCreator(TileMapScreen p_screen) {
        World world = p_screen.getWorld();
        TiledMap map = p_screen.getMap();

        /**se obtienen los layers ground y trees
         * se cargan en el array para poder ser renderizados
         * **/
        int layersGround = map.getLayers().getIndex("ground");
        STATIC_OBJECT_LAYERS [0]= layersGround;

        int layersPipes = map.getLayers().getIndex("trees");
        if (layersPipes != -1) {
            STATIC_OBJECT_LAYERS [1]= layersPipes;
        }

        //Create all collisions for static object layers
        for (int i = 0; i < STATIC_OBJECT_LAYERS.length; i++) {
            GenerateStaticObjects(world, map, STATIC_OBJECT_LAYERS[i]);
        }

        /**carga layer interactables**/
        for (MapObject object : map.getLayers().get("interactables").getObjects().getByType(RectangleMapObject.class)) {
            p_screen.createObject(generateInteractable(world, object));
        }
    }

    private ScreenObject generateInteractable(World p_world, MapObject p_object) {
        String type = p_object.getProperties().get("interactable_type").toString();

        if (type.equals("DIALOGUE")) {
            return new DialogueTrigger(p_world, p_object);
        }
        if (type.equals("PORTAL")) {
            return new ScreenChanger(p_world, p_object);
        }
        if (type.equals("HIGH_GRASS")) {
            return new HighGrass(p_world, p_object);
        }

        return null;
    }

    private void GenerateStaticObjects(World p_world, TiledMap p_map, int p_layerIndex) {
        short collisionBit = getCollisionBitFromLayer(p_layerIndex);

        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : p_map.getLayers().get(p_layerIndex).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();  //Get rectangle data

            bDef.type = BodyDef.BodyType.StaticBody;
            //Position
            float xPos = (rect.getX() + rect.getWidth() / 2) / GameInstance.PIX_PER_MTR;
            float yPos = (rect.getY() + rect.getHeight() / 2) / GameInstance.PIX_PER_MTR;
            bDef.position.set(xPos, yPos);

            //Add body to world
            body = p_world.createBody(bDef);

            //Body shape properties definition
            shape.setAsBox((rect.getWidth() / 2) / GameInstance.PIX_PER_MTR, (rect.getHeight() / 2) / GameInstance.PIX_PER_MTR);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = collisionBit;

            body.createFixture(fixtureDef);
        }
    }

    private short getCollisionBitFromLayer(int p_layerIndex) {
        return PhysicsLayers.DEFAULT_BIT;
    }
}
