package com.xhl.pvz.entity;
// import java.awt.*;
// import javax.swing.*;
import com.xhl.pvz.core.LevelContext;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public abstract class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean alive = true;

    public Entity(double x,double y,int width,int height){
        this.x=x;
        this.y=y;
        this.width = width;
        this.height=height;
    }
    public abstract void update(LevelContext context);
    public abstract void render(Graphics2D g);

    public boolean isAlive(){
        return alive;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,width,height);
    }

    public Rectangle getCollisionBounds(){
        return getBounds();
    }

    // public boolean isAlive(){
    //     return alive;
    // }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
