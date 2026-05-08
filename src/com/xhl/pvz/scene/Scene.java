package com.xhl.pvz.scene;

import java.awt.Graphics2D;

public interface Scene {
    void onEnter();
    void onExit();
    void update();

    void render(Graphics2D g);
    void onMousePressed(int x,int y);
    void onMouseMoved(int x,int y);
    void onMouseReleased(int x,int y);
    void onMouseDragged(int x,int y);
    void onKeyPressed(int keyCode);
    void onKeyReleased(int keyCode);
}
