package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uudma
 * class for owl objects
 */
public class Owl extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Owl(double x, double y, double width, double height) {
		super(x, y, width, height);
		// assign images
		img = new Image(Owl.class.getResource("/uos/foodweb/gameassets/images/owl/Owl.png").toExternalForm());
		img2 = new Image(Owl.class.getResource("/uos/foodweb/gameassets/images/owl/Owlleft.png").toExternalForm());
		img1 = new Image(Owl.class.getResource("/uos/foodweb/gameassets/images/owl/Owl.png").toExternalForm());
	}

}
