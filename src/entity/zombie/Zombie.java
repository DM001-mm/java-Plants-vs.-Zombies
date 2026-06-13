package src.entity.zombie;

import src.entity.LivingEntity;
import src.entity.plant.Plant;

public abstract class Zombie extends LivingEntity {

    protected int row;
    protected double speed;
    protected int damage;
    protected DeathType deathType = DeathType.NORMAL;
    protected double baseSpeed;
    protected int slowTimer = 0;
    protected double slowFactor = 1.0;

    public enum DeathType {
        NORMAL,
        EXPLOSION
    }

    public Zombie(int row,double x,double y,int width,int height,int maxHp,double speed,int damage){
        super(x, y, width, height, maxHp);

        this.row = row;
        this.speed = speed;
        this.baseSpeed = speed;
        this.damage = damage;
    }
    public int getRow() {
        return row;
    }

    public int getDamage() {
        return damage;
    }

    public boolean canBeTargeted() {
        return isAlive() && getHp() > 0;
    }

    public void killNormally() {
        deathType = DeathType.NORMAL;
        takeDamage(999999);
    }

    public void killByExplosion() {
        deathType = DeathType.EXPLOSION;
        takeDamage(999999);
    }

    public DeathType getDeathType() {
        return deathType;
    }

    public void applySlow(int duration, double factor) {
        if (!isAlive() || hp <= 0) {
            return;
        }

        if (duration <= 0) {
            return;
        }

        if (factor <= 0 || factor >= 1) {
            return;
        }

        slowFactor = Math.min(slowFactor, factor);
        slowTimer = Math.max(slowTimer, duration);
        speed = baseSpeed * slowFactor;
    }

    protected void updateSlowEffect() {
        if (slowTimer <= 0) {
            speed = baseSpeed;
            slowFactor = 1.0;
            return;
        }

        slowTimer--;

        if (slowTimer <= 0) {
            speed = baseSpeed;
            slowFactor = 1.0;
        } else {
            speed = baseSpeed * slowFactor;
        }
    }

    public boolean isSlowed() {
        return slowTimer > 0;
    }

    public abstract void attack(Plant plant); // 这里主要是为了 和 植物进行交互
}
