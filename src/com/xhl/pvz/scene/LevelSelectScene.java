package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.level.LevelDefinition;
import com.xhl.pvz.level.LevelDefinitions;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.ui.UIButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectScene extends BaseScene {

    private final SceneManager sceneManager;

    private BufferedImage background;
    private final List<LevelOption> levelOptions = new ArrayList<>();
    private UIButton backButton;

    public LevelSelectScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onEnter() {
        System.out.println("进入关卡选择场景");

        if (ImageManager.hasImage(ImageKeys.BACKGROUND_MAIN_MENU)) {
            background = ImageManager.getImage(ImageKeys.BACKGROUND_MAIN_MENU);
        }

        buildLevelButtons();
        buildBackButton();

        AudioManager.playBGM("main_menu");
    }

    private void buildLevelButtons() {
        levelOptions.clear();

        List<LevelDefinition> levels = LevelDefinitions.getAllLevels();

        int buttonX = 130;
        int buttonY = 185;
        int buttonWidth = 220;
        int buttonHeight = 60;
        int gap = 88;

        BufferedImage buttonImage = null;

        if (ImageManager.hasImage(ImageKeys.UI_START_BUTTON)) {
            buttonImage = ImageManager.getImage(ImageKeys.UI_START_BUTTON);
        }

        for (int i = 0; i < levels.size(); i++) {
            LevelDefinition levelDefinition = levels.get(i);
            UIButton button = new UIButton(
                    buttonX,
                    buttonY + i * gap,
                    buttonWidth,
                    buttonHeight,
                    buttonImage
            );

            levelOptions.add(new LevelOption(button, levelDefinition));
        }
    }

    private void buildBackButton() {
        BufferedImage buttonImage = null;

        if (ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)) {
            buttonImage = ImageManager.getImage(ImageKeys.UI_MENU_BUTTON);
        }

        backButton = new UIButton(
                130,
                485,
                220,
                58,
                buttonImage
        );
    }

    @Override
    public void render(Graphics2D g) {
        drawBackground(g);
        drawTitle(g);
        drawLevelButtons(g);
        drawBackButton(g);
    }

    private void drawBackground(Graphics2D g) {
        if (background != null) {
            g.drawImage(
                    background,
                    0,
                    0,
                    GameConfig.WINDOW_WIDTH,
                    GameConfig.WINDOW_HEIGHT,
                    null
            );
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(new Color(60, 110, 70));
        g.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        g.setColor(oldColor);
    }

    private void drawTitle(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(new Color(0, 0, 0, 140));
        g.fillRoundRect(245, 80, 410, 80, 24, 24);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 42));

        String title = "选择关卡";
        int textWidth = g.getFontMetrics().stringWidth(title);

        g.drawString(title, (GameConfig.WINDOW_WIDTH - textWidth) / 2, 135);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawLevelButtons(Graphics2D g) {
        for (LevelOption option : levelOptions) {
            option.button.render(g);
            drawTextButton(g, option.button, option.levelDefinition.getName());
            drawLevelInfo(g, option);
        }
    }

    private void drawBackButton(Graphics2D g) {
        if (backButton == null) {
            return;
        }

        backButton.render(g);
        drawTextButton(g, backButton, "返回主菜单");
    }

    private void drawTextButton(Graphics2D g, UIButton button, String text) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        Rectangle bounds = button.getBounds();

        g.setColor(new Color(95, 62, 32, 210));
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 18, 18);

        g.setColor(new Color(245, 220, 130));
        g.drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 18, 18);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textX = bounds.x + (bounds.width - textWidth) / 2;
        int textY = bounds.y + bounds.height / 2 + 9;

        g.setColor(Color.WHITE);
        g.drawString(text, textX, textY);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawLevelInfo(Graphics2D g, LevelOption option) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        Rectangle bounds = option.button.getBounds();
        LevelDefinition level = option.levelDefinition;

        int infoX = bounds.x + bounds.width + 25;
        int infoY = bounds.y + 22;

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        drawShadowText(
                g,
                "难度：" + level.getDifficulty(),
                infoX,
                infoY,
                getDifficultyColor(level.getDifficulty())
        );

        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        drawShadowText(
                g,
                level.getDescription(),
                infoX,
                infoY + 26,
                new Color(235, 235, 235)
        );

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawShadowText(
            Graphics2D g,
            String text,
            int x,
            int y,
            Color textColor
    ) {
        g.setColor(new Color(0, 0, 0, 150));
        g.drawString(text, x + 2, y + 2);

        g.setColor(textColor);
        g.drawString(text, x, y);
    }

    private Color getDifficultyColor(String difficulty) {
        if ("简单".equals(difficulty)) {
            return new Color(135, 235, 125);
        }

        if ("困难".equals(difficulty)) {
            return new Color(255, 120, 95);
        }

        return new Color(255, 220, 105);
    }

    @Override
    public void onMousePressed(int x, int y) {
        for (LevelOption option : levelOptions) {
            if (option.button.contains(x, y)) {
                AudioManager.playEffect("click");
                sceneManager.changeScene(
                        new LevelScene(sceneManager, option.levelDefinition)
                );
                return;
            }
        }

        if (backButton != null && backButton.contains(x, y)) {
            AudioManager.playEffect("click");
            sceneManager.changeScene(new MainMenuScene(sceneManager));
        }
    }

    private static class LevelOption {

        private final UIButton button;
        private final LevelDefinition levelDefinition;

        private LevelOption(UIButton button, LevelDefinition levelDefinition) {
            this.button = button;
            this.levelDefinition = levelDefinition;
        }
    }
}
