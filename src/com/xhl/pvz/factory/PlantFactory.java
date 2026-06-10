package com.xhl.pvz.factory;

import com.xhl.pvz.entity.plant.Plant;

public class PlantFactory {
    public static final String PEASHOOTER = PlantRegistry.PEASHOOTER;
    public static final String SUNFLOWER = PlantRegistry.SUNFLOWER;

    private PlantFactory() {
    }

    public static Plant createPlant(
            String plantType,
            int row,
            int col,
            double x,
            double y
    ) {
        return PlantRegistry.createPlant(plantType, row, col, x, y);
    }

    public static String getPlantType(Plant plant) {
        return PlantRegistry.getPlantType(plant);
    }
}
