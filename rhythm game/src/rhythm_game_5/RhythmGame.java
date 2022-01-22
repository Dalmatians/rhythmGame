package rhythm_game_5;

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
	private Image background = new ImageIcon(Main.class.getResource("../images/RhythmGame_bg.jpg")).getImage();
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/GrievousLady_Thumbnail.jpeg")).getImage();
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/GrievousLadyTitle.png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private ImageIcon exitButtonBasic = new ImageIcon(Main.class.getResource("../images/Exit.png"));
	private ImageIcon exitButtonPressed = new ImageIcon(Main.class.getResource("../images/exitPressed.png"));
	private ImageIcon startButtonBasic = new ImageIcon(Main.class.getResource("../images/startButton.png"));
	private ImageIcon startButtonEntered = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon quitButtonBasic = new ImageIcon(Main.class.getResource("../images/quitButton.png"));
	private ImageIcon quitButtonEntered = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon leftButtonBasic = new ImageIcon(Main.class.getResource("../images/leftButton.png"));
	private ImageIcon leftButtonEntered = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasic = new ImageIcon(Main.class.getResource("../images/rightButton.png"));
	private ImageIcon rightButtonEntered = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	
	private JButton exitButton = new JButton(exitButtonBasic);
	private JButton startButton = new JButton(startButtonBasic);
	private JButton quitButton = new JButton(quitButtonBasic);
	private JButton leftButton = new JButton(leftButtonBasic);
	private JButton rightButton = new JButton(rightButtonBasic);

	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;

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
		
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEntered); //�̹��� �ٲ���
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasic); //�̹��� �ٲ���
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//���� ���� ������ �̵�
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/mainBg.jpg")).getImage();
				isMainScreen = true;
			}
		});
		add(startButton);
		
		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEntered); //�̹��� �ٲ���
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasic); //�̹��� �ٲ���
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0); //��ư ������ ���α׷� ����
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEntered); //�̹��� �ٲ���
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasic); //�̹��� �ٲ���
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//���� ��ư �̺�Ʈ
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEntered); //�̹��� �ٲ���
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasic); //�̹��� �ٲ���
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//������ ��ư �̺�Ʈ
			}
		});
		add(rightButton);


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
		g.drawImage(background, 0, 0, null); // ��ũ���̹����� �׸�
		if(isMainScreen) {
			g.drawImage(selectedImage, 415, 100, null);
			g.drawImage(titleImage, 415, 550, null);
		}
		paintComponents(g); // jlabel�����͵��� jframe�� �߰��ϸ� �װ͵��� �׷���
		this.repaint();
	}

}
