package com.cenfotec.cenfomon.BE.entities;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.game_logic.entities.Attack;

public class BattleCenfomon {
    private Cenfomon cenfomon;
    private boolean equiped;
    private String nickname;

    private int level;
    private int experience;
    private int maxHealthPoints;
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;

    private Attack attack1;
    private Attack attack2;
    private Attack attack3;
    private Attack attack4;

    private float attackMult;
    private float defenseMult;

    public BattleCenfomon() {
        this.cenfomon = null;
        this.equiped = false;
        this.nickname = "";
        this.level = 0;
        this.experience = 0;
        this.healthPoints = 0;
        this.attackPoints = 0;
        this.defensePoints = 0;
        this.attack1 = null;
        this.attack2 = null;
        this.attack3 = null;
        this.attack4 = null;
        this.attackMult = 1;
        this.defenseMult = 1;
    }

    public BattleCenfomon(Cenfomon cenfomon, boolean equiped, String nickname, int level, int experience, int maxHealthPoints, int healthPoints, int attackPoints, int defensePoints, Attack attack1, Attack attack2, Attack attack3, Attack attack4, float attackMult, float defenseMult) {
        this.cenfomon = cenfomon;
        this.equiped = equiped;
        this.nickname = nickname;
        this.level = level;
        this.experience = experience;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.attack3 = attack3;
        this.attack4 = attack4;
        this.attackMult = attackMult;
        this.defenseMult = defenseMult;
    }

    public Cenfomon getCenfomon() {
        return cenfomon;
    }

    public void setCenfomon(Cenfomon cenfomon) {
        this.cenfomon = cenfomon;
    }

    public boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public Attack getAttack1() {
        return attack1;
    }

    public void setAttack1(Attack attack1) {
        this.attack1 = attack1;
    }

    public Attack getAttack2() {
        return attack2;
    }

    public void setAttack2(Attack attack2) {
        this.attack2 = attack2;
    }

    public Attack getAttack3() {
        return attack3;
    }

    public void setAttack3(Attack attack3) {
        this.attack3 = attack3;
    }

    public Attack getAttack4() {
        return attack4;
    }

    public void setAttack4(Attack attack4) {
        this.attack4 = attack4;
    }

    public float getAttackMult() {
        return attackMult;
    }

    public void setAttackMult(float attackMult) {
        this.attackMult = attackMult;
    }

    public float getDefenseMult() {
        return defenseMult;
    }

    public void setDefenseMult(float defenseMult) {
        this.defenseMult = defenseMult;
    }

    public String toStringTSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cenfomon.getId());
        sb.append("\t");
        sb.append(this.equiped? 1: 0);
        sb.append("\t");
        sb.append(this.nickname);
        sb.append("\t");
        sb.append(this.level);
        sb.append("\t");
        sb.append(this.experience);
        sb.append("\t");
        sb.append(this.maxHealthPoints);
        sb.append("\t");
        sb.append(this.healthPoints);
        sb.append("\t");
        sb.append(this.attackPoints);
        sb.append("\t");
        sb.append(this.defensePoints);
        sb.append("\t");
        sb.append(this.attack1);
        sb.append("\t");
        sb.append(this.attack2);
        sb.append("\t");
        sb.append(this.attack3);
        sb.append("\t");
        sb.append(this.attack4);
        return sb.toString();
    }
}
