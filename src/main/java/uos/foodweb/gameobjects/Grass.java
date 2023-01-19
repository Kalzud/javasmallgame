package uos.foodweb.gameobjects;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uduma
 * class for grass objects
 */
public class Grass extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Grass(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Grass.class.getResource("/uos/foodweb/gameassets/images/Grass.png").toExternalForm());
	}

	//customise render method for object
	@Override
	public void render(GraphicsContext gc) {
		dx =0;
		dy =0;
		super.render(gc);
	}
}
