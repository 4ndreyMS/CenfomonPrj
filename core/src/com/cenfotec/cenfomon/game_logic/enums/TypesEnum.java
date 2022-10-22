package com.cenfotec.cenfomon.game_logic.enums;

public enum TypesEnum {
    PLANTA(0),
    AGUA(1),
    FUEGO(2),
    ELECTRICO(3),
    BICHO(4),
    VOLADOR(5),
    NORMAL(6),
    FANTASMA(7);

    int _index;
    TypesEnum(int p_index) {
        this._index = p_index;
    }
    public int getIndex() {
        return this._index;
    }
    public TypesEnum fromInt(int p_index) {
        switch (p_index) {
            case 0:
                return PLANTA;
            case 1:
                return AGUA;
            case 2:
                return FUEGO;
            case 3:
                return ELECTRICO;
            case 4:
                return BICHO;
            case 5:
                return VOLADOR;
            case 6:
                return NORMAL;
            case 7:
                return FANTASMA;
        }
        return null;
    }
}
