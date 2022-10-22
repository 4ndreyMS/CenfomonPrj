package com.cenfotec.cenfomon.ui_stages.battles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.game_elements.battle_system.BattlePlayer;
import com.cenfotec.cenfomon.game_elements.items.ItemsManager;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class PlayerCenfomonsStage extends UIStageBase {
    public enum StageRequest {
        CHANGE_CENFOMON,
        USE_ITEM
    }

    private Skin skin;
    private BitmapFont _defaultFont;
    private BattleManager _battleManager;
    private BattlePlayer _curPlayer;
    private StageRequest _curRequest;
    private UsableItem _curItem;

    public Table _mainContainer;

    //To change cenfomon
    public PlayerCenfomonsStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager, BattlePlayer p_player) {
        super(p_viewport, p_batch, false);
        this._battleManager = p_battleManager;
        this._curPlayer = p_player;
        this._curRequest = StageRequest.CHANGE_CENFOMON;
        defineActors();
    }

    //To use item on cenfomon
    public PlayerCenfomonsStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager, BattlePlayer p_player, UsableItem p_item) {
        super(p_viewport, p_batch, false);
        this._battleManager = p_battleManager;
        this._curPlayer = p_player;
        this._curRequest = StageRequest.USE_ITEM;
        this._curItem = p_item;
        defineActors();
    }

    //When a cenfomon button is pressed
    public void onCenfomonPressed(int p_cenfIndex) {
        BattleCenfomon cenfomon = this._curPlayer.getCenfomon(p_cenfIndex);
        if (cenfomon == null) return;

        switch (_curRequest) {
            case CHANGE_CENFOMON:
                tryChangeCenfomon(cenfomon);
                break;
            case USE_ITEM:
                tryuseItem(_curItem, cenfomon);
                break;
        }
    }

    private void tryChangeCenfomon(BattleCenfomon p_cenfomon) {
        if (p_cenfomon.getHealthPoints() <= 0) {
            _battleManager.showDialogue("No puedes elegir a " + p_cenfomon.getName() + " porque está debilitado", 2.0f);
            return;
        }
        if (_curPlayer.equals(_battleManager.getActivePlayer())) {
            if (p_cenfomon.equals(_battleManager.getActivePlayerCenfomon())) {
                _battleManager.showDialogue(p_cenfomon.getName() + " ya se encuentra en batalla", 2.0f);
                return;
            }
        } else {
            if (p_cenfomon.equals(_battleManager.getNonActivePlayerScreenCenfomon())) {
                _battleManager.showDialogue(p_cenfomon.getName() + " ya se encuentra en batalla", 2.0f);
                return;
            }
        }

        _battleManager.changeCenfomon(this._curPlayer, p_cenfomon);
    }

    private void tryuseItem(UsableItem p_item, BattleCenfomon p_cenfomon) {
        _battleManager.useItem(p_item, p_cenfomon);
    }

    public void onBackPressed() {
        //Impossible to go back if you must change cenfomon
        if (_battleManager.isCenfomonWeakened())
        {
            _battleManager.showDialogue("Debes seleccionar un cenfomon", 2.0f);
            return;
        }

        UIManager.getInstance().setPreviousStageAsActive();
    }

    private String tryGetCenfomonData(BattlePlayer p_player, int p_cenfIndex) {
        BattleCenfomon cenfomon = p_player.getCenfomon(p_cenfIndex);
        if (cenfomon != null)
            return cenfomon.getNickname() + "    PV: " + cenfomon.getHealthPoints() + "/" + cenfomon.getMaxHealthPoints();
        return "-";
    }

    @Override
    public void defineActors() {
        skin = new Skin(Gdx.files.internal("UISkin/uiskin.json"));
        _defaultFont = new BitmapFont();

        _mainContainer = new Table();
        _mainContainer.bottom();
        _mainContainer.setFillParent(true);
        //_mainContainer.debug();

        Table btnsTable = new Table();

        TextButton backBtn = new TextButton("Volver", skin);
        //backBtn.setFillParent(true);
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onBackPressed();
            }
        });

        btnsTable.add(defineCenfomonButton(0)).expandX().width(225);
        btnsTable.add(defineCenfomonButton(1)).expandX().width(225);
        btnsTable.row();
        btnsTable.add(defineCenfomonButton(2)).expandX().width(225);
        btnsTable.add(defineCenfomonButton(3)).expandX().width(225);
        btnsTable.row();
        btnsTable.add(defineCenfomonButton(4)).expandX().width(225);
        btnsTable.add(defineCenfomonButton(5)).expandX().width(225);
        btnsTable.row();
        btnsTable.add(defineCenfomonButton(6)).expandX().width(225);
        btnsTable.add(backBtn).colspan(4).expandX().fillX().center();

        _mainContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _mainContainer.setColor(new Color(0, 0, 0,0.85f));

        _mainContainer.add(btnsTable).expandX().fillX();
        this.addActor(_mainContainer);
    }

    private TextButton defineCenfomonButton(int p_index) {
        TextButton cenfBtn = new TextButton(tryGetCenfomonData(this._curPlayer, p_index), skin);
        cenfBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onCenfomonPressed(p_index);
            }
        });
        return cenfBtn;
    }

    @Override
    public void update(float delta) {

    }
}
