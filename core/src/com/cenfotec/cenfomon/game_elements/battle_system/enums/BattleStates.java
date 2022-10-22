package com.cenfotec.cenfomon.game_elements.battle_system.enums;

public enum BattleStates {
    START(0),
    PLAYER_TURN_START(1),
    WAITING_FOR_PLAYER(2),
    WAITING_FOR_CENFOMON_CHANGE(3),
    PLAYER_TURN_END(4),
    BATTLE_END(5),
    SHOWING_DIALOGUE(6);

    private int index;
    public int getIndex() {
        return index;
    }

    BattleStates(int p_index) {
        this.index = p_index;
    }

    public static BattleStates fromInt(int p_value) {
        switch (p_value) {
            case 0:
                return START;
            case 1:
                return PLAYER_TURN_START;
            case 2:
                return WAITING_FOR_PLAYER;
            case 3:
                return WAITING_FOR_CENFOMON_CHANGE;
            case 4:
                return PLAYER_TURN_END;
            case 5:
                return BATTLE_END;
            case 6:
                return SHOWING_DIALOGUE;
            default:
                return null;
        }
    }
}
