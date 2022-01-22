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

	private Image screenImage; // 더블버퍼링 : 화면을 계속 갱신하여 자연스럽게 보이게 하는 방법
	private Graphics screenGraphic;

	// 이미지를 변수에 초기화
	// 인트로배경
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
	
	ArrayList<Track> trackList = new ArrayList<Track>(); // 변수들을 담을수있는 하나의 이미 만들어진 배열

	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0; //현재 선택된 곡 디폴트 
	
	private Music introMusic = new Music("pretender.mp3", true); // 무한재생
	
	public static Game game;
	
	public RhythmGame() { // 생성자 : 인스턴스를 만들어 주었을떄 가장 먼저 실행되는 부분
		
		// titleImage, startImage, gameImage, startMusic, gameMusic, titleName
		trackList.add(new Track("GrievousLadyTitle.png", "GrievousLady_Thumbnail.jpeg", "GrievousLady_Ingame.jpg", 
				"GrievousLadySelected.mp3", "GrievousLady.mp3", "Grievous Lady"));
		trackList.add(new Track("MUSICTitle.png", "MUSIC_Thumbnail.png", "MUSIC_Ingame.png", 
				"MUSIC Selected.mp3", "MUSIC.mp3", "MUSIC"));
		trackList.add(new Track("smokedTurkeyRagTitle.png", "smokedTurkeyRag_Thumbnail.jpeg", "smokedTurkeyRag_Ingame.jpg", 
				"smokedTurkeyRagSelected.mp3", "smokedTurkeyRag.mp3", "Smoked Trukey Rag"));
		
		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 안보임
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // 창이 화면 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창이 꺼지면 프로그렘도 종료
		setVisible(true); // 디폴트값 false
		setBackground(new Color(0, 0, 0, 0)); // paintComponent를 했을때 회색이아니라 하얀색으로 출력됨
		setLayout(null);
		
		addKeyListener(new KeyListener()); // 키 이벤트 추가
		
		introMusic.start();
		

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
				easyButton.setIcon(easyButtonEntered); //이미지 바꿔줌
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasic); //이미지 바꿔줌
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//난이도 쉬움 이벤트
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
				hardButton.setIcon(hardButtonEntered); //이미지 바꿔줌
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasic); //이미지 바꿔줌
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//난이도 어려움 이벤트
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
				backButton.setIcon(backButtonEntered); //이미지 바꿔줌
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스모양 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasic); //이미지 바꿔줌
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//곡 선택창 이동
				backMain();
			}
		});
		add(backButton);



	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 창의 0, 0 위치에 배경그리기
	}

	public void screenDraw(Graphics2D g) { //곡선택창
		g.drawImage(background, 0, 0, null); // 스크린이미지를 그림
		if(isMainScreen) {
			g.drawImage(selectedImage, 415, 100, null);
			g.drawImage(titleImage, 415, 550, null);
		}
		if(isGameScreen) { //인게임
			game.screenDraw(g);
		}
		paintComponents(g); // jlabel같은것들을 jframe에 추가하면 그것들을 그려줌
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
		//선택된 트랙의 이미지를 가져옴
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0) // 0번째이면 오른쪽 맨끝으로 
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
		game.start(); //game인스턴스의 run함수가 자동으로 실행됨
		setFocusable(true); // 메인 프레임에 키보드 포커스가 맞춰저 있어야 정상작동
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
		selectTrack(0); // 0번 초기화
		startButton.setVisible(false);
		quitButton.setVisible(false);
		introMusic.close();
		background = new ImageIcon(Main.class.getResource("../images/mainBg.jpg")).getImage();
		isMainScreen = true; //곡선택창
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
	}
}
