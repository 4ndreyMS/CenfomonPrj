package com.cenfotec.cenfomon.BE.entities;

public class Attack {
    private String id;
    private String name;
    private String rivalEffect;
    private String ownEffect;
    private String attribute;
    private int points;
    private int effectiveness;
    private int repeat;

    public Attack() {}

    public Attack(String id, String name, String rivalEffect, String ownEffect, String attribute, int points, int effectiveness, int repeat) {
        this.id = id;
        this.name = name;
        this.rivalEffect = rivalEffect;
        this.ownEffect = ownEffect;
        this.attribute = attribute;
        this.points = points;
        this.effectiveness = effectiveness;
        this.repeat = repeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRivalEffect() {
        return rivalEffect;
    }

    public void setRivalEffect(String rivalEffect) {
        this.rivalEffect = rivalEffect;
    }

    public String getOwnEffect() {
        return ownEffect;
    }

    public void setOwnEffect(String ownEffect) {
        this.ownEffect = ownEffect;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
