package com.xhl.pvz.core;

public class GameConfig {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;

    public static final int LEVEL_WORLD_WIDTH = 1400;
    public static final int LEVEL_WORLD_HEIGHT = 600;

    public static final int FPS = 60;
    public static final int FRAME_DELAY = 1000 / FPS;

    public static final String TITLE = "Plant vs. Zombies Java";

    public static final boolean DEBUG_GRID = false;

    /**
     * 背景图是 1400x600，窗口只显示其中一段。
     * LEVEL_CAMERA_X 表示镜头从背景图的哪个 x 坐标开始看。
     */
    public static final int LEVEL_CAMERA_X = 120;
    public static final int LEVEL_CAMERA_LAWN_X = LEVEL_CAMERA_X;
    public static final int LEVEL_CAMERA_ROAD_X = 420;
    public static final int LEVEL_CAMERA_SPEED = 16;
    public static final int INTRO_LAWN_PAUSE_TICKS = 60;

    /**
     * 这些是背景图原始坐标，也就是世界坐标。
     */
    public static final int LAWN_ROW_COUNT = 5;
    public static final int LAWN_COL_COUNT = 9;

    public static final int LAWN_START_X = 250;
    public static final int LAWN_START_Y = 85;

    public static final int LAWN_CELL_WIDTH = 83;
    public static final int LAWN_CELL_HEIGHT = 100;

    public static final int PLANT_WIDTH = 80;
    public static final int PLANT_HEIGHT = 80;

    public static final int SUN_SIZE = 40;

    public static final int LAWN_MOWER_WIDTH = 70;
    public static final int LAWN_MOWER_HEIGHT = 55;
    public static final double LAWN_MOWER_SPEED = 14.0;

    public static final int NORMAL_ZOMBIE_HP = 270;
    public static final int CONEHEAD_ZOMBIE_HP = 560;
    public static final int BUCKETHEAD_ZOMBIE_HP = 1100;

    public static final double NORMAL_ZOMBIE_SPEED = 0.45;
    public static final double CONEHEAD_ZOMBIE_SPEED = 0.38;
    public static final double BUCKETHEAD_ZOMBIE_SPEED = 0.32;

    public static final int NORMAL_ZOMBIE_DAMAGE = 20;
    public static final int CONEHEAD_ZOMBIE_DAMAGE = 20;
    public static final int BUCKETHEAD_ZOMBIE_DAMAGE = 20;

    /**
     * 僵尸从草坪右侧外面一点进入，而不是从整张背景图最右边进来。
     */
    public static final int ZOMBIE_SPAWN_X =
            LAWN_START_X + LAWN_COL_COUNT * LAWN_CELL_WIDTH + 50;

    private GameConfig() {
    }

    public static int screenToWorldX(int screenX) {
        return screenX + LEVEL_CAMERA_X;
    }

    public static int worldToScreenX(int worldX) {
        return worldX - LEVEL_CAMERA_X;
    }
}
