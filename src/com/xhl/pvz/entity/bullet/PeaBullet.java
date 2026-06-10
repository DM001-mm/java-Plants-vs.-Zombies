package com.xhl.pvz.entity.bullet;

import com.xhl.pvz.animation.Animation;
import com.xhl.pvz.animation.AnimationPlayer;
import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PeaBullet extends Bullet {

    private static final int HIT_FALLBACK_TICKS = 8;

    private BufferedImage image;
    private BufferedImage hitImage;

    private Animation hitAnimation;
    private AnimationPlayer hitAnimationPlayer;

    private boolean hit = false;
    private int hitTimer = 0;

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

        loadHitResources();
    }

    private void loadHitResources() {
        if (ImageManager.hasImage(ImageKeys.BULLET_PEA_HIT)) {
            hitImage = ImageManager.getImage(ImageKeys.BULLET_PEA_HIT);
        }

        if (ImageManager.hasFrames(ImageKeys.ANIM_PEA_HIT)) {
            hitAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_PEA_HIT),
                    3,
                    false
            );
        }
    }

    @Override
    public void update(LevelContext context) {
        if (hit) {
            updateHitState();
            return;
        }

        x += speed;

        if (x > GameConfig.LEVEL_WORLD_WIDTH) {
            setAlive(false);
        }
    }

    private void updateHitState() {
        if (hitAnimationPlayer != null) {
            hitAnimationPlayer.update();

            if (hitAnimationPlayer.isFinished()) {
                setAlive(false);
            }

            return;
        }

        hitTimer++;

        if (hitTimer >= HIT_FALLBACK_TICKS) {
            setAlive(false);
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (hit) {
            renderHit(g);
            return;
        }

        renderFlying(g);
    }

    private void renderFlying(Graphics2D g) {
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
            drawFallbackPea(g);
        }
    }

    private void renderHit(Graphics2D g) {
        BufferedImage currentFrame = null;

        if (hitAnimationPlayer != null) {
            currentFrame = hitAnimationPlayer.getCurrentFrame();
        }

        if (currentFrame != null) {
            g.drawImage(
                    currentFrame,
                    (int) x,
                    (int) y,
                    width,
                    height,
                    null
            );
            return;
        }

        if (hitImage != null) {
            g.drawImage(
                    hitImage,
                    (int) x,
                    (int) y,
                    width,
                    height,
                    null
            );
            return;
        }

        drawFallbackHit(g);
    }

    private void drawFallbackPea(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(Color.GREEN);
        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }

    private void drawFallbackHit(Graphics2D g) {
        Color oldColor = g.getColor();

        int size = Math.max(width, height) + 8;

        g.setColor(Color.YELLOW);
        g.fillOval((int) x - 4, (int) y - 4, size, size);

        g.setColor(Color.ORANGE);
        g.drawOval((int) x - 4, (int) y - 4, size, size);

        g.setColor(oldColor);
    }

    @Override
    public boolean canHit() {
        return isAlive() && !hit;
    }

    @Override
    public void onHit() {
        if (hit) {
            return;
        }

        hit = true;
        hitTimer = 0;

        if (hitAnimation != null) {
            hitAnimationPlayer = new AnimationPlayer(hitAnimation);
        }
    }

    @Override
    public Rectangle getCollisionBounds() {
        if (hit) {
            return new Rectangle(0, 0, 0, 0);
        }

        return new Rectangle(
                (int) x + width - 10,
                (int) y + 6,
                10,
                height - 12
        );
    }
}
