package com.xhl.pvz.entity.item;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.Entity;
import com.xhl.pvz.entity.zombie.Zombie;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LawnMower extends Entity {
    private final int row;

    private boolean activated = false;
    private double speed = 12.0;

    private BufferedImage image;

    public LawnMower(int row, double x, double y) {
        super(
                x,
                y,
                GameConfig.LAWN_MOWER_WIDTH,
                GameConfig.LAWN_MOWER_HEIGHT
        );

        this.row = row;

        if (ImageManager.hasImage(ImageKeys.ITEM_LAWN_MOWER)) {
            image = ImageManager.getImage(ImageKeys.ITEM_LAWN_MOWER);
        }
    }

    @Override
    public void update(LevelContext context) {
        if (!activated) {
            checkTrigger(context);
        } else {
            moveAndKillZombies(context);
        }
    }

    private void checkTrigger(LevelContext context) {
        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (!zombie.isAlive()) {
                continue;
            }

            if (zombie.getRow() != row) {
                continue;
            }

            if (getCollisionBounds().intersects(zombie.getCollisionBounds())) {
                activate();
                return;
            }
        }
    }

    private void activate() {
        activated = true;

        AudioManager.playEffect("plant_remove");

        System.out.println("第 " + row + " 行小推车启动");
    }

    private void moveAndKillZombies(LevelContext context) {
        x += speed;

        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (!zombie.isAlive()) {
                continue;
            }

            if (zombie.getRow() != row) {
                continue;
            }

            if (getCollisionBounds().intersects(zombie.getCollisionBounds())) {
                zombie.setAlive(false);
                System.out.println("小推车消灭僵尸");
            }
        }

        if (x > GameConfig.LEVEL_CAMERA_X + GameConfig.WINDOW_WIDTH + 100) {
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
            drawFallback(g);
        }
    }

    private void drawFallback(Graphics2D g) {
        Color oldColor = g.getColor();

        if (activated) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.LIGHT_GRAY);
        }

        g.fillRect((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect((int) x, (int) y, width, height);
        g.drawString("车", (int) x + 20, (int) y + 35);

        g.setColor(oldColor);
    }

    public int getRow() {
        return row;
    }

    public boolean isActivated() {
        return activated;
    }
}
