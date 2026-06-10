package com.xhl.pvz.factory;

import com.xhl.pvz.entity.plant.CherryBomb;
import com.xhl.pvz.entity.plant.IceShooter;
import com.xhl.pvz.entity.plant.PotatoMine;
import com.xhl.pvz.entity.plant.Peashooter;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.plant.Sunflower;
import com.xhl.pvz.entity.plant.Walnut;

public class PlantFactory {
    public static final String PEASHOOTER = "peashooter";
    public static final String SUNFLOWER = "sunflower";
    public static final String WALNUT = "walnut";
    public static final String POTATO_MINE = "potato_mine";
    public static final String CHERRY_BOMB = "cherry_bomb";
    public static final String ICE_SHOOTER = "ice_shooter";
    
    private PlantFactory(){
    }

    public static Plant createPlant(String plantType,int row,int col,double x,double y){
        if (matches(plantType, PEASHOOTER)) {
            return new Peashooter(row,col,x,y);
        }
        if (matches(plantType, SUNFLOWER)) {
            return new Sunflower(row,col,x,y);
        }
        if (matches(plantType, WALNUT)) {
            return new Walnut(row, col, x, y);
        }
        if (matches(plantType, POTATO_MINE)) {
            return new PotatoMine(row, col, x, y);
        }
        if (matches(plantType, CHERRY_BOMB)) {
            return new CherryBomb(row, col, x, y);
        }
        if (matches(plantType, ICE_SHOOTER)) {
            return new IceShooter(row, col, x, y);
        }

        System.out.println("未知植物类型："+plantType);
        return null;
    }

    private static boolean matches(String actualType, String expectedType) {
        return actualType != null && actualType.equalsIgnoreCase(expectedType);
    }

    public static String getPlantType(Plant plant) {
        if (plant instanceof Peashooter) {
            return PEASHOOTER;
        }

        if (plant instanceof Sunflower) {
            return SUNFLOWER;
        }

        if (plant instanceof Walnut) {
            return WALNUT;
        }

        if (plant instanceof PotatoMine) {
            return POTATO_MINE;
        }

        if (plant instanceof CherryBomb) {
            return CHERRY_BOMB;
        }

        if (plant instanceof IceShooter) {
            return ICE_SHOOTER;
        }

        return plant.getClass().getSimpleName();
    }

}
