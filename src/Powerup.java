import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Powerup {
	private Image image;
	private AffineTransform tx;
	
	// variables to store information about the powerup
	String type;
	
	Ship player;
	double x, y;
	int width, height;
	double angle, velocity = 0;
	
	int duration = 0;
	
	double scaleWidth = 1.2;		//change to scale image
	double scaleHeight = 1.2; 		//change to scale image
	
	
	public Powerup() {
		image = getImage("/imgs/"+"blueship.png");

		//initialize variables
		width = 30;
		height = 30;
		x = 0;
		y = 0;
		angle = Math.PI / 2;
		player = null;
		duration = 100;

		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	// alternate constructor for powerup
	public Powerup(String tp, double x, double y) {
		this();
		this.type = tp;
		this.x = x;
		this.y = y;
		
		if (type.equals("shield")) {
			image = getImage("/imgs/"+"shield.gif");
		}
	}
	
	// checks if a ship is currently intersecting the powerup
	// if it is grant it immunity and subtract from the duration
	void check(Ship s) {
		if(player == null) {
			if (s.isHit(this)) {
				player = s;
			}
		} else if (player.equals(s)) {
			if (duration > 0) {
				duration--;
			}
			
			s.setShielded(duration > 0);
		}
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		if (player != null) {
			x = player.getX() - 12;
			y = player.getY() - 12;
		}
		
		init(x,y);
		
		// only draw image if there is duration remaining
		if (duration > 0) {
			g2.drawImage(image,  tx, null);
		}
		//g2.drawRect((int) x, (int) y, width, height);
	}
	
	// setters and getters
	
	public void setPos(double d, double e) {
		this.x = d;
		this.y = e;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	private void init(double a, double b) {
		
		/*
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
		*/
		
	 	int imgW = image.getWidth(null);
	    int imgH = image.getHeight(null);

	    if (imgW <= 0 || imgH <= 0) return; // Prevent invalid transforms

	    tx = new AffineTransform();

	    tx.translate(a, b);

	    tx.rotate(-(angle - Math.PI / 2), imgW * scaleWidth / 2.0, imgH * scaleHeight / 2.0);
	    tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Powerup.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle((int) x, (int) y, width, height);
	}
}


