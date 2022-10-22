package com.cenfotec.cenfomon.core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.interfaces.IRenderizable;
import com.cenfotec.cenfomon.managers.UIManager;

import java.util.Iterator;

public class BaseScreen implements Screen {
    //Game elements
    protected GameInstance gameInstance;
    protected ScreenCamera gameCamera;
    protected Viewport gameViewport;
    protected UIManager uiManager;

    protected Array<IRenderizable> renderizables = new Array<IRenderizable>();
    protected Array<ScreenObject> screenObjects = new Array<ScreenObject>();

    public BaseScreen (GameInstance p_gameInstance) {
        this.gameInstance = p_gameInstance;
        this.gameCamera = new ScreenCamera(null);

        //Viewport to keep game aspect
        this.gameViewport = new FitViewport(GameInstance.V_WIDTH / GameInstance.PIX_PER_MTR, GameInstance.V_HEIGHT / GameInstance.PIX_PER_MTR, GameInstance.mainCamera);

        //Creates HUD layer for all UI related stuff
        this.uiManager = UIManager.getInstance();

        //Add camera to updatables
        createObject(gameCamera);
    }

    public void createObject(ScreenObject p_object) {
        if (p_object == null) return;

        screenObjects.add(p_object);
        if (IRenderizable.class.isAssignableFrom(p_object.getClass())) {
            renderizables.add((IRenderizable) p_object);
        }
        p_object.start();
    }

    public void update(float delta) {
        handleInput(delta);

        //GameObjects update
        for (Iterator<ScreenObject> iter = screenObjects.iterator(); iter.hasNext();) {
            ScreenObject curObject = iter.next();
            curObject.update(delta);

            if (curObject.setToDestroy) {
                //Remove deleted object from renderizables array
                if (IRenderizable.class.isAssignableFrom(curObject.getClass())) {
                    renderizables.removeValue(((IRenderizable) curObject), true);
                }

                curObject.onDestroy();
                iter.remove();
            }
        }

        uiManager.update(delta);
    }

    public void handleInput(float delta) {
        //Pause and general game flow input stuff
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear previous frame
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Custom objects render
        gameInstance.batch.setProjectionMatrix(GameInstance.mainCamera.combined);
        gameInstance.batch.begin();
        //Used in subclases to render extra elements
        customRender(delta);
        for (int i = 0; i < renderizables.size; i++) {
            renderizables.get(i).render(gameInstance.batch);
        }
        gameInstance.batch.end();

        //Draw ui layer
        gameInstance.batch.setProjectionMatrix(uiManager.camera.combined);
        uiManager.render(delta);
    }

    protected void customRender(float delta) {
    }

    @Override
    public void resize(int p_width, int p_height) {
        gameViewport.update(p_width, p_height);
        uiManager.viewport.update(p_width, p_height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        uiManager.dispose();
    }
}
