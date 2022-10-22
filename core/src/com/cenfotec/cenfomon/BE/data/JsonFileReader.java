package com.cenfotec.cenfomon.BE.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileReader {
    public static String fileUrl;
    private static File file;
    private static JsonFileReader jsonFileReader = null;

    public static JsonFileReader loadFile(String url) {
        if (jsonFileReader == null) {
            fileUrl = url;
            jsonFileReader = new JsonFileReader(url);
        }
        return jsonFileReader;
    }

    public static void resetJsonFileReader() {
        jsonFileReader = null;
    }

    private JsonFileReader(String url) {
        this.setFileUrl(url);
        file = new File(url);
    }

    private void setFileUrl(String url) {
        fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public File getFile() {
        return file;
    }

    public static JSONObject getJsonData() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(jsonFileReader.getFileUrl());
        Object obj = jsonParser.parse(reader);
        return (JSONObject) obj;
    }

    public static JSONArray getJsonArray() throws Exception, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(jsonFileReader.getFileUrl());
        Object obj = jsonParser.parse(reader);
        return (JSONArray) obj;
    }

}
