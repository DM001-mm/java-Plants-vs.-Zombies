package com.xhl.pvz.entity;

public abstract class LivingEntity extends Entity {
    protected int hp;
    protected int maxHp;

    public LivingEntity(double x,double y,int width,int height,int maxHp){
        super(x,y,width,height);
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public void takeDamage(int damage){
        if(!alive){
            return ;
        }
        hp-= damage;
        if(hp<=0){
            hp=0;
            onDeath();
        }
    }

    protected void onDeath(){
        alive = false;
    }

    public int getHp(){
        return hp;
    }
    
    public int getMaxHp(){
        return maxHp;
    }

}
