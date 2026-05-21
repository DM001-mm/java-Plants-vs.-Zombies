package com.xhl.pvz.factory;

import com.xhl.pvz.entity.zombie.NormalZombie;
import com.xhl.pvz.entity.zombie.Zombie;

public class ZombieFactory {

    public static final String NORMAL_ZOMBIE = "NormalZombie";

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

        System.out.println("未知僵尸类型: " + zombieType);
        return null;
    }

    public static String getZombieType(Zombie zombie) {
        if (zombie instanceof NormalZombie) {
            return NORMAL_ZOMBIE;
        }

        return zombie.getClass().getSimpleName();
    }
}