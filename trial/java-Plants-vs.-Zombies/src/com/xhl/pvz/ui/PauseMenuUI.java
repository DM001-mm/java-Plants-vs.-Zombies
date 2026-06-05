package com.xhl.pvz.ui;

import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PauseMenuUI {

    private UIButton continueButton;
    private UIButton saveButton;
    private UIButton loadButton;
    private UIButton menuButton;

    public PauseMenuUI() {
        continueButton = new UIButton(
                340,
                180,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_CONTINUE_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_CONTINUE_BUTTON)
                        : null
        );

        saveButton = new UIButton(
                340,
                260,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_SAVE_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_SAVE_BUTTON)
                        : null
        );

        loadButton = new UIButton(
                340,
                340,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_LOAD_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_LOAD_BUTTON)
                        : null
        );

        menuButton = new UIButton(
                340,
                420,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_MENU_BUTTON)
                        : null
        );
    }

    public void render(Graphics2D g) {
        drawOverlay(g);
        drawTitle(g);

        continueButton.render(g);
        saveButton.render(g);
        loadButton.render(g);
        menuButton.render(g);

        drawFallbackText(g);
    }

    private void drawOverlay(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 900, 600);

        g.setColor(oldColor);
    }

    private void drawTitle(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString("游戏暂停", 365, 130);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawFallbackText(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));

        if (!ImageManager.hasImage(ImageKeys.UI_CONTINUE_BUTTON)) {
            g.drawString("继续游戏", 400, 220);
        }

        if (!ImageManager.hasImage(ImageKeys.UI_SAVE_BUTTON)) {
            g.drawString("保存游戏", 400, 300);
        }

        if (!ImageManager.hasImage(ImageKeys.UI_LOAD_BUTTON)) {
            g.drawString("读取存档", 400, 380);
        }

        if (!ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)) {
            g.drawString("返回主菜单", 390, 460);
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    public boolean isContinueButtonClicked(int x, int y) {
        return continueButton.contains(x, y);
    }

    public boolean isSaveButtonClicked(int x, int y) {
        return saveButton.contains(x, y);
    }

    public boolean isLoadButtonClicked(int x, int y) {
        return loadButton.contains(x, y);
    }

    public boolean isMenuButtonClicked(int x, int y) {
        return menuButton.contains(x, y);
    }
}
