package com.cenfotec.cenfomon.game_elements.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.game_elements.Player;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleData;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleSystem;
import com.cenfotec.cenfomon.game_elements.battle_system.templates.BattlePlayerTemplates;
import com.cenfotec.cenfomon.game_elements.interactables.base.Interactablebase;
import com.cenfotec.cenfomon.managers.ScreensManager;

public class HighGrass extends Interactablebase {
    private Player _playerRef;
    private boolean _playerInside;

    private float _startBattleTime = 2.0f;
    private float _curBattleTime = 0.0f;

    private String _possibleCenfomonsId;
    private int _battleProbability = 5;

    public HighGrass(World world, MapObject object) {
        super(world, object);
        _possibleCenfomonsId = tileProperties.get("cenfomon_template").toString();
        _playerRef = null;
        _playerInside = false;
    }

    private void tryStartBattle() {
        _curBattleTime = 0.0f;
        boolean battle= true;

        if (battle) {
            BattleData battleData = new BattleData(BattlePlayerTemplates.createBattlePlayer(_possibleCenfomonsId));
            battleData.setIsWildCenfomon(true);

            BattleSystem.setupBattle(battleData);
            ScreensManager.instance.changeScreen(ScreensManager.ScreensEnum.BATTLE_SCREEN, "", false);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float delta) {
        _curBattleTime += delta;

        if (this._playerInside) {
            interact();
            tryStartBattle();
        }


        if (_curBattleTime >= _startBattleTime) {
            if (this._playerInside) {
                if (_playerRef.getCurState() == Player.States.WALK) {
                    //
                }
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollisionEnter(Fixture p_fixture) {
        if (p_fixture.getFilterData().categoryBits == PhysicsLayers.PLAYER_BIT) {
            _curBattleTime = 0.0f;
            this._playerInside = true;
            this._playerRef = (Player) p_fixture.getUserData();
        }
    }

    @Override
    public void onCollisionExit(Fixture p_fixture) {
        if (p_fixture.getFilterData().categoryBits == PhysicsLayers.PLAYER_BIT) {
            this._playerInside = false;
            this._playerRef = null;
        }
    }

    @Override
    public void interact() {
        if (_possibleCenfomonsId.equals("osotias")) {
            System.out.println("cenfcomon osotias");
        }

    }
}
