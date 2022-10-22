package com.cenfotec.cenfomon.ui_stages.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.core.tools.TextUtils;
import com.cenfotec.cenfomon.game_elements.battle_system.BattleManager;
import com.cenfotec.cenfomon.game_elements.battle_system.BattlePlayer;
import com.cenfotec.cenfomon.game_elements.items.UsableItem;
import com.cenfotec.cenfomon.game_logic.entities.Attack;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;
import com.cenfotec.cenfomon.ui_stages.battles.PlayerCenfomonsStage;

public class ItemsStage  extends UIStageBase {
    private Skin skin;
    private BitmapFont _defaultFont;

    public Table _mainContainer;

    private UsableItem _activeItem;
    private BattlePlayer _activePlayer;
    private BattleManager _battleManager;

    private Label _itemName;
    private Label _itemDesc;

    public ItemsStage(Viewport p_viewport, SpriteBatch p_batch, BattleManager p_battleManager, BattlePlayer p_player) {
        super(p_viewport, p_batch, false);
        this._activePlayer = p_player;
        this._battleManager = p_battleManager;
        defineActors();
    }

    public void onItemPressed(UsableItem p_item) {
        _activeItem = p_item;
        _itemName.setText(_activeItem.getItem().getName());
        _itemDesc.setText(TextUtils.getWrappedText(_activeItem.getItem().getDescription(), 26));
    }

    public void onUseItemPressed() {
        if (_activeItem == null) return;
        if (_activeItem.getQuantity() == 0) {
            _battleManager.showDialogue("No tienes unidades disponibles de este item para usarlo", 2.0f);
            return;
        }

        if (_activeItem.getItem().getEffect().equals("ATRAPAR")) {
            //TODO: Logica para atrapar
        } else {
            //Si no es atrapar entonces es un item para el que se debe especificar el cenfomon
            UIManager.getInstance().setActiveStage(new PlayerCenfomonsStage(UIManager.getInstance().viewport, UIManager.getInstance().batch, this._battleManager, this._activePlayer, this._activeItem), false);
        }
    }

    public void onBackPressed() {
        UIManager.getInstance().setPreviousStageAsActive();
    }

    private String tryGetAttackName(BattleCenfomon p_cenfomon, int p_atkIndex) {
        Attack attack = p_cenfomon.getAttack(p_atkIndex);
        if (attack != null)
            return attack.getName();
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

        Table itemBtnsTable = new Table();
        Table itemDescTable = new Table();

        //items initialization
        for (int i = 0; i < _activePlayer.getItems().size(); i++) {
            if (_activePlayer.getItems().get(i).getQuantity() > 0) {    //Only display items if quantity is greate than 0
                itemBtnsTable.add(defineItemButton(_activePlayer.getItems().get(i))).expandX().fillX();
                itemBtnsTable.row();
            }
        }

        _itemName = new Label("Item name", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _itemName.setAlignment(Align.top);
        _itemDesc = new Label("Item description", new Label.LabelStyle(_defaultFont, Color.WHITE));
        _itemDesc.setAlignment(Align.left);

        itemDescTable.add(_itemName);
        itemDescTable.row();
        itemDescTable.add(_itemDesc).height(130);
        itemDescTable.row();

        TextButton useItemBtn = new TextButton("Usar", skin);
        useItemBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onUseItemPressed();
            }
        });
        useItemBtn.bottom();
        itemDescTable.add(useItemBtn).width(180).height(40);

        TextButton backBtn = new TextButton("Volver", skin);
        //backBtn.setFillParent(true);
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onBackPressed();
            }
        });

        _mainContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Sprites/Menus/Rect.png")))));
        _mainContainer.setColor(new Color(0, 0, 0,0.85f));

        _mainContainer.add(itemBtnsTable).width(230).fillY();
        _mainContainer.add(itemDescTable).expand().width(220).fillY();
        _mainContainer.row();
        _mainContainer.add(backBtn).fillX().colspan(2);
        this.addActor(_mainContainer);

        onItemPressed(_activePlayer.getItems().get(0));
    }

    @Override
    public void update(float delta) {

    }

    private TextButton defineItemButton(UsableItem p_item) {
        TextButton itemBtn = new TextButton(p_item.getItem().getName() + "     x" + p_item.getQuantity(), skin);
        itemBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onItemPressed(p_item);
            }
        });
        return itemBtn;
    }
}
