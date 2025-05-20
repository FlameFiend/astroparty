import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Ship {
	protected Image image;
	private AffineTransform tx;
	
	double x, y;
	int width, height;
	double angle, velocity = 0;
	
	boolean turning;
	double turningAngle = 0.1;
	
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	
	ArrayList<Bullet> bullets;
	
	int reloadCooldown = 50;
	int reloadTime = reloadCooldown;
	int ammo[];
	Bullet bullet = new Bullet();
	double ammoAngle = 0;
	
	public Ship() {
		image	= getImage("/imgs/"+"redship.png"); //load the image for Tree
		
		//alter these
		width = 0;
		height = 0;
		x = 0;
		y = 0;
		angle = Math.PI / 2;
		turning = false;
		
		
		ammo = new int[3];
		ammo[0] = ammo[1] = ammo[2] = 1;
		
		bullets = new ArrayList<>();

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
	
	public boolean isHit(double tx, double ty) {
		double x1 = x;
		double x2 = x + 10;
		double y1 = y;
		double y2 = y + 10;
		
		return tx >= x1 && tx <= x2 && ty >= y1 && ty <= y2;
	}
	
	public void shoot() {
		for (int i = 0; i < 3; i++) {
			if (ammo[i] == 1) {
				bullets.add(new Bullet(x - 2, y - 2, angle));
				ammo[i] = 0;
				break;
			}
		}
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		reloadTime--;
		
		// full ammo means you can't reload
		if (ammo[0] + ammo[1] + ammo[2] == 3) {
			reloadTime = reloadCooldown;
		}
		
		ammoAngle += 0.05;
		if (reloadTime == 0) {
			for (int i = 0; i < 3; i++) {
				if (ammo[i] == 0) {
					ammo[i] = 1;
					break;
				}
			}
			
			reloadTime = reloadCooldown;
		}
		
		// draw ammo around the ship
		for (int i = 0; i < 3; i++) {
			if (ammo[i] == 1) {
				double R = 20;
				double theta = ammoAngle + (2 * Math.PI / 3) * i;
				
				bullet.setPos(this.x + R * Math.cos(theta), this.y + R * Math.sin(theta));
				bullet.paint(g);
			}
		}
		
		for (Bullet b : bullets) {
			b.paint(g);
		}
		
		
		if (turning) {
			angle -= turningAngle;
		}
		
		x += velocity * Math.cos(angle);
		y -= velocity * Math.sin(angle);
		
		init(x,y);
		
		g2.drawImage(image,  tx, null);
	}

	private void init(double a, double b) {
		int imgW = image.getWidth(null);
		int imgH = image.getHeight(null);

		if (imgW <= 0 || imgH <= 0) return;

		tx = new AffineTransform();

		tx.translate(a, b);

		tx.rotate(-(angle - Math.PI / 2));

		tx.translate(-imgW * scaleWidth / 2.0, -imgH * scaleHeight / 2.0);

		// Step 4: Scale the image
		tx.scale(scaleWidth, scaleHeight);
	}
	

	protected Image getImage(String path) {
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
