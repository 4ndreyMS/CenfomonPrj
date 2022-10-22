package com.cenfotec.cenfomon.game_elements.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class DialogueBox extends Table {

    private Label textLabel;


    public DialogueBox(Skin skin) {
        super(skin);
    }

    public void initDialogueBox(String text) {
        textLabel = new Label(text, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.add(textLabel);
        this.setPosition(50, 30);
    }

    public void setText(String text) {
        this.textLabel.setText(text);
    }

}
