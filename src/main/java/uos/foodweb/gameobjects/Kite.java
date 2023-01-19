package uos.foodweb.gameobjects;


import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for kite objects
 */
public class Kite extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Kite(double x, double y, double width, double height) {
		super(x, y, width, height);
		//Increase speed by increasing dy and dx
		dx =10;
		dy =10;
		//assign images
		img = new Image(Kite.class.getResource("/uos/foodweb/gameassets/images/kite/Kite.png").toExternalForm());
		img2 = new Image(Kite.class.getResource("/uos/foodweb/gameassets/images/kite/Kite.png").toExternalForm());
		img1 = new Image(Kite.class.getResource("/uos/foodweb/gameassets/images/kite/Kiteright.png").toExternalForm());
	}
}
