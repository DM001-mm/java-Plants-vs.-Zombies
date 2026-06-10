package src.entity.plant;

import src.animation.Animation;
import src.animation.AnimationPlayer;
import src.core.LevelContext;
import src.entity.item.Sun;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sunflower extends Plant {

    private static final int STATE_IDLE = 0;
    private static final int STATE_PRODUCE = 1;

    private int state = STATE_IDLE;

    private BufferedImage fallbackImage;

    private Animation idleAnimation;
    private Animation produceAnimation;
    private AnimationPlayer animationPlayer;

    /**
     * 30 FPS 下，300 帧约等于 10 秒。
     */
    private final int produceInterval = 300;
    private int produceTimer = 0;

    public Sunflower(int row, int col, double x, double y) {
        super(
                row,
                col,
                x,
                y,
                80,
                80,
                300,
                50,
                150
        );

        loadImages();
        loadAnimations();
    }

    private void loadImages() {
        if (ImageManager.hasImage(ImageKeys.PLANT_SUNFLOWER_IDLE_0)) {
            fallbackImage = ImageManager.getImage(ImageKeys.PLANT_SUNFLOWER_IDLE_0);
        }
    }

    private void loadAnimations() {
        if (ImageManager.hasFrames(ImageKeys.ANIM_SUNFLOWER_IDLE)) {
            idleAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_SUNFLOWER_IDLE),
                    5,
                    true
            );
        }

        if (ImageManager.hasFrames(ImageKeys.ANIM_SUNFLOWER_PRODUCE)) {
            produceAnimation = new Animation(
                    ImageManager.getFrames(ImageKeys.ANIM_SUNFLOWER_PRODUCE),
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
        produceTimer++;

        if (produceTimer >= produceInterval) {
            produceTimer = 0;
            produceSun(context);
        }

        updateAnimation();
    }

    private void produceSun(LevelContext context) {
        double sunX = x + 15;
        double sunY = y - 20;

        Sun sun = new Sun(sunX, sunY);
        context.getEntityManager().addSun(sun);

        changeState(STATE_PRODUCE);

        System.out.println("向日葵生产阳光");
    }

    private void changeState(int newState) {
        if (state == newState) {
            return;
        }

        state = newState;

        if (animationPlayer == null) {
            return;
        }

        if (state == STATE_IDLE && idleAnimation != null) {
            animationPlayer.setAnimation(idleAnimation);
        } else if (state == STATE_PRODUCE && produceAnimation != null) {
            animationPlayer.setAnimation(produceAnimation);
        }
    }

    private void updateAnimation() {
        if (animationPlayer == null) {
            return;
        }

        animationPlayer.update();

        if (state == STATE_PRODUCE && animationPlayer.isFinished()) {
            changeState(STATE_IDLE);
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

        if (state == STATE_PRODUCE) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.ORANGE);
        }

        g.fillOval((int) x, (int) y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, width, height);

        g.setColor(oldColor);
    }
}