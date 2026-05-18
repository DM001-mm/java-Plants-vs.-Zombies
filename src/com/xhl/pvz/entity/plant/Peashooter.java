package com.xhl.pvz.entity.plant;

import com.xhl.pvz.manager.ImageManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Peashooter extends Plant {
    private BufferedImage image;
    
    public Peashooter(int row,int col,double x,double y){
        super(row,col,x,y,80,80,300,100,150);
        image = ImageManager.getImage("plant.peashooter.idle.0");
    }
    @Override
    public void update(){

    }
    @Override
    public void render(Graphics2D g){
        g.drawImage(image,(int)x,(int)y,width,height,null);
    }
}
