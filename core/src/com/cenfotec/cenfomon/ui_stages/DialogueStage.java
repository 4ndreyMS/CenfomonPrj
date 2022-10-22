package com.cenfotec.cenfomon.ui_stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.game_elements.ui.DialogueBox;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;

public class DialogueStage extends UIStageBase {
    private boolean inDialogue;
    private Image dialogueBackground;
    private Label dialogueText;
    private Label dialogueTextMenuBeforFigth;
    private Table menuDialog;
    private Skin skin;
    private DialogueBox dialogueBox;

    private float dialogueTime;

    public DialogueStage(Viewport p_viewport, SpriteBatch p_batch, Skin skin) {
        super(p_viewport, p_batch);
        this.skin = skin;
        inDialogue = false;
    }
    /**permite definir las propeidades de los cuadros de la UI**/
    @Override
    public void defineActors() {

        dialogueBackground = new Image(new Texture(Gdx.files.internal("Sprites/Menus/menuBrown.png")));
        dialogueBackground.setWidth(GameInstance.V_WIDTH);
        dialogueBackground.setHeight(100);
        dialogueBackground.setPosition(2,2);
        dialogueBackground.setVisible(false);
        addActor(dialogueBackground);

        dialogueBox = new DialogueBox(this.skin);
        addActor(dialogueBox);
    }

    @Override
    public void update(float delta) {
        if (!inDialogue) return;

        dialogueTime += delta;
        if (dialogueTime > 0.2f) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                this.dialogueBackground.setVisible(false);
                UIManager.getInstance().setPreviousStageAsActive();
            }
        }
    }

    public void dialogBeforFightWild(String _dialogueBeforFight){
        dialogueTextMenuBeforFigth.setText(_dialogueBeforFight);
        inDialogue = true;
        dialogueTime = 0.0f;
    }

    public void startDialogue(String p_dialogue) {
        dialogueBox.initDialogueBox(p_dialogue);
        this.dialogueBackground.setVisible(true);
        inDialogue = true;
        dialogueTime = 0.0f;
    }
}
