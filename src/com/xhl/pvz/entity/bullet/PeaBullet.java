package com.xhl.pvz.entity.bullet;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PeaBullet extends Bullet {

    private BufferedImage image;

    public PeaBullet(int row, double x, double y) {
        super(
                row,
                x,
                y,
                30,
                30,
                6.0,
                20
        );

        if (ImageManager.hasImage(ImageKeys.BULLET_PEA)) {
            image = ImageManager.getImage(ImageKeys.BULLET_PEA);
        }
    }

    @Override
    public void update(LevelContext context) {
        x += speed;

        if (x > GameConfig.LEVEL_WORLD_WIDTH) {
            alive = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (image != null) {
            g.drawImage(
                    image,
                    (int) x,
                    (int) y,
                    width,
                    height,
                    null
            );
        } else {
            Color oldColor = g.getColor();

            g.setColor(Color.GREEN);
            g.fillOval((int) x, (int) y, width, height);

            g.setColor(Color.BLACK);
            g.drawOval((int) x, (int) y, width, height);

            g.setColor(oldColor);
        }
    }
}
