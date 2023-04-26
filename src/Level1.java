import java.awt.*;
import java.awt.event.*;

public class Level1 implements LevelInterface{
	
	private TileMap tileMap;
	private Player player;
	
	private LevelManager levelManager;
	
	private BackgroundManager backgroundManager;
	
	private SoundManager soundManager;

	public Level1(LevelManager levelManager) {
		
		this.levelManager = levelManager;
		init();
		
		soundManager.playClip("forest", true);
	}

	@Override
	public void init() {
		
		String backgroundImages[] = {
				"background/x32-layer-7-sky-background.png",
				"background/x32-layer-6-clouds.png",
				"background/x32-layer-5-mountains-3.png",
				"background/x32-layer-4-mountains-2.png",
				"background/x32-layer-3-mountains-1.png",
				"background/x32-layer-2-trees-2.png",
				"background/x32-layer-1-trees-1.png"
		};
		
		double moveScale[] = {
				0.01,
				0.02,
				0.04,
				0.04,
				0.04,
				0.1,
				0.1
		};
		
		backgroundManager = new BackgroundManager(backgroundImages, moveScale);
		
		tileMap = new TileMap("tilemap/level1.txt", 32);
		tileMap.loadTileset("tileset/x32-florest-tileset.png");
		
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
		
		if(player.getX() > ((tileMap.mapWidth() - 1) * tileMap.tileSize())) {
			soundManager.stopClip("forest");
			levelManager.setLevel(LevelManager.LEVEL2);
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
