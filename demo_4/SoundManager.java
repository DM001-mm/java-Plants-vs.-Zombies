package demo_4;
import java.io.InterruptedIOException;
import src.Music.MP3Player;
public class SoundManager{
    public static void play(SoundType type){
        string path = type.getPath();
        new Thread(()->{
            try {
                MP3Player player =new MP3Player(path);
                player.play();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }).start();
    }
    // 声音本来就短，这样搞，只能说分散了Zombie 类的压力，和我的压力，因为代码封装 -> 复用了
    // public static void stop(SoundType type){
    //     MP3Player player =player.get(type);
    // }
}
// import java.io.FileNotFoundException;
// public class SoundManager{
//     public static void startZombieSound(){
//         Thread t=new Thread(()->{ //lambda 表达式
//             while(true){
//                 try {
//                     MP3Player player =new MP3Player("E:\\植物大战僵尸\\demo_1\\MC_Zombie.mp3");
//                     player.play();
//                     Thread.sleep(3000);
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 } catch(FileNotFoundException e){
//                     e.printStackTrace();
//                 }
//             }
//         });
//         t.start();
//     }
// }
