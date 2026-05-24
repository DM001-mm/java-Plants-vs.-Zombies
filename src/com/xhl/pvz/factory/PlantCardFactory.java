package com.xhl.pvz.factory;

import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
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
                ImageManager.hasImage(ImageKeys.CARD_PEASHOOTER)
                        ? ImageManager.getImage(ImageKeys.CARD_PEASHOOTER)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
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
                ImageManager.hasImage(ImageKeys.CARD_SUNFLOWER)
                        ? ImageManager.getImage(ImageKeys.CARD_SUNFLOWER)
                        : null,
                ImageManager.hasImage(ImageKeys.UI_COOLDOWN_MASK)
                        ? ImageManager.getImage(ImageKeys.UI_COOLDOWN_MASK)
                        : null
        );
    }
}
