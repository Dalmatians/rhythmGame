package rhythm_game_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	
	private Image hitLineImage = new ImageIcon(Main.class.getResource("../images/hitLine.png")).getImage();
	private Image lineBorderImage = new ImageIcon(Main.class.getResource("../images/lineBorder.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteLine.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		gameMusic.start();
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
		g.drawImage(noteBasicImage, 1004, 600, null);
		
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
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	public void pressF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	
	public void pressJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteLinePressed.png")).getImage();
		new Music("hitSound.mp3", false).start();
	}
	
	public void pressK() {
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
		
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
}
