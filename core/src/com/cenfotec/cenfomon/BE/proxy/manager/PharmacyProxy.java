package com.cenfotec.cenfomon.BE.proxy.manager;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.proxy.realSubject.Pharmacy;
import com.cenfotec.cenfomon.BE.proxy.subject.iPharmacy;

public class PharmacyProxy implements iPharmacy {

    private final Pharmacy pharmacy = new Pharmacy();
    private boolean modified = false;


    @Override
    public boolean healthUp(Cenfomon HCenfomon) {
        if (HCenfomon.getInitialHp() <200){
            pharmacy.healthUp(HCenfomon);
            modified = true;
        }
        return modified;

    }

    @Override
    public boolean XpUp(Cenfomon XCenfomon) {
        if (XCenfomon.getInitialExp() < 100){
            pharmacy.XpUp(XCenfomon);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean PwrAttackUp(Cenfomon PwCenfomon) {
        if (PwCenfomon.getInitialAttack() < 100){
            pharmacy.PwrAttackUp(PwCenfomon);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean PwDeffenceUp(Cenfomon PdCenfomon) {
        if (PdCenfomon.getInitialDefense() < 100){
            pharmacy.PwDeffenceUp(PdCenfomon);
            modified = true;
        }
        return modified;
    }
}
