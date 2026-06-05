package com.xhl.pvz.entity;

// import javax.swing.text.html.parser.Entity;

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

    public void setHp(int hp) {
        if (hp < 0) {
            hp = 0;
        }

        if (hp > maxHp) {
            hp = maxHp;
        }

        this.hp = hp;

        if (this.hp <= 0) {
            alive = false;
        }
    } // 读档保存功能实现 需要恢复血量 对吗
}
