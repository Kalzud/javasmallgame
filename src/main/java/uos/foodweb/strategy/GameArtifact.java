package uos.foodweb.strategy;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author Emmanuel Uduma
 * This interface is to help with the updates 
 * and rendering of graphics context of various game states
 */
public interface GameArtifact {
	    // update method of artifact
		public void update();
		// draw the graphics of the artifact on the canvas
		public void render(GraphicsContext gc);
		
}
