package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for snake objects
 */
public class Snake extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Snake(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Snake.class.getResource("/uos/foodweb/gameassets/images/snake/Snake.png").toExternalForm());
		img2 = new Image(Snake.class.getResource("/uos/foodweb/gameassets/images/snake/Snakeleft.png").toExternalForm());
		img1 = new Image(Snake.class.getResource("/uos/foodweb/gameassets/images/snake/Snake.png").toExternalForm());
	}
	
}
