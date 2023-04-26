import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

public final class Player {
	
	private double x, y, dx, dy;
	
	private int width, height;
	
	private boolean left, right;
	
	private boolean jumping, falling;
	
	private double gravity;
	
	private TileMap tileMap;
	
	private double speed;
	private double max, min;
	private double tVelocity;

	private boolean topLeft, topRight, bottomLeft, bottomRight;
	
	private Animation animation;
	
	private BufferedImage[] idle, walk, jump, fall;
	
	private static Player instance = null;
	
	public Player() {
		width = 22;
		height = 22;
		
		speed = 0.6;
		max = 4.2;
		min = 0.30;
		tVelocity = 12;
		gravity = 0.44;
		
		loadSprite();
		animation = new Animation();
	}
	
	public static Player getInstance(){

        if(instance == null)
            instance = new Player();

        return instance;

    }
	
	public void tileMap(TileMap tileMap) {
		
		this.tileMap = tileMap;
	}
	
	public void loadSprite() {
		try {
			
			idle = new BufferedImage[1];
			walk = new BufferedImage[6];
			jump = new BufferedImage[1];
			fall = new BufferedImage[1];
			
			idle[0] = ImageIO.read(new File("player/kirbyidle.gif"));
			
			BufferedImage image = ImageIO.read(new File("player/kirbywalk.gif"));
			for(int i = 0; i < walk.length; i++) {
				walk[i] = image.getSubimage(
						i * width + i,
						0,
						width,
						height
				);
			}
			
			jump[0] = ImageIO.read(new File("player/kirbyjump.gif"));
			
			fall[0] = ImageIO.read(new File("player/kirbyfall.gif"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setLeft(boolean b) {
		left = b;
	}
	
	public void setRight(boolean b) {
		right = b;
	}
	
	public void setJumping(boolean b) {
		if(!falling)
			jumping = true;
	}
	
	public void update() {
		System.out.println(x + "," + y);
		
		if(left) {
			dx -= speed;
			if(dx < -max) {
				dx = -max;
			}
		}else if(right) {
			dx += speed;
			if(dx > max) {
				dx = max;
			}
		}else {
			if(dx > 0) {
				dx -= min;
				if(dx < 0) {
					dx = 0;
				}
			}else if(dx < 0) {
				dx += min;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		if(jumping) {
			dy = -11.0;
			falling = true;
			jumping = false;
		}
		
		if(falling) {
			dy += gravity;
			if(dy > tVelocity) {
				dy = tVelocity;
			}
		}else {
			dy = 0;
		}
		
		int column = tileMap.column((int) x);
		int row = tileMap.row((int) y);
		
		double X = x;
		double Y = y;
		
		cornerCalc(x, y + dy);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				Y = row * tileMap.tileSize() + height / 2;
			}else {
				Y += dy;
			}
		}if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				Y = (row + 1) * tileMap.tileSize() - height / 2;
			}else {
				Y += dy;
			}
		}
		
		cornerCalc(x + dx, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				X = column * tileMap.tileSize() + width / 2;
			}else {
				X += dx;
			}
		}if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				falling = false;
				X = (column + 1) * tileMap.tileSize() - width / 2;
			}else {
				X += dx;
			}
		}
		
		if(!falling) {
			cornerCalc(x, y + dy + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
		
		x = X;
		y = Y;
		
		tileMap.pos(x, y);
		
		if(left || right) {
			animation.setFrames(walk);
			animation.setDelay(100);

		}else {
			animation.setFrames(idle);
			animation.setDelay(-1);
		}
		
		if(dy < 0) {
			animation.setFrames(jump);
			animation.setDelay(-1);
		}
		
		if(dy > 0) {
			animation.setFrames(fall);
			animation.setDelay(-1);
		}
		
		animation.update();
	}
	
	private void cornerCalc(double x, double y) {
		
		int left = tileMap.column((int) (x - width / 2));
		int right = tileMap.column((int) (x + width / 2) - 1);
		int top = tileMap.row((int) (y - height / 2));
		int bottom = tileMap.row((int) (y + height / 2) - 1);
		
		topLeft = tileMap.tileType(top, left) == Tile.BLOCKED;
		topRight = tileMap.tileType(top, right) == Tile.BLOCKED;
		bottomLeft = tileMap.tileType(bottom, left) == Tile.BLOCKED;
		bottomRight = tileMap.tileType(bottom, right) == Tile.BLOCKED;
	}

	public void draw(Graphics2D g) {
		int tileX = tileMap.getX();
		int tileY = tileMap.getY();
		
		if(left) {
			g.drawImage(
					animation.getImage(),
					(int) (tileX + x - width / 2), 
					(int) (tileY + y - height / 2), 
					null
			);
		}else {
			g.drawImage(
					animation.getImage(),
					(int) (tileX + x - width / 2 + width), 
					(int) (tileY + y - height / 2), 
					-width, 
					height,
					null
			);
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	
}
