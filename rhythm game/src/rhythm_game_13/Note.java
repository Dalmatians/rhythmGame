package rhythm_game_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 1100 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true; //현재 노트의 진행여부
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("D")) {
			x = 728;
		}else if(noteType.equals("F")) {
			x = 866;
		}else if(noteType.equals("J")) {
			x = 1004;
		}else if(noteType.equals("K")) {
			x = 1142;
		}
		
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}
	
	public void drop() {
		y += Main.NOTE_SPEED; 
		if(y > 720) {
			System.out.println("miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) { // 1초에 700px만큼 내려감
				drop(); 
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME); // 0.001 * 10
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
 
	public String judge() {
		if(y >= 740) {
			System.out.println("Late");
			close();
			return "late";
		}
		else if(y >= 720) {
			System.out.println("Good");
			close();
			return "good";
		}
		else if(y >= 700) {
			System.out.println("Perfect");
			close();
			return "perfect";
		}
		else if(y >= 680) {
			System.out.println("Good");
			close();
			return "good";
		}
		else if(y >= 640) {
			System.out.println("Early");
			close();
			return "early";
		}
		return "none";
	}
	
	public int getY() {
		return y;
	}
	
}

