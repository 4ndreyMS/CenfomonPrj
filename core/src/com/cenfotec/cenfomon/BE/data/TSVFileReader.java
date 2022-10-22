package com.cenfotec.cenfomon.BE.data;

import java.io.*;
import java.util.ArrayList;

public class TSVFileReader {
    private static String fileUrl;
    private static File file;
    private static TSVFileReader tsvFileReader = null;


    public static TSVFileReader loadFile(String url) {
        if (tsvFileReader == null) {
            fileUrl = url;
            tsvFileReader = new TSVFileReader(url);
        }
        return tsvFileReader;
    }

    public static void resetFileReader() {
        tsvFileReader = null;
    }

    private TSVFileReader(String url) {
        this.setFileUrl(url);
        file = new File(url);
    }

    private void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public File getFile() {
        return file;
    }

    public static ArrayList<String> readFile() throws IOException {
        // Funcion que devuelver un array list donde cada linea es una linea del archivo.
        // Retorna un arraylist vacio en caso de que el archivo este vacio
        ArrayList<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(tsvFileReader.file));
        String row;
        while ((row = reader.readLine()) != null) {
            data.add(row);
        }
        reader.close();
        return data;
    }

    public static void addLineToTSV(String line) throws IOException {
        FileWriter writer = new FileWriter(tsvFileReader.file);
        writer.write("\n");
        writer.write(line);
    }
}
