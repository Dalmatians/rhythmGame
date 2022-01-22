package rhythm_game_13;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	
	private Image hitLineImage = new ImageIcon(Main.class.getResource("../images/hitLine.png")).getImage();
	private Image lineBorderImage = new ImageIcon(Main.class.getResource("../images/lineBorder.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	
	private Image judgeImage;
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	public void screenDraw(Graphics2D g) {
		g.drawImage(gameInfoImage, 0, 640, null);
		g.drawImage(lineBorderImage, 720, 30, null);
		g.drawImage(lineBorderImage, 858, 30, null);
		g.drawImage(lineBorderImage, 996, 30, null);
		g.drawImage(lineBorderImage, 1134, 30, null);
		g.drawImage(lineBorderImage, 1272, 30, null);
		
		g.drawImage(noteRouteDImage, 728, 30, null);
		g.drawImage(noteRouteFImage, 866, 30, null);
		g.drawImage(noteRouteJImage, 1004, 30, null);
		g.drawImage(noteRouteKImage, 1142, 30, null);
		
		g.drawImage(hitLineImage, 720, 680, null);
		for(int i = 0; i<noteList.size(); i++) { // 노트 출력
			Note note = noteList.get(i);
			if(note.getY() > 740) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if(!note.isProceeded()) { //사용되지 않은 노트는 없어짐
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		
		g.drawImage(judgeImage, 310, 640, null);
		
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 30, 685);
		g.drawString("100%", 600, 685);
		
		g.drawString("D", 788, 710);
		g.drawString("F", 926, 710);
		g.drawString("J", 1071, 710);
		g.drawString("K", 1209, 710);
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	
	public void releasedD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	}
	
	public void releasedF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	}
	
	public void releasedJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	}
	
	public void releasedK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName, this.difficulty);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName, String difficulty) {
		Beat[] beats = null;
		if(titleName.equals("Grievous Lady") && difficulty.equals("Easy")) {
			int startTime = 1000 - Main.REACH_TIME * 1000;
			int gap = 150;
			beats = new Beat[] {
					new Beat(startTime, "D"),
					new Beat(startTime + gap , "F"),
					
					new Beat(startTime + gap * 3, "J"),
					new Beat(startTime + gap * 4, "K"),
					new Beat(startTime + gap * 5, "J"),
					new Beat(startTime + gap * 6, "F"),
					new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 8, "F"),
					new Beat(startTime + gap * 9, "J"),
					new Beat(startTime + gap * 10, "K"),
					new Beat(startTime + gap * 11, "J"),
					new Beat(startTime + gap * 12, "F"),
					new Beat(startTime + gap * 13, "D"),
					
					new Beat(startTime + gap * 16, "K"),
					new Beat(startTime + gap * 17, "D"),
					
					new Beat(startTime + gap * 19, "J"),
					
					new Beat(startTime + gap * 21, "F"),
					new Beat(startTime + gap * 22, "J"),
					new Beat(startTime + gap * 23, "K"),
					
					new Beat(startTime + gap * 26, "J"),
					new Beat(startTime + gap * 27, "F"),
					new Beat(startTime + gap * 28, "D"),
					
					new Beat(startTime + gap * 31, "D"),
					new Beat(startTime + gap * 32, "F"),
					new Beat(startTime + gap * 33, "J"),
					
			};
		}
		else if(titleName.equals("Grievous Lady") && difficulty.equals("Hard")) {
			int startTime = 1000 - Main.REACH_TIME * 1000;
			int gap = 150;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("MUSIC") && difficulty.equals("Easy")) {
			int startTime = 1000 - Main.REACH_TIME * 500;
			int gap = 210;
			beats = new Beat[] {
					new Beat(startTime, "F"),
			};
		}
		else if(titleName.equals("MUSIC") && difficulty.equals("Hard")) {
			int startTime = 1000 - Main.REACH_TIME * 500;
			int gap = 210;
			beats = new Beat[] {
					new Beat(startTime, "F"),
			};
		}
		else if(titleName.equals("Smoked Trukey Rag") && difficulty.equals("Easy")) {
			int startTime = 1000 - Main.REACH_TIME * 500;
			int gap = 150;
			beats = new Beat[] {
					new Beat(startTime, "K"),
			};
		}
		else if(titleName.equals("Smoked Trukey Rag") && difficulty.equals("Hard")) {
			int startTime = 1000 - Main.REACH_TIME * 500;
			int gap = 150;
			beats = new Beat[] {
					new Beat(startTime, "K"),
			};
		}
		int i = 0;	
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) { //현재 곡이 재생되는 시점을 실시간 파악, 음악에 걸맞는 노트를 떨어트림
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) { // 노트가 바로 안떨어지면 텀을 두면서 떨어짐
				try {
					Thread.sleep(5);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void judge(String input) {
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) { // 입력한 키랑 눌러야하는 키랑 같으면
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(judge.equals("miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		}
		else if(judge.equals("early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();
		}
		else if(judge.equals("late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();
		}
		else if(judge.equals("perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
		}
	}
	
}
