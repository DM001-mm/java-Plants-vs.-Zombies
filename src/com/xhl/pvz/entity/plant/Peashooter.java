package com.xhl.pvz.entity.plant;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.bullet.PeaBullet;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Peashooter extends Plant {
    private BufferedImage image;
    private final int shootInterval =60;
    private int shootTimer = 0;

    public Peashooter(int row,int col,double x,double y){
        super(row,col,x,y,80,80,300,100,150);
        image = ImageManager.getImage("plant.peashooter.idle.0");
    }
    @Override
    public void update(LevelContext context){
        shootTimer++;

        boolean hasZombie =context.getEntityManager().hasZombieInRow(row, x);
        if(hasZombie&&shootTimer>=shootInterval){ // 这里的射击机制有点奇怪
            shootTimer =0; 
            shoot(context); 
        }
    }
    
    private void shoot(LevelContext context){
        double bulletX =x+55;
        double bulletY =y+20;

        PeaBullet bullet =new PeaBullet(shootInterval, bulletX, bulletY); //666 后面直接添加到 entitymanager里面
        context.getEntityManager().addBullet(bullet);

        AudioManager.playEffect("peashoot");
        
        System.out.println("豌豆射手发射豌豆");//sb
        
    }
    @Override
    public void render(Graphics2D g) {
        if (image != null) {
            g.drawImage(
                    image,
                    (int) x,
                    (int) y,
                    width,
                    height,
                    null
            );
        } else {
            Color oldColor = g.getColor();

            g.setColor(Color.GREEN);
            g.fillOval((int) x, (int) y, width, height);

            g.setColor(Color.BLACK);
            g.drawOval((int) x, (int) y, width, height);

            g.setColor(oldColor);
        }
    }
}
