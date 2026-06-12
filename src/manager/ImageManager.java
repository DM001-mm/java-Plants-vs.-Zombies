package src.manager;

import src.resource.ImageKeys;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * ImageManager 负责统一加载和缓存游戏中的图片资源。
 *
 * 设计目的：
 * 1. 图片只加载一次，避免重复读硬盘。
 * 2. 其他类只通过 key 获取图片，不直接写路径。
 * 3. 支持单张图片和动画帧组。
 */
public class ImageManager {

    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, List<BufferedImage>> animations = new HashMap<>();

    private ImageManager() {
    }

    /**
     * 游戏启动时调用一次。
     */
    public static void loadAll() {
        clear();

        loadBackgrounds();
        loadUI();
        loadCards();
        loadPlants();
        loadZombies();
        loadBullets();
        loadItems();

        System.out.println("ImageManager initialized.");
    }

    private static void loadBackgrounds() {
        loadImage(ImageKeys.BACKGROUND_MAIN_MENU, "resources/images/background/main_menu.png");
        loadImage(ImageKeys.BACKGROUND_LAWN_DAY, "resources/images/background/lawn_day.png");
        loadImage(ImageKeys.BACKGROUND_LAWN_NIGHT, "resources/images/background/lawn_night.png");
        loadImage(ImageKeys.BACKGROUND_PAUSE, "resources/images/background/pause_background.png");
        loadImage(ImageKeys.BACKGROUND_GAME_OVER, "resources/images/background/game_over.png");
        loadImage(ImageKeys.BACKGROUND_WIN, "resources/images/background/win.png");
        loadImage(ImageKeys.BACKGROUND_TROPHY, "resources/images/background/trophy.png");
    }

    private static void loadUI() {
        loadImage(ImageKeys.UI_START_BUTTON, "resources/images/ui/start_button.png");
        loadImage(ImageKeys.UI_LOAD_BUTTON, "resources/images/ui/load_button.png");
        loadImage(ImageKeys.UI_EXIT_BUTTON, "resources/images/ui/exit_button.png");
        loadImage(ImageKeys.UI_CONTINUE_BUTTON, "resources/images/ui/continue_button.png");
        loadImage(ImageKeys.UI_SAVE_BUTTON, "resources/images/ui/save_button.png");
        loadImage(ImageKeys.UI_MENU_BUTTON, "resources/images/ui/menu_button.png");
        loadImage(ImageKeys.UI_RESTART_BUTTON, "resources/images/ui/restart_button.png");
        loadImage(ImageKeys.UI_SUN_BANK, "resources/images/ui/sun_bank.png");
        loadImage(ImageKeys.UI_SEED_BANK, "resources/images/ui/seed_bank.png");
        loadImage(ImageKeys.UI_CARD_PANEL, "resources/images/ui/seed_bank.png");
        loadImage(ImageKeys.UI_CARD_SLOT, "resources/images/ui/card_slot.png");
        loadImage(ImageKeys.UI_COOLDOWN_MASK, "resources/images/ui/cooldown_mask.png");
        loadImage(ImageKeys.UI_SHOVEL, "resources/images/ui/shovel.png");
        loadImage(ImageKeys.UI_SHOVEL_SLOT, "resources/images/ui/shovel_slot.png");
        loadImage(ImageKeys.UI_PROGRESS_BAR, "resources/images/ui/progress_bar.png");
        loadImage(ImageKeys.UI_PROGRESS_HEAD, "resources/images/ui/progress_head.png");
        loadImage(ImageKeys.UI_SELECT_PANEL, "resources/images/ui/select_panel.png");
        loadImage(ImageKeys.UI_SELECT_START_BUTTON, "resources/images/ui/select_start.png");
    }

    private static void loadCards() {
        loadImage(ImageKeys.CARD_PEASHOOTER, "resources/images/card/peashooter_card.png");
        loadImage(ImageKeys.CARD_SUNFLOWER, "resources/images/card/sunflower_card.png");
        loadImage(ImageKeys.CARD_CHERRY_BOMB, "resources/images/card/cherry_bomb_card.png");
        loadImage(ImageKeys.CARD_WALLNUT, "resources/images/card/wallnut_card.png");
        loadImage(ImageKeys.CARD_SNOW_PEASHOOTER, "resources/images/card/snow_peashooter.png");
        loadImage(ImageKeys.CARD_POTATO_MINE, "resources/images/card/potato_mine_card.png");
    }

    private static void loadBullets() {
        loadImage(ImageKeys.BULLET_PEA, "resources/images/bullets/pea.png");
        loadImage(ImageKeys.BULLET_PEA_HIT, "resources/images/bullets/pea_hit.png");
        loadFrames(
                ImageKeys.ANIM_PEA_HIT,
                "resources/images/bullets/pea_hit",
                20
        );

        loadImage(ImageKeys.BULLET_SNOW_PEA, "resources/images/bullets/snow_pea.png");
        loadImage(ImageKeys.BULLET_SNOW_PEA_HIT, "resources/images/bullets/snow_pea_hit.png");
    }

    private static void loadItems() {
        loadImage(ImageKeys.ITEM_SUN_STATIC, "resources/images/items/sun_static.png");
        loadImage(ImageKeys.ITEM_LAWN_MOWER, "resources/images/items/lawn_mower.png");

        loadFrames(
                ImageKeys.ANIM_SUN,
                "resources/images/items/sun",
                22
        );
    }

    private static void loadPlants() {
        loadImage(
                ImageKeys.PLANT_PEASHOOTER_IDLE_0,
                "resources/images/plants/peashooter/idle/0.png"
        );

        loadImage(
                ImageKeys.PLANT_SUNFLOWER_IDLE_0,
                "resources/images/plants/sunflower/idle/0.png"
        );

        loadImage(
                ImageKeys.PLANT_SNOW_PEASHOOTER_IDLE_0,
                "resources/images/plants/snow_peashooter/idle/0.png"
        );

        loadImage(
                ImageKeys.PLANT_WALLNUT_IDLE_0,
                "resources/images/plants/wall_nut/idle/0.png"
        );

        loadImage(
                ImageKeys.PLANT_WALLNUT_CRACKED1_0,
                "resources/images/plants/wall_nut/cracked1/0.png"
        );

        loadImage(
                ImageKeys.PLANT_WALLNUT_CRACKED2_0,
                "resources/images/plants/wall_nut/cracked2/0.png"
        );

        loadImage(
                ImageKeys.PLANT_CHERRY_BOMB_IDLE_0,
                "resources/images/plants/cherry_bomb/idle/CherryBomb1.png"
        );

        loadImage(
                ImageKeys.PLANT_CHERRY_BOMB_EXPLODE_0,
                "resources/images/plants/cherry_bomb/explode/CherryBomb1.png"
        );

        loadImage(
                ImageKeys.PLANT_POTATO_MINE_IDLE_0,
                "resources/images/plants/potato_mine/unarmed/0.gif"
        );

        loadImage(
                ImageKeys.PLANT_POTATO_MINE_ARMED_0,
                "resources/images/plants/potato_mine/armed/PotatoMine1.png"
        );

        loadImage(
                ImageKeys.PLANT_POTATO_MINE_EXPLODE_0,
                "resources/images/plants/potato_mine/explode/PotatoMine1.png"
        );

        loadFrames(
                ImageKeys.ANIM_PEASHOOTER_IDLE,
                "resources/images/plants/peashooter/idle",
                13
        );

        loadFrames(
                ImageKeys.ANIM_PEASHOOTER_SHOOT,
                "resources/images/plants/peashooter/shoot",
                20
        );

        loadFrames(
                ImageKeys.ANIM_SUNFLOWER_IDLE,
                "resources/images/plants/sunflower/idle",
                18
        );

        loadFrames(
                ImageKeys.ANIM_SUNFLOWER_PRODUCE,
                "resources/images/plants/sunflower/produce",
                20
        );

        loadFrames(
                ImageKeys.ANIM_SNOW_PEASHOOTER_IDLE,
                "resources/images/plants/snow_peashooter/idle",
                15
        );

        loadFrames(
                ImageKeys.ANIM_SNOW_PEASHOOTER_SHOOT,
                "resources/images/plants/snow_peashooter/shoot",
                20
        );

        loadFrames(
                ImageKeys.ANIM_WALLNUT_IDLE,
                "resources/images/plants/wall_nut/idle",
                16
        );

        loadFrames(
                ImageKeys.ANIM_WALLNUT_CRACKED1,
                "resources/images/plants/wall_nut/cracked1",
                11
        );

        loadFrames(
                ImageKeys.ANIM_WALLNUT_CRACKED2,
                "resources/images/plants/wall_nut/cracked2",
                15
        );
    }

    private static void loadZombies() {
        loadImage(
                ImageKeys.ZOMBIE_NORMAL_WALK_0,
                "resources/images/zombie/normal_zombie/walk/0.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_NORMAL_ATTACK_0,
                "resources/images/zombie/normal_zombie/attack/0.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_NORMAL_DIE_0,
                "resources/images/zombie/normal_zombie/die/0.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_CONEHEAD_WALK_0,
                "resources/images/zombie/conehead_zombie/walk/move1.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_CONEHEAD_ATTACK_0,
                "resources/images/zombie/conehead_zombie/attack/attack1.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_CONEHEAD_DIE_0,
                "resources/images/zombie/conehead_zombie/die/died1.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_BUCKETHEAD_WALK_0,
                "resources/images/zombie/buckethead_zombie/move/move1.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_BUCKETHEAD_ATTACK_0,
                "resources/images/zombie/buckethead_zombie/attack/attack1.png"
        );

        loadImage(
                ImageKeys.ZOMBIE_BUCKETHEAD_DIE_0,
                "resources/images/zombie/buckethead_zombie/die/died1.png"
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_WALK,
                "resources/images/zombie/normal_zombie/walk",
                22
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_ATTACK,
                "resources/images/zombie/normal_zombie/attack",
                21
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE,
                "resources/images/zombie/normal_zombie/die",
                10
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE_NORMAL,
                "resources/images/zombie/normal_zombie/die",
                10
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE_EXPLODE,
                "resources/images/zombie/normal_zombie/die",
                "died",
                1,
                20
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_WALK,
                "resources/images/zombie/conehead_zombie/walk",
                "move",
                1,
                21
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_ATTACK,
                "resources/images/zombie/conehead_zombie/attack",
                "attack",
                1,
                11
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_DIE,
                "resources/images/zombie/conehead_zombie/die",
                "died",
                1,
                20
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_DIE_NORMAL,
                "resources/images/zombie/conehead_zombie/die",
                "dieh",
                1,
                10
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_DIE_EXPLODE,
                "resources/images/zombie/conehead_zombie/die",
                "died",
                1,
                20
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_WALK,
                "resources/images/zombie/buckethead_zombie/move",
                "move",
                1,
                15
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_ATTACK,
                "resources/images/zombie/buckethead_zombie/attack",
                "attack",
                1,
                11
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_DIE,
                "resources/images/zombie/buckethead_zombie/die",
                "died",
                1,
                20
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_DIE_NORMAL,
                "resources/images/zombie/buckethead_zombie/die",
                "dieh",
                1,
                10
        );

        loadPrefixedFrames(
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_DIE_EXPLODE,
                "resources/images/zombie/buckethead_zombie/die",
                "died",
                1,
                20
        );
    }

    /**
     * 加载单张图片。
     *
     * 注意：
     * 这里文件不存在时不抛异常，而是跳过。
     * 因为你的队友可能还没有把所有资源整理好。
     */
    private static void loadImage(String key, String path) {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("图片文件不存在，暂时跳过: " + path);
            return;
        }

        try {
            BufferedImage image = ImageIO.read(file);

            if (image == null) {
                System.out.println("图片格式无法识别: " + path);
                return;
            }

            images.put(key, image);
            System.out.println("图片加载成功: " + key);

        } catch (Exception e) {
            System.out.println("图片加载失败: " + key + " -> " + path);
            e.printStackTrace();
        }
    }

    /**
     * 加载按 0.png、1.png、2.png ... 命名的动画帧。
     */
    private static void loadFrames(String key, String folderPath, int maxFrameCount) {
        List<BufferedImage> frames = new ArrayList<>();

        for (int i = 0; i < maxFrameCount; i++) {
            File file = new File(folderPath + "/" + i + ".png");

            if (!file.exists()) {
                continue;
            }

            try {
                BufferedImage image = ImageIO.read(file);

                if (image != null) {
                    frames.add(image);
                } else {
                    System.out.println("动画帧格式无法识别: " + file.getPath());
                }
            } catch (Exception e) {
                System.out.println("动画帧加载失败: " + file.getPath());
                e.printStackTrace();
            }
        }

        if (frames.isEmpty()) {
            System.out.println("动画帧不存在，暂时跳过: " + folderPath);
            return;
        }

        animations.put(key, frames);
        System.out.println("动画加载成功: " + key + ", 帧数: " + frames.size());
    }

    /**
     * 加载按 move1.png、attack1.png 这类前缀命名的动画帧。
     */
    private static void loadPrefixedFrames(
            String key,
            String folderPath,
            String filePrefix,
            int startIndex,
            int frameCount
    ) {
        List<BufferedImage> frames = new ArrayList<>();
        int endExclusive = startIndex + frameCount;

        for (int i = startIndex; i < endExclusive; i++) {
            File file = new File(folderPath + "/" + filePrefix + i + ".png");

            if (!file.exists()) {
                continue;
            }

            try {
                BufferedImage image = ImageIO.read(file);

                if (image != null) {
                    frames.add(image);
                } else {
                    System.out.println("动画帧格式无法识别: " + file.getPath());
                }
            } catch (Exception e) {
                System.out.println("动画帧加载失败: " + file.getPath());
                e.printStackTrace();
            }
        }

        if (frames.isEmpty()) {
            System.out.println("动画帧不存在，暂时跳过: " + folderPath);
            return;
        }

        animations.put(key, frames);
        System.out.println("动画加载成功: " + key + ", 帧数: " + frames.size());
    }

    public static BufferedImage getImage(String key) {
        BufferedImage image = images.get(key);

        if (image == null) {
            throw new RuntimeException("图片没有加载或不存在: " + key);
        }

        return image;
    }

    public static BufferedImage getImageOrNull(String key) {
        return images.get(key);
    }

    public static boolean hasImage(String key) {
        return images.containsKey(key);
    }

    // 这个地方 这么写 我认为代码组织上有些混乱 // 这里留一个悬念
    public static List<BufferedImage> getFrames(String key) { // 这个函数是用来干什么的?
        List<BufferedImage> frames = animations.get(key);   // 这里 搞错了 ,animations 实际上是 map类型,那么 ImageManager还是总的Image加载类

        if (frames == null) {
            throw new RuntimeException("动画没有加载或不存在: " + key);
        }

        return Collections.unmodifiableList(frames); //
    }

    public static List<BufferedImage> getFramesOrEmpty(String key) {
        List<BufferedImage> frames = animations.get(key);

        if (frames == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(frames);
    }

    public static boolean hasFrames(String key) {
        return animations.containsKey(key);
    }

    public static void clear() {
        images.clear();
        animations.clear();
    }
}
// 升级的地方 主要是 图片loadImage 不是一大堆,放在一起而是分类处理了,用到了 刚写的 枚举/映射类,
// 强调一下 没有用到享元
