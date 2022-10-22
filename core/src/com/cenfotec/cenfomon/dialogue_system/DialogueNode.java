package com.cenfotec.cenfomon.dialogue_system;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
    private ArrayList<Integer> pointers = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<Integer> points = new ArrayList<>();
    private String text;
    private int id;
    private NODETYPE type;

    public enum NODETYPE {
        MULTIPLE_CHOICE, // dialogue with multiple options
        LINEAR, // dialogue with no options or paths
        END, // end dialogue after a node of this type the dialogue will end
    }

    @Override
    public String toString() {
        return "DialogueNode{" +
                "pointers=" + pointers +
                ", labels=" + labels +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    public DialogueNode(String text, int id) {
        this.text = text;
        this.id = id;
        type = NODETYPE.END;
    }

    public void addChoice(String option, int nodeId) {
        // avoid having a linear node type with multiple options
        if (type == NODETYPE.LINEAR) {
            pointers.clear();
        }
        labels.add(option);
        pointers.add(nodeId);
        points.add(0);
        type = NODETYPE.MULTIPLE_CHOICE;
    }

    public void addChoice(String option, int nodeId, int choicePoints) {
        // avoid having a linear node type with multiple options
        if (type == NODETYPE.LINEAR) {
            pointers.clear();
        }
        labels.add(option);
        pointers.add(nodeId);
        points.add(choicePoints);
        type = NODETYPE.MULTIPLE_CHOICE;
    }

    public void makeLinear(int nodeId) {
//         makes the node linear it will recieve the next ndde id
        pointers.clear();
        labels.clear();
        pointers.add(nodeId);
        type = NODETYPE.LINEAR;
    }

    public List<Integer> getPointers() {
        return pointers;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public NODETYPE getType() {
        return type;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
}
