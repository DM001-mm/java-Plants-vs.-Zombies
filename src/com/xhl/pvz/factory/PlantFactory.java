package com.xhl.pvz.factory;

import com.xhl.pvz.entity.plant.Peashooter;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.plant.Sunflower;

public class PlantFactory {
    public static final String PEASHOOTER = "peashooter";
    public static final String SUNFLOWER ="Sunflower";
    
    private PlantFactory(){
    }

    public static Plant createPlant(String plantType,int row,int col,double x,double y){
        if(PEASHOOTER.equals(plantType)){
            return new Peashooter(row,col,x,y);
        }
        if(SUNFLOWER.equals(plantType)){
            return new Peashooter(row,col,x,y);
        }

        System.out.println("未知植物类型："+plantType);
        return null;
    }

    public static String getPlantType(Plant plant) {
        if (plant instanceof Peashooter) {
            return PEASHOOTER;
        }

        if (plant instanceof Sunflower) {
            return SUNFLOWER;
        }

        return plant.getClass().getSimpleName();
    }

}
