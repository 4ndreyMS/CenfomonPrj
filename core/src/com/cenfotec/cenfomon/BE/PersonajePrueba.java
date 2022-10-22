package com.cenfotec.cenfomon.BE;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.product.BugCenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.product.FireCenfomon;

public class PersonajePrueba {

    public PersonajePrueba() {
    }

            /*cenfomon.setInitialHp(30);
        cenfomon.setType("BICHO");
        cenfomon.setDescription("Cenfo de prueba");
        cenfomon.setInitialExp(30);
        cenfomon.setInitialAttack(30);
        cenfomon.setInitialDefense(30);
        cenfomon.setDebilidad("aire");*/
    private Cenfomon cenfomon = new BugCenfomon(12,"prueba","Cenfo de prueba",30,29,29,29,"BICHO","aire");

    public Cenfomon getCenfomon() {
        return cenfomon;
    }

}
