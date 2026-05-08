package com.xhl.pvz.app;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import javax.swing.JFrame;
public class GameWindow extends JFrame{
    private final GamePanel gamePanel;
    
   
    public GameWindow(SceneManager sceneManager){
        setTitle(GameConfig.TITLE);
        // setSize(GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        gamePanel = new GamePanel(sceneManager);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void showWindow(){
        setVisible(true);
        gamePanel.requestFocusInWindow();
    } // 相当于 外面 用了 现在 再用就是相当于 是 外面的对象 在使用它
    // made 还是 被 课本的 代码 框架 固定 限制了思维
    public GamePanel getGamePanel(){
        return gamePanel;
    }
}
