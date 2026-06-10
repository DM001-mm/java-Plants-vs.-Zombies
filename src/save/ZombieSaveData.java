package src.save;

import java.io.Serializable;

public class ZombieSaveData implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String zombieType;
    private final int row;
    private final double x;
    private final double y;
    private final int hp;

    public ZombieSaveData(String zombieType, int row, double x, double y, int hp) {
        this.zombieType = zombieType;
        this.row = row;
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public String getZombieType() {
        return zombieType;
    }

    public int getRow() {
        return row;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }
}