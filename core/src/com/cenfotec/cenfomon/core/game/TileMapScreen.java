package com.cenfotec.cenfomon.core.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.core.tools.B2WorldCreator;
import com.cenfotec.cenfomon.core.tools.WorldContactListener;

public class TileMapScreen extends BaseScreen {
    //Tilemap
    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    //Box2d (physics handling)
    protected World world;
    protected Box2DDebugRenderer debugRenderer;

    public TileMapScreen (GameInstance p_gameInstance, String p_levelPath) {
        super(p_gameInstance);
        //World visuals initialization
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(p_levelPath);
        this.renderer = new OrthogonalTiledMapRenderer(map, 1 / GameInstance.PIX_PER_MTR);

        //World physics initialization
        this.world = new World(new Vector2(0, -10), true);
        this.debugRenderer = new Box2DDebugRenderer();
        new B2WorldCreator(this);
        world.setContactListener(new WorldContactListener());
    }

    public World getWorld() { return world; }
    public TiledMap getMap() { return map; }

    @Override
    public void update(float delta) {
        super.update(delta);

        //Physics update
        world.step(1/60f, 6, 2);
        renderer.setView(GameInstance.mainCamera);
    }

    @Override
    protected void customRender(float delta) {
        //render world
        renderer.render();

        if (PhysicsLayers.DEBUG_COLLISIONS) {
            //render debug collisions
            debugRenderer.render(world, GameInstance.mainCamera.combined);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        renderer.dispose();
        debugRenderer.dispose();
    }
}
