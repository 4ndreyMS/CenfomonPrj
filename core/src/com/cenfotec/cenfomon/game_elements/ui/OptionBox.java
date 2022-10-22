package com.cenfotec.cenfomon.game_elements.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

public class OptionBox extends Table {
    private int selectedIndex = 0;
    private List<Image> arrows = new ArrayList<>();
    private List<Label> options = new ArrayList<>();
    private List<Integer> optionsPoints = new ArrayList<>();
    private Table uiContainer;
    public OptionBox(Skin skin) {
//        super(skin);
        super(new Skin(new TextureAtlas("graphics_packed/uipack.atlas")));
        uiContainer = new Table();
        this.add(uiContainer);
    }

    public void addOption(String option) {
        Label optionLabel = new Label(option, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        options.add(optionLabel);
        Image arrow = new Image(this.getSkin(), "arrow");
        arrow.setVisible(false);
        arrows.add(arrow);
        uiContainer.add(arrow).expand().align(Align.left).space(5f);
        uiContainer.add(optionLabel).expand().align(Align.left).space(8f);
        uiContainer.setSize(50, 50);
        uiContainer.row();

        calcArrowVisibility();
    }
    public void addOption(String option, int choicePoints) {
        Label optionLabel = new Label(option, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        options.add(optionLabel);
        optionsPoints.add(choicePoints);
        Image arrow = new Image(this.getSkin(), "arrow");
        arrow.setVisible(false);
        arrows.add(arrow);
        uiContainer.add(arrow).expand().align(Align.left).space(5f);
        uiContainer.add(optionLabel).expand().align(Align.left).space(8f);
        uiContainer.setSize(50, 50);
        uiContainer.row();
        calcArrowVisibility();
    }

    private void calcArrowVisibility() {
        for (int i = 0; i < arrows.size(); i++) {
            if (i == selectedIndex) {
                arrows.get(i).setVisible(true);
            } else {
                arrows.get(i).setVisible(false);
            }
        }
    }

    public void moveUp() {
        selectedIndex--;
        if (selectedIndex < 0) {
            selectedIndex = 0;
        }
        calcArrowVisibility();
    }

    public void moveDown() {
        selectedIndex++;
        if (selectedIndex >= options.size()) {
            selectedIndex = options.size() - 1;
        }
        calcArrowVisibility();
    }

    public int getSelected() {
        return this.selectedIndex;
    }

    public int getSelectedChoicePoints() {
        return optionsPoints.get(selectedIndex);
    }

    public void clearChoices() {
        uiContainer.clearChildren();
        arrows.clear();
        options.clear();
        selectedIndex = 0;
    }
}
