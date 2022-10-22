package com.cenfotec.cenfomon.BE.gestores;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_factory.FCenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.fabrics.*;
import com.cenfotec.cenfomon.BE.data.TSVFileReader;
import com.cenfotec.cenfomon.game_logic.enums.TypesEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CenfomonGestor {
    private ArrayList<Cenfomon> cenfomons; // list of cenfomons in memorie
    public CenfomonGestor() {
        this.cenfomons = new ArrayList<>();
    }

    // gets a cenfomon by its id
    public Cenfomon getCenfomonById(int cenfomonId) {
        if (this.cenfomons.isEmpty()) this.getCenfomons();
        for (Cenfomon c: this.cenfomons) {
            if (c.getId() == cenfomonId) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Cenfomon> getCenfomonById(ArrayList<Integer>[] ids) {
        if (this.cenfomons.isEmpty()) this.getCenfomons();
        for (Cenfomon c: this.cenfomons) {
//            if (ids.contains())
        }
        return null;
    }

    // returns the cenfomons list
    // if cenfomons list is empty or the force parameter is true, it will try to get the list of cenfomons (some kind of in memory cache)
    public ArrayList<Cenfomon> getCenfomonsList(boolean force) {
        if (this.cenfomons.isEmpty() || force) {
            this.getCenfomons();
        }
        return this.cenfomons;
    }

    // retrieves the list of cenfomons from the tsv
    public void getCenfomons() {
        try {
            cenfomons.clear();
            TSVFileReader.loadFile("Cenfomones.tsv");
            ArrayList<String> cenfomonsData = TSVFileReader.readFile();
            int index = 0;
            while(index < cenfomonsData.size()) {
                if (index > 0) {
                    String line = cenfomonsData.get(index);
                    String[] cenfomonData = line.split("\t");
                    Cenfomon c = createCenfomon(cenfomonData);
                    if (c != null) {
                        this.cenfomons.add(c);
                    }
                }
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // create the cenfomon, used inside the getCenfomons method
    private Cenfomon createCenfomon(String[] cenfomonData) {
        String primaryType = cenfomonData[7].split(":")[0];
        FCenfomon fCenfomon = getCenfomonFabric(primaryType);
        if (fCenfomon != null) {
            Cenfomon c = fCenfomon.createCenfomon();
            c.setId(Integer.parseInt(cenfomonData[0]));
            c.setName(cenfomonData[1]);
            c.setDescription(cenfomonData[2]);
            c.setInitialHp(Integer.parseInt(cenfomonData[3]));
            c.setInitialAttack(Integer.parseInt(cenfomonData[4]));
            c.setInitialDefense(Integer.parseInt(cenfomonData[5]));
            c.setInitialExp(Integer.parseInt(cenfomonData[6]));
            c.setType(cenfomonData[7]);
            c.setDebilidad(cenfomonData[8]);


            return c;
        } else {
            return null;
        }
    }

    private FCenfomon getCenfomonFabric(String type) {
        switch (type) {
            case "AGUA":
                return new FWaterCenfomon();
            case "FUEGO":
                return new FFireCenfomon();
            case "PLANTA":
                return new FPlantCenfomon();
            case "NORMAL":
                return new FNormalCenfomon();
            case "ELECTRICO":
                return new FElectricCenfomon();
            case "FANTASMA":
                return new FGhostCenfomon();
            case "VOLADOR":
                return new FFlyingCenfomon();
            case "BICHO":
                return new FBugCenfomon();
            default:
                return null;
        }
    }
}
