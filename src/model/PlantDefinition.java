package src.model;

import src.entity.plant.Plant;

public class PlantDefinition {

    public interface PlantCreator {
        Plant create(int row, int col, double x, double y);
    }

    private final String type;
    private final String displayName;
    private final int cost;
    private final int cooldownTicks;
    private final String cardImageKey;
    private final String disabledCardImageKey;
    private final boolean selectable;
    private final PlantCreator creator;

    public PlantDefinition(
            String type,
            String displayName,
            int cost,
            int cooldownTicks,
            String cardImageKey,
            String disabledCardImageKey,
            boolean selectable,
            PlantCreator creator
    ) {
        this.type = type;
        this.displayName = displayName;
        this.cost = cost;
        this.cooldownTicks = cooldownTicks;
        this.cardImageKey = cardImageKey;
        this.disabledCardImageKey = disabledCardImageKey;
        this.selectable = selectable;
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCost() {
        return cost;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public String getCardImageKey() {
        return cardImageKey;
    }

    public String getDisabledCardImageKey() {
        return disabledCardImageKey;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public Plant createPlant(int row, int col, double x, double y) {
        return creator.create(row, col, x, y);
    }
}
