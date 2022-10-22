package com.cenfotec.cenfomon.ui_stages.battles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;
import com.cenfotec.cenfomon.ui_stages.inventory.ItemsStage;

public class PlayerTurnStage extends UIStageBase {
    private BattleManager _battleManager;

    private Skin skin;
    private BitmapFont _defaultFont;

    private Label _timerLabel;

    public Table _mainContainer;

    public PlayerTurnStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager) {
        super(p_viewport, p_batch);
        this._battleManager = p_battleManager;
    }

    public void onDoAttackPressed() {
        //Show attacks menu
        UIManager.getInstance().setActiveStage(new PlayerAttacksStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this._battleManager), false);
    }

    public void onChangeCenfomonPressed() {
        //Show cenfomons menu
        UIManager.getInstance().setActiveStage(new PlayerCenfomonsStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this._battleManager, this._battleManager.getActivePlayer()), false);
    }

    public void onUseItemPressed() {
        //Show inventory menu
        UIManager.getInstance().setActiveStage(new ItemsStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this._battleManager, this._battleManager.getActivePlayer()), false);
    }

    public void onFleePressed() {
        //Try to escape
        _battleManager.tryFlee();
    }


    @Override
    public void defineActors() {
        skin = new Skin(Gdx.files.internal("UISkin/uiskin.json"));
        _defaultFont = new BitmapFont();

        _mainContainer = new Table();
        _mainContainer.bottom();
        _mainContainer.setFillParent(true);
        //_mainContainer.debug();

        Label timerTitleLabel = new Label("Tiempo restante: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        _timerLabel = new Label(String.format("%03d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table timerTable = new Table();
        timerTable.add(timerTitleLabel);
        timerTable.add(_timerLabel).align(-1);

        Table btnsTable = new Table();
        TextButton atkButton = new TextButton("Atacar", skin);
        atkButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onDoAttackPressed();
            }
        });

        TextButton changeCenfomonButton = new TextButton("Cenfomon", skin);
        changeCenfomonButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onChangeCenfomonPressed();
            }
        });

        TextButton useItemButton = new TextButton("Usar objeto", skin);
        useItemButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onUseItemPressed();
            }
        });

        TextButton fleeButton = new TextButton("Huir", skin);
        fleeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onFleePressed();
            }
        });

        btnsTable.add(atkButton).expandX().width(110);
        btnsTable.add(changeCenfomonButton).expandX().width(110);
        btnsTable.add(useItemButton).expandX().width(110);
        btnsTable.add(fleeButton).expandX().width(110);

        _mainContainer.add(timerTable).left().top().expandY().padTop(60);
        _mainContainer.row();
        _mainContainer.add(btnsTable).expandX().fillX();

        this.addActor(_mainContainer);
    }

    public void updateTimer(float p_time) {
        _timerLabel.setText(String.format("%02d", (int) p_time));
    }

    @Override
    public void update(float delta) {

    }
}
