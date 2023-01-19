package uos.foodweb.gameobjects;


import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for jackal objects
 */
public class Jackal extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Jackal(double x, double y, double width, double height) {
		super(x, y, width, height);
		//increase speed by increasing dy and dx
		dx = 4;
		dy = 4;
		//assign images
		img = new Image(Jackal.class.getResource("/uos/foodweb/gameassets/images/jackal/Jackal.png").toExternalForm());
		img2 = new Image(Jackal.class.getResource("/uos/foodweb/gameassets/images/jackal/Jackalleft.png").toExternalForm());
		img1 = new Image(Jackal.class.getResource("/uos/foodweb/gameassets/images/jackal/Jackal.png").toExternalForm());
	}
}
