package src.entity.plant;

import src.animation.Animation;
import src.animation.AnimationPlayer;
import src.core.LevelContext;
import src.entity.bullet.IcePeaBullet;
import src.manager.AudioManager;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class IceShooter extends Plant {

    private BufferedImage fallbackImage;

    private Animation idleAnimation;
    private Animation shootAnimation;
    private AnimationPlayer animationPlayer;

    private final int shootInterval = 60;
    private int shootTimer = 0;

    public IceShooter(int row, int col, double x, double y) {
        super(row, col, x, y, 80, 80, 300, 175, 150);
        loadImages();
        loadAnimations();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_ICE_SHOOTER_IDLE_0)) {
            fallbackImage = ImageManager.getImage(ImageKeys.PLANT_ICE_SHOOTER_IDLE_0);
        }
    }

    private void loadAnimations() {
        if (ImageManager.hasFrames(ImageKeys.ANIM_ICE_SHOOTER_IDLE)) {
            idleAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_ICE_SHOOTER_IDLE),
                    5,
                    true
            );
        }

        if (ImageManager.hasFrames(ImageKeys.ANIM_ICE_SHOOTER_SHOOT)) {
            shootAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_ICE_SHOOTER_SHOOT),
                    4,
                    false
            );
        }

        if (idleAnimation != null) {
            animationPlayer = new AnimationPlayer(idleAnimation);
        }
    }

    @Override
    public void update(LevelContext context) {
        updateAnimation();

        if (!hasTarget(context)) {
            shootTimer = 0;
            return;
        }

        shootTimer++;

        if (shootTimer >= shootInterval) {
            shootTimer = 0;
            shoot(context);
        }
    }

    private boolean hasTarget(LevelContext context) {
        double startX = x + width / 2.0;
        double range = 900;

        return context.getEntityManager().hasZombieInRowAhead(
                row,
                startX,
                range
        );
    }

    private void shoot(LevelContext context) {
        double bulletX = x + 55;
        double bulletY = y + 20;

        IcePeaBullet bullet = new IcePeaBullet(row, bulletX, bulletY);
        context.getEntityManager().addBullet(bullet);

        if (animationPlayer != null && shootAnimation != null) {
            animationPlayer.setAnimation(shootAnimation);
        }

        AudioManager.playEffect("ice_shoot");

        System.out.println("寒冰射手发射冰豌豆");
    }

    private void updateAnimation() {
        if (animationPlayer == null) {
            return;
        }

        animationPlayer.update();

        if (animationPlayer.isFinished() && idleAnimation != null) {
            animationPlayer.setAnimation(idleAnimation);
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage currentImage = null;

        if (animationPlayer != null) {
            currentImage = animationPlayer.getCurrentFrame();
        }

        if (currentImage == null) {
            currentImage = fallbackImage;
        }

        if (currentImage != null) {
            g.drawImage(currentImage, (int) x, (int) y, width, height, null);
        } else {
            drawFallback(g);
        }
    }

    private void drawFallback(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(new Color(100, 180, 255));
        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }
}
