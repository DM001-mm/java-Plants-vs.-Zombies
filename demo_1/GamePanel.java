package demo_1;
import java.awt.*;
import javax.swing.*;/* */
// import GamePanel;

public class GamePanel extends JPanel{
    private Zombie zombie;
    public GamePanel(){
        zombie =new Zombie(300,300);
        Timer timer = new Timer(100,e->{ // 画面渲染线程
            updateGame();//主游戏场景的加载就只是为了重新渲染，然后植物和僵尸的动作更新 我直接把他移到 僵尸对象的构造函数里面，因为僵尸一创建就会走路，
            repaint();
        });
        timer.start();
    }
    private void updateGame(){
        zombie.update();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        zombie.draw(g);
    }
}