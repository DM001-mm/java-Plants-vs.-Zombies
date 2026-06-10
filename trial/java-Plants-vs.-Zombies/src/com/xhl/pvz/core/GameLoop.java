package com.xhl.pvz.core;

import com.xhl.pvz.app.GamePanel;
import javax.swing.Timer;

public class GameLoop {
    private final SceneManager sceneManager; // 不是 场景管理器 怎么 也 在这里
    private final GamePanel gamePanel;
    private Timer timer;

    public GameLoop(SceneManager sceneManager,GamePanel gamePanel){
        this.sceneManager = sceneManager;
        this.gamePanel = gamePanel;
    }
    public void start(){
        timer = new Timer(GameConfig.FRAME_DELAY,e->{
            sceneManager.update();
            gamePanel.repaint();            
        });
        timer.start();
    }

    public void stop(){
        if(timer!=null){
            timer.stop();
        }
    }
}
