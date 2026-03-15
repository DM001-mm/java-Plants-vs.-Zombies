package demo_3;
import javax.swing.*;
import java.io.FileNotFoundException;
import demo_3.interface.Attack;
import demo_3.interface.Walk;
import java.awt.image.BufferedImage;

// import demo_3.interface.Attack;
// import demo_3.interface.Walk;

public class Zombie implements Walk,Attack{
    int HP;
    int x;
    int y;
    private int status;//0 died ,1 dying,2 attacking,3 walking
    BufferedImage[] walk_png;
    BufferedImage[] attack_png;
    // BufferedImage walks;
    int index_1;
    int index_2;
    int index_3;
    BufferedImage[] dying_png;
    MP3Player player;
    public Zombie(){
        x=1000;
        y=300;
        status =3;
        HP=100;
        player =new MP3Player("E:\\植物大战僵尸\\demo_1\\MC_Zombie.mp3");
        walk_png=new BufferedImage[18];        
        attack_png=new BufferedImage[18];
        dying_png=new BufferedImage[18];
        index_1=index_2=index_3=0;
        // MP3Player player;
        try {
            Thread t=new Thread(new Runnable(){
                @Override
                public void run(){
                    while(HP>0){
                        player.play();
                        Thread.sleep(100);
                    }
                } 
            });
            t.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){ // 其实 如果离开书本，我写里面写外面 都不影响 现在 这种 赶作业的我
            e.printStackTrace();
        }
    }
    public void walk(){
        x--;
        status=3;
        index_2++;
    }
    public void attack(){
        status=2;
        index_3++;
    }
    public int getStatus(){
        return this.status;
    }
    public void dying(){
        status=1;
        player.close();  // 这个代码 太混乱了 不行了，ai教了我soundManager 短音频管理类的框架 短音频的播放优化，见demo_4
        
    }
    public void draw(Graphics g){
        if(status==0) return ;
        else if(status==1) g.drawImage(dying_png,x,y,null);
        else if(status==2) g.drawImage(attack_png,x,y,null);
        else if(status==3) g.drawImage(walk_png,x,y,null);
        
    }
}
