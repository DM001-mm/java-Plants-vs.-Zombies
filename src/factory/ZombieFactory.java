package src.factory;

import src.entity.zombie.BucketheadZombie;
import src.entity.zombie.ConeheadZombie;
import src.entity.zombie.NormalZombie;
import src.entity.zombie.Zombie;

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
            return new ConeheadZombie(row, x, y);
        }

        if (BUCKETHEAD_ZOMBIE.equals(zombieType)) {
            return new BucketheadZombie(row, x, y);
        }

        System.out.println("未知僵尸类型: " + zombieType + "，暂时使用 NormalZombie 代替");
        return new NormalZombie(row, x, y);
    }

    public static String getZombieType(Zombie zombie) {
        if (zombie instanceof BucketheadZombie) {
            return BUCKETHEAD_ZOMBIE;
        }

        if (zombie instanceof ConeheadZombie) {
            return CONEHEAD_ZOMBIE;
        }

        if (zombie instanceof NormalZombie) {
            return NORMAL_ZOMBIE;
        }

        return zombie.getClass().getSimpleName();
    }
}
