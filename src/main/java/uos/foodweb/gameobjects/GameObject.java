package uos.foodweb.gameobjects;


//import statements
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uos.foodweb.strategy.GameArtifact;

/**
 * 
 * @author Emmanuel Uduma
 *This is the general parent class for all game objects
 *properties and features for all game objects can 
 *easily be created in this class
 */

public class GameObject implements GameArtifact{
//variables
	protected Image img, lifeImgFull, img4, img3, img2, img1, deadImg;
	protected double x, y;
	protected double width, height;
	protected double dx =2, dy=2;
	protected int age = 0;//this variable would be used to control life span of game objects
	private Rectangle r;//this variable is used to manipulate intersection of object rectangles
	protected String name;
	
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public GameObject(double x, double y, double width, double height) { 
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setR(new Rectangle(0, 0, width, height));
	}
	
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public void moveLeft() {
		x--;
	}
	public void moveRight() {
		x++;
	}
	public void moveUp() {
		y--;
	}
	public void moveDown() {
		y++;
	}
	
	/**
	 * This next six controls control the 
	 * life bar of the player as the names imply
	 * the methods when called display different 
	 * pictures on the life bar of the player 
	 * depicting how much health the player has left.
	 */
	public void displayFullLife() {
		img = lifeImgFull;
	}
	public void display4Life() {
		img = img4;
	}
	public void display3Life() {
		img = img3;
	}
	public void display2Life() {
		img = img2;
	}
	public void display1Life() {
		img = img1;
	}
	public void displayDeadLife() {
		img = deadImg;
	}
	
	/**
	 * this method updates the invisible
	 * rectangle the game object is in it 
	 * would be vital for detecting collision
	 */
	public void updateRectangle() {
		getR().setX(x);
		getR().setY(y);
		getR().setWidth(width);
		getR().setHeight(height);
	}


	@Override
	public void update() {
		
	}

   /**
    * This method control the general movement of 
    * game objects in the application the method 
    * is over ridden and customised in sub classes if need be.
    */
	@Override
	public void render(GraphicsContext gc) {
		if(img != null) {
			age += 1;
			gc.drawImage(img, x, y, width, height);
			x+=dx;//Continuous horizontal movement
			if(x>800) {//change direction when at extreme
				dx = -dx;
				if(img2 != null) {
					img = img2;
				}
			}
			if(x<0) {//change direction when at extreme
				dx = -dx;
				if(img1 != null) {
					img = img1;
				}
			}
			y+=dy;//Continuous vertical movement
		    if(y>600) {//change direction when at extreme
		    	dy = -dy;
		    	if(img3 != null) {
					img = img3;
				}
		    }
			if(y<0) {//change direction when at extreme
				dy = -dy;
				if(img4 != null) {
					img = img4;
				}
			}
		}
		updateRectangle();
		
	}


	public Rectangle getR() {
		return r;
	}


	public void setR(Rectangle r) {
		this.r = r;
	}
}
