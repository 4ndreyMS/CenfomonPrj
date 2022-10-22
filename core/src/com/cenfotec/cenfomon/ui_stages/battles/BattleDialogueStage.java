package com.cenfotec.cenfomon.ui_stages.battles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.core.tools.TextUtils;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleData;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class BattleDialogueStage extends UIStageBase {
    private BitmapFont _defaultFont;

    public Table _mainContainer;

    public Table _dialogueBackgroundTable;
    private Label _dialogueText;

    public BattleDialogueStage(Viewport p_viewport, SpriteBatch p_batch) {
        super(p_viewport, p_batch);
    }

    public void showDialogue(String p_dialogue) {
        _dialogueText.setText(TextUtils.getWrappedText(p_dialogue, 60));
    }

    @Override
    public void defineActors() {
        _defaultFont = new BitmapFont();

        _mainContainer = new Table();
        _mainContainer.setFillParent(true);
        //_mainContainer.debug();

        //P1 stats definition
        _dialogueBackgroundTable = new Table();

        _dialogueBackgroundTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _dialogueBackgroundTable.setColor(Color.LIGHT_GRAY);
        _dialogueBackgroundTable.pad(5.0f, 5.0f, 5.0f, 5.0f);

        //_p1StatsTable.debug();
        _dialogueText = new Label("Test dialogue", new Label.LabelStyle(_defaultFont, Color.WHITE));
        //Integration
        _dialogueBackgroundTable.add(_dialogueText).center();
        _mainContainer.add(_dialogueBackgroundTable).expand(true, true).fill(true, false).bottom().height(100);

        this.addActor(_mainContainer);
    }

    @Override
    public void update(float delta) {

    }
}
