package src.entity.zombie;

import src.entity.LivingEntity;
import src.entity.plant.Plant;

public abstract class Zombie extends LivingEntity {

    protected int row;
    protected double speed;
    protected int damage;
    protected DeathType deathType = DeathType.NORMAL;

    public enum DeathType {
        NORMAL,
        EXPLOSION
    }

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

    public abstract void attack(Plant plant); // 这里主要是为了 和 植物进行交互
}
