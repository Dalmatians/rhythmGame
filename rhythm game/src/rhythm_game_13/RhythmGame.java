package rhythm_game_13;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

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
	private ImageIcon rightButtonEntered = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));private ImageIcon easyButtonBasic = new ImageIcon(Main.class.getResource("../images/easyButton.png"));
	private ImageIcon easyButtonEntered = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasic = new ImageIcon(Main.class.getResource("../images/hardButton.png"));
	private ImageIcon hardButtonEntered = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon backButtonBasic = new ImageIcon(Main.class.getResource("../images/backButton.png"));
	private ImageIcon backButtonEntered = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	private JButton exitButton = new JButton(exitButtonBasic);
	private JButton startButton = new JButton(startButtonBasic);
	private JButton quitButton = new JButton(quitButtonBasic);
	private JButton leftButton = new JButton(leftButtonBasic);
	private JButton rightButton = new JButton(rightButtonBasic);
	private JButton easyButton = new JButton(easyButtonBasic);
	private JButton hardButton = new JButton(hardButtonBasic);
	private JButton backButton = new JButton(backButtonBasic);

	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>(); // �������� �������ִ� �ϳ��� �̹� ������� �迭

	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0; //���� ���õ� �� ����Ʈ 
	
	private Music introMusic = new Music("pretender.mp3", true); // �������
	
	public static Game game;
	
	public RhythmGame() { // ������ : �ν��Ͻ��� ����� �־����� ���� ���� ����Ǵ� �κ�
		
		// titleImage, startImage, gameImage, startMusic, gameMusic, titleName
		trackList.add(new Track("GrievousLadyTitle.png", "GrievousLady_Thumbnail.jpeg", "GrievousLady_Ingame.jpg", 
				"GrievousLadySelected.mp3", "GrievousLady.mp3", "Grievous Lady"));
		trackList.add(new Track("MUSICTitle.png", "MUSIC_Thumbnail.png", "MUSIC_Ingame.png", 
				"MUSIC Selected.mp3", "MUSIC.mp3", "MUSIC"));
		trackList.add(new Track("smokedTurkeyRagTitle.png", "smokedTurkeyRag_Thumbnail.jpeg", "smokedTurkeyRag_Ingame.jpg", 
				"smokedTurkeyRagSelected.mp3", "smokedTurkeyRag.mp3", "Smoked Trukey Rag"));
		
		setUndecorated(true); // �⺻������ �����ϴ� �޴��ٰ� �Ⱥ���
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // â�� ȭ�� ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â�� ������ ���α׷��� ����
		setVisible(true); // ����Ʈ�� false
		setBackground(new Color(0, 0, 0, 0)); // paintComponent�� ������ ȸ���̾ƴ϶� �Ͼ������ ��µ�
		setLayout(null);
		
		addKeyListener(new KeyListener()); // Ű �̺�Ʈ �߰�
		
		introMusic.start();
		

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
				enterMain();
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
				selectLeft();
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
				selectRight();
			}
		});
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(125, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEntered); //�̹��� �ٲ���
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasic); //�̹��� �ٲ���
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//���̵� ���� �̺�Ʈ
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(905, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEntered); //�̹��� �ٲ���
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasic); //�̹��� �ٲ���
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//���̵� ����� �̺�Ʈ
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEntered); //�̹��� �ٲ���
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //���콺��� �ٲ�
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasic); //�̹��� �ٲ���
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//�� ����â �̵�
				backMain();
			}
		});
		add(backButton);



	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // â�� 0, 0 ��ġ�� ���׸���
	}

	public void screenDraw(Graphics2D g) { //���â
		g.drawImage(background, 0, 0, null); // ��ũ���̹����� �׸�
		if(isMainScreen) {
			g.drawImage(selectedImage, 415, 100, null);
			g.drawImage(titleImage, 415, 550, null);
		}
		if(isGameScreen) { //�ΰ���
			game.screenDraw(g);
		}
		paintComponents(g); // jlabel�����͵��� jframe�� �߰��ϸ� �װ͵��� �׷���
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();
		//���õ� Ʈ���� �̹����� ������
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0) // 0��°�̸� ������ �ǳ����� 
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		isGameScreen = true;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true); 
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,trackList.get(nowSelected).getGameMusic());
		game.start(); //game�ν��Ͻ��� run�Լ��� �ڵ����� �����
		setFocusable(true); // ���� �����ӿ� Ű���� ��Ŀ���� ������ �־�� �����۵�
	}
	
	public void backMain() {
		isMainScreen = true;
		isGameScreen = false;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBg.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		game.close();
	}
	
	public void enterMain() {
		selectTrack(0); // 0�� �ʱ�ȭ
		startButton.setVisible(false);
		quitButton.setVisible(false);
		introMusic.close();
		background = new ImageIcon(Main.class.getResource("../images/mainBg.jpg")).getImage();
		isMainScreen = true; //���â
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
	}
}
