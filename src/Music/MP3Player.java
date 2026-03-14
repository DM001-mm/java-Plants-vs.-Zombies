package src.Music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player {
    Player player;
    public MP3Player(String s){
        try {
            player=new Player(new FileInputStream(s));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void play(){
        try{
            player.play();
        }catch(JavaLayerException e){
            e.printStackTrace();
        }
    }
}
