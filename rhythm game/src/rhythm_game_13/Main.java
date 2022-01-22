package rhythm_game_13;

// https://www.youtube.com/watch?v=xs92kqU2YWg&list=PLRx0vPvlEmdDySO3wDqMYGKMVH4Qa4QhR&index=1

public class Main {

	public static final int SCREEN_WIDTH = 1280; //바뀌지않는 상수를 선언할때는 전부 대문자
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 8;
	public static final int SLEEP_TIME = 7; 
	public static final int REACH_TIME = 1; //판정바에 도달하기까지의 시간
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RhythmGame(); //클래스를 이용한 하나의 객체를 생성함
	}

}

// 14강 7분 30초 
// 노트 구현 이전까지