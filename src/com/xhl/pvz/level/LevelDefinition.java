package com.xhl.pvz.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelDefinition {

    private final int levelId;
    private final String name;
    private final String description;
    private final String difficulty;
    private final int totalTicks;
    private final int initialSun;
    private final List<SpawnEvent> spawnEvents;

    public LevelDefinition(
            int levelId,
            String name,
            int totalTicks,
            int initialSun,
            List<SpawnEvent> spawnEvents
    ) {
        this(
                levelId,
                name,
                "",
                "普通",
                totalTicks,
                initialSun,
                spawnEvents
        );
    }

    public LevelDefinition(
            int levelId,
            String name,
            String description,
            String difficulty,
            int totalTicks,
            int initialSun,
            List<SpawnEvent> spawnEvents
    ) {
        this.levelId = levelId;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.totalTicks = totalTicks;
        this.initialSun = initialSun;
        this.spawnEvents = new ArrayList<>(spawnEvents);
        this.spawnEvents.sort((a, b) -> Integer.compare(a.getTick(), b.getTick()));
    }

    public int getLevelId() {
        return levelId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getTotalTicks() {
        return totalTicks;
    }

    public int getInitialSun() {
        return initialSun;
    }

    public List<SpawnEvent> getSpawnEvents() {
        return Collections.unmodifiableList(spawnEvents);
    }
}
