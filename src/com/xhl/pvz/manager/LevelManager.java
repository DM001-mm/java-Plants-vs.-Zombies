package com.xhl.pvz.manager;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.zombie.Zombie;
import com.xhl.pvz.factory.ZombieFactory;
import com.xhl.pvz.level.LevelDefinition;
import com.xhl.pvz.level.LevelDefinitions;
import com.xhl.pvz.level.SpawnEvent;
import java.util.List;
import java.util.Random;

public class LevelManager {

    private final int gridStartY;
    private final int cellHeight;
    private final LevelDefinition levelDefinition;
    private final List<SpawnEvent> spawnEvents;
    private final Random random = new Random();

    private int tick =0;
    private int nextEventIndex =0;

    public LevelManager(int gridStartY,int cellHeight){
        this(gridStartY, cellHeight, LevelDefinitions.createLevel1());
    }

    public LevelManager(
            int gridStartY,
            int cellHeight,
            LevelDefinition levelDefinition
    ) {
        this.gridStartY=gridStartY;
        this.cellHeight=cellHeight;
        this.levelDefinition = levelDefinition;
        this.spawnEvents = levelDefinition.getSpawnEvents();
    }

    public void update(LevelContext context){
        tick++;

        while(nextEventIndex<spawnEvents.size()
                && spawnEvents.get(nextEventIndex).getTick() <= tick){
            SpawnEvent event =spawnEvents.get(nextEventIndex);
            spawnZombieByEvent(context,event);
            nextEventIndex++;
        }
    }

    private void spawnZombieByEvent(LevelContext context, SpawnEvent event) {
        int row = getSpawnRow(event);

        double zombieX = GameConfig.ZOMBIE_SPAWN_X;
        double zombieY = gridStartY + row * cellHeight;

        Zombie zombie = ZombieFactory.createZombie(
                event.getZombieType(),
                row,
                zombieX,
                zombieY
        );

        context.getEntityManager().addZombie(zombie);

        System.out.println(
                "刷出僵尸: type = " + event.getZombieType()
                        + ", row = " + row
                        + ", tick = " + tick
        );
    }

    private int getSpawnRow(SpawnEvent event) {
        if (!event.isRandomRow()) {
            return event.getRow();
        }

        return random.nextInt(GameConfig.LAWN_ROW_COUNT);
    }

    public boolean isAllZombiesSpawned() {
        return isFinishedSpawning();
    }

    public boolean isFinishedSpawning() {
        return nextEventIndex >= spawnEvents.size();
    }

    public int getTick() {
            return tick;
        }

    public double getProgressRatio() {
        int totalTicks = levelDefinition.getTotalTicks();

        if (totalTicks <= 0) {
            return 1.0;
        }

        double ratio = (double) tick / totalTicks;
        return Math.max(0.0, Math.min(1.0, ratio));
    }

    public String getLevelName() {
        return levelDefinition.getName();
    }

    public LevelDefinition getLevelDefinition() {
        return levelDefinition;
    }

    public void setTick(int tick) {
        this.tick = tick;
        recalculateNextEventIndex();
    }

    private void recalculateNextEventIndex() {
        nextEventIndex = 0;

        while (nextEventIndex < spawnEvents.size()
                && spawnEvents.get(nextEventIndex).getTick() <= tick) {
            nextEventIndex++;
        }
    }
}
