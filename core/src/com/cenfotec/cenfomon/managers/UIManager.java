package com.cenfotec.cenfomon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.ui_stages.*;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class UIManager implements Disposable {
    public enum StagesEnum {
        IN_GAME,
        MAIN_MENU,
        GAME_OVER,
        UI_TEST,
        DIALOGUE,
        QUESTIONDIALOGUE,
    }

    private Array<UIStageBase> stages;
    private UIStageBase activeStage;
    public OrthographicCamera camera;
    public Viewport viewport;
    public SpriteBatch batch;

    private static UIManager instance;
    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager(GameInstance.batch);
        }

        return instance;
    }

    public UIManager(SpriteBatch p_batch) {
        instance = this;
        this.stages = new Array<>();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(GameInstance.V_WIDTH, GameInstance.V_HEIGHT, this.camera);
        this.batch = p_batch;
    }

    public void update(float delta) {
        if (stages.size > 0) {
            for (int i = 0; i < stages.size; i++) {
                stages.get(i).update(delta);
            }
        }

    }

    public void render(float delta) {
        if (stages.size > 0) {
            for (int i = 0; i < stages.size; i++) {
                stages.get(i).draw();
            }
        }
    }

    public void setActiveStage(StagesEnum p_newStage) {
        switch (p_newStage) {
            case IN_GAME:
                activeStage = new InGameStage(this.viewport, this.batch);
                break;
            case MAIN_MENU:
                break;
            case GAME_OVER:
                activeStage = new GameOverStage(this.viewport, this.batch);
                break;
            case UI_TEST:
                activeStage = new UITestStage(this.viewport, this.batch);
                break;
            case DIALOGUE:
                activeStage = new DialogueStage(this.viewport, this.batch, GameInstance.boxAtlas);
                break;
            case QUESTIONDIALOGUE:
                activeStage = new QuestionDialogueStage(this.viewport, this.batch, GameInstance.boxAtlas);
                break;
        }

        stages.add(activeStage);
        Gdx.input.setInputProcessor(activeStage);
    }

    public void setActiveStage(StagesEnum p_newStage, boolean p_clearPrevious) {
        if (p_clearPrevious) {
            stages.clear();
        }

        setActiveStage(p_newStage);
    }

    public void setActiveStage(UIStageBase p_newStage, boolean p_clearPrevious) {
        if (p_clearPrevious) {
            stages.clear();
        }

        activeStage = p_newStage;
        stages.add(activeStage);
        Gdx.input.setInputProcessor(activeStage);
    }
    public void setPreviousStageAsActive() {
        if (stages.size > 1) {
            stages.removeValue(activeStage, true);
            activeStage = stages.get(stages.size - 1);
            Gdx.input.setInputProcessor(activeStage);
        }
    }

    public UIStageBase getActiveStage() {
        return activeStage;
    }

    @Override
    public void dispose() {
        if (stages.size > 0) {
            for (int i = 0; i < stages.size; i++) {
                stages.get(i).dispose();
            }
        }
    }
}
