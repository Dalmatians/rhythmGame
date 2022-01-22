package rhythm_game_13;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{

	private Player player;
	private boolean isloop; //현재 곡이 무한재생인지 확인
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isloop) {
		try {
			this.isloop = isloop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //버퍼에 담아서 읽어올수있도록함
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //시간단위
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {
		isloop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() { //쓰레드 쓰면 무조건 해야됨
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); //버퍼에 담아서 읽어올수있도록함
				player = new Player(bis);
			}while(isloop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}







