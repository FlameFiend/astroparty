import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ship {
	private Image image;
	private AffineTransform tx;
	
	double x, y;
	int width, height;
	double angle, velocity;
	
	boolean turning;
	double turningAngle = 0.05;
	
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	
	
	public Ship() {
		image	= getImage("/imgs/"+"redship.png"); //load the image for Tree
		
		//alter these
		width = 0;
		height = 0;
		x = 0;
		y = 0;
		angle = Math.PI / 2;
		velocity = 2;
		turning = false;

		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public Ship(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public void setTurning(boolean turning) {
		this.turning = turning;
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		if (turning) {

			angle -= turningAngle;
		}
		
		x += velocity * Math.cos(angle);
		y -= velocity * Math.sin(angle);
		
		init(x,y);
		
		g2.drawImage(image,  tx, null);

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
			URL imageURL = Ship.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	
}
