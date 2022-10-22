package com.cenfotec.cenfomon.BE;

import com.cenfotec.cenfomon.BE.entities.Item;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.game_logic.enums.AtkAttribute;
import com.cenfotec.cenfomon.game_logic.enums.AtkEffect;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;

public class TestData {
    public static Attack[] attacks;
    public static BattleCenfomon[] cenfomons;
    public static Item[] items;

    private static boolean _initialized = false;

    public static void initData() {
        if (_initialized) return;

        _initialized = true;

        attacks = new Attack[] {
                new Attack(0, "Ascua", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(1, "Lanzallamas", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(2, "Anillo de fuego", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(3, "Burbuja", TypesEnum.AGUA, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(4, "Baño María", TypesEnum.AGUA, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(5, "Defensa acuosa", TypesEnum.AGUA, AtkEffect.NINGUNO, AtkEffect.INCREMENTA, AtkAttribute.DEFENSA, 10, 100, 1)
        };

        cenfomons = new BattleCenfomon[] {
                new BattleCenfomon(null, true, "Osotias", 12, 1200, 12, 12, 10, 15, attacks[0], attacks[2], null, null),
                new BattleCenfomon(null, true, "Fenixluna", 44, 1846, 10, 10, 10, 15, attacks[0], attacks[2], null, null),
                new BattleCenfomon(null, true, "Orucros", 8, 1220, 20, 20, 4, 7, attacks[3], attacks[1], attacks[0], attacks[4]),
                new BattleCenfomon(null, true, "Corder", 15, 1660, 10, 10, 6, 9, attacks[4], attacks[0], null, null),
                new BattleCenfomon(null, true, "Pamo", 15, 1660, 10, 10, 6, 9, attacks[4], attacks[0], null, null),
        };

        items = new Item[] {
                new Item("1", "Pocion", "20", "La poción más básica para recuperar la salud de nuestros compañeros. Recupera el 20% de la salud", "SANAR", 20),
                new Item("2", "Pocion mediana", "80", "Una poción superior que llega a recuperar el 45% de la salud del cenfomon en que se use", "SANAR", 45),
                new Item("3", "Pocion grande", "140", "Una poción superior que llega a recuperar el 100% de la salud del cenfomon en que se use", "SANAR", 80),
                new Item("4", "Revivir", "500", "Se usa cuando un compañero está fuera de combate. Esta poción especial recupera a nuestro compañero con un 15% de su salud", "REVIVIR", 15),
        };
    }

    public static void addFenixLuna() {
        BattleCenfomon fenixLuna = new BattleCenfomon(null, true, "Fenixluna", 44, 1846, 10, 10, 10, 15, attacks[0], attacks[2], null, null);
        cenfomons = new BattleCenfomon[] { fenixLuna };
    }

    public static void addOsotias() {
        BattleCenfomon osotias = new BattleCenfomon(null, true, "Osotias", 12, 1200, 12, 12, 10, 15, attacks[0], attacks[2], null, null);
        cenfomons = new BattleCenfomon[] { osotias };
    }

    public static BattleCenfomon[] getCenfomons() {
        return cenfomons;
    }
}
