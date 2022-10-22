package com.cenfotec.cenfomon.ui_stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.managers.ScreensManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class GameOverStage extends UIStageBase {

    public GameOverStage(Viewport p_viewport, SpriteBatch p_batch) {
        super(p_viewport, p_batch);
    }

    @Override
    public void defineActors() {
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();

        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label instructionLabel = new Label("Click to play again", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(instructionLabel).expandX();

        this.addActor(table);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            GameInstance.playerData.setLastPosition(new Vector2(64, 64).scl(1 / GameInstance.PIX_PER_MTR));
            ScreensManager.instance.changeScreen(ScreensManager.ScreensEnum.LEVEL_SCREEN, "Maps/1_1.tmx", false);
        }
    }
}
