package com.xhl.pvz.level;

public class SpawnEvent {

    public static final int RANDOM_ROW = -1;

    private final int tick;
    private final String zombieType;
    private final int row;

    public SpawnEvent(int tick, String zombieType, int row) {
        this.tick = tick;
        this.zombieType = zombieType;
        this.row = row;
    }

    public int getTick() {
        return tick;
    }

    public String getZombieType() {
        return zombieType;
    }

    public int getRow() {
        return row;
    }

    public boolean isRandomRow() {
        return row == RANDOM_ROW;
    }
}
