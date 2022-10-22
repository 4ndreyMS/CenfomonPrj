package com.cenfotec.cenfomon.BE.Cenfomones.product;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;
import com.cenfotec.cenfomon.BE.entities.Attack;

import java.util.ArrayList;

public class BugCenfomon implements Cenfomon {
    private int id;
    private String name;
    private String description;
    private int initialHp;
    private int initialAttack;
    private int initialDefense;
    private int initialExp;
    private String type;
    private String debilidad;
    private ArrayList<Attack> attacks;

    public BugCenfomon() {}

    public BugCenfomon(int id, String name, String description, int initialHp, int initialAttack, int initialDefense, int initialExp,String type, ArrayList<Attack> attacks, String debilidad) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialHp = initialHp;
        this.initialAttack = initialAttack;
        this.initialDefense = initialDefense;
        this.initialExp = initialExp;
        this.type = type;
        this.attacks = attacks;
        this.debilidad = debilidad;
    }

    public BugCenfomon(int id, String name, String description, int initialHp, int initialAttack, int initialDefense, int initialExp,String type, String debilidad) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialHp = initialHp;
        this.initialAttack = initialAttack;
        this.initialDefense = initialDefense;
        this.initialExp = initialExp;
        this.type = type;
        this.debilidad = debilidad;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getInitialHp() {
        return initialHp;
    }

    @Override
    public int getInitialAttack() {
        return initialAttack;
    }

    @Override
    public int getInitialDefense() {
        return initialDefense;
    }

    @Override
    public int getInitialExp() {
        return initialExp;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description =description;
    }

    @Override
    public void setInitialHp(int hp) {
        this.initialHp = hp;
    }

    @Override
    public void setInitialAttack(int attack) {
        this.initialAttack = attack;
    }

    @Override
    public void setInitialDefense(int defense) {
        this.initialDefense = defense;
    }

    @Override
    public void setInitialExp(int exp) {
        this.initialExp = exp;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    @Override
    public void setDebilidad(String debilidad) {
        this.debilidad = debilidad;
    }

    @Override
    public String getDebilidad() {
        return debilidad;
    }

    @Override
    public String toStringTSV() {
        return id + "\n" + name + "\n" + description + "\n" + initialHp + "\n" + initialAttack + "\n" + initialDefense + "\n" + initialExp + "\n" + type + "\n" + debilidad + "\n" + "";
    }

    @Override
    public String toString() {
        return "BugCenfomon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialHp=" + initialHp +
                ", initialAttack=" + initialAttack +
                ", initialDefense=" + initialDefense +
                ", initialExp=" + initialExp +
                ", type=" + type +
                ", debilidad=" + debilidad +
                ", attacks=" + attacks +
                '}';
    }
}
