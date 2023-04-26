import java.awt.*;
import java.awt.event.*;

public class Level2 implements LevelInterface{
	
	private TileMap tileMap;
	private Player player;
	
	private LevelManager levelManager;
	
	private BackgroundManager backgroundManager;
	
	private SoundManager soundManager;

	public Level2(LevelManager levelManager) {
		
		this.levelManager = levelManager;
		init();
		
		soundManager.playClip("cave", true);
	}

	@Override
	public void init() {

		String backgroundImages[] = {
				"background/background1.png",
				"background/background2.png",
				"background/background3.png",
				"background/background4b.png",
		};
		
		double moveScale[] = {
				0.05,
				0.06,
				0.07,
				0.08,
		};
		
		backgroundManager = new BackgroundManager(backgroundImages, moveScale);

		tileMap = new TileMap("tilemap/level2.txt", 32);
		tileMap.loadTileset("tileset/CaveTileset.png");
		
		player = Player.getInstance();
		player.tileMap(tileMap);
		
		player.setX(50);
		player.setY(50);
		
		soundManager = SoundManager.getInstance();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(player.getY() > ((tileMap.mapHeight() - 1) * tileMap.tileSize())) {
			levelManager.setLevel(levelManager.current());
		}
		
		if((player.getY() > ((tileMap.mapHeight() - 4) * tileMap.tileSize())) && (player.getX() > ((tileMap.mapWidth() - 1) * tileMap.tileSize()))) {
			soundManager.stopClip("cave");
			levelManager.setLevel(levelManager.VICTORY);
		}
		
		tileMap.update();
		player.update();
		
		backgroundManager.position(tileMap.getX(), tileMap.getY());
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		backgroundManager.draw(g);
		tileMap.draw(g);
		player.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_UP) 
			player.setJumping(true);
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_LEFT) 
			player.setLeft(false);
		if (k == KeyEvent.VK_RIGHT) 
			player.setRight(false);
	}

}
