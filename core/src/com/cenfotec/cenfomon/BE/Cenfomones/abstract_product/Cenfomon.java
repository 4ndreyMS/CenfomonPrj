package com.cenfotec.cenfomon.BE.Cenfomones.abstract_product;

import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;
import com.cenfotec.cenfomon.BE.entities.Attack;

import java.util.ArrayList;

public interface Cenfomon {
    public int getId();
    public String getName();
    public String getDescription();
    public int getInitialHp();
    public int getInitialAttack();
    public int getInitialDefense();
    public int getInitialExp();
    public String getType();
    public ArrayList<Attack> getAttacks();
    public void setId(int id);
    public void setName(String name);
    public void setDescription(String description);
    public void setInitialHp(int hp);
    public void setInitialAttack(int attack);
    public void setInitialDefense(int defense);
    public void setInitialExp(int exp);
    public void setType(String type);
    public void setAttacks(ArrayList<Attack> attacks);
    public void setDebilidad(String debilidad);
    public String getDebilidad();
    public String toStringTSV();

}
