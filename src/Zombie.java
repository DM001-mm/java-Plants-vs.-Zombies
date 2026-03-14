package src;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Zombie{
    private BufferedImage[] frames;
    private int frameIndex =0; // 目前的帧图的编号
    private int x; // 僵尸的横坐标位移
    private int y;
    private MP3Player player;
    public Zombie(int x,int y){
        this.x=x;
        this.y=y;
        loadImages();
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                Timer timer =new Timer(3,e->{
                    player.start();
                });
                timer.start();
                // player.play();
            }
        });
    }
    private void loadImages(){
        frames =new BufferedImage[18];
        try {
            frames[0]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move1.png"));
            frames[1]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move2.png"));
            frames[2]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move3.png"));
            frames[3]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move4.png"));
            frames[4]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move5.png"));
            frames[5]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move6.png"));
            frames[6]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move7.png"));
            frames[7]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move8.png"));
            frames[8]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move9.png"));
            frames[9]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move10.png"));
            frames[10]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move11.png"));
            frames[11]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move12.png"));
            frames[12]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move13.png"));
            frames[13]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move14.png"));
            frames[14]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move15.png"));
            frames[15]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move16.png"));
            frames[16]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move17.png"));
            frames[17]=ImageIO.read(new File("E:\\植物大战僵尸\\pvz\\src\\resources\\ZombiePng\\NormalZombie\\move\\move18.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        frameIndex++;
        x--;
        if(x<=0) x=1000;
        if(frameIndex>=frames.length){
            frameIndex=0;
        }
    }
    public void draw(Graphics g){
        g.drawImage(frames[frameIndex],x,y,null);
    }
}