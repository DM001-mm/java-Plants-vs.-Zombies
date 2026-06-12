package src.factory;

import src.manager.ImageManager;
import src.model.PlantDefinition;
import src.resource.ImageKeys;
import src.ui.PlantCard;
import java.awt.image.BufferedImage;

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
                getImageOrNull(definition.getCardImageKey()),
                getImageOrNull(definition.getDisabledCardImageKey()),
                getImageOrNull(ImageKeys.UI_COOLDOWN_MASK)
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
                getImageOrNull(definition.getCardImageKey()),
                getImageOrNull(definition.getDisabledCardImageKey()),
                getImageOrNull(ImageKeys.UI_COOLDOWN_MASK)
        );
    }

    private static BufferedImage getImageOrNull(String key) {
        if (key == null) {
            return null;
        }

        return ImageManager.hasImage(key)
                ? ImageManager.getImage(key)
                : null;
    }

    public static PlantCard createPeashooterCard(int x, int y) {
        return createCard(PlantRegistry.PEASHOOTER, x, y);
    }

    public static PlantCard createSunflowerCard(int x, int y) {
        return createCard(PlantRegistry.SUNFLOWER, x, y);
    }
}
