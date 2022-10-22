package com.cenfotec.cenfomon.core.physics;

public class PhysicsLayers {
    public static final short NOTHING_BIT = 0;
    public static final short DEFAULT_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short BLOCK_BIT = 4;
    public static final short ENEMY_BIT = 8;
    public static final short ENEMY_HEAD_BIT = 16;
    public static final short ITEM_BIT = 32;
    public static final short INTERACTABLE_BIT = 64;

    public static final boolean DEBUG_COLLISIONS = false;
}
