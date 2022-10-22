package com.cenfotec.cenfomon.BE.entities;

import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

import java.util.ArrayList;

public class PlayerData {
    //Variables
    private String _name;
    private String _gender;
    private ArrayList<BattleCenfomon> _cenfomons;
    private ArrayList<UsableItem> _items;
    private String _lastMap;
    private Vector2 _lastPosition;
    private Vector2 _lastDirVec;
    private int _cCoins;

    //Constructor
    public PlayerData(String p_name, String p_gender, ArrayList<BattleCenfomon> p_cenfomons, ArrayList<UsableItem> p_items, String p_lastMap, Vector2 p_lastPosition, Vector2 p_lastDirVec, int p_cCoins) {
        this._name = p_name;
        this._gender = p_gender;
        this._cenfomons = p_cenfomons;
        this._items = p_items;
        this._lastMap = p_lastMap;
        this._lastPosition = p_lastPosition;
        this._lastDirVec = p_lastDirVec;
        this._cCoins = p_cCoins;
    }

    //Gets & Sets
    public String getName() {
        return _name;
    }
    public void setName(String _name) {
        this._name = _name;
    }

    public String getGender() {
        return _gender;
    }
    public void setGender(String _gender) {
        this._gender = _gender;
    }

    public ArrayList<BattleCenfomon> getCenfomons() {
        return _cenfomons;
    }
    public void setCenfomons(ArrayList<BattleCenfomon> _cenfomons) {
        this._cenfomons = _cenfomons;
    }

    public ArrayList<UsableItem> getItems() {
        return _items;
    }
    public void setItems(ArrayList<UsableItem> _items) {
        this._items = _items;
    }

    public String getLastMap() {
        return _lastMap;
    }
    public void setLastMap(String _lastMap) {
        this._lastMap = _lastMap;
    }

    public Vector2 getLastPosition() {
        return _lastPosition;
    }
    public void setLastPosition(Vector2 _lastPosition) {
        this._lastPosition = _lastPosition;
    }

    public Vector2 getLastDirVec() {
        return _lastDirVec;
    }
    public void setLastDirVec(Vector2 _lastDirVec) {
        this._lastDirVec = _lastDirVec;
    }

    public int getCCoins() {
        return _cCoins;
    }
    public void setCCoins(int _cCoins) {
        this._cCoins = _cCoins;
    }
}
