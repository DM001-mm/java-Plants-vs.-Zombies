package com.xhl.pvz.manager;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
public class AudioManager {
    private static final Map<String,String> bgmPaths = new HashMap<>();
    private static final Map<String,String> effectPaths = new HashMap<>();

    private static Clip currentBgm;
    private static String currentBgmKey;

    private AudioManager(){}

    public static void loadAll(){
                // background music
        bgmPaths.put("main_menu", "resources/sounds/bgm/main_menu.wav");
        bgmPaths.put("level_day", "resources/sounds/bgm/level_day.wav");
        bgmPaths.put("game_over", "resources/sounds/bgm/game_over.wav");
        bgmPaths.put("win", "resources/sounds/bgm/win.wav");

        // sound effects
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

    public static void playBGM(String key){
        if(key==null){
            return ;
        }
        if(key.equals(currentBgmKey)&&currentBgm!=null&&currentBgm.isRunning()){
            return ;
        }
        stopBGM();

        String path = bgmPaths.get(key);
        if(path ==null){
            System.out.println("BGM key not found: "+key);
            return ;
        }
        File file = new File(path);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            currentBgm = AudioSystem.getClip();
            currentBgm.open(audioStream);
            
        } catch (Exception e) {
        }

    }
}
