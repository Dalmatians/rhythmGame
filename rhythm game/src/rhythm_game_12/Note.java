package rhythm_game_12;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
	//private String noteType;
	
	public Note(int x/*, String noteType*/) {
		this.x = x;
		//this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}
	
	public void drop() {
		y += Main.NOTE_SPEED; 
	}
	
	@Override
	public void run() {
		try {
			while(true) { // 1초에 700px만큼 내려감
				drop(); 
				Thread.sleep(Main.SLEEP_TIME); // 0.001 * 10
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
 
}

