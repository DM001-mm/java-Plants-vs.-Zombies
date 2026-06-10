package src;
import src.app.*;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            GameApp app =new GameApp();
            app.start();
        }); // 这里 注意 timer的 特殊机制 不是 和raylib 中的 主游戏循环一样
    }
}
