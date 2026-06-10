package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.ui.UIButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MainMenuScene extends BaseScene {
    private final SceneManager sceneManager;
    private BufferedImage background;
    private UIButton startButton;
    private UIButton loadButton;
    private UIButton exitButton;

    public MainMenuScene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }
    @Override
    public void onEnter(){ // 这个是 给 Manager 中的 change 操作使用的 ，就是不是 主动调用的，至于在哪里调用，当然是通过事件响应机制调用了
        System.out.println("进入主菜单场景");
        if (ImageManager.hasImage(ImageKeys.BACKGROUND_MAIN_MENU)) {
            background = ImageManager.getImage(ImageKeys.BACKGROUND_MAIN_MENU);//
        }
        startButton = new UIButton(425,80,390,120,ImageManager.getImage(ImageKeys.UI_START_BUTTON));
        loadButton = new UIButton(
                510,
                225,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_LOAD_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_LOAD_BUTTON)
                        : null
        );
        exitButton = new UIButton(
                510,
                305,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_EXIT_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_EXIT_BUTTON)
                        : null
        );
        AudioManager.playBGM("main_menu");
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
        loadButton.render(g);
        exitButton.render(g);
        drawFallbackButtons(g);
    }
    @Override
    public void onMousePressed(int x,int y){
        if(startButton.contains(x,y)){
            AudioManager.playEffect("click");
            sceneManager.changeScene(new LevelSelectScene(sceneManager));
            return;
        }

        if (loadButton.contains(x, y)) {
            AudioManager.playEffect("click");
            sceneManager.changeScene(new LevelScene(sceneManager, true));
            return;
        }

        if (exitButton.contains(x, y)) {
            AudioManager.playEffect("click");
            System.exit(0);
        }
    }// 你想过了 没有 如果让你来写 你会怎么样，类是类，对象是对象，实际做事的 是对象，你知道了，但是 工程中你又 怎么 才能 用到呢？ 这里就用到了，如果真的按照你理解的面向对象的思想，
    // 这里应该会是一番套娃操作
    private void drawBackground(Graphics2D g){
        g.drawImage(background,0,0,GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT,null);
    }

    private void drawFallbackButtons(Graphics2D g) {
        if (!ImageManager.hasImage(ImageKeys.UI_LOAD_BUTTON)) {
            drawTextButton(g, loadButton, "读取存档");
        }

        if (!ImageManager.hasImage(ImageKeys.UI_EXIT_BUTTON)) {
            drawTextButton(g, exitButton, "退出游戏");
        }
    }

    private void drawTextButton(Graphics2D g, UIButton button, String text) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        int x = button.getBounds().x;
        int y = button.getBounds().y;
        int width = button.getBounds().width;
        int height = button.getBounds().height;

        g.setColor(new Color(130, 85, 40, 220));
        g.fillRoundRect(x, y, width, height, 18, 18);

        g.setColor(new Color(245, 220, 130));
        g.drawRoundRect(x, y, width, height, 18, 18);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
        int textWidth = g.getFontMetrics().stringWidth(text);

        g.setColor(Color.WHITE);
        g.drawString(text, x + (width - textWidth) / 2, y + 40);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
}
