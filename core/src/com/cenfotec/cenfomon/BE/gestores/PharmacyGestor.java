package com.cenfotec.cenfomon.BE.gestores;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.proxy.manager.PharmacyProxy;

public class PharmacyGestor {
    PharmacyProxy ControllerProxy = new PharmacyProxy();
    public void upgradeCenfomon(int option, Cenfomon _Cenfomon){
        switch (option){
            case 1:
                ControllerProxy.healthUp(_Cenfomon);
                break;
            case 2:
                ControllerProxy.XpUp(_Cenfomon);
                break;
            case 3:
                ControllerProxy.PwrAttackUp(_Cenfomon);
                break;
            case 4:
                ControllerProxy.PwDeffenceUp(_Cenfomon);
                break;

            default:
                System.out.println("ERROR ON PHARMACY ELECTION");
                break;
        }
    }
}
