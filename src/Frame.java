import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener{
	// frame width/height
	static int width = 800;
	static int height = 800;
	Map map = new Map(width, height, 80);
	static Ship ship = new Ship(200, 200);
	static Ship ship2 = new Ship2(400, 200);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		map.draw(g);
		g.drawLine(0, 0, 585, 560);
		
		ship.paint(g);
		ship2.paint(g);
		
		System.out.println(ship.hitting(ship2));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame();
	}

	public Frame() {
		map.generateMap(.12);
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		
		
		if (e.getKeyCode() == 38) { // UP
			ship.setTurning(true);
		}
		if (e.getKeyCode() == 39) { // RIGHT
			ship.shoot();
		}
		if (e.getKeyCode() == 37) { // LEFT
			ship.dashTurn();
		}
		 
		 
		 
		 if (e.getKeyCode() == 87) { // W
			 ship2.setTurning(true);
		 }
		 if (e.getKeyCode() == 68) { // D
			 ship2.shoot();
		 }
		 if (e.getKeyCode() == 65) { // A
			 ship2.dashTurn();
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == 38) { // UP
			ship.setTurning(false);
		}
		
		
		 if (e.getKeyCode() == 87) { // W
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
		// TODO Auto-generated method stub
		if(ship.outOfBounds(785, 760)) {
			ship.collide(785, 760);
		}
		if(ship2.outOfBounds(785, 760)) {
			ship2.collide(785, 760);
		}
		repaint();
	}

}
