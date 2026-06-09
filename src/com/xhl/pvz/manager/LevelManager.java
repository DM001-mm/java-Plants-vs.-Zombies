package com.xhl.pvz.manager;

// 这个类 就类似于 僵尸工厂，就是管理关卡用的
import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.zombie.NormalZombie;
import java.util.ArrayList;
import java.util.List;
public class LevelManager {
    private int tick =0;

    private final int gridStartY;
    private final int cellHeight;
    private boolean allZombiesSpawned = false;
    private final List<SpawnEvent> spawnEvents=new ArrayList<>(); // 内部类

    private int nextEventIndex =0;
    // private boolean levelFinished = false;

    public LevelManager(int gridStartY,int cellHeight){
        this.gridStartY=gridStartY;
        this.cellHeight=cellHeight;

        initLevel1();
    }

    private static class SpawnEvent{
        private final int timeTick;
        private final int row;

        private SpawnEvent(int timeTick,int row){
            this.timeTick =timeTick;
            this.row=row;
        }
    } // 就是一个更加可读的二元组
    private void initLevel1(){
        spawnEvents.add(new SpawnEvent(120,2));
        spawnEvents.add(new SpawnEvent(300,1));
        spawnEvents.add(new SpawnEvent(480,3));
        spawnEvents.add(new SpawnEvent(660,0));
        spawnEvents.add(new SpawnEvent(840,4));
        spawnEvents.add(new SpawnEvent(1020,2));
        // spawnEvents.add(new SpawnEvent(120,2));
        
    }

    public void update(LevelContext context){
        if(allZombiesSpawned){
            return ; // 直接没有update了
        }
        tick++;
        while(nextEventIndex<spawnEvents.size()&&tick>=spawnEvents.get(nextEventIndex).timeTick){
            SpawnEvent event =spawnEvents.get(nextEventIndex);
            spawnZombie(context,event.row); // 到了时候开始生产僵尸
            nextEventIndex++;
        }
        if(nextEventIndex>=spawnEvents.size()){
            // levelFinished =true;
            allZombiesSpawned=true;
        }
    }
    private void spawnZombie(LevelContext context, int row) {
        double zombieX = GameConfig.ZOMBIE_SPAWN_X;
        double zombieY = gridStartY + row * cellHeight;

        NormalZombie zombie = new NormalZombie(row, zombieX, zombieY);

        context.getEntityManager().addZombie(zombie);

        System.out.println("刷出普通僵尸: row = " + row);
    } // 这个so easy，知道逻辑就行了，至于 为什么要这样分，只是为了清晰一些

    public boolean isAllZombiesSpawned() {
        return allZombiesSpawned;
    }

    public int getTick() {
            return tick;
        }

    public double getProgressRatio() {
        if (spawnEvents.isEmpty()) {
            return 1.0;
        }

        int lastSpawnTick = spawnEvents.get(spawnEvents.size() - 1).timeTick;

        if (lastSpawnTick <= 0) {
            return 1.0;
        }

        double ratio = (double) tick / lastSpawnTick;
        return Math.max(0.0, Math.min(1.0, ratio));
    }

    public void setTick(int tick) {
        this.tick = tick;

        nextEventIndex = 0;

        while (nextEventIndex < spawnEvents.size()
                && spawnEvents.get(nextEventIndex).timeTick <= tick) {
            nextEventIndex++;
        }

        allZombiesSpawned = nextEventIndex >= spawnEvents.size();
    }
}
