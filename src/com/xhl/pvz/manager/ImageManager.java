package com.xhl.pvz.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageManager {
    private static final Map<String,BufferedImage> images=new HashMap<>();
    private ImageManager(){}
    public static void loadAll(){
        loadImage("background.main_menu","resources\\resources\\images\\interface\\Surface.jpg");
        loadImage("background.lawn_day","resources\\resources\\background\\background1.png");
        loadImage("ui.start_button","");
        loadImage("ui.sun_bank","");
        loadImage("ui.pause_button","");
        loadImage("card.peashooter","");
        loadImage("card.sunflower","");
        loadImage("plant.peashooter.idle.0", "resources\\images\\plants\\peashooter\\idle\\0.png");
        loadImage("ui.sun_bank", "resources/images/ui/sun_bank.png");
        loadImage("plant.sunflower.idle.0", "resources/images/plants/sunflower/idle/0.png");
        loadImage("card.sunflower", "resources/images/cards/sunflower_card.png");
    }

    private static void loadImage(String key,String path){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            if(image==null){
                throw new RuntimeException("图片格式无法识别:"+path);
            }
            images.put(key,image);
            System.out.println("图片加载成功："+key+"->"+path);
        } catch (Exception e) {
            throw new RuntimeException("图片加载失败："+key+"->"+path);
        }
    }

    public static BufferedImage getImage(String key){
        BufferedImage image = images.get(key);
        if(image ==null){
            throw new RuntimeException("图片没有加载："+key);
        }
        return image;
    }
    
    public static boolean hasImage(String key){
        return images.containsKey(key);
    }

    public static void clear(){
        images.clear();
    }
}
