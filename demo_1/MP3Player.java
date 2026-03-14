import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player; 
 
public class MP3Player { // 这样封装成一个类，应该就不能要播放音乐的时候 还要写这么长名字的包
	Player player;
	public void play(String s){ //static 不能单独用某个对象的成员
		try {
			player= new Player(new FileInputStream(s));
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	// public static void main(String[] args)  {
	// 	try {
	// 		Player player = new Player(new FileInputStream("E:\\植物大战僵尸\\demo_1\\MC_Zombie.mp3"));  // 创建播放器
	// 		player.play();                                            // 开始播放
	// 	} catch (JavaLayerException e) {
	// 		e.printStackTrace();
	// 	} catch (FileNotFoundException e) {
	// 		e.printStackTrace();
	// 	}
	// }
}