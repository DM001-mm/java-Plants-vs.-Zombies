package com.xhl.pvz.core;

import com.xhl.pvz.scene.Scene;
import java.awt.*;
public class SceneManager { //场景管理器，负责 管理 当前 游戏 正在 显示哪个界面，并把update,render,鼠标键盘事件发给当前的 场景
    private Scene currentScene; // 工厂模式 只 作为 框架
    
    public void changeScene(Scene newScene){
        if(currentScene !=null){
            currentScene.onExit();
        }

        currentScene = newScene;

        if(currentScene!=null){
            currentScene.onEnter();
        }
    }
    
    public void update(){
        if(currentScene!=null){
            currentScene.update();
        }
    }
    
    public void render(Graphics2D g){  // 这个还是那个非常古老的包里面的
        if(currentScene!=null){
            currentScene.render(g);
        }
    }
    
    public void onMousePressed(int x,int y){
        if(currentScene!=null){
            currentScene.onMousePressed(x,y);
        }
    }
    
    public void onMouseReleased(int x,int y){
        if(currentScene!=null){
            currentScene.onMousePressed(x,y);
        }
    }

    public void onMouseMoved(int x,int y){
        if(currentScene!=null){
            currentScene.onMouseMoved(x,y);
        }
    }

    public void onMouseDragged(int x,int y){
        if(currentScene!=null){
            currentScene.onMouseDragged(x,y);
        }
    }

    public void onKeyPressed(int keyCode){
        if(currentScene!=null){
            currentScene.onKeyPressed(keyCode);
        }
    }
    
    public void onKeyReleased(int keyCode){
        if(currentScene!=null){
            currentScene.onKeyReleased(keyCode);
        }
    }
}
