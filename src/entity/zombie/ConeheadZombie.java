package src.entity.zombie;

import src.core.GameConfig;
import src.resource.ImageKeys;

public class ConeheadZombie extends NormalZombie {

    public ConeheadZombie(int row, double x, double y) {
        super(
                row,
                x,
                y,
                100,
                120,
                GameConfig.CONEHEAD_ZOMBIE_HP,
                GameConfig.CONEHEAD_ZOMBIE_SPEED,
                GameConfig.CONEHEAD_ZOMBIE_DAMAGE,
                ImageKeys.ZOMBIE_CONEHEAD_WALK_0,
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_WALK,
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_ATTACK,
                ImageKeys.ANIM_CONEHEAD_ZOMBIE_DIE
        );
    }
}
