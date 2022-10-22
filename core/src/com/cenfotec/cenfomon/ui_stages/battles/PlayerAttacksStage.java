package com.cenfotec.cenfomon.ui_stages.battles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class PlayerAttacksStage extends UIStageBase {
    private Skin skin;
    private BitmapFont _defaultFont;
    private BattleManager _battleManager;

    private BattleCenfomon _activePlayerCenfomon;

    public Table _mainContainer;

    public PlayerAttacksStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager) {
        super(p_viewport, p_batch, false);
        this._battleManager = p_battleManager;
        defineActors();
    }

    public void onAttackPressed(int attackIndex) {
        //Do attack
        if (_activePlayerCenfomon.getAttack(attackIndex) == null) return;
        _battleManager.doAttack(_activePlayerCenfomon.getAttack(attackIndex));
    }

    public void onBackPressed() {
        UIManager.getInstance().setPreviousStageAsActive();
    }

    private String tryGetAttackName(BattleCenfomon p_cenfomon, int p_atkIndex) {
        Attack attack = p_cenfomon.getAttack(p_atkIndex);
        if (attack != null)
            return attack.getName();
        return "-";
    }

    @Override
    public void defineActors() {
        skin = new Skin(Gdx.files.internal("UISkin/uiskin.json"));
        _defaultFont = new BitmapFont();

        _mainContainer = new Table();
        _mainContainer.bottom();
        _mainContainer.setFillParent(true);
        //_mainContainer.debug();

        Table btnsTable = new Table();

        //Attacks initialization
        this._activePlayerCenfomon = this._battleManager.getActivePlayerCenfomon();
        TextButton atkBtn1 = new TextButton(tryGetAttackName(_activePlayerCenfomon, 0), skin);
        atkBtn1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onAttackPressed(0);
            }
        });

        TextButton atkBtn2 = new TextButton(tryGetAttackName(_activePlayerCenfomon, 1), skin);
        atkBtn2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onAttackPressed(1);
            }
        });

        TextButton atkBtn3 = new TextButton(tryGetAttackName(_activePlayerCenfomon, 2), skin);
        atkBtn3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onAttackPressed(2);
            }
        });

        TextButton atkBtn4 = new TextButton(tryGetAttackName(_activePlayerCenfomon, 3), skin);
        atkBtn4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onAttackPressed(3);
            }
        });

        TextButton backBtn = new TextButton("Volver", skin);
        //backBtn.setFillParent(true);
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onBackPressed();
            }
        });

        btnsTable.add(atkBtn1).expandX().width(225);
        btnsTable.add(atkBtn2).expandX().width(225);
        btnsTable.row();
        btnsTable.add(atkBtn3).expandX().width(225);
        btnsTable.add(atkBtn4).expandX().width(225);
        btnsTable.row();
        btnsTable.add(backBtn).colspan(4).expandX().fillX().center();

        _mainContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _mainContainer.setColor(new Color(0, 0, 0,0.85f));

        _mainContainer.add(btnsTable).expandX().fillX();
        this.addActor(_mainContainer);
    }

    @Override
    public void update(float delta) {

    }
}
