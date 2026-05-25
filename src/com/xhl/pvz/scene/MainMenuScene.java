package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.ui.UIButton;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MainMenuScene extends BaseScene {
    private final SceneManager sceneManager;
    private BufferedImage background;
    private UIButton startButton;
    // private Rectangle startButtonBounds;
    public MainMenuScene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }
    @Override
    public void onEnter(){ // 这个是 给 Manager 中的 change 操作使用的 ，就是不是 主动调用的，至于在哪里调用，当然是通过事件响应机制调用了
        System.out.println("进入主菜单场景");
        background = ImageManager.getImage(ImageKeys.BACKGROUND_MAIN_MENU);//
        startButton = new UIButton(350,410,200,80,ImageManager.getImage(ImageKeys.UI_START_BUTTON));
        // 如果 需要 ，这里可以加上 声音
    } // 进入只是 为了加载一些功能，画面渲染 分给了 render函数
    @Override
    public void onExit(){
            
    }
    @Override
    public void update(){

    }

    @Override
    public void render(Graphics2D g){ // 绘画交给专门的部分 我只负责提供方法，对吗
        drawBackground(g);
        startButton.render(g);
    }
    @Override
    public void onMousePressed(int x,int y){
        if(startButton.contains(x,y)){
            sceneManager.changeScene(new LevelScene(sceneManager)); // 改进入 选择 游戏 关卡 界面了
        }
    }// 你想过了 没有 如果让你来写 你会怎么样，类是类，对象是对象，实际做事的 是对象，你知道了，但是 工程中你又 怎么 才能 用到呢？ 这里就用到了，如果真的按照你理解的面向对象的思想，
    // 这里应该会是一番套娃操作
    private void drawBackground(Graphics2D g){
        g.drawImage(background,0,0,GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT,null);
    }
}
