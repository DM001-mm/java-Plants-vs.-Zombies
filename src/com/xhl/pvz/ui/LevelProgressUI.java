package com.xhl.pvz.ui;

import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.manager.LevelManager;
import com.xhl.pvz.resource.ImageKeys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LevelProgressUI {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private final LevelManager levelManager;

    private BufferedImage barImage;
    private BufferedImage headImage;

    public LevelProgressUI(int x, int y, int width, int height, LevelManager levelManager) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.levelManager = levelManager;

        if (ImageManager.hasImage(ImageKeys.UI_PROGRESS_BAR)) {
            barImage = ImageManager.getImage(ImageKeys.UI_PROGRESS_BAR);
        }

        if (ImageManager.hasImage(ImageKeys.UI_PROGRESS_HEAD)) {
            headImage = ImageManager.getImage(ImageKeys.UI_PROGRESS_HEAD);
        }
    }

    public void render(Graphics2D g) {
        drawBar(g);
        drawProgressHead(g);
    }

    private void drawBar(Graphics2D g) {
        if (barImage != null) {
            g.drawImage(barImage, x, y, width, height, null);
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);

        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);

        g.setColor(oldColor);
    }

    private void drawProgressHead(Graphics2D g) {
        double ratio = levelManager.getProgressRatio();

        int headSize = height + 10;
        int headX = x + (int) (ratio * (width - headSize));
        int headY = y - 5;

        if (headImage != null) {
            g.drawImage(headImage, headX, headY, headSize, headSize, null);
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(Color.RED);
        g.fillOval(headX, headY, headSize, headSize);

        g.setColor(Color.BLACK);
        g.drawOval(headX, headY, headSize, headSize);

        g.setColor(oldColor);
    }
}