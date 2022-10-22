package com.cenfotec.cenfomon.BE.Cenfomones.product;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;
import com.cenfotec.cenfomon.BE.entities.Attack;

import java.sql.Types;
import java.util.ArrayList;

public class NormalCenfomon implements Cenfomon {
    private int id;
    private String name;
    private String description;
    private int initialHp;
    private int initialAttack;
    private int initialDefense;
    private int initialExp;
    private String type;
    private ArrayList<Attack> attacks;
    private String debilidad;


    public NormalCenfomon() {}

    public NormalCenfomon(int id, String name, String description, int initialHp, int initialAttack, int initialDefense, int initialExp, String type, ArrayList<Attack> attacks, String debilidad) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialHp = initialHp;
        this.initialAttack = initialAttack;
        this.initialDefense = initialDefense;
        this.initialExp = initialExp;
        this.type = type;
        this.debilidad = debilidad;
        this.attacks = attacks;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getInitialHp() {
        return initialHp;
    }

    @Override
    public void setInitialHp(int initialHp) {
        this.initialHp = initialHp;
    }

    @Override
    public int getInitialAttack() {
        return initialAttack;
    }

    @Override
    public void setInitialAttack(int initialAttack) {
        this.initialAttack = initialAttack;
    }

    @Override
    public int getInitialDefense() {
        return initialDefense;
    }

    @Override
    public void setInitialDefense(int initialDefense) {
        this.initialDefense = initialDefense;
    }

    @Override
    public int getInitialExp() {
        return initialExp;
    }

    @Override
    public void setInitialExp(int initialExp) {
        this.initialExp = initialExp;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ArrayList<Attack> getAttacks() {
        return attacks;
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
    public String toString() {
        return "NormalCenfomon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialHp=" + initialHp +
                ", initialAttack=" + initialAttack +
                ", initialDefense=" + initialDefense +
                ", initialExp=" + initialExp +
                ", type=" + type +
                ", attacks=" + attacks +
                ", debilidad=" + debilidad +
                '}';
    }

    @Override
    public String toStringTSV() {
        return id + "\n" + name + "\n" + description + "\n" + initialHp + "\n" + initialAttack + "\n" + initialDefense + "\n" + initialExp + "\n" + type +  "\n" + debilidad + "\n" + "";
    }
}
