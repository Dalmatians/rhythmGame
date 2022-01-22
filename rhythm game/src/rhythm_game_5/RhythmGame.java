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

	private Image screenImage; // 더블버퍼링 : 화면을 계속 갱신하여 자연스럽게 보이게 하는 방법
	private Graphics screenGraphic;

	// 이미지를 변수에 초기화
	// 인트로배경
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

	public RhythmGame() { // 생성자 : 인스턴스를 만들어 주었을떄 가장 먼저 실행되는 부분
		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 안보임
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // 창이 화면 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창이 꺼지면 프로그렘도 종료
		setVisible(true); // 디폴트값 false
		setBackground(new Color(0, 0, 0, 0)); // paintComponent를 했을때 회색이아니라 하얀색으로 출력됨
		setLayout(null);

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonPressed); //이미지 바꿔줌
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasic); //이미지 바꿔줌
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0); //버튼 누르면 프로그램 종료
			}
		});
		add(exitButton);
		
		menuBar.setBounds(0, 0, 1280, 30); // 위치와 크기 지정
		menuBar.addMouseListener(new MouseAdapter() { //메뉴바를 잡고 이동시킬려고 하는거임
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { //메뉴바를 잡고 이동시킬려고 하는거임
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
				startButton.setIcon(startButtonEntered); //이미지 바꿔줌
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasic); //이미지 바꿔줌
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//게임 메인 페이지 이동
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
				quitButton.setIcon(quitButtonEntered); //이미지 바꿔줌
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasic); //이미지 바꿔줌
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0); //버튼 누르면 프로그램 종료
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
				leftButton.setIcon(leftButtonEntered); //이미지 바꿔줌
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasic); //이미지 바꿔줌
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//왼쪽 버튼 이벤트
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
				rightButton.setIcon(rightButtonEntered); //이미지 바꿔줌
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasic); //이미지 바꿔줌
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//오른쪽 버튼 이벤트
			}
		});
		add(rightButton);


		Music intromusic = new Music("pretender.mp3", true); // 무한재생
		intromusic.start();
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 창의 0, 0 위치에 배경그리기
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 스크린이미지를 그림
		if(isMainScreen) {
			g.drawImage(selectedImage, 415, 100, null);
			g.drawImage(titleImage, 415, 550, null);
		}
		paintComponents(g); // jlabel같은것들을 jframe에 추가하면 그것들을 그려줌
		this.repaint();
	}

}
