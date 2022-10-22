package com.cenfotec.cenfomon.screens;

import com.badlogic.gdx.math.Vector2;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.core.game.TileMapScreen;
import com.cenfotec.cenfomon.game_elements.Player;
import com.cenfotec.cenfomon.managers.UIManager;

public class GameLevelScreen extends TileMapScreen {
    //Game elements
    private Player player;

    public GameLevelScreen(GameInstance gameInstance, String levelPath) {
        super(gameInstance, levelPath);
        //Dynamic objects initialization
        player = new Player(this);
        createObject(player);

        gameCamera.setTarget(player);
        gameCamera.setPosition(new Vector2(player.getPosition().x, gameCamera.getPosition().y));
        uiManager.setActiveStage(UIManager.StagesEnum.IN_GAME, true);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
