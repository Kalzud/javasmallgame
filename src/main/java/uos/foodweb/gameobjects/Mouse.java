package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for mouse object
 */
public class Mouse extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Mouse(double x, double y, double width, double height) {
		super(x, y, width, height);
		//Increase speed by increasing dy and dx
		dx = 3;
		dy = 3;
		//assign images
		img = new Image(Mouse.class.getResource("/uos/foodweb/gameassets/images/mouse/Mouse.png").toExternalForm());
		img4 = new Image(Mouse.class.getResource("/uos/foodweb/gameassets/images/mouse/Mouse.png").toExternalForm());
		img3 = new Image(Mouse.class.getResource("/uos/foodweb/gameassets/images/mouse/Mouseup.png").toExternalForm());
		img2 = new Image(Mouse.class.getResource("/uos/foodweb/gameassets/images/mouse/Mouseleft.png").toExternalForm());
		img1 = new Image(Mouse.class.getResource("/uos/foodweb/gameassets/images/mouse/Mouseright.png").toExternalForm());
	}
}
