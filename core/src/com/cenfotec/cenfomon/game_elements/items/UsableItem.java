package com.cenfotec.cenfomon.game_elements.items;

import com.cenfotec.cenfomon.BE.entities.Item;

public class UsableItem {
    private Item _item;
    private int _quantity;

    public UsableItem(Item p_item, int p_quantity) {
        this._item = p_item;
        this._quantity = p_quantity;
    }

    public Item getItem() {
        return this._item;
    }
    public void setItem(Item p_item) {
        this._item = p_item;
    }

    public int getQuantity() {
        return this._quantity;
    }
    public void setQuantity(int p_quantity) {
        this._quantity = p_quantity;
    }
}
