import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Walls {
    private ArrayList<Rectangle> wallRects;

    public Walls() {
        wallRects = new ArrayList<>();
    }

    public void addWall(Rectangle wall) {
        wallRects.add(wall);
    } 

    public ArrayList<Rectangle> getWallRects() {
        return wallRects;
    }

    public void draw(Graphics g) {
        for (Rectangle wall : wallRects) {
        	g.setColor(Color.GRAY);
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
            g.setColor(Color.ORANGE);
            g.fillRect(wall.x+3, wall.y+3, wall.width-6, wall.height-6);
        }
    }

    public boolean checkCollision(Rectangle object) {
        for (Rectangle wall : wallRects) {
            if (object.intersects(wall)) return true;
        }
        return false;
    }
}