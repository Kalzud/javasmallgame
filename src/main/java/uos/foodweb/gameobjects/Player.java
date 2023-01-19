package uos.foodweb.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * This class is the class for the player object
 * it controls the movement and player images
 */
public class Player extends GameObject{


	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);
		// assign images
		img = new Image(Player.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcat.png").toExternalForm());
		img2 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcat.png").toExternalForm());
		img1 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcatright.png").toExternalForm());
	}
	
	//customise render method for this object
	@Override
	public void render(GraphicsContext gc) {
		dx=0;
		dy=0;
		super.render(gc);
	}



	/**
	 * Customise the following methods for this object
	 */
	
	
	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		x -= 30;
		img = img2;
		super.moveLeft();
	}



	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		x += 30;
		img = img1;
		super.moveRight();
	}



	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		y -= 30;
		super.moveUp();
	}



	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		y += 30;
		super.moveDown();
	}
	
	
	
}
