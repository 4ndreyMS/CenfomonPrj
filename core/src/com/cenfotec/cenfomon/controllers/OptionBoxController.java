package com.cenfotec.cenfomon.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.cenfotec.cenfomon.game_elements.ui.OptionBox;

public class OptionBoxController extends InputAdapter {
    private OptionBox box;
    public OptionBoxController(OptionBox box) {
        this.box = box;
    }

    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.I) {
            box.moveUp();
        } else if (keycode == Input.Keys.K) {
            box.moveDown();
        }
        return false;
    }
}
