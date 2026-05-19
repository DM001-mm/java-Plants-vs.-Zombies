package com.xhl.pvz.entity.plant;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.item.Sun;
import com.xhl.pvz.manager.ImageManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Sunflower extends Plant {
    private BufferedImage image;

    private final int produceInterval =300;
    private int produceTimer =0;

    public Sunflower(int row,int col,double x,double y){
        super(row,col,x,y,80,80,300,50,150);
        if(ImageManager.hasImage("plant.sunflower.idle.0")){
            image=ImageManager.getImage("plant.sunflower.idle.0");
        }
    }
    @Override
    public void update(LevelContext context){
        produceTimer++;
        
        if(produceTimer>=produceInterval){
            produceTimer =0;
            produceSun(context);
        }
    }

    private void produceSun(LevelContext context){
        double sunX = x+15;
        double sunY = y-20;

        Sun sun = new Sun(sunX,sunY);
        context.getEntityManager().addSun(sun);
        System.out.println("向日葵生产阳光");
    }

    @Override
    public void render(Graphics2D g){
        if(image!=null){
            g.drawImage(image,(int)x,(int)y,width,height,null);
        }else{
            Color oldColor = g.getColor();

            g.setColor(Color.ORANGE);
            g.fillOval((int) x, (int) y, width, height);

            g.setColor(Color.BLACK);
            g.drawOval((int) x, (int) y, width, height);

            g.setColor(oldColor);
        }
    }
}
