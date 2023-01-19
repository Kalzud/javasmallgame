package uos.foodweb.strategy;

import javafx.scene.canvas.GraphicsContext;
/**
 * @author Emmanuel Uduma
 * This class would be responsible for setting and 
 * getting game artifact to be used for rendering 
 * and updating during various game states
 * It would have only one instance throughout the application life time
 */
public class Context implements GameArtifact{
   private static Context instance = null;
   private GameArtifact artifact;
   
   
   /**
    * Constructor
    */
   private Context() {
	   super();
	   artifact = null;
   }
   
   //return instance of this class
   public static Context getInstance() {
	   if(instance == null)
		   instance = new Context();
	   return instance;
   }
   
   //return the current artifact
   public GameArtifact getArtifact() {
	   return artifact;
   }
   
   //set the artifact
   public void setArtifact(GameArtifact artifact) {
	   this.artifact = artifact;
   }
   /**
	 * call the update method of the artifact
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		artifact.update();
	}

	/**
	 * call the render method of the artifact
	 */
	@Override
	public void render(GraphicsContext gc) {
		artifact.render(gc);
	}
}
