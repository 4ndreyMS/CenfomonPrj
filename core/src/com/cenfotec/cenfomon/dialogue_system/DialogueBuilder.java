package com.cenfotec.cenfomon.dialogue_system;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DialogueBuilder {
    public static Dialogue buildDialogue(JSONArray data) {
        Dialogue dialogue = new Dialogue();
        for (int i = 0; i < data.size(); i++) {
            JSONObject node = (JSONObject) data.get(i);
            System.out.println(node.toJSONString());
            Long id = (Long) node.get("id");
            DialogueNode dialogueNode = new DialogueNode((String) node.get("content"), id.intValue());
            if (node.get("pointer") != null) {
                Long pointer = (Long) node.get("pointer");
                dialogueNode.makeLinear(pointer.intValue());
            }
            if (node.get("choices") != null) {
                JSONArray choicesArray = (JSONArray) node.get("choices");
                for(int j = 0; j < choicesArray.size(); j++) {
                    JSONObject choice = (JSONObject) choicesArray.get(j);
                    Long l = (Long) choice.get("option_id");
                    if (choice.get("points") != null) {
                        Long points = (Long) choice.get("points");
                        dialogueNode.addChoice((String) choice.get("option"), l.intValue(), points.intValue());
                    } else {
                        dialogueNode.addChoice((String) choice.get("option"), l.intValue());
                    }
                }
            }
            dialogue.addNode(dialogueNode);
        }
        return dialogue;
    }
}
