package com.cenfotec.cenfomon.game_logic.entities;

import com.badlogic.gdx.math.MathUtils;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleSystem;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;

public class BattleCenfomon {
    //Variables
    private Cenfomon _cenfomon;

    private boolean _equiped;
    private String _nickname;

    private int _level;
    private int _experience;
    private int _maxHealthPoints;
    private int _healthPoints;
    private int _attackPoints;
    private int _defensePoints;

    private Attack _attack1;
    private Attack _attack2;
    private Attack _attack3;
    private Attack _attack4;

    private float _attackMult;
    private float _defenseMult;

    //Constructors
    public BattleCenfomon() {
        this._cenfomon = null;
        this._equiped = false;
        this._nickname = "";
        this._level = 0;
        this._experience = 0;
        this._healthPoints = 0;
        this._attackPoints = 0;
        this._defensePoints = 0;
        this._attack1 = null;
        this._attack2 = null;
        this._attack3 = null;
        this._attack4 = null;
        this._attackMult = 1;
        this._defenseMult = 1;
    }
    public BattleCenfomon(String[] p_attributes) {
        this._equiped = Boolean.parseBoolean(p_attributes[1]);
        this._nickname = p_attributes[2];
        this._level = Integer.parseInt(p_attributes[3]);
        this._experience = Integer.parseInt(p_attributes[4]);
        this._maxHealthPoints = Integer.parseInt(p_attributes[5]);
        this._healthPoints = Integer.parseInt(p_attributes[6]);
        this._attackPoints = Integer.parseInt(p_attributes[7]);
        this._defensePoints = Integer.parseInt(p_attributes[8]);
        this._attackMult = 1;
        this._defenseMult = 1;
    }
    public BattleCenfomon(Cenfomon p_cenfomon, boolean p_equiped, String p_nickname, int p_level, int p_experience, int p_maxHealthPoints, int p_healthPoints, int p_attackPoints, int p_defensePoints, Attack p_attack1, Attack p_attack2, Attack p_attack3, Attack p_attack4) {
        this._cenfomon = p_cenfomon;
        this._equiped = p_equiped;
        this._nickname = p_nickname;
        this._level = p_level;
        this._experience = p_experience;
        this._maxHealthPoints = p_maxHealthPoints;
        this._healthPoints = p_healthPoints;
        this._attackPoints = p_attackPoints;
        this._defensePoints = p_defensePoints;
        this._attack1 = p_attack1;
        this._attack2 = p_attack2;
        this._attack3 = p_attack3;
        this._attack4 = p_attack4;
        this._attackMult = 1;
        this._defenseMult = 1;
    }

    //Gets
    public String getRegionName() {
        return getName();
        //return _cenfomon.getName();
    }
    public Cenfomon getCenfomon() {
        return _cenfomon;
    }
    public boolean isEquiped() {
        return _equiped;
    }
    public String getName() {
        if (!getNickname().equals("")) {
            return getNickname();
        }
        return this._cenfomon.getName();
    }
    public String getNickname() {
        return _nickname;
    }
    public int getLevel() {
        return _level;
    }
    public int getExperience() {
        return _experience;
    }
    public int getMaxHealthPoints() {
        return _maxHealthPoints;
    }
    public int getHealthPoints() {
        return _healthPoints;
    }
    public int getAttackPoints() {
        return _attackPoints;
    }
    public int getDefensePoints() {
        return _defensePoints;
    }
    public Attack getAttack(int p_index) {
        switch (p_index) {
            case 0:
                return _attack1;
            case 1:
                return _attack2;
            case 2:
                return _attack3;
            case 3:
                return _attack4;
            default:
                return null;
        }
    }
    public float getAttackMult() {
        return _attackMult;
    }
    public float getDefenseMult() {
        return _defenseMult;
    }

    //Sets
    public void setCenfomon(Cenfomon p_cenfomon) {
        this._cenfomon = p_cenfomon;
    }
    public void setEquiped(boolean p_equiped) {
        this._equiped = p_equiped;
    }
    public void setNickname(String p_nickname) {
        this._nickname = p_nickname;
    }
    public void setLevel(int p_level) {
        this._level = p_level;
    }
    public void setExperience(int p_experience) {
        this._experience = p_experience;
    }
    public void set_maxHealthPoints(int p_maxHealthPoints) {
        this._maxHealthPoints = p_maxHealthPoints;
    }
    public void setHealthPoints(int p_healthPoints) {
        this._healthPoints = p_healthPoints;
    }
    public void setAttackPoints(int p_attackPoints) {
        this._attackPoints = p_attackPoints;
    }
    public void setDefensePoints(int p_defensePoints) {
        this._defensePoints = p_defensePoints;
    }
    public void setAttackMult(float p_attackMult) {
        this._attackMult = p_attackMult;
    }
    public void setDefenseMult(float p_defenseMult) {
        this._defenseMult = p_defenseMult;
    }
    public void setAttack(int p_index, Attack p_attack) {
        switch (p_index) {
            case 1:
                _attack1 = p_attack;
                break;
            case 2:
                _attack2 = p_attack;
                break;
            case 3:
                _attack3 = p_attack;
            case 4:
                _attack4 = p_attack;
        }
    }

    //Methods
    public void doAttack(Attack p_attack) {
        switch (p_attack.getAttributeToAlter()) {
            case VIDA:
                setHealthPoints(getHealthPoints() + (p_attack.getSelfEffect().getIndex() * p_attack.getPoints()));
                break;
            case ATAQUE:
                setAttackMult(getAttackMult() + (p_attack.getSelfEffect().getIndex() * p_attack.getPoints()));
                break;
            case DEFENSA:
                setDefenseMult(getDefenseMult() + (p_attack.getSelfEffect().getIndex() * p_attack.getPoints()));
                break;
        }
        clampValues();
    }
    public void receiveAttack(Attack p_attack) {
        switch (p_attack.getAttributeToAlter()) {
            case VIDA:
                setHealthPoints(getHealthPoints() + (p_attack.getTargetEffect().getIndex() * p_attack.getPoints()));
                break;
            case ATAQUE:
                setAttackMult(getAttackMult() + (p_attack.getTargetEffect().getIndex() * p_attack.getPoints()));
                break;
            case DEFENSA:
                setDefenseMult(getDefenseMult() + (p_attack.getTargetEffect().getIndex() * p_attack.getPoints()));
                break;
        }
        clampValues();
    }
    public void clampValues() {
        setHealthPoints(MathUtils.clamp(getHealthPoints(), 0, getMaxHealthPoints()));
        setAttackMult(MathUtils.clamp(getAttackMult(), BattleSystem.MIN_ATK_MULT, BattleSystem.MAX_ATK_MULT));
        setDefenseMult(MathUtils.clamp(getDefenseMult(), BattleSystem.MIN_DEF_MULT, BattleSystem.MAX_DEF_MULT));
    }
}
