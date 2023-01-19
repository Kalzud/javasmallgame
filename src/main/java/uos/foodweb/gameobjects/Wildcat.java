package uos.foodweb.gameobjects;

import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for wildcat
 */
public class Wildcat extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Wildcat(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Wildcat.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcat.png").toExternalForm());
		img2 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcat.png").toExternalForm());
		img1 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/wildcat/Wildcatright.png").toExternalForm());
	}
}
