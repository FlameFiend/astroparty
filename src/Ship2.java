
public class Ship2 extends Ship {
	public Ship2() {
		super();
		
		image	= getImage("/imgs/"+"blueship.png"); //load the image for Tree
	}
	
	public Ship2(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
}
