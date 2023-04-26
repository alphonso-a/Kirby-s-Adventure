import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class TileMap{


	private int x, y;

	private int tileSize;

	private int[][] map;

	private int mapWidth, mapHeight;

	private String fileName;
	
	private BufferedImage tileset;
	private Tile[][] tiles;
	
	private int minX, minY, maxX, maxY;


	public TileMap(String fileName, int tileSize) {
		this.tileSize = tileSize;
		
		loadMap(fileName);
	}
	
	public void loadTileset(String fileName) {
			try {
				tileset = ImageIO.read(new File(fileName));
				int rows = tileset.getHeight() / tileSize;
				int columns = tileset.getWidth() / tileSize;
				tiles = new Tile[rows][columns];

				BufferedImage subImage;
				for(int row = 0; row < rows; row++) {
					for(int col = 0; col < columns; col++) {
						subImage = tileset.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
						tiles[row][col] = new Tile(subImage, Tile.BLOCKED);
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}

	public void loadMap(String fileName) {

		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

			mapWidth = Integer.parseInt(bufferedReader.readLine());
			mapHeight = Integer.parseInt(bufferedReader.readLine());
			map = new int[mapHeight][mapWidth];
			
			minX = GamePanel.WIDTH - mapWidth * tileSize;
			minY = GamePanel.HEIGHT - mapHeight * tileSize;

			String delimiters = ",";

			for(int row=0; row < mapHeight; row++){
				String line = bufferedReader.readLine();
				String[] tokens = line.split(delimiters);
				for(int col=0; col < mapWidth; col++){
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}catch(Exception e){

		}

	}
	
	public int getX() {
		return x;
	}

	public void setX(int i) {
		x = i;
		
		if(x < minX)
			x = minX;
		if(x > maxX)
			x = maxX;
	}

	public int getY() {
		return y;
	}

	public void setY(int i) {
		y = i;
		
		if(y < minY)
			y = minY;
		if(y > maxY)
			y = maxY;
	}
	
	public int row(int y) {
		return y / tileSize;
		
	}
	
	public int column(int x) {
		return x / tileSize;
	}
	
	public void update() {
		
	}
	
	public void pos(double x, double y) {

		setX((int) (GamePanel.WIDTH / 2 - x));
		setY((int) (GamePanel.HEIGHT / 2 - y));
		
	}
	
	public int tileType(int row, int col) {
		
		int tile = map[row][col];
		
		if (map[row][col] == -1)
		{
			return 0;
		}
		
		int r = tile / tiles[0].length;
		int c = tile % tiles[0].length;
		
		return tiles[r][c].getType();
		
	}
	

	public void draw(Graphics2D g){
		for(int row=0; row < mapHeight; row++)
			for(int col=0; col < mapWidth; col++){
				
				int rc = map[row][col];
				
				if (map[row][col] == -1)
				{
					continue;
				}

				int r = rc / tiles[0].length;
				int c = rc % tiles[0].length;

				g.drawImage(
						tiles[r][c].getImage(),
						x + col * tileSize,
						y + row * tileSize,
						null
				);
						
			}
	}

	public int tileSize() {
		
		return tileSize;
		
	}
	
	public int mapHeight() {
		
		return mapHeight;
		
	}
	
	public int mapWidth() {
		
		return mapWidth;
		
	}

}
