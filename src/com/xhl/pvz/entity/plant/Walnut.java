package com.xhl.pvz.entity.plant;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Walnut extends Plant {

    private BufferedImage idleImage;
    private BufferedImage cracked1Image;
    private BufferedImage cracked2Image;

    public Walnut(int row, int col, double x, double y) {
        super(row, col, x, y, 80, 80, 4000, 50, 300);
        loadImages();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_WALNUT_IDLE_0)) {
            idleImage = ImageManager.getImage(ImageKeys.PLANT_WALNUT_IDLE_0);
        }

        if (ImageManager.hasImage(ImageKeys.PLANT_WALNUT_CRACKED1_0)) {
            cracked1Image = ImageManager.getImage(ImageKeys.PLANT_WALNUT_CRACKED1_0);
        }

        if (ImageManager.hasImage(ImageKeys.PLANT_WALNUT_CRACKED2_0)) {
            cracked2Image = ImageManager.getImage(ImageKeys.PLANT_WALNUT_CRACKED2_0);
        }
    }

    @Override
    public void update(LevelContext context) {
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage image = getCurrentImage();

        if (image != null) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
            return;
        }

        Color oldColor = g.getColor();
        g.setColor(new Color(120, 80, 30));
        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);
        g.setColor(oldColor);
    }

    private BufferedImage getCurrentImage() {
        double ratio = (double) hp / maxHp;

        if (ratio > 0.66) {
            return idleImage;
        }

        if (ratio > 0.33) {
            return cracked1Image != null ? cracked1Image : idleImage;
        }

        return cracked2Image != null ? cracked2Image : (cracked1Image != null ? cracked1Image : idleImage);
    }
}