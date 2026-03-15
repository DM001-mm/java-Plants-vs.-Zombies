package src.Zombie;

import javax.swing.*;

import src.Music.MP3Player;

import java.awt.*;
import java.io.InterruptedIOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Zombie {
    public int x; // 横坐标
    public int y; // 纵坐标
    private int XP;
    private int state;// 0 为eat,1为walk ,僵尸当前的状态
    BufferedImage[] walk_action;
    BufferedImage[] eat_action;
    private int index_1;
    private int index_2;
    private MP3Player player;
    public Zombie(){
        this.state=0;
        x=1000;
        loadImages();
        player=new MP3Player("E:\\植物大战僵尸\\demo_1\\MC_Zombie.mp3");
    }
    private void loadImages(){
        walk_action =new BufferedImage[18]; // 行走的动作，以后还会有吃饭的动作
        try {
            walk_action[0]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move1.png"));
            walk_action[1]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move2.png"));
            walk_action[2]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move3.png"));
            walk_action[3]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move4.png"));
            walk_action[4]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move5.png"));
            walk_action[5]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move6.png"));
            walk_action[6]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move7.png"));
            walk_action[7]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move8.png"));
            walk_action[8]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move9.png"));
            walk_action[9]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move10.png"));
            walk_action[10]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move11.png"));
            walk_action[11]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move12.png"));
            walk_action[12]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move13.png"));
            walk_action[13]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move14.png"));
            walk_action[14]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move15.png"));
            walk_action[15]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move16.png"));
            walk_action[16]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move17.png"));
            walk_action[17]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move18.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    public void walkAction(){
        index_1++;
        if(index_1>=walk_action.length){
            index_1=0;
        }
    }
    public void eatAction(){
        index_2++;
        if(index_2>=eat_action.length){
            index_2=0;
        }
    }
    
    public void yell(){
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                while(XP>0){
                    player.play();
                }
            }
        });
        t.start();
    }  
    public void draw(Graphics g){
        switch(state){
            case 0->g.drawImage(eat_action[index_2],x,y,null);
            case 1->g.drawImage(walk_action[index_1],x,y,null);
            // default->break;
        }

    }
}
