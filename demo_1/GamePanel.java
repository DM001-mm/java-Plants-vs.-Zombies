package demo;
import java.awt.*;
import javax.swing.*;/* */
// import GamePanel;

public class GamePanel extends JPanel{
    private Zombie zombie;
    public GamePanel(){
        zombie =new Zombie(300,300);
        Timer timer = new Timer(100,e->{
            updateGame();
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