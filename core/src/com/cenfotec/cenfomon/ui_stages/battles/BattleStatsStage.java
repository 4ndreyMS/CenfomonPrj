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
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class BattleStatsStage extends UIStageBase {
    private BitmapFont _defaultFont;
    private BattleManager _battleManager;

    public Table _mainContainer;

    public Table _p1StatsTable;
    public Label _p1CenfomonNameTxt;
    public Label _p1CenfomonLevelTxt;
    public Label _p1CenfomonHPTxt;

    public Table _p2StatsTable;
    public Label _p2CenfomonNameTxt;
    public Label _p2CenfomonLevelTxt;
    public Label _p2CenfomonHPTxt;

    public BattleStatsStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager) {
        super(p_viewport, p_batch);
        this._battleManager = p_battleManager;
    }

    public void updateStats() {
        BattleCenfomon p1Cenfomon = this._battleManager.getP1Cenfomon();
        BattleCenfomon p2Cenfomon = this._battleManager.getP2Cenfomon();

        _p1CenfomonNameTxt.setText(p1Cenfomon.getNickname());
        _p1CenfomonHPTxt.setText("PV: " + p1Cenfomon.getHealthPoints() + "/" + p1Cenfomon.getMaxHealthPoints());
        _p1CenfomonLevelTxt.setText("Nvl: " + p1Cenfomon.getLevel());

        _p2CenfomonNameTxt.setText(p2Cenfomon.getNickname());
        _p2CenfomonHPTxt.setText("PV: " + p2Cenfomon.getHealthPoints() + "/" + p2Cenfomon.getMaxHealthPoints());
        _p2CenfomonLevelTxt.setText("Nvl: " + p2Cenfomon.getLevel());
    }

    @Override
    public void defineActors() {
        _defaultFont = new BitmapFont();

        _mainContainer = new Table();
        _mainContainer.setFillParent(true);
        //_mainContainer.debug();

        //P1 stats definition
        _p1StatsTable = new Table();
        _p1StatsTable.setHeight(300);

        _p1StatsTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _p1StatsTable.setColor(Color.LIGHT_GRAY);
        _p1StatsTable.pad(5.0f, 5.0f, 5.0f, 5.0f);
        //_p1StatsTable.debug();
        _p1CenfomonNameTxt = new Label("Name", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _p1CenfomonLevelTxt = new Label("Lvl: 100", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _p1CenfomonHPTxt = new Label("HP: 100/100", new Label.LabelStyle(_defaultFont, Color.WHITE));
        //Integration
        _p1StatsTable.add(_p1CenfomonNameTxt).left().expandX();
        _p1StatsTable.add(_p1CenfomonLevelTxt);
        _p1StatsTable.row();
        _p1StatsTable.add(_p1CenfomonHPTxt).left().expandX();

        //P2 stats definition
        _p2StatsTable = new Table();
        _p2StatsTable.setHeight(300);
        _p2StatsTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _p2StatsTable.setColor(Color.LIGHT_GRAY);
        _p2StatsTable.pad(5.0f, 5.0f, 5.0f, 5.0f);

        _p2CenfomonNameTxt = new Label("Name", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _p2CenfomonLevelTxt = new Label("Lvl: 100", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _p2CenfomonHPTxt = new Label("HP: 100/100", new Label.LabelStyle(_defaultFont, Color.WHITE));
        //Integration
        _p2StatsTable.add(_p2CenfomonNameTxt).left().expandX();
        _p2StatsTable.add(_p2CenfomonLevelTxt);
        _p2StatsTable.row();
        _p2StatsTable.add(_p2CenfomonHPTxt).left().expandX();

        _mainContainer.add(_p1StatsTable).expand(true, true).width(180).left().top();
        _mainContainer.add(_p2StatsTable).expand(true, true).width(180).right().top();

        this.addActor(_mainContainer);
    }

    @Override
    public void update(float delta) {

    }
}
