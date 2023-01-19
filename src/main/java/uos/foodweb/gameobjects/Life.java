package uos.foodweb.gameobjects;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author Emmanuel Uduma
 * Class for life bar object
 */
public class Life extends GameObject{

	/**
	 * Construtor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Life(double x, double y, double width, double height) {
		super(x, y, width, height);
		//assign images
		img = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/fullheart.png").toExternalForm());
		lifeImgFull = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/fullheart.png").toExternalForm());
		img4 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/fourlives.png").toExternalForm());
		img3 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/threelives.png").toExternalForm());
		img2 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/twolives.png").toExternalForm());
		img1 = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/onelive.png").toExternalForm());
		deadImg = new Image(Life.class.getResource("/uos/foodweb/gameassets/images/lives/dead.png").toExternalForm());
	}

	//customise render method for object
	@Override
	public void render(GraphicsContext gc) {
		dx=0;
		dy=0;
		super.render(gc);
	}
}
