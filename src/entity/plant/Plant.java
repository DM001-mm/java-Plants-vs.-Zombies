package src.entity.plant;

import src.entity.LivingEntity;

public abstract class Plant extends LivingEntity {
    protected int row;
    protected int col;

    protected int cost;
    protected int cooldown;

    public Plant(int row,int col,double x,double y,int width,int height,int maxHp,int cost,int cooldown){
        super(x,y,width,height,maxHp);
        
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.cooldown =cooldown;
    }
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }

    public int getCost(){
        return cost;
    }
    public int getCooldown(){
        return cooldown;
    }
}
