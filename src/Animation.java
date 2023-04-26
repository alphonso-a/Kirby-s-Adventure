import java.awt.image.*;

public class Animation {
	
	private BufferedImage[] frames;
	private int current;
	
	private long start;
	private long delay;
	
	public Animation() {
		
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		
		if(current >= frames.length)
			current = 0;
	}
	
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public void update() {
		if(delay == -1)
			return;
		
		long elapsed = (System.nanoTime() - start) / 1000000;
		
		if(elapsed > delay) {
			current++;
			start = System.nanoTime();
		}
		if(current == frames.length) {
			current = 0;
		}
	}
	
	public BufferedImage getImage() {
		return frames[current];
	}
}
