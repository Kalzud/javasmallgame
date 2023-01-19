package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for lion object
 */
public class Lion extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Lion(double x, double y, double width, double height) {
		super(x, y, width, height);
		//Increase speed by increasing dy and dx
		dx = 3;
		dy = 3;
		// assign images
		img = new Image(Lion.class.getResource("/uos/foodweb/gameassets/images/lion/Lion.png").toExternalForm());
		img2 = new Image(Lion.class.getResource("/uos/foodweb/gameassets/images/lion/Lion.png").toExternalForm());
		img1 = new Image(Lion.class.getResource("/uos/foodweb/gameassets/images/lion/Lionright.png").toExternalForm());
	}
}
