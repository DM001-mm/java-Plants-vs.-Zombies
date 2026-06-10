package com.xhl.pvz.entity.zombie;

import com.xhl.pvz.entity.LivingEntity;
import com.xhl.pvz.entity.plant.Plant;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Zombie extends LivingEntity {

    protected int row;
    protected double speed;
    protected int damage;
    private int slowTicksRemaining = 0;
    private double slowFactor = 1.0;

    protected static final int HURT_FLASH_DURATION = 6;
    protected int hurtFlashTimer = 0;

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

<<<<<<< HEAD
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

=======
    @Override
    public void takeDamage(int damage) {
        if (!isAlive()) {
            return;
        }

        if (hp <= 0) {
            return;
        }

        super.takeDamage(damage);

        if (hp > 0) {
            hurtFlashTimer = HURT_FLASH_DURATION;
        }
    }

    protected void updateHurtFlash() {
        if (hurtFlashTimer > 0) {
            hurtFlashTimer--;
        }
    }

    protected void renderHurtFlash(Graphics2D g) {
        if (hurtFlashTimer <= 0) {
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(new Color(255, 0, 0, 90));
        g.fillRect((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }

>>>>>>> fee6e5a890ea8ba92ca17ddd7dd98027c19662ef
    public abstract void attack(Plant plant); // 这里主要是为了 和 植物进行交互
}
