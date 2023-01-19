package uos.foodweb.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Emmanuel Uudma
 * class for rain
 */
public class Rain extends GameObject{

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rain(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Rain.class.getResource("/uos/foodweb/gameassets/images/rain.png").toExternalForm());
	}

	//customise render method for this object
	@Override
	public void render(GraphicsContext gc) {
		dx= 0;
		if(y>600) {
			dy =1;
		}
		super.render(gc);
	}
}
