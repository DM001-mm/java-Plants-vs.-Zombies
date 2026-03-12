package demo;
import javax.swing.JFrame;
public class Main{
    public static void main(String[] args){
        JFrame frame= new JFrame("PvZ Animation Demo"); // 只要是main函数里面有顶层gui组件 就能够显示，不要被书本上的东西给困住了
        GamePanel panel =new GamePanel();// 主游戏循环类
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}