import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

    private Thread game;
    private boolean running; 
    
    private BufferedImage image;
    private Graphics2D g;
    
    private SoundManager soundManager;
    private LevelManager levelManager;

    public GamePanel(){
    	
    	super();

    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        soundManager = SoundManager.getInstance();
        
        
    }
    
    public void addNotify() {
		super.addNotify();
		if(game == null) {
			game = new Thread(this);
			addKeyListener(this);
			game.start();
		}
	}
    
    private void init() {
    	
    	image = new BufferedImage(
    			WIDTH, HEIGHT,
    			BufferedImage.TYPE_INT_RGB
    			);
    	g = (Graphics2D) image.getGraphics();
    	
    	running = true;
    	
    	levelManager = new LevelManager();
    }


    @Override
    public void run() {
        init();
        try {
	        while(running) {
	        	update();
	        	render();
	        	Thread.sleep(10);
	        }
        }catch(InterruptedException e) {
			
		}
        
    }


    private void update() {
		// TODO Auto-generated method stub
		levelManager.update();
	}

	private void render() {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		levelManager.render(g);
		// TODO Auto-generated method stub
		
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g2.dispose();
		
	}

	@Override
    public void keyPressed(KeyEvent key) {
        // TODO Auto-generated method stub
		levelManager.keyPressed(key.getKeyCode());
    }


    @Override
    public void keyReleased(KeyEvent key) {
        // TODO Auto-generated method stub
        levelManager.keyReleased(key.getKeyCode());
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    	
    }

}
