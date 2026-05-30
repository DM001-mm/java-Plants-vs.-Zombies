package com.xhl.pvz;
import com.xhl.pvz.app.*;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            GameApp app =new GameApp();
            app.start();
        }); 
    }
}
