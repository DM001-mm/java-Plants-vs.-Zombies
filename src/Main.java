package src;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame frame =new JFrame();
        GamePanel panel =new GamePanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    
}
