package src.entity.zombie;

import src.core.GameConfig;
import src.resource.ImageKeys;

public class BucketheadZombie extends NormalZombie {

    public BucketheadZombie(int row, double x, double y) {
        super(
                row,
                x,
                y,
                100,
                120,
                GameConfig.BUCKETHEAD_ZOMBIE_HP,
                GameConfig.BUCKETHEAD_ZOMBIE_SPEED,
                GameConfig.BUCKETHEAD_ZOMBIE_DAMAGE,
                ImageKeys.ZOMBIE_BUCKETHEAD_WALK_0,
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_WALK,
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_ATTACK,
                ImageKeys.ANIM_BUCKETHEAD_ZOMBIE_DIE
        );
    }
}
