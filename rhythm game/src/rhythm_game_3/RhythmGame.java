package rhythm_game_3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RhythmGame extends JFrame {

	private Image screenImage; // ������۸� : ȭ���� ��� �����Ͽ� �ڿ������� ���̰� �ϴ� ���
	private Graphics screenGraphic;

	// �̹����� ������ �ʱ�ȭ
	// ��Ʈ�ι��
	private Image introbackground = new ImageIcon(Main.class.getResource("../images/RhythmGame_bg.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	private ImageIcon exitButtonBasic = new ImageIcon(Main.class.getResource("../images/Exit.png"));
	private ImageIcon exitButtonPressed = new ImageIcon(Main.class.getResource("../images/exitPressed.png"));
	private JButton exitButton = new JButton(exitButtonBasic);

	private int mouseX, mouseY;

	public RhythmGame() { // ������ : �ν��Ͻ��� ����� �־����� ���� ���� ����Ǵ� �κ�
		setUndecorated(true); // �⺻������ �����ϴ� �޴��ٰ� �Ⱥ���
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // â�� ȭ�� ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â�� ������ ���α׷��� ����
		setVisible(true); // ����Ʈ�� false
		setBackground(new Color(0, 0, 0, 0)); // paintComponent�� ������ ȸ���̾ƴ϶� �Ͼ������ ��µ�
		setLayout(null);

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonPressed); //�̹��� �ٲ���
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasic); //�̹��� �ٲ���
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0); //��ư ������ ���α׷� ����
			}
		});
		add(exitButton);
		
		menuBar.setBounds(0, 0, 1280, 30); // ��ġ�� ũ�� ����
		menuBar.addMouseListener(new MouseAdapter() { //�޴��ٸ� ��� �̵���ų���� �ϴ°���
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { //�޴��ٸ� ��� �̵���ų���� �ϴ°���
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);


		Music intromusic = new Music("pretender.mp3", true); // �������
		intromusic.start();
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // â�� 0, 0 ��ġ�� ���׸���
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introbackground, 0, 0, null); // ��ũ���̹����� �׸�
		paintComponents(g); // jlabel�����͵��� jframe�� �߰��ϸ� �װ͵��� �׷���
		this.repaint();
	}

}
