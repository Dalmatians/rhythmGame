package rhythm_game_1;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RhythmGame extends JFrame {
	
	private Image screenImage; //������۸� : ȭ���� ��� �����Ͽ� �ڿ������� ���̰� �ϴ� ���
	private Graphics screenGraphic; 
	
	private Image introbackground; //��Ʈ�ι�� ��� ��ü
	
	public RhythmGame() { //������ : �ν��Ͻ��� ����� �־����� ���� ���� ����Ǵ� �κ�
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //â�� ȭ�� ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //â�� ������ ���α׷��� ����
		setVisible(true); //����Ʈ�� false
		
		//�̹����� ������ �ʱ�ȭ
		introbackground = new ImageIcon(Main.class.getResource("../images/RhythmGame_bg.jpg")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); //â�� 0, 0 ��ġ�� ���׸��� 
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introbackground, 0, 0, null); // ��ũ���̹����� �׸�
		this.repaint();
	}

}
