package src.manager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class AudioManager {

    private static final Map<String, String> bgmPaths = new HashMap<>();
    private static final Map<String, String> effectPaths = new HashMap<>();

    private static Clip currentBgm;
    private static String currentBgmKey;

    private AudioManager() {
    }

    public static void loadAll() {
        bgmPaths.clear();
        effectPaths.clear();

        // 背景音乐：建议全部使用 wav
        bgmPaths.put("main_menu", "resources/sounds/bgm/main_menu.wav");
        bgmPaths.put("level_day", "resources/sounds/bgm/level_day.wav");
        bgmPaths.put("game_over", "resources/sounds/bgm/game_over.wav");
        bgmPaths.put("win", "resources/sounds/bgm/win.wav");

        // 音效
        effectPaths.put("click", "resources/sounds/effect/click.wav");
        effectPaths.put("plant_place", "resources/sounds/effect/plant_place.wav");
        effectPaths.put("plant_remove", "resources/sounds/effect/plant_remove.wav");
        effectPaths.put("card_select", "resources/sounds/effect/card_select.wav");
        effectPaths.put("card_error", "resources/sounds/effect/card_error.wav");
        effectPaths.put("pea_shoot", "resources/sounds/effect/pea_shoot.wav");
        effectPaths.put("pea_hit", "resources/sounds/effect/pea_hit.wav");
        effectPaths.put("sun_collect", "resources/sounds/effect/sun_collect.wav");
        effectPaths.put("zombie_eat", "resources/sounds/effect/zombie_eat.wav");
        effectPaths.put("zombie_die", "resources/sounds/effect/zombie_die.wav");

        System.out.println("AudioManager initialized.");
    }

    public static void playBGM(String key) {
        if (key == null) {
            return;
        }

        if (key.equals(currentBgmKey) && currentBgm != null && currentBgm.isRunning()) {
            return;
        }

        stopBGM();

        String path = bgmPaths.get(key);

        if (path == null) {
            System.out.println("BGM key not found: " + key);
            return;
        }

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("BGM file not found: " + file.getAbsolutePath());
            return;
        }

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            currentBgm = AudioSystem.getClip();
            currentBgm.open(audioStream);
            currentBgm.loop(Clip.LOOP_CONTINUOUSLY);
            currentBgm.start();

            currentBgmKey = key;

            System.out.println("Playing BGM: " + key);

        } catch (Exception e) {
            System.out.println("Failed to play BGM: " + path);
            e.printStackTrace();
        }
    }

    public static void stopBGM() {
        if (currentBgm != null) {
            currentBgm.stop();
            currentBgm.close();
            currentBgm = null;
        }

        currentBgmKey = null;
    }

    public static void playEffect(String key) {
        if (key == null) {
            return;
        }

        String path = effectPaths.get(key);

        if (path == null) {
            System.out.println("Effect key not found: " + key);
            return;
        }

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Effect file not found: " + file.getAbsolutePath());
            return;
        }

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (Exception e) {
            System.out.println("Failed to play effect: " + path);
            e.printStackTrace();
        }
    }
}