import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener{
	// frame width/height
	static int width = 734;
	static int height = 800;
	Map map = new Map(800, 800, 60);
	static Ship ship = new Ship(400, 200);
	static Ship ship2 = new Ship2(200, 200);
	static Ship ship3 = new Ship2(100, 600);
	static Ship ship4 = new Ship(600, 200);
	long lastCollisionTime = 0;
	final int collisionCD = 1000;
	boolean rightPressed = false;
	boolean leftPressed = false;

	boolean dPressed = false;
	boolean aPressed = false; 
	boolean iPressed = false;
	boolean jPressed = false;
	boolean kPressed = false;
	boolean lPressed = false;
	boolean num5Pressed = false;
	boolean num6Pressed = false;
	boolean num3Pressed = false;
	boolean num9Pressed = false;
    
	int score =0;
	int score2=0;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		map.draw(g);
		
		ship.paint(g, map);
		ship2.paint(g, map);
		ship3.paint(g, map);
		ship4.paint(g, map);
		g.setColor(Color.GRAY);
		g.fillRect(0, 720, 734, 43);
		g.setColor(Color.WHITE);
		g.drawString("Blue Wins: " + score2 + "    |    Red Wins`: " + score, 288, 743);
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
		map.setTileSize(60);
	    ship.x = map.getSpawnB().x;
	    ship.y = map.getSpawnB().y;
	    ship2.x = map.getSpawnA().x;
	    ship2.y = map.getSpawnA().y;
	    ship.alive = true;
	    ship2.alive = true;
	    ship3.alive = true;
	    ship4.alive = true;
	    ship3.x = 100; ship3.y = 600;
	    ship4.x = 600; ship4.y = 100;
	    ship3.angle = 0;
	    ship4.angle = Math.PI;
	    ship3.velocity = ship4.velocity = 0;
	    ship3.ammo[0] = ship3.ammo[1] = ship3.ammo[2] = 1;
	    ship4.ammo[0] = ship4.ammo[1] = ship4.ammo[2] = 1;
	    ship.angle=Math.PI/2;
	    ship2.angle=-Math.PI/2;
	    ship.velocity=ship2.velocity=0;
	    ship.ammo[0] = ship.ammo[1] = ship.ammo[2] = 1;
	    ship2.ammo[0] = ship2.ammo[1] = ship2.ammo[2] = 1;
	    map.generateMap(0.3+Math.log10(score+score2+1)/7);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int code = e.getKeyCode();
	    
	    if (code == 82) {
	        resetGame(); //R
	    }
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
	 // Ship 3 - IJKL
	    if (code == 73) ship3.shoot();
	    if (code == 75) ship3.backstep();
	    if (code == 76) lPressed = true;
	    if (code == 74) jPressed = true;

	    // Ship 4 - NUMPAD 5/3/6/9
	    if (code == 101) ship4.shoot();
	    if (code == 102) ship4.backstep();
	    if (code == 105) num6Pressed = true;
	    if (code == 99) num5Pressed = true;
	    //CUSTOM MAP IMPORTING
	    if(code == 49) { // 1
	    	resetGame();
	    	map.setTileSize(80);
	    	int[][] custommap = { {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,1,0,1,0,1,0}, {0,0,0,0,0,0,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 50) { // 2
	    	resetGame();
	    	map.setTileSize(80);
	    	int[][] custommap = { {0,0,0,0,0,0,0,0,0}, {0,1,1,0,0,0,1,1,0}, {0,1,0,0,1,0,0,1,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,1,0,1,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,1,0,0,1,0,0,1,0}, {0,1,1,0,0,0,1,1,0}, {0,0,0,0,0,0,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 51) { // 3
	    	resetGame();
	    	map.setTileSize(80);
	    	int[][] custommap = { {0,0,0,1,0,0,0,1,1}, {0,0,0,1,0,1,0,0,1}, {0,0,0,0,0,1,0,0,0}, {1,1,0,0,0,0,1,1,0}, {0,0,0,0,1,0,0,0,0}, {0,1,1,0,0,0,0,1,1}, {0,0,0,1,0,0,0,0,0}, {1,0,0,1,0,1,0,0,0}, {1,1,0,0,0,1,0,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 52) { // 4
	    	resetGame();
	    	map.setTileSize(80);
	    	int[][] custommap = { {0,0,1,0,0,0,1,0,0}, {0,0,1,0,0,0,0,0,0}, {0,0,1,0,0,0,0,0,0}, {0,0,1,0,0,0,1,0,0}, {0,0,1,1,0,1,1,0,0}, {0,0,1,0,0,0,1,0,0}, {0,0,0,0,0,0,1,0,0}, {0,0,0,0,0,0,1,0,0}, {0,0,1,0,0,0,1,0,0} };
	    	map.loadMap(custommap);
	    	ship.x+=40;
	    	ship2.x-=40;
	    }
	    if(code == 53) { // 5
	    	resetGame();
	    	map.setTileSize(80);
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
	    if (code == 76) lPressed = false;
	    if (code == 74) jPressed = false;

	    if (code == 105) num6Pressed = false;
	    if (code == 99) num5Pressed = false;
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
	 // ship 3 turning
	    if (lPressed && !jPressed) {
	        ship3.setTurning(true); ship3.setTurnDir(1);
	    } else if (jPressed && !lPressed) {
	        ship3.setTurning(true); ship3.setTurnDir(-1);
	    } else {
	        ship3.setTurning(false);
	    }

	    // ship 4 turning
	    if (num6Pressed && !num5Pressed) {
	        ship4.setTurning(true); ship4.setTurnDir(1);
	    } else if (num5Pressed && !num6Pressed) {
	        ship4.setTurning(true); ship4.setTurnDir(-1);
	    } else {
	        ship4.setTurning(false);
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
		if(ship3.outOfBounds(712, 712)) {
			ship3.collide(712, 712);
		}
		if(ship4.outOfBounds(712, 712)) {
			ship4.collide(712, 712);
		}
		Ship[] redTeam = {ship, ship4};
		Ship[] blueTeam = {ship2, ship3};
		if (now - lastCollisionTime > collisionCD) {
		    // Check collisions between ships of opposing teams
		    for (Ship attacker : redTeam) {
		        for (Ship defender : blueTeam) {
		            if (attacker.alive && defender.alive && attacker.hitting(defender)) {
		                defender.alive = false; 
		                System.out.println("RED scored! Blue ship killed.");
		                lastCollisionTime = now;
		                break;
		            }
		        }
		    }
		    
		    for (Ship attacker : blueTeam) {
		        for (Ship defender : redTeam) {
		            if (attacker.alive && defender.alive && attacker.hitting(defender)) {
		                defender.alive = false;
		                System.out.println("BLUE scored! Red ship killed.");
		                lastCollisionTime = now;
		                break;
		            }
		        }
		    }
		    
		    // Check if all blue ships dead RED wins
		    boolean blueAllDead = true;
		    for (Ship s : blueTeam) {
		        if (s.alive) {
		            blueAllDead = false;
		            break;
		        }
		    }
		    
		    if (blueAllDead) {
		        System.out.println("RED WINS");
		        score++;
		        resetGame();
		        lastCollisionTime = now;
		        return;
		    }
		    
		    // Check if all red ships dead → BLUE wins
		    boolean redAllDead = true;
		    for (Ship s : redTeam) {
		        if (s.alive) {
		            redAllDead = false;
		            break;
		        }
		    }
		    
		    if (redAllDead) {
		        System.out.println("BLUE WINS");
		        score2++;
		        resetGame();
		        lastCollisionTime = now;
		        return;
		    }
		}
		repaint();
	}

}
