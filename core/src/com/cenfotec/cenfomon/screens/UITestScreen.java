package com.cenfotec.cenfomon.screens;

import com.cenfotec.cenfomon.core.game.BaseScreen;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.managers.UIManager;

public class UITestScreen extends BaseScreen {
    public UITestScreen(GameInstance p_gameInstance) {
        super(p_gameInstance);
        uiManager.setActiveStage(UIManager.StagesEnum.UI_TEST, true);
    }
}
