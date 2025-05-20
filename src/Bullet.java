import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Bullet {
	private Image image;
	private AffineTransform tx;
	
	double x, y;
	int width, height;
	double angle, velocity = 9;
	
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image
	
	
	public Bullet() {
		image	= getImage("/imgs/"+"bullet.png"); //load the image for Tree
		
		//alter these
		width = 0;
		height = 0;
		x = 0;
		y = 0;
		angle = Math.PI / 2;

		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public Bullet(double x, double y, double angle) {
		this();
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void setPos(double d, double e) {
		this.x = d;
		this.y = e;
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		
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
			URL imageURL = Bullet.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	
}
