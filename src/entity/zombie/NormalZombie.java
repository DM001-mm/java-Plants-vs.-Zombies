package src.entity.zombie;

import src.animation.Animation;
import src.animation.AnimationPlayer;
import src.core.GameConfig;
import src.core.LevelContext;
import src.entity.plant.Plant;
import src.manager.AudioManager;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class NormalZombie extends Zombie {

    private static final int VISUAL_Y_OFFSET = 15;

    private static final int STATE_WALK = 0;
    private static final int STATE_ATTACK = 1;
    private static final int STATE_DIE = 2;

    private enum DamageStage {
        NORMAL,
        DAMAGED
    }

    private int state = STATE_WALK;
    private DamageStage damageStage = DamageStage.NORMAL;

    private BufferedImage fallbackImage;

    private Animation walkAnimation; // 有三种动作就会有三种动画帧组
    private Animation attackAnimation;
    private Animation normalDieAnimation;
    private Animation explosionDieAnimation;
    private Animation dieAnimation;

    private Animation damagedWalkAnimation;
    private Animation damagedAttackAnimation;

    private AnimationPlayer animationPlayer; // 类似于 插槽机制

    private final String fallbackWalkImageKey;
    private final String walkAnimationKey;
    private final String attackAnimationKey;
    private final String normalDieAnimationKey;
    private final String explosionDieAnimationKey;
    private final String damagedWalkAnimationKey;
    private final String damagedAttackAnimationKey;

    private final int attackInterval = 30;
    private int attackTimer = 0;

    private static final int DEATH_FALLBACK_DURATION = 30;
    private int deathFallbackTimer = 0;

    public NormalZombie(int row, double x, double y) { // 常规设置
        this(
                row,
                x,
                y,
                100,
                120,
                GameConfig.NORMAL_ZOMBIE_HP,
                GameConfig.NORMAL_ZOMBIE_SPEED,
                GameConfig.NORMAL_ZOMBIE_DAMAGE,
                ImageKeys.ZOMBIE_NORMAL_WALK_0,
                ImageKeys.ANIM_NORMAL_ZOMBIE_WALK,
                ImageKeys.ANIM_NORMAL_ZOMBIE_ATTACK,
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE_NORMAL,
                ImageKeys.ANIM_NORMAL_ZOMBIE_DIE_EXPLODE,
                ImageKeys.ANIM_NORMAL_ZOMBIE_WALK_DAMAGED,
                ImageKeys.ANIM_NORMAL_ZOMBIE_ATTACK_DAMAGED
        );
    }

    protected NormalZombie(
            int row,
            double x,
            double y,
            int width,
            int height,
            int maxHp,
            double speed,
            int damage,
            String fallbackWalkImageKey,
            String walkAnimationKey,
            String attackAnimationKey,
            String normalDieAnimationKey,
            String explosionDieAnimationKey
    ) {
        this(
                row,
                x,
                y,
                width,
                height,
                maxHp,
                speed,
                damage,
                fallbackWalkImageKey,
                walkAnimationKey,
                attackAnimationKey,
                normalDieAnimationKey,
                explosionDieAnimationKey,
                null,
                null
        );
    }

    private NormalZombie(
            int row,
            double x,
            double y,
            int width,
            int height,
            int maxHp,
            double speed,
            int damage,
            String fallbackWalkImageKey,
            String walkAnimationKey,
            String attackAnimationKey,
            String normalDieAnimationKey,
            String explosionDieAnimationKey,
            String damagedWalkAnimationKey,
            String damagedAttackAnimationKey
    ) {
        super(
                row,
                x,
                y - VISUAL_Y_OFFSET,
                width,
                height,
                maxHp,
                speed,
                damage
        );

        this.fallbackWalkImageKey = fallbackWalkImageKey;
        this.walkAnimationKey = walkAnimationKey;
        this.attackAnimationKey = attackAnimationKey;
        this.normalDieAnimationKey = normalDieAnimationKey;
        this.explosionDieAnimationKey = explosionDieAnimationKey;
        this.damagedWalkAnimationKey = damagedWalkAnimationKey;
        this.damagedAttackAnimationKey = damagedAttackAnimationKey;

        loadImages();
        loadAnimations();
    }

    @Override
    public double getY() {
        return y + VISUAL_Y_OFFSET;
    }

    private void loadImages() {
        if (ImageManager.hasImage(fallbackWalkImageKey)) {
            fallbackImage = ImageManager.getImage(fallbackWalkImageKey);
        }
    }

    private void loadAnimations() {
        if (ImageManager.hasFrames(walkAnimationKey)) {
            walkAnimation = new Animation(
                    ImageManager.getFrames(walkAnimationKey),
                    5,
                    true
            );
        }

        if (ImageManager.hasFrames(attackAnimationKey)) {
            attackAnimation = new Animation(
                    ImageManager.getFrames(attackAnimationKey),
                    5,
                    true
            );
        }

        if (damagedWalkAnimationKey != null && ImageManager.hasFrames(damagedWalkAnimationKey)) {
            damagedWalkAnimation = new Animation(
                    ImageManager.getFrames(damagedWalkAnimationKey),
                    5,
                    true
            );
        }

        if (damagedAttackAnimationKey != null && ImageManager.hasFrames(damagedAttackAnimationKey)) {
            damagedAttackAnimation = new Animation(
                    ImageManager.getFrames(damagedAttackAnimationKey),
                    5,
                    true
            );
        }

        if (ImageManager.hasFrames(normalDieAnimationKey)) {
            normalDieAnimation = new Animation(
                    ImageManager.getFrames(normalDieAnimationKey),
                    5,
                    false
            );
        }

        if (ImageManager.hasFrames(explosionDieAnimationKey)) {
            explosionDieAnimation = new Animation(
                    ImageManager.getFrames(explosionDieAnimationKey),
                    4,
                    false
            );
        }

        dieAnimation = normalDieAnimation;

        if (walkAnimation != null) {
            animationPlayer = new AnimationPlayer(walkAnimation);
        }
    }

    @Override
    public void update(LevelContext context) { 
        if (state == STATE_DIE) {
            updateDieAnimation();
            return;
        }

        updateDamageStage();

        Plant collidingPlant = context.getEntityManager().getCollidingPlant(this);

        if (collidingPlant != null) {
            changeState(STATE_ATTACK); // 动画 和 数值 并不是看起来的那样同步
            attack(collidingPlant);
        } else {
            changeState(STATE_WALK);
            move();
        }

        if (x + width < 0) {
            alive = false;
            context.requestGameOver();
            System.out.println("僵尸进入房子，游戏失败");
        }

        updateAnimation(); // 插槽 牛逼
    }

    private void move() {
        x -= speed;
        attackTimer = 0;
    }

    @Override
    public void attack(Plant plant) {
        if (plant == null || !plant.isAlive()) {
            return;
        }

        attackTimer++;

        if (attackTimer >= attackInterval) {
            attackTimer = 0;

            plant.takeDamage(damage);

            AudioManager.playEffect("zombie_eat");

            System.out.println("僵尸攻击植物，植物剩余 HP: " + plant.getHp());
        }
    }

    private void changeState(int newState) { // 更新插槽 ,逻辑更清晰
        if (state == newState) {
            return;
        }

        state = newState;

        refreshAnimationByState();
    }

    private void updateDamageStage() {
        if (state == STATE_DIE) {
            return;
        }

        DamageStage oldStage = damageStage;

        if (hp <= maxHp / 2) {
            damageStage = DamageStage.DAMAGED;
        } else {
            damageStage = DamageStage.NORMAL;
        }

        if (oldStage != damageStage) {
            refreshAnimationByState();
        }
    }

    private void refreshAnimationByState() {
        if (animationPlayer == null) {
            return;
        }

        Animation targetAnimation = getAnimationForCurrentState();

        if (targetAnimation != null) {
            animationPlayer.setAnimation(targetAnimation);
        }
    }

    private Animation getAnimationForCurrentState() {
        if (state == STATE_DIE) {
            return dieAnimation;
        }

        if (state == STATE_WALK) {
            if (damageStage == DamageStage.DAMAGED && damagedWalkAnimation != null) {
                return damagedWalkAnimation;
            }

            return walkAnimation;
        }

        if (state == STATE_ATTACK) {
            if (damageStage == DamageStage.DAMAGED && damagedAttackAnimation != null) {
                return damagedAttackAnimation;
            }

            return attackAnimation;
        }

        return null;
    }

    private void updateAnimation() {
        if (animationPlayer != null) {
            animationPlayer.update();
        }
    }

    private void updateDieAnimation() {
        if (dieAnimation == null) {
            deathFallbackTimer++;

            if (deathFallbackTimer >= DEATH_FALLBACK_DURATION) {
                alive = false;
            }

            return;
        }

        if (animationPlayer == null) {
            alive = false;
            return;
        }

        animationPlayer.update();

        if (animationPlayer.isFinished()) {
            alive = false;
        }
    }

    @Override
    protected void onDeath() {
        if (state == STATE_DIE) {
            return;
        }

        deathFallbackTimer = 0;

        if (getDeathType() == DeathType.EXPLOSION && explosionDieAnimation != null) {
            dieAnimation = explosionDieAnimation;
        } else if (normalDieAnimation != null) {
            dieAnimation = normalDieAnimation;
        } else {
            dieAnimation = explosionDieAnimation;
        }

        changeState(STATE_DIE);

        AudioManager.playEffect("zombie_die");

        System.out.println("僵尸死亡，死亡类型: " + getDeathType());
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

        renderDeathFallbackOverlay(g);
    }

    private void renderDeathFallbackOverlay(Graphics2D g) {
        if (state != STATE_DIE) {
            return;
        }

        if (dieAnimation != null) {
            return;
        }

        Color oldColor = g.getColor();

        g.setColor(new Color(60, 60, 60, 130));
        g.fillRect((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }

    private void drawFallback(Graphics2D g) {
        Color oldColor = g.getColor();

        if (state == STATE_ATTACK) {
            g.setColor(Color.DARK_GRAY);
        } else if (state == STATE_DIE) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillRect((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }

    @Override
    public Rectangle getCollisionBounds() {
        if (state == STATE_DIE || hp <= 0) {
            return new Rectangle(0, 0, 0, 0);
        }

        return new Rectangle(
                (int) x + 38,
                (int) y + 20,
                width - 55,
                height - 30
        );
    }
}
