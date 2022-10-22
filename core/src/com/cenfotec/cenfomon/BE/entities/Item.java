package com.cenfotec.cenfomon.BE.entities;

public class Item {
    private String id;
    private String name;
    private String price;
    private String description;
    private String effect;
    private int healthPrecentage;

    public Item() {}

    public Item(String id, String name, String price, String description, String effect, int healthPrecentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.effect = effect;
        this.healthPrecentage = healthPrecentage;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public float getHealthPrecentage() {
        return healthPrecentage / 100.0f;
    }

    public void setHealthPrecentage(int healthPrecentage) {
        this.healthPrecentage = healthPrecentage;
    }
}
