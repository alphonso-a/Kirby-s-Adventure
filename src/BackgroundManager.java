import java.awt.*;
import javax.swing.*;

public class BackgroundManager {
	private String backgroundImages[];
	private double moveScale[];
	
	private Background[] background;
	
	private JPanel gamePanel;
	
	public BackgroundManager(String[] backgroundImages, double[] moveScale) {
		
		this.backgroundImages = backgroundImages;
		this.moveScale = moveScale;
		
		background = new Background[backgroundImages.length];
		
		for(int i = 0; i < backgroundImages.length; i++)
			background[i] = new Background(backgroundImages[i], moveScale[i]);
		
	}

  	public void position(double x, double y) {
		for (int i=0; i < backgroundImages.length; i++)
      			background[i].position(x, y);
  	}
	
	public void draw(Graphics2D g2) {
		for(int i = 0; i < backgroundImages.length; i++)
			background[i].draw(g2);
	}
}
