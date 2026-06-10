package com.xhl.pvz.entity.zombie;

import com.xhl.pvz.entity.LivingEntity;
import com.xhl.pvz.entity.plant.Plant;
public abstract class Zombie extends LivingEntity {
    protected int row;
    protected double speed;
    protected int damage;
    private int slowTicksRemaining = 0;
    private double slowFactor = 1.0;

    public Zombie(int row,double x,double y,int width,int height,int maxHp,double speed,int damage){
        super(x, y, width, height, maxHp);

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

    public void applySlow(int duration, double factor) {
        if (duration <= 0 || factor <= 0) {
            return;
        }

        slowTicksRemaining = Math.max(slowTicksRemaining, duration);
        slowFactor = Math.min(slowFactor, factor);
    }

    protected double getEffectiveSpeed() {
        if (slowTicksRemaining > 0) {
            return speed * slowFactor;
        }

        return speed;
    }

    protected void tickSlowEffect() {
        if (slowTicksRemaining > 0) {
            slowTicksRemaining--;
        }

        if (slowTicksRemaining <= 0) {
            slowTicksRemaining = 0;
            slowFactor = 1.0;
        }
    }

    public abstract void attack(Plant plant); // 这里主要是为了 和 植物进行交互
}
