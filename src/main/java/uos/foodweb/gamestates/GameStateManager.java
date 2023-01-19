package uos.foodweb.gamestates;

import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import uos.foodweb.factory.Factory;
import uos.foodweb.strategy.Context;
import uos.foodweb.strategy.GameArtifact;

/**
 * 
 * @author Emmanuel Uduma
 * This class is the class that manages the game states possess a 
 * stack which it adds and removes game states from
 * In order to manage game states it adds the current game state 
 * to top of the game state stack and peeks at it to run features 
 * in that particular game state
 * to ensure it is the only class that can manipulate game state 
 * to avoid errors it has only one instance in the life span of 
 * the game or run time of the application
 * it uses the context to set artifact to current game state
 */
public class GameStateManager implements GameArtifact, EventAction{
    //instance of this class
	private static GameStateManager instance = null;
	//a stack used to store the game states
	private Stack<GameState> gamestates;
	//factory used for creation of objects
	private Factory factory;
	//context used as part of strategy pattern
	private Context context;
	
	/**
	 * constructor
	 */
	private GameStateManager() {
		//initialise variables 
		gamestates= new Stack<GameState>();
		factory = Factory.getInstance(this);
		context = Context.getInstance();  
		//start game application on start state
		gamestates.push(factory.createGameState("start"));
	}
	
	//single instance of Game State Manager class
	public static GameStateManager getInstance() {
		if(instance == null)
			instance = new GameStateManager();
		return instance;
	}
	
	//return game states in stack
	public Stack<GameState> getGameStates(){
		return gamestates;
	}
	
	//put the current game state into the stack
	public void pushState(GameState state) {
		gamestates.push(state);
	}
	
	//removes game state from stack
	public void popState() {
		gamestates.pop();
	}
	
	//use event keys of state on top of stack
	@Override
	public void keyPressed(KeyEvent event) {
		gamestates.peek().keyPressed(event);
		
	}

	//update game state at top of stack
	@Override
	public void update() {
		context.setArtifact(gamestates.peek());
		context.update();
	}

	//render game state on top of stack
	@Override
	public void render(GraphicsContext gc) {
		context.setArtifact(gamestates.peek());
		context.render(gc);
	}

}
