package com.xhl.pvz.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelDefinition {

    private final int levelId;
    private final String name;
    private final int totalTicks;
    private final List<SpawnEvent> spawnEvents;

    public LevelDefinition(
            int levelId,
            String name,
            int totalTicks,
            List<SpawnEvent> spawnEvents
    ) {
        this.levelId = levelId;
        this.name = name;
        this.totalTicks = totalTicks;
        this.spawnEvents = new ArrayList<>(spawnEvents);
        this.spawnEvents.sort((a, b) -> Integer.compare(a.getTick(), b.getTick()));
    }

    public int getLevelId() {
        return levelId;
    }

    public String getName() {
        return name;
    }

    public int getTotalTicks() {
        return totalTicks;
    }

    public List<SpawnEvent> getSpawnEvents() {
        return Collections.unmodifiableList(spawnEvents);
    }
}
