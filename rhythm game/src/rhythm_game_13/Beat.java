package rhythm_game_13;

public class Beat {
	
	private int time;
	private String noteName;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public String getNoteName() {
		return noteName;
	}
	
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Beat(String noteName) {
		super();
		this.noteName = noteName;
	}

	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}

	
}
