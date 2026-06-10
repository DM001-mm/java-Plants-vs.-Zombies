package com.xhl.pvz.level;

import com.xhl.pvz.factory.ZombieFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelDefinitions {

    private LevelDefinitions() {
    }

    public static LevelDefinition createLevel1() {
        List<SpawnEvent> events = new ArrayList<>();

        events.add(new SpawnEvent(600, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(900, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(1150, ZombieFactory.NORMAL_ZOMBIE, 3));

        events.add(new SpawnEvent(1400, ZombieFactory.NORMAL_ZOMBIE, 0));
        events.add(new SpawnEvent(1550, ZombieFactory.NORMAL_ZOMBIE, 4));
        events.add(new SpawnEvent(1700, ZombieFactory.NORMAL_ZOMBIE, 2));

        addWave(
                events,
                1950,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{1, 3, 2},
                120
        );

        addWave(
                events,
                2350,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{0, 4, 1, 3},
                100
        );

        events.add(new SpawnEvent(2750, ZombieFactory.NORMAL_ZOMBIE, 0));
        events.add(new SpawnEvent(2780, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(2810, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(2840, ZombieFactory.NORMAL_ZOMBIE, 3));
        events.add(new SpawnEvent(2870, ZombieFactory.NORMAL_ZOMBIE, 4));

        return new LevelDefinition(
                1,
                "Day 1",
                3200,
                events
        );
    }

    public static LevelDefinition createLevel2() {
        List<SpawnEvent> events = new ArrayList<>();

        events.add(new SpawnEvent(500, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(750, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(1000, ZombieFactory.NORMAL_ZOMBIE, 3));

        addWave(
                events,
                1300,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{0, 2, 4},
                100
        );

        addWave(
                events,
                1800,
                ZombieFactory.CONEHEAD_ZOMBIE,
                new int[]{1, 3},
                180
        );

        addWave(
                events,
                2300,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{0, 1, 2, 3, 4},
                80
        );

        return new LevelDefinition(
                2,
                "Day 2",
                3000,
                events
        );
    }

    private static void addWave(
            List<SpawnEvent> events,
            int startTick,
            String zombieType,
            int[] rows,
            int intervalTicks
    ) {
        for (int i = 0; i < rows.length; i++) {
            events.add(new SpawnEvent(
                    startTick + i * intervalTicks,
                    zombieType,
                    rows[i]
            ));
        }
    }
}
