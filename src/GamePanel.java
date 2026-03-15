package src;
import java.awt.*;
import javax.swing.*;
import src.Zombie.Zombie;
public class GamePanel extends JPanel{
    Zombie zombie_1;
    Zombie zombie_2;
    Timer timer;
    public GamePanel(){
        zombie_1 = new Zombie();
        zombie_2 = new Zombie();
        
    }
    void update(){
        timer =new Timer(1,actionEvent->{
            zombie_1.walkAction();
            zombie_2.walkAction();
            repaint();
        });
        timer.start(); // 把这个游戏画面的更新放在了update()里面 为了能主动调用，进而实现和声音的多线程 
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        zombie_1.draw(g);
        zombie_2.draw(g);
    }
}
