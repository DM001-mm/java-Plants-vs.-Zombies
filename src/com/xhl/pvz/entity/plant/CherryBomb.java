package com.xhl.pvz.entity.plant;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.zombie.Zombie;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CherryBomb extends Plant {

    private static final int STATE_IDLE = 0;
    private static final int STATE_EXPLODING = 1;
    private static final int STATE_DONE = 2;

    private final int fuseTime = 30;
    private final int blastRadius = 160;

    private int state = STATE_IDLE;
    private int fuseTimer = 0;

    private BufferedImage idleImage;
    private BufferedImage explodeImage;

    public CherryBomb(int row, int col, double x, double y) {
        super(row, col, x, y, 80, 80, 300, 150, 600);
        loadImages();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_CHERRY_BOMB_IDLE_0)) {
            idleImage = ImageManager.getImage(ImageKeys.PLANT_CHERRY_BOMB_IDLE_0);
        }

        if (ImageManager.hasImage(ImageKeys.PLANT_CHERRY_BOMB_EXPLODE_0)) {
            explodeImage = ImageManager.getImage(ImageKeys.PLANT_CHERRY_BOMB_EXPLODE_0);
        }
    }

    @Override
    public void update(LevelContext context) {
        if (state == STATE_DONE) {
            alive = false;
            return;
        }

        fuseTimer++;

        if (state == STATE_IDLE && fuseTimer >= fuseTime) {
            explode(context);
        }

        if (state == STATE_EXPLODING && fuseTimer >= fuseTime + 20) {
            state = STATE_DONE;
            alive = false;
        }
    }

    private void explode(LevelContext context) {
        state = STATE_EXPLODING;
        AudioManager.playEffect("cherry_bomb_explode");

        Rectangle blastArea = getBlastArea();

        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (!zombie.isAlive()) {
                continue;
            }

            if (blastArea.intersects(zombie.getBounds())) {
                zombie.takeDamage(9999);
            }
        }
    }

    private Rectangle getBlastArea() {
        int centerX = (int) x + width / 2;
        int centerY = (int) y + height / 2;

        return new Rectangle(centerX - blastRadius, centerY - blastRadius, blastRadius * 2, blastRadius * 2);
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage image = getCurrentImage();

        if (image != null) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
            return;
        }

        Color oldColor = g.getColor();

        if (state == STATE_EXPLODING) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }

        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);
        g.setColor(oldColor);
    }

    private BufferedImage getCurrentImage() {
        if (state == STATE_EXPLODING) {
            return explodeImage != null ? explodeImage : idleImage;
        }

        return idleImage;
    }
}