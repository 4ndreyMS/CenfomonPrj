package com.cenfotec.cenfomon.BE.proxy.realSubject;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.proxy.subject.iPharmacy;

public class Pharmacy implements iPharmacy {
    @Override
    public boolean healthUp(Cenfomon HCenfomon) {
        System.out.println(HCenfomon.getInitialExp());//quitar
        HCenfomon.setInitialHp(200);
        System.out.println("vida aumentada");//quitar
        return true;
    }

    @Override
    public boolean XpUp(Cenfomon XCenfomon) {
        if (XCenfomon.getInitialExp() < 30) {
            System.out.println(XCenfomon.getInitialExp());//quitar
            XCenfomon.setInitialExp(50);
            System.out.println("xp aumentada 50");//quitar
        }else if (XCenfomon.getInitialExp() < 65 && XCenfomon.getInitialExp() >= 50) {
            System.out.println(XCenfomon.getInitialExp());//quitar
            XCenfomon.setInitialExp(75);
            System.out.println("xp aumentada 75");//quitar
        }else{
            System.out.println(XCenfomon.getInitialExp());//quitar
            XCenfomon.setInitialExp(100);
            System.out.println("xp aumentada 100");//quitar

        }
        return true;
    }

    @Override
    public boolean PwrAttackUp(Cenfomon PwCenfomon) {
        if (PwCenfomon.getInitialAttack() < 30) {
            System.out.println(PwCenfomon.getInitialExp());//quitar
            PwCenfomon.setInitialAttack(50);
            System.out.println("PW aumentada 50");//quitar

        }else if (PwCenfomon.getInitialAttack() < 65 && PwCenfomon.getInitialAttack() >= 50) {
            System.out.println(PwCenfomon.getInitialExp());//quitar
            PwCenfomon.setInitialAttack(75);
            System.out.println("PW aumentada 75");//quitar
        }else{
            System.out.println(PwCenfomon.getInitialExp());//quitar
            PwCenfomon.setInitialAttack(100);
            System.out.println("PW aumentada 100");//quitar

        }
        return true;

    }

    @Override
    public boolean PwDeffenceUp(Cenfomon PdCenfomon) {
        if (PdCenfomon.getInitialDefense() < 30) {
            System.out.println(PdCenfomon.getInitialDefense());//quitar
            PdCenfomon.setInitialDefense(50);
            System.out.println("Pd aumentada 50");//quitar

        }else if (PdCenfomon.getInitialDefense() < 65 && PdCenfomon.getInitialDefense() >= 50) {
            System.out.println(PdCenfomon.getInitialDefense());//quitar
            PdCenfomon.setInitialDefense(75);
            System.out.println("Pd aumentada 75");//quitar

        }else{
            System.out.println(PdCenfomon.getInitialDefense());//quitar
            PdCenfomon.setInitialDefense(100);
            System.out.println("Pd aumentada 100");//quitar
        }
        return true;
    }
}
