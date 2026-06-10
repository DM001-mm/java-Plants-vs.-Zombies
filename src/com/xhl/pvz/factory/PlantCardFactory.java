package com.xhl.pvz.factory;

import com.xhl.pvz.resource.ImageKeys;
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

<<<<<<< HEAD
    public static PlantCard createWalnutCard(int x, int y) {
        return new PlantCard(
                PlantFactory.WALNUT,
                x,
                y,
                70,
                90,
                50,
                300,
                ImageManager.hasImage(ImageKeys.CARD_WALNUT)
                        ? ImageManager.getImage(ImageKeys.CARD_WALNUT)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }

    public static PlantCard createPotatoMineCard(int x, int y) {
        return new PlantCard(
                PlantFactory.POTATO_MINE,
                x,
                y,
                70,
                90,
                25,
                300,
                ImageManager.hasImage(ImageKeys.CARD_POTATO_MINE)
                        ? ImageManager.getImage(ImageKeys.CARD_POTATO_MINE)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }

    public static PlantCard createCherryBombCard(int x, int y) {
        return new PlantCard(
                PlantFactory.CHERRY_BOMB,
                x,
                y,
                70,
                90,
                150,
                600,
                ImageManager.hasImage(ImageKeys.CARD_CHERRY_BOMB)
                        ? ImageManager.getImage(ImageKeys.CARD_CHERRY_BOMB)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }

    public static PlantCard createIceShooterCard(int x, int y) {
        return new PlantCard(
                PlantFactory.ICE_SHOOTER,
                x,
                y,
                70,
                90,
                175,
                150,
                ImageManager.hasImage(ImageKeys.CARD_ICE_SHOOTER)
                        ? ImageManager.getImage(ImageKeys.CARD_ICE_SHOOTER)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }
}
=======
    public static PlantCard createSunflowerCard(int x, int y) {
        return createCard(PlantRegistry.SUNFLOWER, x, y);
    }
}
>>>>>>> fee6e5a890ea8ba92ca17ddd7dd98027c19662ef
