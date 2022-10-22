package com.cenfotec.cenfomon.ui_stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class InGameStage extends UIStageBase {
    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Label countdownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public InGameStage(Viewport p_viewport, SpriteBatch p_batch) {
        super(p_viewport, p_batch);
    }

    @Override
    public void defineActors() {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel.setVisible(false);
        worldLabel.setVisible(false);
        //marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Distributes objects using the same space horizontally
        //table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        //table.add(timeLabel).expandX().padTop(10);

        table.row();    //Ends current row

        //table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        table.row();
        this.addActor(table);
    }

    @Override
    public void update(float delta) {
//        timeCount += delta;
//        if (timeCount >= 1) {
//            worldTimer--;
//            countdownLabel.setText(String.format("%03d", worldTimer));
//            timeCount = 0;
//        }
    }

    public void addScore(int p_value) {
        score += p_value;
        scoreLabel.setText(String.format("%06d", score));
    }
}
