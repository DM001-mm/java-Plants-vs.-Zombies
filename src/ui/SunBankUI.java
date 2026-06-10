package src.ui;

import src.manager.ImageManager;
import src.model.SunResource;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SunBankUI {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final SunResource sunResource;
    // 都是 组件显示相关
    private BufferedImage image;

    public SunBankUI(int x,int y,int width,int height,SunResource sunResource){
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
        this.sunResource= sunResource;
        this.image = ImageManager.getImage(ImageKeys.UI_SUN_BANK);
        //if (ImageManager.hasImage(ImageKeys.UI_SUN_BANK)) {
    // this.image = ImageManager.getImage(ImageKeys.UI_SUN_BANK);
        // }  这里没有必要这么改
    }

    public void render(Graphics2D g){
        if(image!=null){
            g.drawImage(image,x,y,width,height,null);
        }
        
        drawSunAmount(g);
    }

    private void drawSunAmount(Graphics2D g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,20));

        String text = String.valueOf(sunResource.getAmount());

        int textX=x+45;
        int textY = y+38;
        g.drawString(text,textX,textY);
    }
}
