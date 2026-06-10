package src.scene;

import java.awt.*;

public class BaseScene implements Scene{
    @Override
    public void onEnter(){}

    @Override
    public void onExit(){}

    @Override
    public void update(){}

    @Override
    public void render(Graphics2D g){}

    @Override
    public void onMousePressed(int x,int y){}

    @Override
    public void onMouseReleased(int x,int y){}

    @Override
    public void onMouseMoved(int x,int y){}

    @Override
    public void onMouseDragged(int x,int y){}

    @Override
    public void onKeyPressed(int keyCode){}

    @Override
    public void onKeyReleased(int keyCode){}
}
