package com.xhl.pvz.entity.item;

import com.xhl.pvz.animation.Animation;
import com.xhl.pvz.animation.AnimationPlayer;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sun extends CollectableItem {

    private BufferedImage fallbackImage;

    private Animation sunAnimation;
    private AnimationPlayer animationPlayer;

    private int lifeTicks = 900;

    private boolean falling = false;
    private double targetY;
    private double fallSpeed = 2.0;

    /**
     * 普通阳光：直接出现在指定位置。
     * 比如向日葵生产的阳光。
     */
    public Sun(double x, double y) {
        super(x, y, 50, 50, 25);

        this.falling = false;
        this.targetY = y;

        loadImages();
        loadAnimations();
    }

    /**
     * 下落阳光：从 y 开始，掉到 targetY 停下。
     * 比如天空自然掉落阳光。
     */
    public Sun(double x, double y, double targetY) {
        super(x, y, 50, 50, 25);

        this.falling = true;
        this.targetY = targetY;

        loadImages();
        loadAnimations();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.ITEM_SUN_STATIC)) {
            fallbackImage = ImageManager.getImage(ImageKeys.ITEM_SUN_STATIC);
        }
    }

    private void loadAnimations() {
        if (ImageManager.hasFrames(ImageKeys.ANIM_SUN)) {
            sunAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_SUN),
                    5,
                    true
            );

            animationPlayer = new AnimationPlayer(sunAnimation);
        }
    }

    @Override
    public void update(LevelContext context) {
        updateFall(); // 作为 一个 属性添加进来的

        lifeTicks--;

        if (lifeTicks <= 0) {
            alive = false;
            return;
        }

        if (animationPlayer != null) {
            animationPlayer.update();
        }
    }

    private void updateFall() {
        if (!falling) {
            return;
        }

        y += fallSpeed;

        if (y >= targetY) {
            y = targetY;
            falling = false;
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
            g.drawImage(
                    currentImage,
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

        g.setColor(Color.YELLOW);
        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.ORANGE);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }
}
