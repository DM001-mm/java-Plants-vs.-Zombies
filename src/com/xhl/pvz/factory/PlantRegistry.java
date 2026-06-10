package com.xhl.pvz.factory;

import com.xhl.pvz.entity.plant.Peashooter;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.plant.Sunflower;
import com.xhl.pvz.model.PlantDefinition;
import com.xhl.pvz.resource.ImageKeys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlantRegistry {

    public static final String PEASHOOTER = "peashooter";
    public static final String SUNFLOWER = "Sunflower";

    private static final Map<String, PlantDefinition> DEFINITIONS =
            new LinkedHashMap<>();
    private static final Map<Class<? extends Plant>, String> TYPES_BY_CLASS =
            new LinkedHashMap<>();

    static {
        register(Peashooter.class, new PlantDefinition(
                PEASHOOTER,
                "豌豆射手",
                100,
                150,
                ImageKeys.CARD_PEASHOOTER,
                true,
                Peashooter::new
        ));

        register(Sunflower.class, new PlantDefinition(
                SUNFLOWER,
                "向日葵",
                50,
                150,
                ImageKeys.CARD_SUNFLOWER,
                true,
                Sunflower::new
        ));
    }

    private PlantRegistry() {
    }

    private static void register(
            Class<? extends Plant> plantClass,
            PlantDefinition definition
    ) {
        DEFINITIONS.put(definition.getType(), definition);
        TYPES_BY_CLASS.put(plantClass, definition.getType());
    }

    public static PlantDefinition getDefinition(String plantType) {
        return DEFINITIONS.get(plantType);
    }

    public static Collection<PlantDefinition> getAllDefinitions() {
        return DEFINITIONS.values();
    }

    public static List<String> getSelectablePlantTypes() {
        List<String> result = new ArrayList<>();

        for (PlantDefinition definition : DEFINITIONS.values()) {
            if (definition.isSelectable()) {
                result.add(definition.getType());
            }
        }

        return result;
    }

    public static List<String> getDefaultPlantTypes() {
        List<String> result = new ArrayList<>();

        if (DEFINITIONS.containsKey(PEASHOOTER)) {
            result.add(PEASHOOTER);
        }

        if (DEFINITIONS.containsKey(SUNFLOWER)) {
            result.add(SUNFLOWER);
        }

        return result;
    }

    public static Plant createPlant(
            String plantType,
            int row,
            int col,
            double x,
            double y
    ) {
        PlantDefinition definition = getDefinition(plantType);

        if (definition == null) {
            System.out.println("未知植物类型: " + plantType);
            return null;
        }

        return definition.createPlant(row, col, x, y);
    }

    public static String getPlantType(Plant plant) {
        String plantType = TYPES_BY_CLASS.get(plant.getClass());

        if (plantType != null) {
            return plantType;
        }

        return plant.getClass().getSimpleName();
    }
}
