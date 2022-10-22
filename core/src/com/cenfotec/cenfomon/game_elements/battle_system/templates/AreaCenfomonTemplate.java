package com.cenfotec.cenfomon.game_elements.battle_system.templates;

import com.badlogic.gdx.math.MathUtils;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

import java.util.ArrayList;

public class AreaCenfomonTemplate {
    //Usado para definir el tipo de la plantilla
    //Si es un area selecciona un cenfomon aleatorio para comenzar la batalla
    //Si es un entrenador entonces devuelve todos los cenfomones guardados
    public enum Type {
        AREA,
        TRAINER
    }

    public String _id;                                      //Id unico con el que se reconoce
    public ArrayList<BattleCenfomon> _possibleCenfomons;    //Lista de cenfomones que serán usados a partir de esta plantilla
    public ArrayList<UsableItem> _possibleItems;            //Lista de los items que serán usados a partir de esta plantilla
    public Type _type;                                      //El tipo de jugador para la plantilla

    public AreaCenfomonTemplate(String p_id, ArrayList<BattleCenfomon> p_possibleCenfomons, ArrayList<UsableItem> p_possibleItems, Type p_type) {
        this._id = p_id;
        this._possibleCenfomons = p_possibleCenfomons;
        this._possibleItems = p_possibleItems;
    }

    public ArrayList<BattleCenfomon> getCenfomons() {
        if (_possibleCenfomons == null)
            _possibleCenfomons = new ArrayList<>();

        //Devuelve los cenfomones del entrenador
        if (_type == Type.TRAINER) {
            return _possibleCenfomons;
        }

        //Devuelve un cenfomon aleatorio
        ArrayList<BattleCenfomon> cenfomon = new ArrayList<>();
        cenfomon.add(_possibleCenfomons.get(MathUtils.random(_possibleCenfomons.size() - 1)));
        return cenfomon;
    }

    public ArrayList<UsableItem> getItems() {
        if (_possibleItems == null)
            _possibleItems = new ArrayList<>();
        return this._possibleItems;
    }
}
