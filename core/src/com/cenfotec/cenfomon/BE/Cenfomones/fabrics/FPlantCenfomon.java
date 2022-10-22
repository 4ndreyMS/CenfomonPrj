package com.cenfotec.cenfomon.BE.Cenfomones.fabrics;

import com.cenfotec.cenfomon.BE.Cenfomones.abstract_factory.FCenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.Cenfomones.product.PlantCenfomon;

public class FPlantCenfomon implements FCenfomon {
    @Override
    public Cenfomon createCenfomon() {
        return new PlantCenfomon();
    }
}
