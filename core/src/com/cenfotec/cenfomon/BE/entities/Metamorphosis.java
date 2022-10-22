package com.cenfotec.cenfomon.BE.entities;

public class Metamorphosis {
    private int cenfomonId;
    private int requiredLevel;
    private Item requiredItem;
    private Attack attack;
    private int idEvolvedCenfomon;

    public Metamorphosis() {

    }

    public Metamorphosis(int cenfomonId, int requiredLevel, Item requiredItem, Attack attack, int idEvolvedCenfomon) {
        this.cenfomonId = cenfomonId;
        this.requiredLevel = requiredLevel;
        this.requiredItem = requiredItem;
        this.attack = attack;
        this.idEvolvedCenfomon = idEvolvedCenfomon;
    }

    public int getCenfomonId() {
        return cenfomonId;
    }

    public void setCenfomonId(int cenfomonId) {
        this.cenfomonId = cenfomonId;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(Item requiredItem) {
        if (requiredItem != null) {
            this.requiredItem = requiredItem;
        } else {
            this.requiredItem = null;
        }
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public int getIdEvolvedCenfomon() {
        return idEvolvedCenfomon;
    }

    public void setIdEvolvedCenfomon(int idEvolvedCenfomon) {
        this.idEvolvedCenfomon = idEvolvedCenfomon;
    }
}
