package com.xhl.pvz.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class UIButton {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    public UIButton(int x,int y,int width,int height,BufferedImage image){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.image=image;
    }
    
    public void render(Graphics2D g){
        if(image !=null){
            g.drawImage(image,x,y,width,height,null);
        }
    }// 有这个方法不代表已经做了，只是可以，只是有能力

    public boolean contains(int mouseX,int mouseY){
        return getBounds().contains(mouseX,mouseY);
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
    }
}
