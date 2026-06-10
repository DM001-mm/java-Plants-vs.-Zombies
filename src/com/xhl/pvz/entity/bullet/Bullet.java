package com.xhl.pvz.entity.bullet;

import com.xhl.pvz.entity.Entity;

public abstract class Bullet extends Entity {

    protected int row;
    protected double speed;
    protected int damage;

    public Bullet(
            int row,
            double x,
            double y,
            int width,
            int height,
            double speed,
            int damage
    ) {
        super(x, y, width, height);

        this.row = row;
        this.speed = speed;
        this.damage = damage;
    }

    public int getRow() {
        return row;
    }

    public int getDamage() {
        return damage;
    }

    public boolean canHit() {
        return isAlive();
    }

    public void onHit() {
        setAlive(false);
    }
}
