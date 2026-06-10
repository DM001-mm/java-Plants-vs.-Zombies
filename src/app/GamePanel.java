package src.app;

import src.core.GameConfig;
import src.core.SceneManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GamePanel extends JPanel{
    private final SceneManager sceneManager;
    public GamePanel(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        initPanel();
        initInput();
    }
    
    private void initPanel(){
        setPreferredSize(new Dimension(GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT));
        setFocusable(true);
        setDoubleBuffered(true); // 类似 raylib里面的双帧机制 ， 游戏开发 里面还是比较常见的
    }
    
    private void initInput(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                sceneManager.onMousePressed(e.getX(),e.getY()); // 这里 感觉 界面 和 逻辑 部分 直接分开了 ，太牛逼了
            }

            @Override
            public void mouseReleased(MouseEvent e){
                sceneManager.onMouseReleased(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                sceneManager.onMouseMoved(e.getX(),e.getY());
            }
            @Override
            public void mouseDragged(MouseEvent e){
                sceneManager.onMouseDragged(e.getX(),e.getY());
            }
        });

        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                sceneManager.onKeyPressed(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e){
                sceneManager.onKeyReleased(e.getKeyCode());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); // 这个是交给父类 清理画布的

        Graphics2D g2=(Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // set 绘图的 质量/绘图的策略

        sceneManager.render(g2);  // 这个方法一定 自己写的方法
    }
}
