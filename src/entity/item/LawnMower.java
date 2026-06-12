package src.entity.item;

import src.core.GameConfig;
import src.core.LevelContext;
import src.entity.Entity;
import src.entity.zombie.Zombie;
import src.manager.AudioManager;
import src.manager.ImageManager;
import src.resource.ImageKeys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class LawnMower extends Entity {
    private final int row;

    private boolean activated = false;

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
            return;
        }

        moveAndKillZombies(context);
    }

    private void checkTrigger(LevelContext context) {
        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (zombie == null) {
                continue;
            }

            if (!zombie.canBeTargeted()) {
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
        if (activated) {
            return;
        }

        activated = true;

        AudioManager.playEffect("lawn_mower_run");

        System.out.println("第 " + row + " 行小推车启动");
    }

    private void moveAndKillZombies(LevelContext context) {
        x += GameConfig.LAWN_MOWER_SPEED;

        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (zombie == null) {
                continue;
            }

            if (!zombie.isAlive()) {
                continue;
            }

            if (zombie.getRow() != row) {
                continue;
            }

            if (getCollisionBounds().intersects(zombie.getCollisionBounds())) {
                zombie.killNormally();
                System.out.println("小推车消灭僵尸");
            }
        }

        int rightLimit = GameConfig.LEVEL_CAMERA_LAWN_X
                + GameConfig.WINDOW_WIDTH
                + 200;

        if (x > rightLimit) {
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
        Font oldFont = g.getFont();

        if (activated) {
            g.setColor(new Color(210, 55, 45));
        } else {
            g.setColor(new Color(170, 175, 170));
        }

        g.fillRoundRect((int) x, (int) y, width, height, 12, 12);

        g.setColor(new Color(40, 45, 45));
        g.drawRoundRect((int) x, (int) y, width, height, 12, 12);

        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("车", (int) x + 24, (int) y + 36);

        g.setFont(oldFont);
        g.setColor(oldColor);
    }

    @Override
    public Rectangle getCollisionBounds() {
        return new Rectangle(
                (int) x + 8,
                (int) y + 8,
                width - 16,
                height - 12
        );
    }

    public int getRow() {
        return row;
    }

    public boolean isActivated() {
        return activated;
    }
}
