package com.xhl.pvz.save;

import java.io.Serializable;

public class BulletSaveData implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String bulletType;
    private final int row;
    private final double x;
    private final double y;

    public BulletSaveData(String bulletType, int row, double x, double y) {
        this.bulletType = bulletType;
        this.row = row;
        this.x = x;
        this.y = y;
    }

    public String getBulletType() {
        return bulletType;
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
}