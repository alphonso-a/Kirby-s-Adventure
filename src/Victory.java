import java.awt.*;
import java.awt.event.KeyEvent;

public class Victory implements LevelInterface {
	
	private LevelManager levelManager;
	
	private SoundManager soundManager;
	
	public Victory(LevelManager levelManager){
		this.levelManager = levelManager;
		
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		soundManager = SoundManager.getInstance();
		
		soundManager.playClip("win", false);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
        g.setFont(new Font("Century Gothic", Font.BOLD, 18));
        g.drawString("YOU WIN!", 155, 180);
        
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Press ENTER to exit.", 140, 200);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_ENTER)
			System.exit(0);
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
