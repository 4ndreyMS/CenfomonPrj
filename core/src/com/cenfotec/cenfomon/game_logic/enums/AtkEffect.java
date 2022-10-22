package com.cenfotec.cenfomon.game_logic.enums;

public enum AtkEffect {
    NINGUNO(0),
    INCREMENTA(1),
    DECREMENTA(-1);

    int _index;
    AtkEffect(int p_index) {
        this._index = p_index;
    }
    public int getIndex() {
        return this._index;
    }
    public AtkEffect fromInt(int p_index) {
        switch (p_index) {
            case -1:
                return DECREMENTA;
            case 0:
                return NINGUNO;
            case 1:
                return INCREMENTA;
        }
        return null;
    }
}
