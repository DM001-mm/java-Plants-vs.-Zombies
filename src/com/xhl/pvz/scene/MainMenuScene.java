package com.xhl.pvz.scene;

import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.manager.ImageManager;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MainMenuScene extends BaseScene {
    private final SceneManager sceneManager;
    private BufferedImage background;
    private BufferedImage startButton;
    private Rectangle startButtonBounds;
    public MainMenuScene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }
    @Override
    public void onEnter(){ // 这个是 给 Manager 中的 change 操作使用的 ，就是不是 主动调用的，至于在哪里调用，当然是通过事件响应机制调用了
        System.out.println("进入主菜单场景");
        background = ImageManager.getImage("background.main_menu");// 
        startButton = ImageManager.getImage("ui.start_button");
        startButtonBounds = new Rectangle(350,400,200,80);
        // 如果 需要 ，这里可以加上 声音
    } // 进入只是 为了加载一些功能，画面渲染 分给了 render函数
    @Override
    public void render(Graphics2D g){ // 绘画交给专门的部分 我只负责提供方法，对吗
        if(background!=null){
            g.drawImage(background,0,0,900,600,null);
        }
        if(startButton!=null){
            g.drawImage(startButton,startButtonBounds.x,startButtonBounds.y,startButtonBounds.width,startButtonBounds.height,null);
        }
    }
    @Override
    public void onMousePressed(int x,int y){
        if(startButtonBounds.contains(x,y)){
            sceneManager.changeScene(new LevelScene(sceneManager)); // 改进入 选择 游戏 关卡 界面了
        }
    }// 你想过了 没有 如果让你来写 你会怎么样，类是类，对象是对象，实际做事的 是对象，你知道了，但是 工程中你又 怎么 才能 用到呢？ 这里就用到了，如果真的按照你理解的面向对象的思想，
    // 这里应该会是一番套娃操作
}
