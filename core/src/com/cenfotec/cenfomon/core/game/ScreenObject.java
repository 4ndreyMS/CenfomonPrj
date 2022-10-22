package com.cenfotec.cenfomon.core.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class ScreenObject {
    private ArrayList<String> tags;
    public boolean setToDestroy;
    public boolean visible;

    public ScreenObject() {
        tags = new ArrayList<>();
        setToDestroy = false;
        visible = true;
    }

    public abstract void start();
    public abstract void update(float delta);
    public abstract void onDestroy();
    public abstract void setPosition(Vector2 position);
    public abstract Vector2 getPosition();

    public void destroy() {
        setToDestroy = true;
    }

    public void addTag(String p_tag) {
        if (!tags.contains(p_tag))
            tags.add(p_tag);
    }
    public void removeTag(String p_tag) {
        if (tags.contains(p_tag))
            tags.remove(p_tag);
    }
    public boolean hasTag(String p_tag) {
        return tags.contains(p_tag);
    }
}
