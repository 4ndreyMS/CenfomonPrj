package com.cenfotec.cenfomon.ui_stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.controllers.OptionBoxController;
import com.cenfotec.cenfomon.dialogue_system.Dialogue;
import com.cenfotec.cenfomon.dialogue_system.DialogueNode;
import com.cenfotec.cenfomon.dialogue_system.DialogueTraverser;
import com.cenfotec.cenfomon.game_elements.ui.DialogueBox;
import com.cenfotec.cenfomon.game_elements.ui.OptionBox;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.base.UIStageBase;
import com.cenfotec.cenfomon.ui_stages.dialogue_observer_interfaces.DialogueObserver;
import com.cenfotec.cenfomon.ui_stages.dialogue_observer_interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

public class QuestionDialogueStage extends UIStageBase implements Subject {
    private boolean showDialogue;
    private Image optionsBackground;
    private Image optionsBackgroundPharmacy;
    private Image dialogueBackground;
    private OptionBox optionBox;
    private DialogueBox dialogueBox;
    private DialogueTraverser dialogueTraverser;
    private Skin skin;
    private List<DialogueObserver> observers = new ArrayList<DialogueObserver>();
    private int questionsPoints = 0;
    private GameInstance gameInstance;


    private float dialogueTime;

    public QuestionDialogueStage(Viewport p_viewport, SpriteBatch p_batch, Skin skin) {
        super(p_viewport, p_batch);
        this.skin = skin;
    }

    @Override
    public void defineActors() {
        optionsBackground = new Image(new Texture(Gdx.files.internal("Sprites/Menus/menuBrown.png")));
        optionsBackground.setWidth(100);
        optionsBackground.setHeight(50);
        optionsBackground.setPosition(348,100);
        optionsBackground.setVisible(false);
        addActor(optionsBackground);

        optionsBackgroundPharmacy = new Image(new Texture(Gdx.files.internal("Sprites/Menus/menuBrown.png")));
        optionsBackgroundPharmacy.setWidth(100);
        optionsBackgroundPharmacy.setHeight(100);
        optionsBackgroundPharmacy.setPosition(348,100);
        optionsBackgroundPharmacy.setVisible(false);
        addActor(optionsBackgroundPharmacy);


        dialogueBackground = new Image(new Texture(Gdx.files.internal("Sprites/Menus/menuBrown.png")));
        dialogueBackground.setWidth(GameInstance.V_WIDTH);
        dialogueBackground.setHeight(100);
        dialogueBackground.setPosition(2, 2);
        dialogueBackground.setVisible(false);
        addActor(dialogueBackground);

        dialogueBox = new DialogueBox(this.skin);
        dialogueBox.setWidth(GameInstance.V_WIDTH - 50);
        dialogueBox.setHeight(100);
        dialogueBox.setPosition(2 ,30);
        addActor(dialogueBox);

        optionBox = new OptionBox(this.skin);
        optionBox.setPosition(348, 100);
        optionBox.setHeight(50);
        optionBox.setWidth(100);
        this.addActor(optionBox);
    }

    @Override
    public void update(float delta) {
        if (!showDialogue) return;
        dialogueTime += delta;

        if (dialogueTime > 0.2f) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                if (dialogueTraverser.getType() == DialogueNode.NODETYPE.END) {
                    UIManager.getInstance().setPreviousStageAsActive();
                    notifyObservers(questionsPoints);
                    dialogueTraverser = null;
                    dialogueBackground.setVisible(false);
                    optionsBackground.setVisible(false);
                    optionsBackgroundPharmacy.setVisible(false);

                } else if (dialogueTraverser.getType() == DialogueNode.NODETYPE.LINEAR) {
                    progress(0);
                } else if (dialogueTraverser.getType() == DialogueNode.NODETYPE.MULTIPLE_CHOICE) {
                    this.updateQuestionPoints(optionBox.getSelectedChoicePoints());
                    progress(optionBox.getSelected());
                }
            }
        }
    }

    private void updateQuestionPoints(int gainedPoints) {
        this.questionsPoints += gainedPoints;
    }

    private void resetPoints() {
        this.questionsPoints = 0;
    }


    public void startDialogue(String dialogueText, String option1, String option2) {
        optionBox.addOption(option1);
        optionBox.addOption(option2);
        dialogueBox.initDialogueBox(dialogueText);
        dialogueBackground.setVisible(true);
        optionsBackground.setVisible(true);
        OptionBoxController optionBoxController = new OptionBoxController(this.optionBox);
        GameInstance.multiplexer.addProcessor(optionBoxController);
        Gdx.input.setInputProcessor(optionBoxController);
        showDialogue = true;
        dialogueTime = 0.0f;
    }

    public void startDialoguePharmacy(Dialogue dialogue){
        this.dialogueTraverser = new DialogueTraverser(dialogue);
        dialogueBox.initDialogueBox(dialogueTraverser.getText());
        if (dialogueTraverser.getType() == DialogueNode.NODETYPE.MULTIPLE_CHOICE) {
            optionBox.clear();
            for (String s: dialogue.getNode(dialogue.getStart()).getLabels()) {
                optionBox.addOption(s);
            }
            optionsBackgroundPharmacy.setVisible(true);
        }
        dialogueBackground.setVisible(true);
        OptionBoxController optionBoxController = new OptionBoxController(this.optionBox);
        GameInstance.multiplexer.addProcessor(optionBoxController);
        Gdx.input.setInputProcessor(optionBoxController);
        showDialogue = true;
        dialogueTime = 0.0f;
    }

    public void startDialogue(Dialogue dialogue) {
        this.dialogueTraverser = new DialogueTraverser(dialogue);
        dialogueBox.initDialogueBox(dialogueTraverser.getText());
        if (dialogueTraverser.getType() == DialogueNode.NODETYPE.MULTIPLE_CHOICE) {
            optionBox.clear();
            for (String s: dialogue.getNode(dialogue.getStart()).getLabels()) {
                optionBox.addOption(s);
            }
            optionsBackground.setVisible(true);
        }
        dialogueBackground.setVisible(true);
        OptionBoxController optionBoxController = new OptionBoxController(this.optionBox);
        GameInstance.multiplexer.addProcessor(optionBoxController);
        Gdx.input.setInputProcessor(optionBoxController);
        showDialogue = true;
        dialogueTime = 0.0f;


    }

    public void progress(int index) {
        optionBox.setVisible(false);
        optionsBackground.setVisible(false);
        DialogueNode nextNode = dialogueTraverser.getNextNode(index);
        dialogueBox.setText(nextNode.getText());
        if (nextNode.getType() == DialogueNode.NODETYPE.MULTIPLE_CHOICE) {
            optionBox.clearChoices();
            for (int i = 0;  i < nextNode.getLabels().size(); i++) {
                optionBox.addOption(nextNode.getLabels().get(i), nextNode.getPoints().get(i));
            }
            optionBox.setVisible(true);
            optionsBackground.setVisible(true);
        }
        dialogueBackground.setVisible(true);
        OptionBoxController optionBoxController = new OptionBoxController(this.optionBox);
        GameInstance.multiplexer.addProcessor(optionBoxController);
        Gdx.input.setInputProcessor(optionBoxController);
        showDialogue = true;
        dialogueTime = 0.0f;
    }

    @Override
    public void addObserver(DialogueObserver dialogueObserver) {
        this.observers.add(dialogueObserver);
    }

    @Override
    public void removeObserver(DialogueObserver dialogueObserver) {
        this.observers.remove(dialogueObserver);
    }

    @Override
    public void notifyObservers(int value) {
        observers.forEach(observer -> observer.update(value));
    }
}
