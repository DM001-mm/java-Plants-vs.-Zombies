package com.xhl.pvz.app;

import com.xhl.pvz.core.GameLoop;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.scene.MainMenuScene;
import com.xhl.pvz.manager.ImageManager;

public class GameApp {

    private GameWindow window;
    private SceneManager sceneManager;
    private GameLoop gameLoop;

    public void start(){
        loadResources();
        initScene();   
        createWindow();
        startGameLoop();
    }
    
    private void loadResources(){
        ImageManager.loadAll();
    }
    private void initScene(){
        sceneManager  = new SceneManager();
        sceneManager.changeScene(new MainMenuScene(sceneManager));
    }
    private void createWindow(){
        window = new GameWindow(sceneManager);
        window.showWindow();
    }
    private void startGameLoop(){
        gameLoop = new GameLoop(sceneManager,window.getGamePanel());
        gameLoop.start();
    }
}
