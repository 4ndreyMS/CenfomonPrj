package com.cenfotec.cenfomon.game_logic.entities;

import com.cenfotec.cenfomon.game_logic.enums.AtkAttribute;
import com.cenfotec.cenfomon.game_logic.enums.AtkEffect;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;

public class Attack {
    //Variables
    private int _id;
    private String _name;

    private TypesEnum _type;
    private AtkEffect _targetEffect;
    private AtkEffect _selfEffect;
    private AtkAttribute _attributeToAlter;

    private int _points;
    private float _effectiveness;
    private int _repeatTimes;

    //Constructors
    public Attack() {
        this._id = 0;
        this._name = "";
        this._type = TypesEnum.PLANTA;
        this._targetEffect = AtkEffect.NINGUNO;
        this._selfEffect = AtkEffect.NINGUNO;
        this._attributeToAlter = AtkAttribute.NINGUNO;
        this._points = 0;
        this._effectiveness = 0.0f;
        this._repeatTimes = 1;
    }
    public Attack(String[] p_attributes) {
        this._id = Integer.parseInt(p_attributes[0]);
        this._name = p_attributes[1];

        this._type = TypesEnum.valueOf(p_attributes[2]);
        this._targetEffect = AtkEffect.valueOf(p_attributes[3]);
        this._selfEffect = AtkEffect.valueOf(p_attributes[4]);
        this._attributeToAlter = AtkAttribute.valueOf(p_attributes[5]);

        this._points = Integer.parseInt(p_attributes[6]);
        this._effectiveness = Float.parseFloat(p_attributes[7]);
        this._repeatTimes = Integer.parseInt(p_attributes[8]);
    }
    public Attack(int p_id, String p_name, TypesEnum p_type, AtkEffect p_targetEffect, AtkEffect p_selfEffect, AtkAttribute p_attributeToAlter, int p_points, float p_effectiveness, int p_repeatTimes) {
        this._id = p_id;
        this._name = p_name;
        this._type = p_type;
        this._targetEffect = p_targetEffect;
        this._selfEffect = p_selfEffect;
        this._attributeToAlter = p_attributeToAlter;
        this._points = p_points;
        this._effectiveness = p_effectiveness;
        this._repeatTimes = p_repeatTimes;
    }

    //Gets
    public int getId() {
        return _id;
    }
    public String getName() {
        return _name;
    }
    public TypesEnum getType() {
        return _type;
    }
    public AtkEffect getTargetEffect() {
        return _targetEffect;
    }
    public AtkEffect getSelfEffect() {
        return _selfEffect;
    }
    public AtkAttribute getAttributeToAlter() {
        return _attributeToAlter;
    }
    public int getPoints() {
        return _points;
    }
    public float getEffectiveness() {
        return _effectiveness;
    }
    public int getRepeatTimes() {
        return _repeatTimes;
    }

    //Sets
    public void setId(int _id) {
        this._id = _id;
    }
    public void setName(String _name) {
        this._name = _name;
    }
    public void setType(TypesEnum _type) {
        this._type = _type;
    }
    public void setTargetEffect(AtkEffect _targetEffect) {
        this._targetEffect = _targetEffect;
    }
    public void setSelfEffect(AtkEffect _selfEffect) {
        this._selfEffect = _selfEffect;
    }
    public void setAttributeToAlter(AtkAttribute _attributeToAlter) {
        this._attributeToAlter = _attributeToAlter;
    }
    public void setPoints(int _points) {
        this._points = _points;
    }
    public void setEffectiveness(float _effectiveness) {
        this._effectiveness = _effectiveness;
    }
    public void setRepeatTimes(int _repeatTimes) {
        this._repeatTimes = _repeatTimes;
    }

    //Methods
}
