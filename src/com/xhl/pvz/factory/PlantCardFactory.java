package com.xhl.pvz.factory;

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
}