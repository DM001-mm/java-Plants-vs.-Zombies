package com.xhl.pvz.model;

public class SunResource {
    private int amount;

    public SunResource(int initialAmount){
        this.amount = initialAmount;
    }

    public boolean canAfford(int cost){
        return amount>=cost;
    }
    // 但是 给外面的 对象 一个接口
    public void spend(int cost){
        if(cost<=0){
            return ;
        }
        amount -=cost;
        
        if(amount <0){
            amount=0;
        }
    }
    
    public void add(int value){
        if(value <=0 ){
            return ;
        }
        amount +=value;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount=Math.max(amount,0); 
    }
}
