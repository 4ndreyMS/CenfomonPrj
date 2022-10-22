package com.cenfotec.cenfomon.game_elements.battle_system;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.cenfotec.cenfomon.game_elements.battle_system.enums.BattleStates;
import com.cenfotec.cenfomon.game_elements.battle_system.scene.BattleScreenCenfomon;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.managers.ScreensManager;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.battles.BattleDialogueStage;
import com.cenfotec.cenfomon.ui_stages.battles.BattleStatsStage;
import com.cenfotec.cenfomon.ui_stages.battles.PlayerCenfomonsStage;
import com.cenfotec.cenfomon.ui_stages.battles.PlayerTurnStage;

public class BattleManager {
    //Variables
    private BattleSystem _battleSystem;

    private BattleStatsStage _statsStage;
    private PlayerTurnStage _playerTurnStage;

    private BattleStates _curState;
    private float _stateTime = 0.0f;
    private boolean _stateChanged = false;
    private boolean _battleStarted = false;

    //Dialogues
    private float _lastDialogueTime = 0.0f;
    private BattleStates _nextStateOnDialogueEnd = null;

    //Cenfomon change
    private boolean _displayedCenfomonChange = false;

    //Scene references
    private BattleScreenCenfomon _p1Cenfomon;
    private BattleScreenCenfomon _p2Cenfomon;

    //Methods
    public BattleManager(BattleSystem p_battleSystem) {
        this._battleSystem = p_battleSystem;

        _statsStage = new BattleStatsStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this);
        UIManager.getInstance().setActiveStage(_statsStage, true);
    }

    public void startBattle(BattleScreenCenfomon p_p1Cenfomon, BattleScreenCenfomon p_p2Cenfomon) {
        _p1Cenfomon = p_p1Cenfomon;
        _p2Cenfomon = p_p2Cenfomon;
        changeState(BattleStates.START);
    }
    public void update(float delta) {
        updateState(delta);
    }

    private void updateState(float delta) {
        switch (_curState) {
            case START:
                //Battle start behavior
                onBattleStart(delta);
                break;
            case PLAYER_TURN_START:
                //Show "Player X turn"
                onPlayerTurnStart(delta);
                break;
            case WAITING_FOR_PLAYER:
                //Wait for player input
                onWaitingForPlayerInput(delta);
                break;
            case WAITING_FOR_CENFOMON_CHANGE:
                //Wait for cenfomon change
                onWaitingForPlayerCenfomonChange(delta);
                break;
            case PLAYER_TURN_END:
                //Show player turn effects
                onPlayerTurnEnd(delta);
                break;
            case BATTLE_END:
                //End battle
                onBattleEnd(delta);
                break;
            case SHOWING_DIALOGUE:
                //Dialogues
                onShowingDialogue(delta);
                break;
        }

        if (_stateChanged) {
            _stateChanged = false;
        } else {
            _stateTime += delta;
        }
    }

    public void doAttack(Attack p_attack) {
        int attacker = _battleSystem.doAttack(p_attack);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                showAttackAnimation(attacker);
            }
        }, 1.0f);
        showDialogue(_battleSystem.getActivePlayerCenfomon().getNickname() + " usa " + p_attack.getName(), 1.0f, BattleStates.PLAYER_TURN_END);
    }
    public void useItem(UsableItem p_item, BattleCenfomon p_cenfomon) {
        if (_battleSystem.useItem(p_item, p_cenfomon)) {
            showDialogue("Has usado " + p_item.getItem().getName() + " en " + p_cenfomon.getName(), 2.0f, BattleStates.PLAYER_TURN_END);

        } else {
            showDialogue("No puedes usar este item", 2.0f);
        }
    }
    public void changeCenfomon(BattlePlayer p_player, BattleCenfomon p_newCenfomon) {
        _battleSystem.changeCenfomon(p_player, p_newCenfomon);
        showDialogue(p_player._name + " elige a " + p_newCenfomon.getNickname(), 2.0f, BattleStates.PLAYER_TURN_END);
        updateStats();
    }
    public void tryFlee() {
        if (_battleSystem.getBattleData().isWildCenfomon()) {
            //TODO: determine if can escape or not based on probabilities
            showDialogue("Has escapado");
            changeState(BattleStates.BATTLE_END);
        } else {
            showDialogue("No puedes escapar contra un entrenador Cenfomon", 2.0f);
        }
    }

    public void showAttackAnimation(int attacker) {
        if (attacker == 1) {
            _p1Cenfomon.changeState(BattleScreenCenfomon.States.ATTACK);
            _p2Cenfomon.changeState(BattleScreenCenfomon.States.HURT);
        } else if (attacker == 2) {
            _p1Cenfomon.changeState(BattleScreenCenfomon.States.HURT);
            _p2Cenfomon.changeState(BattleScreenCenfomon.States.ATTACK);
        }
    }

    //Gets
    public BattlePlayer getActivePlayer() {
        return _battleSystem.getActivePlayer();
    }
    public BattleCenfomon getActivePlayerCenfomon() {
        return _battleSystem.getP1Cenfomon();
    }
    public BattleCenfomon getP1Cenfomon() {
        return _battleSystem.getP1Cenfomon();
    }
    public BattleCenfomon getP2Cenfomon() {
        return _battleSystem.getP2Cenfomon();
    }
    public boolean isCenfomonWeakened() { return _battleSystem.isCenfomonWeakenedByAttack(); }

    public BattleScreenCenfomon getNonActivePlayerScreenCenfomon() {
        if (_battleSystem.getActivePlayer().equals(_battleSystem.getBattleData().getPlayer1())) {
            return _p2Cenfomon;
        } else {
            return _p1Cenfomon;
        }
    }

    //Battle States
    private void onBattleStart(float delta) {
        if (!_battleStarted) {
            _battleSystem.startBattle();
            _battleStarted = true;
            updateStats();
            showDialogue( "Comenzando batalla", 1.0f, BattleStates.PLAYER_TURN_START);
        }
    }
    private void onPlayerTurnStart(float delta) {
        clearUI();
        if (_stateTime == 0.0f) {
            if (!_battleSystem.getActivePlayer()._isCPU) { //Real player
                showPlayerOptions();
                changeState(BattleStates.WAITING_FOR_PLAYER);
            } else {    //CPU player
                _battleSystem.getActivePlayer().startTurn(this);
            }
        }
    }
    private void onWaitingForPlayerInput(float delta) {
        if (_stateTime >= _battleSystem.TURN_TIME) {
            clearUI();
            changeState(BattleStates.PLAYER_TURN_END);
            return;
        } else {
            if (this._playerTurnStage != null) {
                this._playerTurnStage.updateTimer(_battleSystem.TURN_TIME - _stateTime);
            }
        }
    }
    private void onWaitingForPlayerCenfomonChange(float delta) {
        if (_stateTime == 0.0f) {
            getNonActivePlayerScreenCenfomon().setVisible(false);
        }

        if (_battleSystem.getNonActivePlayer()._isCPU) {
            if (MathUtils.floor(_stateTime) == 2) {
                _battleSystem.getNonActivePlayer().changeCenfomon(this);
            }
        } else {
            if (MathUtils.floor(_stateTime) == 2) {
                //Display cenfomon change stage if non active player is not CPU
                clearUI();
                showPlayerCenfomons(_battleSystem.getNonActivePlayer());
            }
        }
    }
    private void onPlayerTurnEnd(float delta) {
        if (_stateTime == 0.0f) {
            updateStats();
        }

        if (MathUtils.floor(_stateTime) == 1) {
            if (_battleSystem.isCenfomonWeakenedByAttack() && !_battleSystem.isCenfomonChanged()) {
                clearUI();
                showDialogue(_battleSystem.getNonActivePlayerCenfomon().getNickname() + " se ha debilitado");

                //Check if player has available cenfomons
                if (_battleSystem.getNonActivePlayer().getAvailableCenfomonCount() > 0) {
                    changeState(BattleStates.WAITING_FOR_CENFOMON_CHANGE);
                    return;
                } else {
                    changeState(BattleStates.BATTLE_END);
                    return;
                }
            }
        }

        //Normal turn end
        if (MathUtils.floor(_stateTime) == 1) {
            _battleSystem.endTurn();
            changeState(BattleStates.PLAYER_TURN_START);
        }
    }
    private void onBattleEnd(float delta) {
        if (MathUtils.floor(_stateTime) == 2) {
            clearUI();
            showDialogue("La batalla ha terminado");
        }

        if (MathUtils.floor(_stateTime) == 3) {
            ScreensManager.instance.loadLastScreen();
        }
    }
    private void onShowingDialogue(float delta) {
        if (_stateTime >= _lastDialogueTime) {
            clearUI();
            changeState(_nextStateOnDialogueEnd);
        }
    }
    private void changeState(BattleStates p_state) {
        _curState = p_state;
        _stateChanged = true;
        _stateTime = 0.0f;
    }

    //UI related
    private void updateStats() {
        this._p1Cenfomon.setSprite(getP1Cenfomon().getRegionName());
        this._p1Cenfomon.setVisible(true);
        this._p2Cenfomon.setSprite(getP2Cenfomon().getRegionName());
        this._p2Cenfomon.setVisible(true);
        this._statsStage.updateStats();
    }
    private void showDialogue(String p_dialogue) {
        clearUI();
        BattleDialogueStage dialogueStage = new BattleDialogueStage(UIManager.getInstance().viewport, UIManager.getInstance().batch);
        dialogueStage.showDialogue(p_dialogue);
        UIManager.getInstance().setActiveStage(dialogueStage, false);
    }
    private void showDialogue(String p_dialogue, float p_dialogueTime, BattleStates p_nextState) {
        this._lastDialogueTime = p_dialogueTime;
        this._nextStateOnDialogueEnd = p_nextState;

        clearUI();
        BattleDialogueStage dialogueStage = new BattleDialogueStage(UIManager.getInstance().viewport, UIManager.getInstance().batch);
        dialogueStage.showDialogue(p_dialogue);
        UIManager.getInstance().setActiveStage(dialogueStage, false);
        changeState(BattleStates.SHOWING_DIALOGUE);
    }
    public void showDialogue(String p_dialogue, float p_dialogueTime) {
        BattleDialogueStage dialogueStage = new BattleDialogueStage(UIManager.getInstance().viewport, UIManager.getInstance().batch);
        dialogueStage.showDialogue(p_dialogue);
        UIManager.getInstance().setActiveStage(dialogueStage, false);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                UIManager.getInstance().setPreviousStageAsActive();
            }
        }, p_dialogueTime);
    }

    private void showPlayerOptions() {
        clearUI();
        this._playerTurnStage = new PlayerTurnStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this);
        UIManager.getInstance().setActiveStage(this._playerTurnStage, false);
    }
    private void showPlayerCenfomons(BattlePlayer p_player) {
        UIManager.getInstance().setActiveStage(new PlayerCenfomonsStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this, p_player), false);
    }
    private void clearUI() {
        UIManager.getInstance().setActiveStage(this._statsStage, true);
    }
}
