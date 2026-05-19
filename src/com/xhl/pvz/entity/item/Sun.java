package com.xhl.pvz.entity.item;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.manager.ImageManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Sun extends CollectableItem {
    private BufferedImage image;
    private int lifeTicks = 900;
    public Sun(double x,double y){
        super(x,y,50,50,25);
        
        if(ImageManager.hasImage("item.sun_static")){
            image=ImageManager.getImage("item.sun_static");
        }
    }

    @Override
    public void update(LevelContext context){
        lifeTicks--;
        if(lifeTicks<=0){
            alive=false;
        }
    }
    @Override
    public void render(Graphics2D g){
        if(image!=null){
            g.drawImage(image,(int)x,(int)y,width,height,null);
        }else{
            Color oldColor = g.getColor();
            g.setColor(Color.YELLOW);
            g.fillOval((int)x,(int)y,width,height);
            g.setColor(Color.ORANGE);
            g.drawOval((int)x,(int)y,width,height);
            g.setColor(oldColor);
        } //这只是一种资源没有加载成功 而作的替代操作，fillOval和 drawOval 是java 2D 里面用来 画 椭圆 /圆 的 操作
    }
    
}
