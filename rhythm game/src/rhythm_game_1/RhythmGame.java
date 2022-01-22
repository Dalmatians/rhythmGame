package rhythm_game_1;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RhythmGame extends JFrame {
	
	private Image screenImage; //더블버퍼링 : 화면을 계속 갱신하여 자연스럽게 보이게 하는 방법
	private Graphics screenGraphic; 
	
	private Image introbackground; //인트로배경 담는 객체
	
	public RhythmGame() { //생성자 : 인스턴스를 만들어 주었을떄 가장 먼저 실행되는 부분
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //창이 화면 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창이 꺼지면 프로그렘도 종료
		setVisible(true); //디폴트값 false
		
		//이미지를 변수에 초기화
		introbackground = new ImageIcon(Main.class.getResource("../images/RhythmGame_bg.jpg")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); //창의 0, 0 위치에 배경그리기 
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introbackground, 0, 0, null); // 스크린이미지를 그림
		this.repaint();
	}

}
