import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener{
	// frame width/height
	static int width = 734;
	static int height = 800;
	Map map = new Map(800, 800, 80);
	static Ship ship = new Ship(400, 200);
	static Ship ship2 = new Ship2(200, 200);
	long lastCollisionTime = 0;
	final int collisionCD = 1000;
	boolean rightPressed = false;
	boolean leftPressed = false;

	boolean dPressed = false;
	boolean aPressed = false; 
	int score =0;
	int score2=0;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		map.draw(g);
		
		ship.paint(g, map);
		ship2.paint(g, map);
		g.setColor(Color.GRAY);
		g.fillRect(0, 720, 734, 43);
		g.setColor(Color.WHITE);
		g.drawString("Blue Kills: " + score2 + "    |    Red Kills: " + score, 288, 743);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame();
	}
	public Frame() {
		map.generateMap(.5);
		JFrame f = new JFrame("Astro Party");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.black);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		resetGame();
	}
	public void resetGame() {
	    ship.x = map.getSpawnB().x;
	    ship.y = map.getSpawnB().y;
	    ship2.x = map.getSpawnA().x;
	    ship2.y = map.getSpawnA().y;
	    ship.angle=Math.PI/2;
	    ship2.angle=-Math.PI/2;
	    ship.velocity=ship2.velocity=0;
	    ship.ammo[0] = ship.ammo[1] = ship.ammo[2] = 1;
	    ship2.ammo[0] = ship2.ammo[1] = ship2.ammo[2] = 1;
	    map.generateMap(0.05+Math.log10(score+score2+1)/5);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int code = e.getKeyCode();
	    
	    if (code == 38) {
	        ship.shoot(); //UP
	    }

	    if (code == 40) {
	        ship.backstep(); //DOWN
	    }

	    if (code == 39) {
	        rightPressed = true; //RIGHT
	    }

	    if (code == 37) {
	        leftPressed = true; //LEFT
	    }

	    if (code == 87) {
	        ship2.shoot(); //W
	    }

	    if (code == 83) {
	        ship2.backstep(); //S
	    }

	    if (code == 68) {
	        dPressed = true; //D
	    }

	    if (code == 65) {
	        aPressed = true; //A
	    }
	    
	    //CUSTOM MAP IMPORTING
	    if(code == 49) { // 1
	    	resetGame();
	    	int[][] custommap = { {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 50) { // 2
	    	resetGame();
	    	int[][] custommap = { {0,0,0,0,0,0,0,0,0}, {0,1,1,0,0,0,1,1,0}, {0,1,0,0,1,0,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,1,0,1,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,0,1,0,0,1,0}, {0,1,1,0,0,0,1,1,0}, {0,0,0,0,0,0,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 51) { // 3
	    	resetGame();
	    	int[][] custommap = { {0,0,0,1,0,0,0,1,1}, {0,0,0,1,0,1,0,0,1}, {0,0,0,0,0,1,0,0,0}, {1,1,0,0,0,0,1,1,0}, {0,0,0,0,1,0,0,0,0}, {0,1,1,0,0,0,0,1,1}, {0,0,0,1,0,0,0,0,0}, {1,0,0,1,0,1,0,0,0}, {1,1,0,0,0,1,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 52) { // 4
	    	resetGame();
	    	int[][] custommap = { {0,0,1,0,0,0,1,0,0}, {0,0,1,0,0,0,0,0,0}, {0,0,1,0,0,0,0,0,0}, {0,0,1,0,0,0,1,0,0}, {0,0,1,1,0,1,1,0,0}, {0,0,1,0,0,0,1,0,0}, {0,0,0,0,0,0,1,0,0}, {0,0,0,0,0,0,1,0,0}, {0,0,1,0,0,0,1,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 53) { // 5
	    	resetGame();
	    	int[][] custommap = { {0,0,0,0,1,0,0,0,1}, {0,0,0,1,1,0,0,0,1}, {0,1,1,1,0,0,0,0,0}, {0,0,1,1,0,0,0,0,0}, {0,0,0,1,0,0,0,1,0}, {0,0,0,0,0,0,1,1,0}, {0,1,0,0,0,1,1,0,0}, {1,1,1,0,0,1,1,0,0}, {1,1,1,0,1,1,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }

	    updateTurning();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    int code = e.getKeyCode();

	    if (code == 39) {
	        rightPressed = false;
	    }

	    if (code == 37) {
	        leftPressed = false;
	    }

	    if (code == 68) {
	        dPressed = false;
	    }

	    if (code == 65) {
	        aPressed = false;
	    }

	    updateTurning();
	}
	private void updateTurning() {
	    // ship 1 turning
	    if (rightPressed && !leftPressed) {
	        ship.setTurning(true);
	        ship.setTurnDir(1);
	    } else if (leftPressed && !rightPressed) {
	        ship.setTurning(true);
	        ship.setTurnDir(-1);
	    } else {
	        ship.setTurning(false);
	    }

	    // ship 2 turning
	    if (dPressed && !aPressed) {
	        ship2.setTurning(true);
	        ship2.setTurnDir(1);
	    } else if (aPressed && !dPressed) {
	        ship2.setTurning(true);
	        ship2.setTurnDir(-1);
	    } else {
	        ship2.setTurning(false);
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long now = System.currentTimeMillis();
		// TODO Auto-generated method stub
		if(ship.outOfBounds(712, 712)) {
			ship.collide(712, 712);
		}
		if(ship2.outOfBounds(712, 712)) {
			ship2.collide(712, 712);
		}
		if ((ship.hitting(ship2) || ship2.hitting(ship)) && now - lastCollisionTime > collisionCD) {
			if(ship.hitting(ship2)) {
				score++;
				if(score >= 5) {
					// RED WINS & reset game & scores
					System.out.println("RED WINS");
					score=0;
					score2=0;
				}
			} else {
				score2++;
				if(score2 >= 5) {
					// BLUE WINS & reset game & scores
					System.out.println("BLUE WINS");
					score2=0;
					score=0;
				}
			}
	        resetGame();
	        lastCollisionTime = now;
	    }
		repaint();
	}

}
