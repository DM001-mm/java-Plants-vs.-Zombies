package com.xhl.pvz.factory;

import com.xhl.pvz.entity.zombie.NormalZombie;
import com.xhl.pvz.entity.zombie.Zombie;

public class ZombieFactory {

    public static final String NORMAL_ZOMBIE = "NormalZombie";
    public static final String CONEHEAD_ZOMBIE = "ConeheadZombie";
    public static final String BUCKETHEAD_ZOMBIE = "BucketheadZombie";

    private ZombieFactory() {
    }

    public static Zombie createZombie(
            String zombieType,
            int row,
            double x,
            double y
    ) {
        if (NORMAL_ZOMBIE.equals(zombieType)) {
            return new NormalZombie(row, x, y);
        }

        if (CONEHEAD_ZOMBIE.equals(zombieType)) {
            System.out.println("ConeheadZombie 暂未实现，暂时使用 NormalZombie 代替");
            return new NormalZombie(row, x, y);
        }

        if (BUCKETHEAD_ZOMBIE.equals(zombieType)) {
            System.out.println("BucketheadZombie 暂未实现，暂时使用 NormalZombie 代替");
            return new NormalZombie(row, x, y);
        }

        System.out.println("未知僵尸类型: " + zombieType);
        return new NormalZombie(row, x, y);
    }

    public static String getZombieType(Zombie zombie) {
        if (zombie instanceof NormalZombie) {
            return NORMAL_ZOMBIE;
        }

        return zombie.getClass().getSimpleName();
    }
}
