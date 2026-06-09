package com.xhl.pvz.core;

public class GameConfig {
    public static final int WINDOW_WIDTH = 1400;
    public static final int WINDOW_HEIGHT = 600;
    public static final int FPS = 60;
    public static final int FRAME_DELAY = 1000/FPS;
    public static final boolean DEBUG_GRID = false;
    public static final String TITLE = "Plant vs. Zombies Java";

       // 草坪网格参数，适配 1400 × 600 的 lawn_day 背景
    public static final int LAWN_ROW_COUNT = 5;
    public static final int LAWN_COL_COUNT = 9;

    public static final int LAWN_START_X = 250;
    public static final int LAWN_START_Y = 80;

    public static final int LAWN_CELL_WIDTH = 83;
    public static final int LAWN_CELL_HEIGHT = 100;

    // 当前植物渲染尺寸
    public static final int PLANT_WIDTH = 80;
    public static final int PLANT_HEIGHT = 80;
    
}
