package com.cenfotec.cenfomon.game_elements.battle_system;

import com.cenfotec.cenfomon.BE.entities.Item;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

import java.util.ArrayList;

/***
 * Class used by the battle manager to handle AI, cenfomons and items
 */
public class BattlePlayer {
    //Constructor
    public BattlePlayer(int p_playerIndex, String p_name, ArrayList<BattleCenfomon> p_cenfomons, ArrayList<UsableItem> p_items, boolean p_isCPU) {
        this._playerIndex = p_playerIndex;
        this._name = p_name;
        this._isCPU = p_isCPU;

        if (p_cenfomons == null) {
            this._cenfomons = new ArrayList<>();
        } else {
            this._cenfomons = p_cenfomons;
        }

        if (p_items == null) {
            this._items = new ArrayList<>();
        } else {
            this._items = p_items;
        }
    }

    //Variables
    public int _playerIndex;
    public String _name;
    private ArrayList<BattleCenfomon> _cenfomons;
    private ArrayList<UsableItem> _items;

    public boolean _isCPU = false;

    //Gets & Sets
    public BattleCenfomon getCenfomon(int p_index) {
        if (p_index >= _cenfomons.size()) return null;
        return _cenfomons.get(p_index);
    }
    public ArrayList<UsableItem> getItems() {
        return this._items;
    }
    public void setItems(ArrayList<UsableItem> p_items) {
        this._items = p_items;
    }
    public void removeItem(UsableItem p_item) {
        _items.remove(p_item);
    }

    //Methods
    public int getAvailableCenfomonCount() {
        int available = 0;
        for (int i = 0; i < _cenfomons.size(); i++) {
            if (_cenfomons.get(i).getHealthPoints() > 0) {
                available++;
            }
        }
        return available;
    }
    public void startTurn(BattleManager p_battleManager) {
        p_battleManager.doAttack(this._cenfomons.get(0).getAttack(0));
    }
    public void changeCenfomon(BattleManager p_battleManager) {
        p_battleManager.changeCenfomon(this, getAvailableCenfomon());
    }

    private BattleCenfomon getAvailableCenfomon() {
        int available = 0;
        for (int i = 0; i < _cenfomons.size(); i++) {
            if (_cenfomons.get(i).getHealthPoints() > 0) {
                return _cenfomons.get(i);
            }
        }
        return null;
    }
}
