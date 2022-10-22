package com.cenfotec.cenfomon.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.BaseScreen;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleData;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleSystem;
import com.cenfotec.cenfomon.game_elements.battle_system.scene.BattleScreenCenfomon;

public class BattleScreen extends BaseScreen {
    private final boolean BATTLE = true;


    private BattleSystem _battleSystem;
    private BattleManager _battleManager;

    private BattleScreenCenfomon _p1Cenfomon;
    private BattleScreenCenfomon _p2Cenfomon;
    private Sprite _background;

    public BattleScreen(GameInstance p_gameInstance) {
        super(p_gameInstance);
        this._battleSystem = new BattleSystem();
        this._battleManager = new BattleManager(this._battleSystem);

        if (BATTLE) {
            BattleSystem.setupBattle(new BattleData());
        }

        _background = new Sprite();
        _background.setRegion(new Texture("Sprites/World/BattleBackground.png"));
        _background.setBounds(0, 0, 640 / GameInstance.PIX_PER_MTR, 480 / GameInstance.PIX_PER_MTR);
        _background.setPosition(-320 / GameInstance.PIX_PER_MTR, -240 / GameInstance.PIX_PER_MTR);

        _p1Cenfomon = new BattleScreenCenfomon();
        _p2Cenfomon = new BattleScreenCenfomon();
        _p1Cenfomon.setStartPos(new Vector2(-160 / GameInstance.PIX_PER_MTR, -50 / GameInstance.PIX_PER_MTR));
        _p2Cenfomon.setStartPos(new Vector2(100 / GameInstance.PIX_PER_MTR, -50 / GameInstance.PIX_PER_MTR));
        _p2Cenfomon.setFlip(true, false);
        gameCamera.setPosition(new Vector2(0, 0));
        createObject(_p1Cenfomon);
        createObject(_p2Cenfomon);

        this._battleManager.startBattle(_p1Cenfomon, _p2Cenfomon);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        _battleManager.update(delta);
    }

    @Override
    protected void customRender(float delta) {
        super.customRender(delta);
        _background.draw(GameInstance.batch);
    }
}
