package src.ui;

import src.manager.ImageManager;
import src.manager.LevelManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Font;
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
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        drawProgressBar(g);
        drawProgressHead(g);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawProgressBar(Graphics2D g) {
        if (barImage != null) {
            g.drawImage(barImage, x, y, width, height, null);
        } else {
            g.setColor(new Color(60, 45, 30, 180));
            g.fillRoundRect(x, y, width, height, 10, 10);
        }

        double progress = getProgressRatio();
        int progressWidth = (int) (width * progress);

        if (progressWidth > 0) {
            g.setColor(new Color(170, 90, 45, 190));
            g.fillRoundRect(x, y, progressWidth, height, 10, 10);
        }

        g.setColor(new Color(20, 15, 10, 220));
        g.drawRoundRect(x, y, width, height, 10, 10);
    }

    private void drawProgressHead(Graphics2D g) {
        double ratio = getProgressRatio();

        int headSize = Math.max(16, height + 8);
        int rawHeadX = x + (int) (width * ratio) - headSize / 2;
        int headX = Math.max(x, Math.min(x + width - headSize, rawHeadX));
        int headY = y + height / 2 - headSize / 2;

        if (headImage != null) {
            g.drawImage(headImage, headX, headY, headSize, headSize, null);
            return;
        }

        g.setColor(new Color(80, 160, 80));
        g.fillOval(headX, headY, headSize, headSize);

        g.setColor(Color.BLACK);
        g.drawOval(headX, headY, headSize, headSize);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
        g.drawString("Z", headX + headSize / 2 - 4, headY + headSize / 2 + 4);
    }

    private double getProgressRatio() {
        if (levelManager == null) {
            return 0.0;
        }

        return levelManager.getProgressRatio();
    }
}
