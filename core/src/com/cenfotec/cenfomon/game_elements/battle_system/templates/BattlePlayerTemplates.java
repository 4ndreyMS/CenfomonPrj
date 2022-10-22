package com.cenfotec.cenfomon.game_elements.battle_system.templates;

import com.cenfotec.cenfomon.BE.TestData;
import com.cenfotec.cenfomon.game_elements.battle_system.BattlePlayer;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

import java.util.ArrayList;

/***
 * Clase usada para guardar las plantillas de cenfomones que pueden aparecer durante todo el juego
 * Se debe acceder cuando se quiere crear un rival para comenzar una batalla
 * Ver AreaCenfomonTemplate para entender cómo se manejan las plantillas
 */
public class BattlePlayerTemplates {
    private static ArrayList<AreaCenfomonTemplate> areaCenfomonTemplates;

    private static boolean initialized = false;

    public static void initTemplates() {
        initialized = true;
        TestData.initData();
        areaCenfomonTemplates = new ArrayList<>();

        ArrayList<BattleCenfomon> curAreaCenfomons = new ArrayList<>();
        //TODO: generar cenfomones con ataques basados en el nivel (Usando la Metamorfosis)
        curAreaCenfomons.add(TestData.cenfomons[3]);
        curAreaCenfomons.add(TestData.cenfomons[4]);

        areaCenfomonTemplates.add(new AreaCenfomonTemplate("start_town_basic", curAreaCenfomons, null, AreaCenfomonTemplate.Type.AREA));
    }

    public static BattlePlayer createBattlePlayer(String p_id) {
        if (!initialized) {
            initTemplates();
        }

        AreaCenfomonTemplate template = null;

        //Intenta obtener una plantilla con el mismo id
        for (int i = 0; i < areaCenfomonTemplates.size(); i++) {
            if (areaCenfomonTemplates.get(i)._id.equals(p_id)) {
                template = areaCenfomonTemplates.get(i);
                break;
            }
        }

        //Si obtiene una plantilla entonces asigna los cenfomones al nuevo jugador rival
        if (template != null) {
            return new BattlePlayer(1, "Cenfomon salvaje", template.getCenfomons(), template.getItems(), true);
        }

        return null;
    }
}
