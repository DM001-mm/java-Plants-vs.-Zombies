package src;
import java.awt.*;
import javax.swing.*;
public class GamePanel extends JPanel{
    Zombie zombie_1;
    Zombie zombie_2;
    Timer timer;
    public GamePanel(){
        zombie_1 = new Zombie(300,300);
        zombie_2 = new Zombie(300,600);
        timer =new Timer(1,actionEvent->{
            update();
            repaint();
        });
        timer.start();
    }
    void update(){
        zombie_1.update();
        zombie_2.update();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        zombie_1.draw(g);
        zombie_2.draw(g);
    }
}
