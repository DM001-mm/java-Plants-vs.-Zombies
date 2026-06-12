package src.resource;

public class ImageKeys { //双引号用多了很累，于是 就有了下面的手写枚举类

    private ImageKeys() {
    }

    // background
    public static final String BACKGROUND_MAIN_MENU = "background.main_menu";
    public static final String BACKGROUND_LAWN_DAY = "background.lawn_day";
    public static final String BACKGROUND_LAWN_NIGHT = "background.lawn_night";
    public static final String BACKGROUND_PAUSE = "background.pause";
    public static final String BACKGROUND_GAME_OVER = "background.game_over";
    public static final String BACKGROUND_WIN = "background.win";
    public static final String BACKGROUND_TROPHY = "background.trophy";

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
    public static final String UI_SEED_BANK = "ui.seed_bank";
    public static final String UI_CARD_PANEL = "ui.card_panel";
    public static final String UI_CARD_SLOT = "ui.card_slot";
    public static final String UI_COOLDOWN_MASK = "ui.cooldown_mask";
    public static final String UI_SHOVEL = "ui.shovel";
    public static final String UI_SHOVEL_SLOT = "ui.shovel_slot";
    public static final String UI_PROGRESS_BAR = "ui.progress_bar";
    public static final String UI_PROGRESS_HEAD = "ui.progress_head";
    public static final String UI_SELECT_PANEL = "ui.select_panel";
    public static final String UI_SELECT_START_BUTTON = "ui.select_start_button";

    // cards
    public static final String CARD_PEASHOOTER = "card.peashooter";
    public static final String CARD_SUNFLOWER = "card.sunflower";
    public static final String CARD_CHERRY_BOMB = "card.cherry_bomb";
    public static final String CARD_WALLNUT = "card.wallnut";
    public static final String CARD_SNOW_PEASHOOTER = "card.snow_peashooter";
    public static final String CARD_POTATO_MINE = "card.potato_mine";

    // bullet
    public static final String BULLET_PEA = "bullet.pea";
    public static final String BULLET_PEA_HIT = "bullet.pea_hit";
    public static final String ANIM_PEA_HIT = "anim.bullet.pea_hit";
    public static final String BULLET_SNOW_PEA = "bullet.snow_pea";
    public static final String BULLET_SNOW_PEA_HIT = "bullet.snow_pea_hit";
    public static final String BULLET_ICE_PEA = BULLET_SNOW_PEA;

    // item
    public static final String ITEM_SUN_STATIC = "item.sun_static";
    public static final String ITEM_LAWN_MOWER = "item.lawn_mower";

    // single fallback images
    public static final String PLANT_PEASHOOTER_IDLE_0 = "plant.peashooter.idle.0";
    public static final String PLANT_SUNFLOWER_IDLE_0 = "plant.sunflower.idle.0";
    public static final String PLANT_SNOW_PEASHOOTER_IDLE_0 = "plant.snow_peashooter.idle.0";
    public static final String PLANT_ICE_SHOOTER_IDLE_0 = PLANT_SNOW_PEASHOOTER_IDLE_0;
    public static final String PLANT_WALLNUT_IDLE_0 = "plant.wallnut.idle.0";
    public static final String PLANT_WALLNUT_CRACKED1_0 = "plant.wallnut.cracked1.0";
    public static final String PLANT_WALLNUT_CRACKED2_0 = "plant.wallnut.cracked2.0";
    public static final String PLANT_WALNUT_IDLE_0 = PLANT_WALLNUT_IDLE_0;
    public static final String PLANT_WALNUT_CRACKED1_0 = PLANT_WALLNUT_CRACKED1_0;
    public static final String PLANT_WALNUT_CRACKED2_0 = PLANT_WALLNUT_CRACKED2_0;
    public static final String PLANT_CHERRY_BOMB_IDLE_0 = "plant.cherry_bomb.idle.0";
    public static final String PLANT_CHERRY_BOMB_EXPLODE_0 = "plant.cherry_bomb.explode.0";
    public static final String PLANT_POTATO_MINE_IDLE_0 = "plant.potato_mine.idle.0";
    public static final String PLANT_POTATO_MINE_ARMED_0 = "plant.potato_mine.armed.0";
    public static final String PLANT_POTATO_MINE_EXPLODE_0 = "plant.potato_mine.explode.0";
    public static final String ZOMBIE_NORMAL_WALK_0 = "zombie.normal.walk.0";
    public static final String ZOMBIE_NORMAL_ATTACK_0 = "zombie.normal.attack.0";
    public static final String ZOMBIE_NORMAL_DIE_0 = "zombie.normal.die.0";
    public static final String ZOMBIE_NORMAL_HEAD_0 = "zombie.normal.head.0";
    public static final String ZOMBIE_CONEHEAD_WALK_0 = "zombie.conehead.walk.0";
    public static final String ZOMBIE_CONEHEAD_ATTACK_0 = "zombie.conehead.attack.0";
    public static final String ZOMBIE_CONEHEAD_DIE_0 = "zombie.conehead.die.0";
    public static final String ZOMBIE_BUCKETHEAD_WALK_0 = "zombie.buckethead.walk.0";
    public static final String ZOMBIE_BUCKETHEAD_ATTACK_0 = "zombie.buckethead.attack.0";
    public static final String ZOMBIE_BUCKETHEAD_DIE_0 = "zombie.buckethead.die.0";

    // animation groups
    public static final String ANIM_PEASHOOTER_IDLE = "anim.plant.peashooter.idle";
    public static final String ANIM_PEASHOOTER_SHOOT = "anim.plant.peashooter.shoot";

    public static final String ANIM_SUNFLOWER_IDLE = "anim.plant.sunflower.idle";
    public static final String ANIM_SUNFLOWER_PRODUCE = "anim.plant.sunflower.produce";

    public static final String ANIM_SNOW_PEASHOOTER_IDLE = "anim.plant.snow_peashooter.idle";
    public static final String ANIM_SNOW_PEASHOOTER_SHOOT = "anim.plant.snow_peashooter.shoot";
    public static final String ANIM_ICE_SHOOTER_IDLE = ANIM_SNOW_PEASHOOTER_IDLE;
    public static final String ANIM_ICE_SHOOTER_SHOOT = ANIM_SNOW_PEASHOOTER_SHOOT;

    public static final String ANIM_WALLNUT_IDLE = "anim.plant.wallnut.idle";
    public static final String ANIM_WALLNUT_CRACKED1 = "anim.plant.wallnut.cracked1";
    public static final String ANIM_WALLNUT_CRACKED2 = "anim.plant.wallnut.cracked2";

    public static final String ANIM_NORMAL_ZOMBIE_WALK = "anim.zombie.normal.walk";
    public static final String ANIM_NORMAL_ZOMBIE_ATTACK = "anim.zombie.normal.attack";
    public static final String ANIM_NORMAL_ZOMBIE_WALK_DAMAGED = "anim.zombie.normal.walk_damaged";
    public static final String ANIM_NORMAL_ZOMBIE_ATTACK_DAMAGED = "anim.zombie.normal.attack_damaged";
    public static final String ANIM_NORMAL_ZOMBIE_DIE = "anim.zombie.normal.die";
    public static final String ANIM_NORMAL_ZOMBIE_HEAD = "anim.zombie.normal.head";

    public static final String ANIM_CONEHEAD_ZOMBIE_WALK = "anim.zombie.conehead.walk";
    public static final String ANIM_CONEHEAD_ZOMBIE_ATTACK = "anim.zombie.conehead.attack";
    public static final String ANIM_CONEHEAD_ZOMBIE_DIE = "anim.zombie.conehead.die";

    public static final String ANIM_BUCKETHEAD_ZOMBIE_WALK = "anim.zombie.buckethead.walk";
    public static final String ANIM_BUCKETHEAD_ZOMBIE_ATTACK = "anim.zombie.buckethead.attack";
    public static final String ANIM_BUCKETHEAD_ZOMBIE_DIE = "anim.zombie.buckethead.die";

    public static final String ANIM_SUN = "anim.item.sun";
}
