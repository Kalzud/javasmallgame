package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * Class for rabbit object
 */
public class Rabbit extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rabbit(double x, double y, double width, double height) {
		super(x, y, width, height);
		//Increase speed by increasing dy and dx
		dx=5;
		dy=5;
		//Assign images
		img = new Image(Rabbit.class.getResource("/uos/foodweb/gameassets/images/rabbit/Rabbit.png").toExternalForm());
		img2 = new Image(Rabbit.class.getResource("/uos/foodweb/gameassets/images/rabbit/Rabbit.png").toExternalForm());
		img1 = new Image(Rabbit.class.getResource("/uos/foodweb/gameassets/images/rabbit/Rabbitright.png").toExternalForm());
	}
}
