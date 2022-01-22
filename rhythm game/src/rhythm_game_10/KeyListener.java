package rhythm_game_10;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter	{
	
	@Override
	public void keyPressed(KeyEvent e) { //Å° ´­·¶À»¶§
		if(RhythmGame.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.pressK();
		}
	}

	@Override 
	public void keyReleased(KeyEvent e) { // Å° ¶ÂÀ»¶§
		if(RhythmGame.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.releasedD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.releasedF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.releasedJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.releasedK();
		}
	}
}
