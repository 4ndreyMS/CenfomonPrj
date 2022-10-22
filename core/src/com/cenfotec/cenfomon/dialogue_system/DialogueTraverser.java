package com.cenfotec.cenfomon.dialogue_system;

import java.util.List;

public class DialogueTraverser {
    private Dialogue dialogue;
    private DialogueNode currentNode;

    public DialogueTraverser(Dialogue dialogue) {
        this.dialogue = dialogue;
        currentNode = dialogue.getNode(dialogue.getStart());
    }

    public DialogueNode getNextNode(int pointerIndex) {
        DialogueNode nextNode = dialogue.getNode(currentNode.getPointers().get(pointerIndex));
        currentNode = nextNode;
        return nextNode;
    }

    public List<String> getOptions() {
        return currentNode.getLabels();
    }

    public String getText() {
        return currentNode.getText();
    }

    public DialogueNode getCurrentNode() {
        return this.currentNode;
    }

    public DialogueNode.NODETYPE getType() {
        return currentNode.getType();
    }
}
