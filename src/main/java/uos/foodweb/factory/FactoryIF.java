package uos.foodweb.factory;

import uos.foodweb.gameobjects.GameObject;
import uos.foodweb.gamestates.GameState;
/**
 * @author Emmanuel Uduma
 * Interface that would help in 
 * creation of objects and game states
 */
public interface FactoryIF {
	//create game states
	public GameState createGameState(String name);
	//create game objects
	public GameObject createProduct(String discrim, double x, double y, double width, double height);
}
