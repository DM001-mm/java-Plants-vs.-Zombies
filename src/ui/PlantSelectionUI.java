package src.ui;

import src.core.GameConfig;
import src.factory.PlantCardFactory;
import src.factory.PlantRegistry;
import src.model.PlantDefinition;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlantSelectionUI {

    private final List<PlantCard> poolCards = new ArrayList<>();
    private final List<String> selectedPlantTypes = new ArrayList<>();

    private final Rectangle startButtonBounds;

    private final int maxSelectedCount;

    public PlantSelectionUI(List<String> availablePlantTypes, int maxSelectedCount) {
        this.maxSelectedCount = maxSelectedCount;

        int startX = 90;
        int startY = 400;
        int gap = 85;

        for (int i = 0; i < availablePlantTypes.size(); i++) {
            String plantType = availablePlantTypes.get(i);

            PlantCard card = PlantCardFactory.createCard(
                    plantType,
                    startX + i * gap,
                    startY
            );

            if (card != null) {
                poolCards.add(card);
            }
        }

        startButtonBounds = new Rectangle(700, 500, 150, 55);
    }

    public void render(Graphics2D g) {
        drawPanel(g);
        drawTitle(g);
        drawSelectedSlots(g);
        drawPoolCards(g);
        drawStartButton(g);
    }

    public boolean handleMousePressed(int x, int y) {
        PlantCard clickedCard = getClickedPoolCard(x, y);

        if (clickedCard == null) {
            return false;
        }

        toggleCard(clickedCard);
        return true;
    }

    public boolean isStartButtonClicked(int x, int y) {
        return startButtonBounds.contains(x, y);
    }

    public boolean hasSelectedPlant() {
        return !selectedPlantTypes.isEmpty();
    }

    public List<String> getSelectedPlantTypes() {
        return Collections.unmodifiableList(selectedPlantTypes);
    }

    private PlantCard getClickedPoolCard(int x, int y) {
        for (PlantCard card : poolCards) {
            if (card.contains(x, y)) {
                return card;
            }
        }

        return null;
    }

    private void toggleCard(PlantCard card) {
        String plantType = card.getPlantType();

        if (selectedPlantTypes.contains(plantType)) {
            selectedPlantTypes.remove(plantType);
            card.setSelected(false);
            return;
        }

        if (selectedPlantTypes.size() >= maxSelectedCount) {
            System.out.println("出战植物数量已达到上限");
            return;
        }

        selectedPlantTypes.add(plantType);
        card.setSelected(true);
    }

    private void drawPanel(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        g.setColor(new Color(120, 78, 38));
        g.fillRoundRect(55, 55, 790, 500, 26, 26);

        g.setColor(new Color(190, 135, 70));
        g.drawRoundRect(55, 55, 790, 500, 26, 26);
        g.drawRoundRect(58, 58, 784, 494, 22, 22);

        g.setColor(oldColor);
    }

    private void drawTitle(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 34));
        g.drawString("选择出战植物", 330, 105);

        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
        g.drawString("点击卡片选择或取消选择，选择完成后点击开始游戏", 235, 140);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawSelectedSlots(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(new Color(70, 45, 25));
        g.fillRoundRect(95, 170, 700, 90, 18, 18);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        g.drawString("已选择：", 115, 205);

        int slotX = 170;
        int slotY = 185;
        int slotWidth = 95;
        int slotHeight = 50;
        int slotGap = 105;

        for (int i = 0; i < maxSelectedCount; i++) {
            int x = slotX + i * slotGap;

            g.setColor(new Color(95, 70, 45));
            g.fillRoundRect(x, slotY, slotWidth, slotHeight, 12, 12);

            g.setColor(Color.BLACK);
            g.drawRoundRect(x, slotY, slotWidth, slotHeight, 12, 12);

            if (i < selectedPlantTypes.size()) {
                String plantType = selectedPlantTypes.get(i);
                String displayName = getDisplayName(plantType);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));

                int textWidth = g.getFontMetrics().stringWidth(displayName);
                g.drawString(
                        displayName,
                        x + (slotWidth - textWidth) / 2,
                        slotY + 30
                );
            }
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private String getDisplayName(String plantType) {
        PlantDefinition definition = PlantRegistry.getDefinition(plantType);

        if (definition == null) {
            return plantType;
        }

        return definition.getDisplayName();
    }

    private void drawPoolCards(Graphics2D g) {
        for (PlantCard card : poolCards) {
            card.render(g);
        }
    }

    private void drawStartButton(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        if (hasSelectedPlant()) {
            g.setColor(new Color(220, 180, 80));
        } else {
            g.setColor(new Color(120, 120, 120));
        }

        g.fillRoundRect(
                startButtonBounds.x,
                startButtonBounds.y,
                startButtonBounds.width,
                startButtonBounds.height,
                18,
                18
        );

        g.setColor(Color.BLACK);
        g.drawRoundRect(
                startButtonBounds.x,
                startButtonBounds.y,
                startButtonBounds.width,
                startButtonBounds.height,
                18,
                18
        );

        g.setColor(Color.BLACK);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));

        String text = "开始游戏";
        int textWidth = g.getFontMetrics().stringWidth(text);

        g.drawString(
                text,
                startButtonBounds.x + (startButtonBounds.width - textWidth) / 2,
                startButtonBounds.y + 36
        );

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
}
