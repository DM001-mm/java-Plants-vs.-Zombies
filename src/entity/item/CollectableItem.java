package src.entity.item;

import src.entity.Entity;

public abstract class CollectableItem extends Entity {
    protected int value;
    
    public CollectableItem(double x,double y,int width,int height,int value){
        super(x,y,width,height);
        this.value=value;
    }

    public boolean contains(int mouseX,int mouseY){
        return getBounds().contains(mouseX,mouseY);
    }

    public void collect(){
        alive= false;
    }
    public int getValue(){
        return value;
    }
}
