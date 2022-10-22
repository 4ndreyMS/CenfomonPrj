package com.cenfotec.cenfomon.ui_stages.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class UIStageBase extends Stage {
    public UIStageBase(Viewport p_viewport, SpriteBatch p_batch) {
        super(p_viewport, p_batch);
        defineActors();
    }

    public UIStageBase(Viewport p_viewport, SpriteBatch p_batch, boolean p_defineActors) {
        super(p_viewport, p_batch);
        if (p_defineActors)
            defineActors();
    }
    
    public abstract void defineActors();
    public abstract void update(float delta);
}
