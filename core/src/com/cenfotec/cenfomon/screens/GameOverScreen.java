package com.cenfotec.cenfomon.screens;

import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.BaseScreen;
import com.cenfotec.cenfomon.managers.UIManager;

public class GameOverScreen extends BaseScreen {
    public GameOverScreen(GameInstance gameInstance) {
        super(gameInstance);
        uiManager.setActiveStage(UIManager.StagesEnum.GAME_OVER, true);
    }
}
