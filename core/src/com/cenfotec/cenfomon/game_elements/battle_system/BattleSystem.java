package com.cenfotec.cenfomon.game_elements.battle_system;

import com.cenfotec.cenfomon.game_elements.items.ItemsManager;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

public class BattleSystem {
    //Constants
    public static final float TURN_TIME = 40.0f;
    public static final float MIN_ATK_MULT = -0.5f;
    public static final float MAX_ATK_MULT = 1.5f;
    public static final float MIN_DEF_MULT = -0.5f;
    public static final float MAX_DEF_MULT = 1.5f;

    //States and data
    private static BattleData _battleData;
    private ItemsManager _itemsManager;

    //Scene elements
    private BattlePlayer _activePlayer;
    private BattleCenfomon _p1Cenfomon;
    private BattleCenfomon _p2Cenfomon;

    //Logic
    private boolean _cenfomonWeakenedByAttack = false;
    private boolean _cenfomonChanged = false;

    //Constructor
    public BattleSystem() {
        _itemsManager = new ItemsManager();
    }

    //Gets
    public BattleData getBattleData() { return this._battleData; }
    public BattlePlayer getActivePlayer() { return this._activePlayer; }
    public BattlePlayer getNonActivePlayer() {
        if (_activePlayer == _battleData.getPlayer1()) {
            return _battleData.getPlayer2();
        } else {
            return _battleData.getPlayer1();
        }
    }
    public BattleCenfomon getP1Cenfomon() { return this._p1Cenfomon; }
    public BattleCenfomon getP2Cenfomon() { return this._p2Cenfomon; }
    public BattleCenfomon getActivePlayerCenfomon() {
        if (_activePlayer == _battleData.getPlayer1())
            return this._p1Cenfomon;
        else
            return this._p2Cenfomon;
    }
    public BattleCenfomon getNonActivePlayerCenfomon() {
        if (_activePlayer == _battleData.getPlayer1())
            return this._p2Cenfomon;
        else
            return this._p1Cenfomon;
    }
    public boolean isCenfomonWeakenedByAttack() { return this._cenfomonWeakenedByAttack; }
    public boolean isCenfomonChanged() { return this._cenfomonChanged; }

    //Methods
    public static void setupBattle(BattleData p_data) {
        _battleData = p_data;
    }

    public int doAttack(Attack p_attack) {

        if (_activePlayer == _battleData.getPlayer1()) {
            _p1Cenfomon.doAttack(p_attack);
            _p2Cenfomon.receiveAttack(p_attack);

            if (_p2Cenfomon.getHealthPoints() == 0) {
                _cenfomonWeakenedByAttack = true;
            }
            return 1;
        } else {
            _p2Cenfomon.doAttack(p_attack);
            _p1Cenfomon.receiveAttack(p_attack);

            if (_p1Cenfomon.getHealthPoints() == 0) {
                _cenfomonWeakenedByAttack = true;
            }
            return 2;
        }
    }
    public boolean useItem(UsableItem p_item, BattleCenfomon p_cenfomon) {
        return _itemsManager.useItem(p_item, p_cenfomon);
    }

    public Attack getRealAttackStats(BattleCenfomon p_from, BattleCenfomon p_to, Attack p_attack) {

        return null;
    }

    public void useItem(Object cenfomon, Object item) {
        //completar
    }
    public void changeCenfomon(BattlePlayer p_player, BattleCenfomon p_newCenfomon) {
        if (p_player == _battleData.getPlayer1()) {
            _p1Cenfomon = p_newCenfomon;
        } else if (p_player == _battleData.getPlayer2()) {
            _p2Cenfomon = p_newCenfomon;
        }

        _cenfomonChanged = true;
    }

    public void startBattle() {
        this._p1Cenfomon = _battleData.getPlayer1().getCenfomon(0);
        this._p2Cenfomon = _battleData.getPlayer2().getCenfomon(0);
        this._activePlayer = _battleData.getPlayer1();
    }
    public void endTurn() {
        //Check if battle ends
        _cenfomonWeakenedByAttack = false;
        _cenfomonChanged = false;

        //Change active player
        if (_activePlayer == _battleData.getPlayer1()) {
            _activePlayer = _battleData.getPlayer2();
        } else {
            _activePlayer = _battleData.getPlayer1();
        }
    }
}
