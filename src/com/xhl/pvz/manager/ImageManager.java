package com.xhl.pvz.manager;

import com.xhl.pvz.resource.ImageKeys;
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
        loadBullets();
        loadItems();
        loadPlants();
        loadZombies();
    }

    private static void loadBackgrounds() {
        loadImage(ImageKeys.BACKGROUND_MAIN_MENU, "resources/images/background/main_menu.png");
        loadImage(ImageKeys.BACKGROUND_LAWN_DAY, "resources/images/background/lawn_day.png");
        loadImage(ImageKeys.BACKGROUND_GAME_OVER, "resources/images/background/game_over.png");
        loadImage(ImageKeys.BACKGROUND_WIN, "resources/images/background/win.png");
    }

    private static void loadUI() {
        loadImage(ImageKeys.UI_START_BUTTON, "resources/images/ui/start_button.png");
        loadImage(ImageKeys.UI_LOAD_BUTTON, "resources/images/ui/load_button.png");
        loadImage(ImageKeys.UI_EXIT_BUTTON, "resources/images/ui/exit_button.png");
        loadImage(ImageKeys.UI_PAUSE_BUTTON, "resources/images/ui/pause_button.png");
        loadImage(ImageKeys.UI_CONTINUE_BUTTON, "resources/images/ui/continue_button.png");
        loadImage(ImageKeys.UI_SAVE_BUTTON, "resources/images/ui/save_button.png");
        loadImage(ImageKeys.UI_MENU_BUTTON, "resources/images/ui/menu_button.png");
        loadImage(ImageKeys.UI_RESTART_BUTTON, "resources/images/ui/restart_button.png");
        loadImage(ImageKeys.UI_SUN_BANK, "resources/images/ui/sun_bank.png");
        loadImage(ImageKeys.UI_COOLDOWN_MASK, "resources/images/ui/cooldown_mask.png");
    }

    private static void loadCards() {
        loadImage(ImageKeys.CARD_PEASHOOTER, "resources/images/cards/peashooter_card.png");
        loadImage(ImageKeys.CARD_SUNFLOWER, "resources/images/cards/sunflower_card.png");
    }

    private static void loadBullets() {
        loadImage(ImageKeys.BULLET_PEA, "resources/images/bullets/pea.png");
        loadImage(ImageKeys.BULLET_PEA_HIT, "resources/images/bullets/pea_hit.png");
    }

    private static void loadItems() {
        loadImage(ImageKeys.ITEM_SUN_STATIC, "resources/images/items/sun_static.png");

        loadFrames(
                ImageKeys.ANIM_SUN,
                "resources/images/items/sun",
                20
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

        loadFrames(
                ImageKeys.ANIM_PEASHOOTER_IDLE,
                "resources/images/plants/peashooter/idle",
                20
        );

        loadFrames(
                ImageKeys.ANIM_PEASHOOTER_SHOOT,
                "resources/images/plants/peashooter/shoot",
                20
        );

        loadFrames(
                ImageKeys.ANIM_SUNFLOWER_IDLE,
                "resources/images/plants/sunflower/idle",
                20
        );

        loadFrames(
                ImageKeys.ANIM_SUNFLOWER_PRODUCE,
                "resources/images/plants/sunflower/produce",
                20
        );
    }

    private static void loadZombies() {
        loadImage(
                ImageKeys.ZOMBIE_NORMAL_WALK_0,
                "resources/images/zombies/normal_zombie/walk/0.png"
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_WALK,
                "resources/images/zombies/normal_zombie/walk",
                30
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_ATTACK,
                "resources/images/zombies/normal_zombie/attack",
                30
        );

        loadFrames(
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE,
                "resources/images/zombies/normal_zombie/die",
                30
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
                break;
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

    public static List<BufferedImage> getFrames(String key) {
        List<BufferedImage> frames = animations.get(key);

        if (frames == null) {
            throw new RuntimeException("动画没有加载或不存在: " + key);
        }

        return Collections.unmodifiableList(frames);
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