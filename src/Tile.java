import java.awt.image.*;

public class Tile {
	
	private BufferedImage image;
	
	private int type;
	
	protected static final int BLOCKED = 1;
	protected static final int DETAIL = 0;	
	
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
}
