package com.cenfotec.cenfomon.game_elements.battle_system;

import com.cenfotec.cenfomon.BE.entities.Item;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.game_logic.enums.AtkAttribute;
import com.cenfotec.cenfomon.game_logic.enums.AtkEffect;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;

import java.util.ArrayList;

public class BattleData {
    public BattleData() {
        Attack[] attacks = new Attack[] {
                new Attack(0, "Ascua", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(1, "Lanzallamas", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(2, "Anillo de fuego", TypesEnum.FUEGO, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(3, "Burbuja", TypesEnum.AGUA, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(4, "Baño María", TypesEnum.AGUA, AtkEffect.DECREMENTA, AtkEffect.NINGUNO, AtkAttribute.VIDA, 5, 100, 1),
                new Attack(5, "Defensa acuosa", TypesEnum.AGUA, AtkEffect.NINGUNO, AtkEffect.INCREMENTA, AtkAttribute.DEFENSA, 10, 100, 1)
        };

        BattleCenfomon[] cenfomons = new BattleCenfomon[] {
                new BattleCenfomon(null, true, "Yencornio", 44, 1846, 12, 12, 10, 15, attacks[0], attacks[2], null, null),
                new BattleCenfomon(null, true, "Dragoluna", 44, 1846, 10, 10, 10, 15, attacks[0], attacks[2], null, null),
                new BattleCenfomon(null, true, "Osotias", 26, 1220, 20, 20, 4, 7, attacks[3], attacks[1], attacks[0], attacks[4]),
                new BattleCenfomon(null, true, "Polartias", 35, 1660, 10, 10, 6, 9, attacks[4], attacks[0], null, null),
        };

        Item[] items = new Item[] {
                new Item("1", "Pocion", "20", "La poción más básica para recuperar la salud de nuestros compañeros. Recupera el 20% de la salud", "SANAR", 20),
                new Item("2", "Pocion mediana", "80", "Una poción superior que llega a recuperar el 45% de la salud del cenfomon en que se use", "SANAR", 45),
                new Item("3", "Pocion grande", "140", "Una poción superior que llega a recuperar el 100% de la salud del cenfomon en que se use", "SANAR", 80),
                new Item("4", "Revivir", "500", "Se usa cuando un compañero está fuera de combate. Esta poción especial recupera a nuestro compañero con un 15% de su salud", "REVIVIR", 15),
        };


        ArrayList<BattleCenfomon> player1Cenfomons = new ArrayList<>();
        player1Cenfomons.add(cenfomons[0]);
        player1Cenfomons.add(cenfomons[1]);

        ArrayList<BattleCenfomon> player2Cenfomons = new ArrayList<>();
        player2Cenfomons.add(cenfomons[2]);
        player2Cenfomons.add(cenfomons[3]);

        ArrayList<UsableItem> player1Items = new ArrayList<>();
        player1Items.add(new UsableItem(items[0], 10));
        player1Items.add(new UsableItem(items[1], 4));
        player1Items.add(new UsableItem(items[2], 3));
        player1Items.add(new UsableItem(items[3], 1));

        this._player1 = new BattlePlayer(1, "Jugador 1", player1Cenfomons, player1Items, false);
        this._player1.setItems(player1Items);
        this._player2 = new BattlePlayer(2, "Jugador 2", player2Cenfomons, null, true);
        this._isWildCenfomon = false;
        //******************
    }

    //Cuando se conocen los dos jugadores
    public BattleData(BattlePlayer p_player1, BattlePlayer p_player2) {
        this._player1 = p_player1;
        this._player2 = p_player2;
    }

    //Cuando se conoce solo el rival y se debe obtener el jugador actual
    public BattleData(BattlePlayer p_rival) {
        _player1 = new BattlePlayer(0, GameInstance.playerData.getName(), GameInstance.playerData.getCenfomons(), GameInstance.playerData.getItems(), false);
        this._player2 = p_rival;
    }

    private BattlePlayer _player1;
    private BattlePlayer _player2;
    private boolean _isWildCenfomon;

    public BattlePlayer getPlayer1() {
        return this._player1;
    }
    public BattlePlayer getPlayer2() {
        return this._player2;
    }
    public boolean isWildCenfomon() {
        return this._isWildCenfomon;
    }
    public void setIsWildCenfomon(boolean p_isWildCenfomon) {
        this._isWildCenfomon = p_isWildCenfomon;
    }
}
