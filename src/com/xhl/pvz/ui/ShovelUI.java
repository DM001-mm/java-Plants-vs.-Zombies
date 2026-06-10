package com.xhl.pvz.ui;

import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ShovelUI {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private BufferedImage image;
    private boolean selected = false;

    public ShovelUI(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (ImageManager.hasImage(ImageKeys.UI_SHOVEL)) {
            image = ImageManager.getImage(ImageKeys.UI_SHOVEL);
        }
    }

    public void render(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            drawFallback(g); // 以防万一
        }

        if (selected) {
            drawSelectedBorder(g);
        }
    }

    private void drawFallback(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(new Color(210, 190, 150));
        g.fillRoundRect(x, y, width, height, 10, 10);

        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width, height, 10, 10);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));

        String text = "铲";
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textX = x + (width - textWidth) / 2;
        int textY = y + height / 2 + 5;

        g.drawString(text, textX, textY);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawSelectedBorder(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(Color.YELLOW);
        g.drawRect(x, y, width, height);
        g.drawRect(x + 1, y + 1, width - 2, height - 2);

        g.setColor(oldColor);
    }

    public boolean contains(int mouseX, int mouseY) {
        return getBounds().contains(mouseX, mouseY);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
