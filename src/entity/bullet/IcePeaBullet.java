package src.entity.bullet;

import src.core.GameConfig;
import src.core.LevelContext;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class IcePeaBullet extends Bullet {

    private final int slowDuration = 120;
    private final double slowFactor = 0.5;

    private BufferedImage image;

    public IcePeaBullet(int row, double x, double y) {
        super(
                row,
                x,
                y,
                30,
                30,
                6.0,
                20
        );

        if (ImageManager.hasImage(ImageKeys.BULLET_ICE_PEA)) {
            image = ImageManager.getImage(ImageKeys.BULLET_ICE_PEA);
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
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(new Color(120, 220, 255));
        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.WHITE);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }

    public int getSlowDuration() {
        return slowDuration;
    }

    public double getSlowFactor() {
        return slowFactor;
    }
}
