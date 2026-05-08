package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.manager.ImageManager;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
// import java.awt.event.keyEvent;

public class LevelScene extends BaseScene {

    private final SceneManager sceneManager;
    private BufferedImage background ;

    private final int rowCount =5;
    private final int colCount =9;

    private final int gridStartX = 170;
    private final int gridStartY = 120;
    private final int cellWidth = 80;
    private final int cellHeight =90;
    
    // 测试阳光数
    // private int sunCount = 50;

    public LevelScene(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    @Override
    public void onEnter(){
        background = ImageManager.getImage("background.lawn_day");

        // 后面 加音乐 就+在这里

    }
    @Override
    public void update(){
        // 后面更新：
        //entityManager.updateAll();
        //collisionManager.checkAll();
        //LevelManager.update();
    }
    @Override
    public void render(Graphics2D g){
        drawBackground(g);
        // 调试格子
        drawDebugGrid(g);
    }

    @Override
    public void onMousePressed(int x,int y){
        int row = getRowByY(y);
        int col=getColByX(x);

        if(row!=-1&&col!=-1){
            System.out.println("点击草坪格子:row="+row+",col="+col);
        }
    }

    @Override
    public void onKeyPressed(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE){
            // System.out.println("按下ESC,返回主菜单");
            sceneManager.changeScene(new MainMenuScene(sceneManager));
        }
    }
    private void drawBackground(Graphics2D g){
        g.drawImage(background,0,0,GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT,null);
    }

    private void drawDebugGrid(Graphics2D g){
        for(int row = 0;row<rowCount;row++){
            for(int col =0;col<colCount;col++){
                int x = gridStartX+col*cellWidth;
                int y = gridStartY+row*cellHeight;
                g.drawRect(x,y,cellWidth,cellHeight);
            }
        }
    }

    private int getRowByY(int y){
        if(y<gridStartY||y>=gridStartY+rowCount*cellHeight){
            return -1;
        }
        return (y-gridStartY)/cellHeight;
    }

    private int getColByX(int x){
        if(x<gridStartX||x>=gridStartX+colCount*cellWidth){
            return -1;
        }
        return (x-gridStartX)/cellWidth;
    }

    
}
