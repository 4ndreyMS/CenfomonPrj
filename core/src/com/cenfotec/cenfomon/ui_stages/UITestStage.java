package com.cenfotec.cenfomon.ui_stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class UITestStage extends UIStageBase {
    private Skin skin;
    private int count = 0;

    Label testLabel;
    TextField nameField;
    Table mainContainer;

    public UITestStage(Viewport p_viewport, SpriteBatch p_batch) {
        super(p_viewport, p_batch);
    }

    @Override
    public void defineActors() {
        skin = new Skin(Gdx.files.internal("UISkin/uiskin.json"));

        mainContainer = new Table();
        mainContainer.setFillParent(true);

        Label titleLabel = new Label("UI TEST SCREEN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nameField = new TextField("Placeholder", skin);

        testLabel = new Label("Score test: 0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        mainContainer.add(titleLabel);
        mainContainer.row();
        mainContainer.add(nameField);
        mainContainer.row();
        mainContainer.add(testLabel);
        mainContainer.row();

        TextButton button = new TextButton("Test", skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nameField.setDisabled(false);
            }
        });

        mainContainer.add(button).width(200).center();

        addActor(mainContainer);

        //Debug lines
        mainContainer.debug();
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            count++;
            testLabel.setText("Score test: " + count);
            nameField.setDisabled(true);
            mainContainer.addAction(Actions.fadeOut(2));
        }
    }
}
