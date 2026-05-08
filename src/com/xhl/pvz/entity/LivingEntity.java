package com.xhl.pvz.entity;

public abstract class LivingEntity extends Entity {
    protected int hp;
    protected int maxHp;

    public void takeDamage(int damage){
        hp -=damage;
        if(hp<=0){
            alive = false;
            onDeath();
        }
    }
    protected void onDeath(){}
}
