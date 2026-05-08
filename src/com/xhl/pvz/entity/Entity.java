package com.xhl.pvz.entity;
import java.awt.*;
import javax.swing.*;

public abstract class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean alive = true;

    public abstract void update();
    public abstract void render(Graphics2D g);

    public boolean isAlive(){
        return alive;
    }
}

