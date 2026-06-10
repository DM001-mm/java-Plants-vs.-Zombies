package com.xhl.pvz.factory;

import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.model.PlantDefinition;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.ui.PlantCard;

public class PlantCardFactory {

    private static final int DEFAULT_CARD_WIDTH = 70;
    private static final int DEFAULT_CARD_HEIGHT = 90;
    private static final int BATTLE_CARD_WIDTH = 56;
    private static final int BATTLE_CARD_HEIGHT = 70;

    private PlantCardFactory() {
    }

    public static PlantCard createCard(String plantType, int x, int y) {
        PlantDefinition definition = PlantRegistry.getDefinition(plantType);

        if (definition == null) {
            System.out.println("未知植物卡片类型: " + plantType);
            return null;
        }

        return new PlantCard(
                definition.getType(),
                x,
                y,
                DEFAULT_CARD_WIDTH,
                DEFAULT_CARD_HEIGHT,
                definition.getCost(),
                definition.getCooldownTicks(),
                ImageManager.hasImage(definition.getCardImageKey())
                        ? ImageManager.getImage(definition.getCardImageKey())
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }

    public static PlantCard createBattleCard(String plantType, int x, int y) {
        PlantDefinition definition = PlantRegistry.getDefinition(plantType);

        if (definition == null) {
            System.out.println("未知战斗卡片类型: " + plantType);
            return null;
        }

        return new PlantCard(
                definition.getType(),
                x,
                y,
                BATTLE_CARD_WIDTH,
                BATTLE_CARD_HEIGHT,
                definition.getCost(),
                definition.getCooldownTicks(),
                ImageManager.hasImage(definition.getCardImageKey())
                        ? ImageManager.getImage(definition.getCardImageKey())
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }

    public static PlantCard createPeashooterCard(int x, int y) {
        return createCard(PlantRegistry.PEASHOOTER, x, y);
    }

    public static PlantCard createSunflowerCard(int x, int y) {
        return createCard(PlantRegistry.SUNFLOWER, x, y);
    }
}
