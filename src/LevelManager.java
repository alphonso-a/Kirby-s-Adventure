import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LevelManager {
	private LevelInterface[] levels;
	private int current;
	
	public static final int LEVEL1 = 0;
	public static final int LEVEL2 = 1;
	public static final int VICTORY = 2;
	
	
	public LevelManager() {
		
		levels = new LevelInterface[3];
		
		current = LEVEL1;
		
		loadLevel(current);
	}
	
	private void loadLevel(int level) {
		
		if(level == LEVEL1) { levels[level] = new Level1(this); }
		if(level == LEVEL2) { levels[level] = new Level2(this); }
		if(level == VICTORY) { levels[level] = new Victory(this); }
	}
	
	private void unloadLevel(int level) { levels[level] = null; }
	
	public void setLevel(int level) {
		
		if(current == level) {
			levels[current].init();
		}else {
			keyReleased(KeyEvent.VK_RIGHT);
			unloadLevel(current);
			current = level;
			loadLevel(current);
		}

		
	}
	
	public void update() {
		
		levels[current].update();
		
	}
	
	public void render(Graphics2D g) {
		
		levels[current].draw(g);
		
	}
	
	public void keyPressed(int k) {
		
		levels[current].keyPressed(k);
		
	}
	
	public void keyReleased(int k) {
		
		levels[current].keyReleased(k);
		
	}
	
	public int current() {
		
		return current;
		
	}
}
