package com.xhl.pvz.entity.zombie;

import com.xhl.pvz.core.GameConfig;

import java.awt.Color;
import java.awt.Graphics2D;

public class BucketheadZombie extends NormalZombie {

    public BucketheadZombie(int row, double x, double y) {
        super(
                row,
                x,
                y,
                GameConfig.BUCKETHEAD_ZOMBIE_HP,
                GameConfig.BUCKETHEAD_ZOMBIE_SPEED,
                GameConfig.BUCKETHEAD_ZOMBIE_DAMAGE
        );
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        if (canBeTargeted()) {
            drawBucketFallback(g);
        }
    }

    private void drawBucketFallback(Graphics2D g) {
        Color oldColor = g.getColor();

        int bucketX = (int) x + width / 2 - 18;
        int bucketY = (int) y + 6;
        int bucketWidth = 36;
        int bucketHeight = 28;

        g.setColor(new Color(150, 150, 150));
        g.fillRoundRect(bucketX, bucketY, bucketWidth, bucketHeight, 8, 8);

        g.setColor(new Color(90, 90, 90));
        g.drawRoundRect(bucketX, bucketY, bucketWidth, bucketHeight, 8, 8);

        g.setColor(new Color(210, 210, 210));
        g.drawLine(bucketX + 5, bucketY + 7, bucketX + bucketWidth - 5, bucketY + 7);

        g.setColor(oldColor);
    }
}
