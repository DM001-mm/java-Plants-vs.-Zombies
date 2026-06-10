package src.level;

import src.factory.ZombieFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelDefinitions {

    private LevelDefinitions() {
    }

    public static List<LevelDefinition> getAllLevels() {
        List<LevelDefinition> levels = new ArrayList<>();

        levels.add(createLevel1());
        levels.add(createLevel2());
        levels.add(createLevel3());

        return levels;
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

        events.add(new SpawnEvent(2500, ZombieFactory.CONEHEAD_ZOMBIE, 2));

        events.add(new SpawnEvent(2750, ZombieFactory.NORMAL_ZOMBIE, 0));
        events.add(new SpawnEvent(2780, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(2810, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(2840, ZombieFactory.NORMAL_ZOMBIE, 3));
        events.add(new SpawnEvent(2870, ZombieFactory.NORMAL_ZOMBIE, 4));

        return new LevelDefinition(
                1,
                "Day 1",
                "普通僵尸入侵，适合熟悉基础玩法。",
                "简单",
                3200,
                150,
                events
        );
    }

    public static LevelDefinition createLevel2() {
        List<SpawnEvent> events = new ArrayList<>();

        events.add(new SpawnEvent(500, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(760, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(1020, ZombieFactory.NORMAL_ZOMBIE, 3));

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
                "路障僵尸开始出现，需要更稳定的防线。",
                "普通",
                3100,
                125,
                events
        );
    }

    public static LevelDefinition createLevel3() {
        List<SpawnEvent> events = new ArrayList<>();

        events.add(new SpawnEvent(450, ZombieFactory.NORMAL_ZOMBIE, 2));
        events.add(new SpawnEvent(700, ZombieFactory.NORMAL_ZOMBIE, 1));
        events.add(new SpawnEvent(950, ZombieFactory.NORMAL_ZOMBIE, 3));

        addWave(
                events,
                1250,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{0, 2, 4},
                100
        );

        addWave(
                events,
                1650,
                ZombieFactory.CONEHEAD_ZOMBIE,
                new int[]{1, 3},
                160
        );

        events.add(new SpawnEvent(2050, ZombieFactory.BUCKETHEAD_ZOMBIE, 2));

        addWave(
                events,
                2400,
                ZombieFactory.NORMAL_ZOMBIE,
                new int[]{0, 1, 3, 4},
                90
        );

        events.add(new SpawnEvent(2700, ZombieFactory.CONEHEAD_ZOMBIE, 0));
        events.add(new SpawnEvent(2750, ZombieFactory.CONEHEAD_ZOMBIE, 4));
        events.add(new SpawnEvent(2900, ZombieFactory.BUCKETHEAD_ZOMBIE, 2));

        return new LevelDefinition(
                3,
                "Day 3",
                "铁桶僵尸来袭，血量更高，压力更大。",
                "困难",
                3400,
                125,
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
