package uos.foodweb.application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uos.foodweb.gamestates.GameStateManager;
import uos.foodweb.strategy.Context;
import uos.foodweb.strategy.GameArtifact;
/**
 * @Author Emmanuel Uduma
 * This is the main Class of the game
 * This class has the main method and runs the application 
 * with various codes and graphic contents from the various 
 * states in the game.
 * it extends Application an makes use of the context and game state manager 
 * to manoeuvre between and manage game states.
 */

public class GameApp extends Application implements GameArtifact{
    //variables
	Pane root;
	Scene scene;
	GraphicsContext gc;
	Canvas canvas;
	private Context context;//get the context class
	private GameStateManager gsm;//get game state manager to help manage game states
	
	/**
	 * Controls key events from the various game states
	 */
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			gsm.keyPressed(event);
		}};

		//animation run during the course of the game for different game states
	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			//for each game states the code in the update and render block would run here
			update();
			render(gc);
		}};
		
		//main method
	public static void main(String[] args) {
		// Main method launching the Application
        launch(args);
	}
	
    /**
     * Every initialisation and method run when the application starts
     */
//	@SuppressWarnings("exports")
	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
		// initialising variables at the start of the application
		root = new Pane(); //initialise new root 
		scene = new Scene(root, 800, 600); // Initialise new scene with the root and sets width and length of app interface
		primaryStage.setTitle("Jungle Lone Survivor");//sets title of the app
		primaryStage.setScene(scene);//sets the scene at start of application
		primaryStage.show();//show the scene set
		canvas = new Canvas(800,600); //sets up background to be drawn on
		gc = canvas.getGraphicsContext2D();//Graphics would be done on Canvas
		context = Context.getInstance();//get context instance
		gsm = GameStateManager.getInstance();//get game state manager instance
		root.getChildren().add(canvas);//add canvas to the root
		timer.start();//this starts the animation timer when the application is run
	}

	//set artifact and update based on artifact
	@Override
	public void update() {
		scene.setOnKeyPressed(keyHandler);
		context.setArtifact(gsm);
        context.update();
				
	}

	//set artifact and render graphic context of artifact
//	@SuppressWarnings("exports")
	@Override
	public void render(@SuppressWarnings("exports") GraphicsContext gc) {
		context.setArtifact(gsm);
        context.render(gc);
	}

}