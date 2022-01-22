package rhythm_game_0;

import javax.swing.JFrame;

public class RhythmGame extends JFrame {
	
	public RhythmGame() { //생성자 : 인스턴스를 만들어 주었을떄 가장 먼저 실행되는 부분
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //창이 화면 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창이 꺼지면 프로그렘도 종료
		setVisible(true); //디폴트값 false
	}

}
