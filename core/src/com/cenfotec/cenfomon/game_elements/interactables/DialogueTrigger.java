package com.cenfotec.cenfomon.game_elements.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.cenfotec.cenfomon.BE.TestData;
import com.cenfotec.cenfomon.BE.gestores.JsonGestor;
import com.cenfotec.cenfomon.BE.PersonajePrueba;
import com.cenfotec.cenfomon.BE.gestores.PharmacyGestor;
import com.cenfotec.cenfomon.GameInstance;
import com.cenfotec.cenfomon.controllers.OptionBoxController;
import com.cenfotec.cenfomon.core.physics.PhysicsLayers;
import com.cenfotec.cenfomon.dialogue_system.Dialogue;
import com.cenfotec.cenfomon.dialogue_system.DialogueBuilder;
import com.cenfotec.cenfomon.dialogue_system.DialogueNode;
import com.cenfotec.cenfomon.dialogue_system.DialogueTraverser;
import com.cenfotec.cenfomon.game_elements.interactables.base.Interactablebase;
import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;
import com.cenfotec.cenfomon.managers.UIManager;
import com.cenfotec.cenfomon.ui_stages.DialogueStage;
import com.cenfotec.cenfomon.ui_stages.QuestionDialogueStage;
import com.cenfotec.cenfomon.ui_stages.dialogue_observer_interfaces.DialogueObserver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogueTrigger extends Interactablebase implements DialogueObserver {
    protected String dialogue_id;
    private OptionBoxController optionBoxController;
    private JsonGestor jsonGestor;
    private PharmacyGestor ph = new PharmacyGestor();
    GameInstance gameInstance;
    PersonajePrueba personajePrueba = new PersonajePrueba();

    /**metoth that obtains the id  of the interactable object**/
    public DialogueTrigger(World world, MapObject object) {
        super(world, object);
        this.dialogue_id = tileProperties.get("dialogue_id").toString();
        jsonGestor = new JsonGestor();
    }

    @Override
    public void start() {
        return;
    }
    @Override

    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (playerInRange && requiresInput) {
                interact();
            }
        }
    }

    @Override
    public void onDestroy() {
        return;
    }

    @Override
    public void onCollisionEnter(Fixture fixture) {
        if (fixture.getFilterData().categoryBits == PhysicsLayers.PLAYER_BIT) {
            playerInRange = true;
            if (!requiresInput) {
                interact();
            }
        }
    }

    @Override
    public void onCollisionExit(Fixture fixture) {
        if (fixture.getFilterData().categoryBits == PhysicsLayers.PLAYER_BIT) {
            playerInRange = false;
        }
    }

/**metodo que interactua con los objetos interactuables**/
    @Override
    public void interact() {
        if (UIManager.getInstance().getActiveStage().getClass() != DialogueStage.class) {
            UIManager.getInstance().setActiveStage(UIManager.StagesEnum.DIALOGUE);
        }


        if (dialogue_id.equals("osotias_delivery")){
            System.out.println("OSOTIAS");
            JSONArray dialogueData = jsonGestor.getDialogueById("osotias_delivery");
            Dialogue dialogue = DialogueBuilder.buildDialogue(dialogueData);
            UIManager.getInstance().setActiveStage(UIManager.StagesEnum.QUESTIONDIALOGUE);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).addObserver(this);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).startDialogue(dialogue);
        }

        if (dialogue_id.equals("fenixluna_delivery")){
            System.out.println("FENIXLUNA");
            JSONArray dialogueData = jsonGestor.getDialogueById("fenixluna_delivery");
            Dialogue dialogue = DialogueBuilder.buildDialogue(dialogueData);
            UIManager.getInstance().setActiveStage(UIManager.StagesEnum.QUESTIONDIALOGUE);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).addObserver(this);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).startDialogue(dialogue);
        }


        if (dialogue_id.equals("prof_fonseca_dialiogue")){
            JSONArray dialogueData = jsonGestor.getDialogueById("level_1_star_dialogue");
            Dialogue dialogue = DialogueBuilder.buildDialogue(dialogueData);
            UIManager.getInstance().setActiveStage(UIManager.StagesEnum.QUESTIONDIALOGUE);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).addObserver(this);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).startDialogue(dialogue);
        }

        if (dialogue_id.equals("phamacy1_interact_dialogue")){
            System.out.println("interact pharmacy");
            JSONArray dialogueData = jsonGestor.getDialogueById("pharmacy_diaglogue");
            Dialogue dialogue = DialogueBuilder.buildDialogue(dialogueData);
            UIManager.getInstance().setActiveStage(UIManager.StagesEnum.QUESTIONDIALOGUE);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).addObserver(this);
            ((QuestionDialogueStage) UIManager.getInstance().getActiveStage()).startDialogue(dialogue);
        }
        gameInstance.playerRef.makeStaticPlayer2S();

    }

    @Override
    public void update(int value) {
        if (dialogue_id.equals("prof_fonseca_dialiogue")) {
            if (value > 12) {
                dialogue_id = "osotias_delivery";
                TestData.addOsotias();
                interact();
            } else {
                dialogue_id = "fenixluna_delivery";
                TestData.addFenixLuna();
                interact();
            }
        }

        if (dialogue_id.equals("phamacy1_interact_dialogue")) {
            ph.upgradeCenfomon(value, personajePrueba.getCenfomon());
        }
    }
}
