package com.xhl.pvz.entity.zombie;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class NormalZombie extends Zombie {

    private BufferedImage image;

    public NormalZombie(int row, double x, double y) {
        super(
                row,
                x,
                y,
                80,
                100,
                270,
                0.5,
                20
        );

        if (ImageManager.hasImage("zombie.normal.walk.0")) {
            image = ImageManager.getImage("zombie.normal.walk.0");
        }
    }

    @Override
    public void update(LevelContext context) {
        // 目前先只实现向左移动
        // 后面有 CollisionManager 后，再实现碰到植物停止并攻击
        x -= speed;

        // 走到最左边，暂时先标记为死亡
        // 后面这里会改成触发游戏失败
        if (x + width < 0) {
            alive = false;
            System.out.println("僵尸进入房子，后面这里要触发游戏失败");
        }
    }

    @Override
    public void attack(Plant plant) {
        if (plant == null || !plant.isAlive()) {
            return;
        }

        plant.takeDamage(damage);
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
            Color oldColor = g.getColor();

            g.setColor(Color.GRAY);
            g.fillRect((int) x, (int) y, width, height);

            g.setColor(Color.BLACK);
            g.drawRect((int) x, (int) y, width, height);

            g.setColor(oldColor);
        }
    }
    @Override
    protected void onDeath() {
        alive = false;
        AudioManager.playEffect("zombie_die");
        System.out.println("普通僵尸死亡");
    }
}