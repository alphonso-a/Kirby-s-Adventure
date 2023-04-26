import java.awt.*;

import javax.swing.*;

public class Background {
	private Image image;
	
	private double x, y, dx;
	
	public Background(String fileName, double dx) {
		this.image = loadImage(fileName);
		this.dx = dx;
	}
	
	public void position(double x, double y) {
		this.x = (x * dx) % GamePanel.WIDTH;
		this.y = (y * 0.4) % GamePanel.HEIGHT;
	}
	
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
	}
	
}
