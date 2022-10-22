package com.cenfotec.cenfomon.BE.gestores;

import com.cenfotec.cenfomon.BE.data.JsonFileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JsonGestor {
    private JSONArray object;
    public JsonGestor() {
        this.object = null;
    }

    public JSONArray getDialogueById(String id) {
        try {
            JsonFileReader.loadFile("dialogues.json");
            JSONObject jsonObject = JsonFileReader.getJsonData();
            JSONArray array = (JSONArray) jsonObject.get(id);
            return array;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
