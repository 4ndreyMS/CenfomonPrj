package com.cenfotec.cenfomon;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cenfotec.cenfomon.BE.TestData;
import com.cenfotec.cenfomon.BE.entities.Item;
import com.cenfotec.cenfomon.BE.entities.PlayerData;
import com.cenfotec.cenfomon.core.game.BaseScreen;
import com.cenfotec.cenfomon.game_elements.Player;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.game_logic.enums.AtkAttribute;
import com.cenfotec.cenfomon.game_logic.enums.AtkEffect;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;
import com.cenfotec.cenfomon.managers.AudioManager;
import com.cenfotec.cenfomon.managers.ScreensManager;


import java.util.ArrayList;

public class GameInstance extends com.badlogic.gdx.Game {
	public static final int V_WIDTH = 448;
	public static final int V_HEIGHT = 252;
	public static final float PIX_PER_MTR = 32;

	public static SpriteBatch batch;
	public static OrthographicCamera mainCamera;
	public static TextureAtlas charactersAtlas;
	public static TextureAtlas cenfomonsAtlas;
	public static Skin boxAtlas;
	public static Player playerRef;

	public static PlayerData playerData;
	public static InputMultiplexer multiplexer = new InputMultiplexer();

	public BaseScreen curScreen;
	public ScreensManager screensManager;

	@Override
	public void create () {

		TestData.initData();

		ArrayList<BattleCenfomon> playerCenfomons = new ArrayList<>();
		playerCenfomons.add(TestData.cenfomons[0]);
		playerCenfomons.add(TestData.cenfomons[1]);
		playerCenfomons.add(TestData.cenfomons[2]);

		ArrayList<UsableItem> playerUsableItems = new ArrayList<>();
		playerUsableItems.add(new UsableItem(TestData.items[0], 10));
		playerUsableItems.add(new UsableItem(TestData.items[1], 4));
		playerUsableItems.add(new UsableItem(TestData.items[2], 3));
		playerUsableItems.add(new UsableItem(TestData.items[3], 1));

		playerData = new PlayerData("Fred", "Male", playerCenfomons, playerUsableItems, "Start_town.tmx", new Vector2(70 / PIX_PER_MTR, 60 / PIX_PER_MTR), new Vector2(0, 1), 1200);

		//Delegates render to Screen Instance
		batch = new SpriteBatch();
		charactersAtlas = new TextureAtlas("Sprites/Characters/CharactersAtlas.pack");
		cenfomonsAtlas = new TextureAtlas("Sprites/Cenfomons/Cenfomons.pack");
		boxAtlas = new Skin();
		boxAtlas.addRegions(new TextureAtlas("graphics_packed/uipack.atlas"));
		screensManager = new ScreensManager(this);
		screensManager.loadLastScreen();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		AudioManager.getInstance().dispose();
	}
}
