import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Ship {
	protected Image image;
	private AffineTransform tx;

	double maxVelocity = 6;
	double acceleration = 0.1;

	double x, y;
	int width, height;
	double angle, velocity = 0;

	boolean turning;
	int turnDir = 1;
	double turningAngle = 0.1;

	double scaleWidth = 2.0;
	double scaleHeight = 2.0;

	ArrayList<Bullet> bullets;

	int reloadCooldown = 50;
	int reloadTime = reloadCooldown;
	int ammo[];
	Bullet bullet = new Bullet();
	double ammoAngle = 0;

	int dashTurnCooldown = 20;
	int dashTurnTime = dashTurnCooldown;
	int dashTurnStage = 100;
	double[] dashTurnAngle = {-0.392699081699, -0.392699081699, -0.392699081699, -0.392699081699};
	double[] dashTurnVelocity = {1, 7, 6, 5};

	public Ship() {
		image = getImage("/imgs/" + "redship.png");

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
		init(x, y);
	}

	public Ship(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	public void dashTurn() {
		if (dashTurnTime > 0) return;

		dashTurnTime = dashTurnCooldown;
		dashTurnStage = 0;
	}

	public void setTurning(boolean turning) {
		this.turning = turning;
	}
	
	public void setTurnDir(int turnDir) {
		this.turnDir = turnDir;
	}

	public boolean inside(double tx, double ty) {
		double x1 = x - 12;
		double x2 = x + 12;
		double y1 = y - 12;
		double y2 = y + 12;
		return tx >= x1 && tx <= x2 && ty >= y1 && ty <= y2;
	}

	public boolean isHit(Bullet b) {
		double x1 = b.getX();
		double x2 = x1 + b.getWidth();
		double y1 = b.getY();
		double y2 = y1 + b.getHeight();
		return inside(x1, y1) || inside(x1, y2) || inside(x2, y1) || inside(x2, y2);
	}

	public boolean hitting(Ship enemy) {
		for (Bullet b : bullets) {
			if (enemy.isHit(b)) return true;
		}
		return false;
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

	public void paint(Graphics g, Map map) {
		Graphics2D g2 = (Graphics2D) g;

		reloadTime--;
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
			angle -= turningAngle*turnDir;
		}

		if (dashTurnStage < dashTurnAngle.length) {
			angle += dashTurnAngle[dashTurnStage]*turnDir;
			velocity = dashTurnVelocity[dashTurnStage];
			dashTurnStage++;
		} else {
			dashTurnTime--;
			if (velocity < maxVelocity) {
				velocity += acceleration;
				if (velocity > maxVelocity) {
					velocity = maxVelocity;
				}
			}
		}
		double nextX = x + velocity * Math.cos(angle);
		double nextY = y - velocity * Math.sin(angle);
		
		Rectangle nextBounds = new Rectangle((int) (nextX - 12), (int) (nextY - 12),24,24);
		if(!map.checkCollision(nextBounds)) {
			x = nextX;
			y = nextY;
		} else {
			velocity = 0;
		}
		
		init(x,y);
		g2.drawImage(image, tx, null);
	}

	private void init(double a, double b) {
		int imgW = image.getWidth(null);
		int imgH = image.getHeight(null);
		if (imgW <= 0 || imgH <= 0) return;

		tx = new AffineTransform();
		tx.translate(a, b);
		tx.rotate(-(angle - Math.PI / 2));
		tx.translate(-imgW * scaleWidth / 2.0, -imgH * scaleHeight / 2.0);
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

	public Rectangle getBounds() {
		int hitboxSize = 24;
		return new Rectangle((int) (x - hitboxSize / 2), (int) (y - hitboxSize / 2), hitboxSize, hitboxSize);
	}

	public boolean outOfBounds(int width, int height) {
		Rectangle r = getBounds();
		return r.x < 0 || r.y < 0 || r.x + r.width > width || r.y + r.height > height;
	}

	public void collide(int width, int height) {
		Rectangle r = getBounds();
		if (r.x < 0) x = r.width / 2.0;
		if (r.y < 0) y = r.height / 2.0;
		if (r.x + r.width > width) x = width - r.width / 2.0;
		if (r.y + r.height > height) y = height - r.height / 2.0;
		velocity = 0;
	}
}