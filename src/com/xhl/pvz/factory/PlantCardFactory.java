package com.xhl.pvz.factory;

import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.ui.PlantCard;

public class PlantCardFactory {

    private PlantCardFactory() {
    }

    public static PlantCard createPeashooterCard(int x, int y) {
        return new PlantCard(
                PlantFactory.PEASHOOTER,
                x,
                y,
                70,
                90,
                100,
                150,
                ImageManager.hasImage("card.peashooter")
                        ? ImageManager.getImage("card.peashooter")
                        : null,
                ImageManager.hasImage("ui.cooldown_mask")
                        ? ImageManager.getImage("ui.cooldown_mask")
                        : null
        );
    }

    public static PlantCard createSunflowerCard(int x, int y) {
        return new PlantCard(
                PlantFactory.SUNFLOWER,
                x,
                y,
                70,
                90,
                50,
                150,
                ImageManager.hasImage("card.sunflower")
                        ? ImageManager.getImage("card.sunflower")
                        : null,
                ImageManager.hasImage("ui.cooldown_mask")
                        ? ImageManager.getImage("ui.cooldown_mask")
                        : null
        );
    }

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