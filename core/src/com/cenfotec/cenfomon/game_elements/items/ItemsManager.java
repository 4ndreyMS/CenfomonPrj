package com.cenfotec.cenfomon.game_elements.items;

import com.cenfotec.cenfomon.game_logic.entities.BattleCenfomon;

public class ItemsManager {
    public boolean useItem(UsableItem p_item, BattleCenfomon p_cenfomon) {
        if (p_item.getItem().getEffect().equals("SANAR")) {
            //Life greater that 0 and less than max health
            if (p_cenfomon.getHealthPoints() > 0 && p_cenfomon.getHealthPoints() < p_cenfomon.getMaxHealthPoints()) {
                p_cenfomon.setHealthPoints((int)(p_cenfomon.getHealthPoints() + (p_cenfomon.getMaxHealthPoints() * p_item.getItem().getHealthPrecentage())));
                p_cenfomon.clampValues();

                //Decrease item quantity
                p_item.setQuantity(p_item.getQuantity() - 1);
                return true;
            }
        }

        if (p_item.getItem().getEffect().equals("REVIVIR")) {
            //Use only if cenfomon is weakened
            if (p_cenfomon.getHealthPoints() == 0) {
                p_cenfomon.setHealthPoints((int)(p_cenfomon.getMaxHealthPoints() * p_item.getItem().getHealthPrecentage()));
                p_cenfomon.clampValues();

                //Decrease item quantity
                p_item.setQuantity(p_item.getQuantity() - 1);
                return true;
            }
        }

        if (p_item.getItem().getEffect().equals("METAMORFOSIS")) {
            p_cenfomon.clampValues();
        }

        if (p_item.getItem().getEffect().equals("ATRAPAR")) {
            p_cenfomon.clampValues();
        }

        return false;
    }
}
