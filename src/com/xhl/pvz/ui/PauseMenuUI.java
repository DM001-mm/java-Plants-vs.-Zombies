package com.xhl.pvz.ui;

import com.xhl.pvz.manager.ImageManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PauseMenuUI {

    private UIButton continueButton;
    private UIButton menuButton;

    public PauseMenuUI() {
        continueButton = new UIButton(
                340,
                230,
                220,
                70,
                ImageManager.hasImage("ui.continue_button")
                        ? ImageManager.getImage("ui.continue_button")
                        : null
        );

        menuButton = new UIButton(
                340,
                320,
                220,
                70,
                ImageManager.hasImage("ui.menu_button")
                        ? ImageManager.getImage("ui.menu_button")
                        : null
        );
    }

    public void render(Graphics2D g) {
        drawOverlay(g);
        drawTitle(g);

        continueButton.render(g);
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
        g.drawString("游戏暂停", 365, 170);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    /**
     * 没有按钮图片时，先用文字提示。
     * 等资源到位后，这部分可以删掉。
     */
    private void drawFallbackText(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));

        if (!ImageManager.hasImage("ui.continue_button")) {
            g.drawString("继续游戏", 400, 275);
        }

        if (!ImageManager.hasImage("ui.menu_button")) {
            g.drawString("返回主菜单", 390, 365);
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    public boolean isContinueButtonClicked(int x, int y) {
        return continueButton.contains(x, y);
    }

    public boolean isMenuButtonClicked(int x, int y) {
        return menuButton.contains(x, y);
    }
}