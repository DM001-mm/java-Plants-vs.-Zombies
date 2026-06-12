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
    private static final double HIDDEN_SCALE = 0.55;
    private static final double EXPLODE_SCALE = 1.25;

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
                zombie.killByExplosion();
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
            drawCurrentImage(g, image);
            return;
        }

        Color oldColor = g.getColor();

        if (state == STATE_ARMED) {
            g.setColor(new Color(160, 110, 50));
        } else {
            g.setColor(new Color(110, 70, 20));
        }

        int drawX = (int) x;
        int drawY = (int) y;
        int drawW = width;
        int drawH = height;

        if (state == STATE_HIDDEN) {
            drawW = (int) (width * HIDDEN_SCALE);
            drawH = (int) (height * HIDDEN_SCALE);
            drawX = (int) x + (width - drawW) / 2;
            drawY = (int) y + height - drawH;
        }

        g.fillOval(drawX, drawY, drawW, drawH);
        g.setColor(Color.BLACK);
        g.drawOval(drawX, drawY, drawW, drawH);
        g.setColor(oldColor);
    }

    private void drawCurrentImage(Graphics2D g, BufferedImage image) {
        int drawX = (int) x;
        int drawY = (int) y;
        int drawW = width;
        int drawH = height;

        if (state == STATE_HIDDEN) {
            drawW = (int) (width * HIDDEN_SCALE);
            drawH = (int) (height * HIDDEN_SCALE);
            drawX = (int) x + (width - drawW) / 2;
            drawY = (int) y + height - drawH;
        } else if (state == STATE_EXPLODED) {
            drawW = (int) (width * EXPLODE_SCALE);
            drawH = (int) (height * EXPLODE_SCALE);
            drawX = (int) x + width / 2 - drawW / 2;
            drawY = (int) y + height / 2 - drawH / 2;
        }

        g.drawImage(image, drawX, drawY, drawW, drawH, null);
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
