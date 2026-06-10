package src.manager;

import src.core.LevelContext;
import src.entity.item.Sun;

import java.util.Random;

public class SkySunSpawner {

    private final Random random = new Random();

    private final int spawnInterval;
    private int spawnTimer = 0;

    private final int minX;
    private final int maxX;
    private final int minTargetY;
    private final int maxTargetY;

    public SkySunSpawner(
            int spawnInterval,
            int minX,
            int maxX,
            int minTargetY,
            int maxTargetY
    ) {
        this.spawnInterval = spawnInterval;
        this.minX = minX;
        this.maxX = maxX;
        this.minTargetY = minTargetY;
        this.maxTargetY = maxTargetY;
    }

    public void update(LevelContext context) {
        spawnTimer++;

        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0;
            spawnSun(context);
        }
    }

    private void spawnSun(LevelContext context) {
        int x = randomBetween(minX, maxX);
        int targetY = randomBetween(minTargetY, maxTargetY);

        Sun sun = new Sun(x, -50, targetY);

        context.getEntityManager().addSun(sun);

        System.out.println("天空掉落阳光: x = " + x + ", targetY = " + targetY);
    }

    private int randomBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}