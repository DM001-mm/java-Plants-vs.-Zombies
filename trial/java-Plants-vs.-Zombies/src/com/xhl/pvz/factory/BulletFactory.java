package com.xhl.pvz.factory;

import com.xhl.pvz.entity.bullet.Bullet;
import com.xhl.pvz.entity.bullet.PeaBullet;

public class BulletFactory {

    public static final String PEA_BULLET = "PeaBullet";

    private BulletFactory() {
    }

    public static Bullet createBullet(
            String bulletType,
            int row,
            double x,
            double y
    ) {
        if (PEA_BULLET.equals(bulletType)) {
            return new PeaBullet(row, x, y);
        }

        System.out.println("未知子弹类型: " + bulletType);
        return null;
    }

    public static String getBulletType(Bullet bullet) {
        if (bullet instanceof PeaBullet) {
            return PEA_BULLET;
        }

        return bullet.getClass().getSimpleName();
    }
}