package com.xhl.pvz.entity.plant;

import com.xhl.pvz.animation.Animation;
import com.xhl.pvz.animation.AnimationPlayer;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.bullet.PeaBullet;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// 原先是 静止的图片,现在 既有 又有图片数组, 但是 没有 加入 animationStatus
// 其他的 就是一些 资源加载,手动异常处理 ,然后就是再次封装 跟他妈的 计网传输pdu 很像
public class Peashooter extends Plant {

    private BufferedImage fallbackImage;

    private Animation idleAnimation;
    private Animation shootAnimation;
    private AnimationPlayer animationPlayer;

    private final int shootInterval = 60;
    private int shootTimer = 0;

    public Peashooter(int row, int col, double x, double y) {
        super(
                row,
                col,
                x,
                y,
                80,
                80,
                300,
                100,
                150
        );

        loadImages();
        loadAnimations();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_PEASHOOTER_IDLE_0)) {
            fallbackImage = ImageManager.getImage(ImageKeys.PLANT_PEASHOOTER_IDLE_0);
        }
    }

    private void loadAnimations() {
        if (ImageManager.hasFrames(ImageKeys.ANIM_PEASHOOTER_IDLE)) {
            idleAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_PEASHOOTER_IDLE),
                    5,
                    true
            );
        }

        if (ImageManager.hasFrames(ImageKeys.ANIM_PEASHOOTER_SHOOT)) {
            shootAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_PEASHOOTER_SHOOT),
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
        shootTimer++;

        boolean hasZombie = context.getEntityManager().hasZombieInRow(row, x);

        if (hasZombie && shootTimer >= shootInterval) {
            shootTimer = 0;
            shoot(context);
        }

        updateAnimation();
    }

    private void shoot(LevelContext context) {
        double bulletX = x + 55;
        double bulletY = y + 20;

        PeaBullet bullet = new PeaBullet(row, bulletX, bulletY);
        context.getEntityManager().addBullet(bullet);

        if (animationPlayer != null && shootAnimation != null) {
            animationPlayer.setAnimation(shootAnimation);
        }

        AudioManager.playEffect("pea_shoot");

        System.out.println("豌豆射手发射豌豆");
    }

    private void updateAnimation() {
        if (animationPlayer == null) {
            return;
        }

        animationPlayer.update();

        if (animationPlayer.isFinished() && idleAnimation != null) {
            animationPlayer.setAnimation(idleAnimation); // 重置 操作
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

        g.setColor(Color.GREEN);
        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }
}