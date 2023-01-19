package uos.foodweb.gameobjects;

import javafx.scene.image.Image;

/**
 * 
 * @author Emmanuel Uduma
 * class for goat objects
 */
public class Goat extends GameObject{
    /**
     * Constructor
     * @param x
     * @param y
     * @param width
     * @param height
     */
	public Goat(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Goat.class.getResource("/uos/foodweb/gameassets/images/goat/Goat.png").toExternalForm());
		img2 = new Image(Goat.class.getResource("/uos/foodweb/gameassets/images/goat/Goatleft.png").toExternalForm());
		img1 = new Image(Goat.class.getResource("/uos/foodweb/gameassets/images/goat/Goat.png").toExternalForm());
	}
}
