package rhythm_game_0;

import javax.swing.JFrame;

public class RhythmGame extends JFrame {
	
	public RhythmGame() { //������ : �ν��Ͻ��� ����� �־����� ���� ���� ����Ǵ� �κ�
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //â�� ȭ�� ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //â�� ������ ���α׷��� ����
		setVisible(true); //����Ʈ�� false
	}

}
