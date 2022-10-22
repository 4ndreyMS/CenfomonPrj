package com.cenfotec.cenfomon.core.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimatedSprite {
    public class AnimatedSpriteAnimation {
        public String name;
        public TextureRegion region;
        public Animation animation;
        public boolean looping;

        public AnimatedSpriteAnimation(String p_name, TextureRegion p_region, boolean p_looping, Animation p_animation) {
            this.name = p_name;
            this.region = p_region;
            this.looping = p_looping;
            this.animation = p_animation;
        }
    }

    public Array<AnimatedSpriteAnimation> animations;
    public AnimatedSpriteAnimation currentAnimation;
    public float animationTime = 0.0f;
    public Sprite spriteRef;


    public int columns;
    public int rows;

    private boolean flipX;
    private boolean flipY;

    public boolean isFlipX() {
        return flipX;
    }
    public boolean isFlipY() {
        return flipY;
    }

    public AnimatedSprite(Sprite p_spriteRef, int p_columns, int p_rows) {
        this.spriteRef = p_spriteRef;
        this.columns = p_columns;
        this.rows = p_rows;

        animations = new Array<AnimatedSpriteAnimation>();
        currentAnimation = null;
    }

    //If playing an animation it applies the current frame to the sprite reference
    public void update(float delta) {
        if (currentAnimation != null) {
            animationTime += delta;
            TextureRegion curRegion = (TextureRegion) currentAnimation.animation.getKeyFrame(animationTime, currentAnimation.looping);
            checkFlips(curRegion);
            spriteRef.setRegion(curRegion);
        }
    }

    //Should the last region flip in any axis?
    public void checkFlips(TextureRegion p_region) {
        if (p_region.isFlipX() != flipX) {
            p_region.flip(true, false);
        }
        if (p_region.isFlipY() != flipY) {
            p_region.flip(false, true);
        }
    }

    //If the animation sent as param exists is applied as current animation
    public void play(String p_animationName) {
        if (currentAnimation != null)
            if (p_animationName.equals(currentAnimation.name)) return;

        boolean foundAnim = false;

        for (int i = 0; i < animations.size; i++) {
            if (animations.get(i).name.equals(p_animationName)) {
                currentAnimation = animations.get(i);
                foundAnim = true;
                break;
            }
        }

        if (foundAnim) {
            animationTime = 0.0f;
        }
    }

    public void flip(boolean p_flipX, boolean p_flipY) {
        this.flipX = p_flipX;
        this.flipY = p_flipY;
    }

    public boolean isAnimationFinished() {
        if (currentAnimation == null) return false;

        return currentAnimation.animation.isAnimationFinished(animationTime);
    }
    /***
     * Use this to add animations to the AnimatedSprite
     * @param p_name the name of the animation
     * @param p_region the sprite region to use
     * @param p_looping should the animation loop when reaches the end?
     * @param p_width the width of the sprite
     * @param p_height the height of the sprite
     * @param p_startFrameIndex the start index of the animation
     * @param p_endFrameIndex the end index of the animation
     * @param p_frameDuration how long will be the wait time between frames
     */
    public void createAnimation(String p_name, TextureRegion p_region, boolean p_looping, int p_width, int p_height, int p_startFrameIndex, int p_endFrameIndex, float p_frameDuration) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = p_startFrameIndex; i < p_endFrameIndex; i++) {
            frames.add(new TextureRegion(p_region, p_width * i, 0, p_width, p_height));
        }

        AnimatedSpriteAnimation anim = new AnimatedSpriteAnimation(p_name, p_region, p_looping, new Animation(p_frameDuration, frames));
        animations.add(anim);
    }

    /***
     * Use this to add animations to the AnimatedSprite using custom indexes
     * @param p_name the name of the animation
     * @param p_region the sprite region to use
     * @param p_looping should the animation loop when reaches the end?
     * @param p_width the width of the sprite
     * @param p_height the height of the sprite
     * @param p_frameIndexes array with the custom indexes to display
     * @param p_frameDuration how long will be the wait time between frames
     */
    public void createAnimation(String p_name, TextureRegion p_region, boolean p_looping, int p_width, int p_height, int[] p_frameIndexes, float p_frameDuration) {
        if (p_frameIndexes.length == 0) return;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < p_frameIndexes.length; i++) {
            frames.add(new TextureRegion(p_region, p_width * p_frameIndexes[i], 0, p_width, p_height));
        }

        AnimatedSpriteAnimation anim = new AnimatedSpriteAnimation(p_name, p_region, p_looping, new Animation(p_frameDuration, frames));
        animations.add(anim);
    }
}
