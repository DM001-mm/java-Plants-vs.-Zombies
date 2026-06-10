package src.entity.plant;

import src.core.LevelContext;
import src.entity.zombie.Zombie;
import src.manager.AudioManager;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PotatoMine extends Plant {

    private static final int STATE_HIDDEN = 0;
    private static final int STATE_ARMED = 1;
    private static final int STATE_EXPLODED = 2;

    private final int armTime = 300;
    private final int blastRadius = 70;

    private int state = STATE_HIDDEN;
    private int armTimer = 0;

    private BufferedImage hiddenImage;
    private BufferedImage armedImage;
    private BufferedImage explodeImage;

    public PotatoMine(int row, int col, double x, double y) {
        super(row, col, x, y, 80, 80, 300, 25, 300);
        loadImages();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_POTATO_MINE_IDLE_0)) {
            hiddenImage = ImageManager.getImage(ImageKeys.PLANT_POTATO_MINE_IDLE_0);
        }

        if (ImageManager.hasImage(ImageKeys.PLANT_POTATO_MINE_ARMED_0)) {
            armedImage = ImageManager.getImage(ImageKeys.PLANT_POTATO_MINE_ARMED_0);
        }

        if (ImageManager.hasImage(ImageKeys.PLANT_POTATO_MINE_EXPLODE_0)) {
            explodeImage = ImageManager.getImage(ImageKeys.PLANT_POTATO_MINE_EXPLODE_0);
        }
    }

    @Override
    public void update(LevelContext context) {
        if (state == STATE_EXPLODED) {
            alive = false;
            return;
        }

        if (state == STATE_HIDDEN) {
            armTimer++;

            if (armTimer >= armTime) {
                state = STATE_ARMED;
                AudioManager.playEffect("potato_mine_arm");
            }

            return;
        }

        if (hasZombieInBlastRange(context)) {
            explode(context);
        }
    }

    private boolean hasZombieInBlastRange(LevelContext context) {
        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (!zombie.isAlive() || zombie.getRow() != row) {
                continue;
            }

            double dx = Math.abs((zombie.getX() + zombie.getWidth() / 2.0) - (x + width / 2.0));
            if (dx <= blastRadius && zombie.getBounds().intersects(getBlastBounds())) {
                return true;
            }
        }

        return false;
    }

    private void explode(LevelContext context) {
        if (state == STATE_EXPLODED) {
            return;
        }

        state = STATE_EXPLODED;

        for (Zombie zombie : context.getEntityManager().getZombies()) {
            if (!zombie.isAlive() || zombie.getRow() != row) {
                continue;
            }

            if (zombie.getBounds().intersects(getBlastBounds())) {
                zombie.takeDamage(9999);
            }
        }

        AudioManager.playEffect("potato_mine_explode");
        alive = false;
    }

    private java.awt.Rectangle getBlastBounds() {
        int blastX = (int) x - blastRadius;
        int blastY = (int) y - blastRadius;
        int blastSize = width + blastRadius * 2;
        return new java.awt.Rectangle(blastX, blastY, blastSize, height + blastRadius * 2);
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage image = getCurrentImage();

        if (image != null) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
            return;
        }

        Color oldColor = g.getColor();

        if (state == STATE_ARMED) {
            g.setColor(new Color(160, 110, 50));
        } else {
            g.setColor(new Color(110, 70, 20));
        }

        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);
        g.setColor(oldColor);
    }

    private BufferedImage getCurrentImage() {
        if (state == STATE_ARMED) {
            return armedImage != null ? armedImage : hiddenImage;
        }

        if (state == STATE_EXPLODED) {
            return explodeImage != null ? explodeImage : armedImage;
        }

        return hiddenImage;
    }
}