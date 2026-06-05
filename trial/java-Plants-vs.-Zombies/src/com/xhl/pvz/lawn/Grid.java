package com.xhl.pvz.lawn;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grid {

    private final int rowCount;
    private final int colCount;

    private final int startX;
    private final int startY;

    private final int cellWidth;
    private final int cellHeight;

    public Grid(
            int rowCount,
            int colCount,
            int startX,
            int startY,
            int cellWidth,
            int cellHeight
    ) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.startX = startX;
        this.startY = startY;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public int getRowByY(int y) {
        if (y < startY || y >= startY + rowCount * cellHeight) {
            return -1;
        }

        return (y - startY) / cellHeight;
    }

    public int getColByX(int x) {
        if (x < startX || x >= startX + colCount * cellWidth) {
            return -1;
        }

        return (x - startX) / cellWidth;
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < rowCount
                && col >= 0 && col < colCount;
    }

    public boolean isInsideGrid(int x, int y) {
        return getRowByY(y) != -1 && getColByX(x) != -1;
    }

    public int getCellX(int col) {
        return startX + col * cellWidth;
    }

    public int getCellY(int row) {
        return startY + row * cellHeight;
    }

    public int getPlantX(int col) {
        return getCellX(col);
    }

    public int getPlantY(int row) {
        return getCellY(row) + 5;
    }

    public int getZombieY(int row) {
        return getCellY(row);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int getRightX() {
        return startX + colCount * cellWidth;
    }

    public int getBottomY() {
        return startY + rowCount * cellHeight;
    }

    public void renderDebugGrid(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(Color.BLACK);

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int x = getCellX(col);
                int y = getCellY(row);

                g.drawRect(x, y, cellWidth, cellHeight);
            }
        }

        g.setColor(oldColor);
    }
}