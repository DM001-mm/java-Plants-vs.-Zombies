package com.xhl.pvz.save;


import java.io.Serializable;

public class PlantSaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String plantType;
    private int row;
    private int col;
    private int hp;

    public PlantSaveData(String plantType,int row,int col,int hp){
        this.plantType = plantType;
        this.row = row;
        this.col=col;
        this.hp=hp;
    }
    
    public String getPlantType(){
        return plantType;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getHp(){
        return hp;
    }
    
}
