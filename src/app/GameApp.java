package src.app;

import src.core.GameLoop;
import src.core.SceneManager;
import src.scene.MainMenuScene;
import src.manager.ImageManager;
import src.manager.AudioManager;
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
        AudioManager.loadAll();
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
    // private void loadResources(){
        // ImageManager.loadAll();
        

    // }
}
