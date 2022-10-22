package com.cenfotec.cenfomon.BE.proxy.subject;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
public interface iPharmacy {
    boolean healthUp(Cenfomon HCenfomon);
    boolean XpUp(Cenfomon XCenfomon);

    boolean PwrAttackUp(Cenfomon PwCenfomon);
    boolean PwDeffenceUp(Cenfomon PdCenfomon);
}
