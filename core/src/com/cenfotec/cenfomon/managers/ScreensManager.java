package com.cenfotec.cenfomon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.BaseScreen;
import com.cenfotec.cenfomon.screens.BattleScreen;
import com.cenfotec.cenfomon.screens.GameOverScreen;
import com.cenfotec.cenfomon.screens.GameLevelScreen;
import com.cenfotec.cenfomon.screens.UITestScreen;

public class ScreensManager {
    public enum ScreensEnum {
        MAIN_MENU_SCREEN,
        LEVEL_SCREEN,
        GAME_OVER_SCREEN,
        UI_TEST_SCREEN,
        BATTLE_SCREEN
    }
    public class Portal {
        public String from;
        public String screenPath;
        public Vector2 outPosition;

        public Portal(String from, String screenPath, Vector2 outPosition) {
            this.from = from;
            this.screenPath = screenPath;
            this.outPosition = outPosition;
        }
    }

    public static ScreensManager instance;
    public GameInstance gameInstance;
    public Portal[] portals;

    public ScreensManager(GameInstance p_gameInstance) {
        this.gameInstance = p_gameInstance;
        instance = this;

        initPortals();
    }
    private void initPortals() {
        portals = new Portal[10];
        portals[0] = new Portal("town_to_fonseca", "fonseca.tmx", new Vector2(122, 32));
        portals[1] = new Portal("fonseca_to_town", "Start_town.tmx", new Vector2(390, 245));
        portals[2] = new Portal("town_to_clinic", "clinic_house.tmx", new Vector2(122, 32));
        portals[3] = new Portal("town_to_town2", "Town_Part2.tmx", new Vector2(20, 410));
        portals[4] = new Portal("town2_to_town", "Start_town.tmx", new Vector2(710, 425));
        portals[5] = new Portal("clinic_house_to_town", "Start_town.tmx", new Vector2(582, 66));
        portals[6] = new Portal("town2_to_clinic_house", "clinic_house_town2.tmx", new Vector2(122, 32));
        portals[7] = new Portal("clinic_house_to_town2", "Town_Part2.tmx", new Vector2(665, 355));
    }
    private Portal getPortal(String p_fromID) {
        for (int i = 0; i < portals.length; i++) {
            if (portals[i].from.equals(p_fromID)) {
                return portals[i];
            }
        }
        return null;
    }

    public void changeScreen(BaseScreen p_newScreen) {
        if (gameInstance.curScreen != null) {
            gameInstance.curScreen.dispose();
        }

        gameInstance.setScreen(p_newScreen);
    }

    public void changeScreen(ScreensEnum p_newScreen, String p_customLevel, boolean savePlayerLocation) {
        //Guardar la posicion y direccion del jugador antes de cambiar de escena
        if (savePlayerLocation && GameInstance.playerRef != null) {
            GameInstance.playerData.setLastPosition(GameInstance.playerRef.getPosition());
            GameInstance.playerData.setLastDirVec(GameInstance.playerRef.getLastDir());
        }

        switch (p_newScreen) {
            case MAIN_MENU_SCREEN:
                //TODO
                break;
            case LEVEL_SCREEN:
                GameInstance.playerData.setLastMap(p_customLevel);
                changeScreen(new GameLevelScreen(this.gameInstance, "Maps/" + p_customLevel));
                break;
            case GAME_OVER_SCREEN:
                changeScreen(new GameOverScreen(this.gameInstance));
                break;
            case UI_TEST_SCREEN:
                changeScreen(new UITestScreen(this.gameInstance));
                break;
            case BATTLE_SCREEN:
                changeScreen(new BattleScreen(this.gameInstance));
                break;
        }
    }

    public void changeScreen(String p_from) {
        Portal portal = getPortal(p_from);
        if (portal != null) {
            GameInstance.playerData.setLastPosition(new Vector2(portal.outPosition.x, portal.outPosition.y).scl(1 / GameInstance.PIX_PER_MTR));
            GameInstance.playerData.setLastDirVec(GameInstance.playerRef.getLastDir());
            changeScreen(ScreensEnum.LEVEL_SCREEN, portal.screenPath, false);
            /**ref of player and turn false**/
            gameInstance.playerRef.makeStaticPlayer1S();
        }
    }

    public void loadDefaultScreen() {


        changeScreen(ScreensEnum.LEVEL_SCREEN, "Maps/Start_town.tmx", false);
//        changeScreen(ScreensEnum.BATTLE_SCREEN, "", false);

    }

    public void loadLastScreen() {
        changeScreen(ScreensEnum.LEVEL_SCREEN, GameInstance.playerData.getLastMap(), false);
//        changeScreen(ScreensEnum.BATTLE_SCREEN, "", false);

    }
}
