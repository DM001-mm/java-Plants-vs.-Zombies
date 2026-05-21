package com.xhl.pvz.resource;

public class ImageKeys { //双引号用多了很累，于是 就有了下面的手写枚举类

    private ImageKeys() {
    }

    // background
    public static final String BACKGROUND_MAIN_MENU = "background.main_menu";
    public static final String BACKGROUND_LAWN_DAY = "background.lawn_day";
    public static final String BACKGROUND_GAME_OVER = "background.game_over";
    public static final String BACKGROUND_WIN = "background.win";

    // ui
    public static final String UI_START_BUTTON = "ui.start_button";
    public static final String UI_LOAD_BUTTON = "ui.load_button";
    public static final String UI_EXIT_BUTTON = "ui.exit_button";
    public static final String UI_PAUSE_BUTTON = "ui.pause_button";
    public static final String UI_CONTINUE_BUTTON = "ui.continue_button";
    public static final String UI_SAVE_BUTTON = "ui.save_button";
    public static final String UI_MENU_BUTTON = "ui.menu_button";
    public static final String UI_RESTART_BUTTON = "ui.restart_button";
    public static final String UI_SUN_BANK = "ui.sun_bank";
    public static final String UI_COOLDOWN_MASK = "ui.cooldown_mask";

    // cards
    public static final String CARD_PEASHOOTER = "card.peashooter";
    public static final String CARD_SUNFLOWER = "card.sunflower";

    // bullet
    public static final String BULLET_PEA = "bullet.pea";
    public static final String BULLET_PEA_HIT = "bullet.pea_hit";

    // item
    public static final String ITEM_SUN_STATIC = "item.sun_static";

    // single fallback images
    public static final String PLANT_PEASHOOTER_IDLE_0 = "plant.peashooter.idle.0";
    public static final String PLANT_SUNFLOWER_IDLE_0 = "plant.sunflower.idle.0";
    public static final String ZOMBIE_NORMAL_WALK_0 = "zombie.normal.walk.0";

    // animation groups
    public static final String ANIM_PEASHOOTER_IDLE = "anim.plant.peashooter.idle";
    public static final String ANIM_PEASHOOTER_SHOOT = "anim.plant.peashooter.shoot";

    public static final String ANIM_SUNFLOWER_IDLE = "anim.plant.sunflower.idle";
    public static final String ANIM_SUNFLOWER_PRODUCE = "anim.plant.sunflower.produce";

    public static final String ANIM_NORMAL_ZOMBIE_WALK = "anim.zombie.normal.walk";
    public static final String ANIM_NORMAL_ZOMBIE_ATTACK = "anim.zombie.normal.attack";
    public static final String ANIM_NORMAL_ZOMBIE_DIE = "anim.zombie.normal.die";

    public static final String ANIM_SUN = "anim.item.sun";
}