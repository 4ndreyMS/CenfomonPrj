package com.cenfotec.cenfomon.BE.gestores;

import com.cenfotec.cenfomon.BE.data.TSVFileReader;
import com.cenfotec.cenfomon.BE.entities.BattleCenfomon;

import java.util.ArrayList;

public class CenfomonTeamGestor {
    private ArrayList<BattleCenfomon> cenfomonTeam;

    public CenfomonTeamGestor() {
        this.cenfomonTeam = new ArrayList<>();
    }

    public CenfomonTeamGestor(ArrayList<BattleCenfomon> cenfomonTeam) {
        this.cenfomonTeam = cenfomonTeam;
    }


    public ArrayList<BattleCenfomon> getCenfomonList() {
        return this.cenfomonTeam;
    }

    public void getBattleCenfomons() {
        try {
            cenfomonTeam.clear();
            TSVFileReader.loadFile("CenfomonTeam");
            ArrayList<String> cenfomonsData = TSVFileReader.readFile();
            int index = 0;
            while (index < cenfomonsData.size()) {
                if (index > 0) {
                    String line = cenfomonsData.get(index);
                    String[] cenfomonData = line.split("\t");



                }
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addCenfomonToTeam(BattleCenfomon cenfomon) {
        this.addCenfomonToTeam(cenfomon);
    }

    private BattleCenfomon generateBattleCenfomon(String[] cenfomonData) {
        return null;
    }


}
