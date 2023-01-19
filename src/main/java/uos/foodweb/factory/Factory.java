package uos.foodweb.factory;

import uos.foodweb.gameobjects.GameObject;
import uos.foodweb.gameobjects.Goat;
import uos.foodweb.gameobjects.Grass;
import uos.foodweb.gameobjects.Jackal;
import uos.foodweb.gameobjects.Kite;
import uos.foodweb.gameobjects.Life;
import uos.foodweb.gameobjects.Lion;
import uos.foodweb.gameobjects.Mouse;
import uos.foodweb.gameobjects.Owl;
import uos.foodweb.gameobjects.Player;
import uos.foodweb.gameobjects.Rabbit;
import uos.foodweb.gameobjects.Rain;
import uos.foodweb.gameobjects.Snake;
import uos.foodweb.gamestates.GameState;
import uos.foodweb.gamestates.GameStateManager;
import uos.foodweb.gamestates.PlayState;
import uos.foodweb.gamestates.StartState;

/**
 * @author Emmanuel Uduma
 * This class is responsible for the creation 
 * of game objects and game states 
 * for the entire game and application
 * To prevent multiple creations (e.g multiple of same game state)
 * and control creation there is only one instance of this class
 */

public class Factory implements FactoryIF{
	private static Factory instance;
	private GameStateManager gsm;
	
	//instance of factory class
	public static Factory getInstance(GameStateManager gsm) {
		if(instance == null)
			instance = new Factory(gsm);
		return instance;
	}
	
	//method to help with creation of instances of game objects in game
	@Override
	public GameObject createProduct(String discrim, double x, double y, double width, double height) {
		//creation of instances of classes
			if(discrim.equals("lion"))
				return new Lion(x, y, width, height);
			else if (discrim.equals("kite"))
				return new Kite(x, y, width, height);
			else if (discrim.equals("owl"))
				return new Owl(x, y, width, height);
			else if (discrim.equals("goat"))
				return new Goat(x, y, width, height);
			else if (discrim.equals("mouse"))
				return new Mouse(x, y, width, height);
			else if (discrim.equals("rabbit"))
				return new Rabbit(x, y, width, height);
			else if (discrim.equals("snake"))
				return new Snake(x, y, width, height);
			else if (discrim.equals("jackal"))
				return new Jackal(x, y, width, height);
			else if (discrim.equals("grass"))
				return new Grass(x, y, width, height);
			else if (discrim.equals("player"))
				return new Player(x, y, width, height);
			else if (discrim.equals("rain"))
				return new Rain(x, y, width, height);
			else if (discrim.equals("life"))
				return new Life(x, y, width, height);
			else
			return null;
		
	}
	
	/**
	 * Constructor
	 * @param gsm
	 */
	private Factory(GameStateManager gsm){
		super();
		this.gsm = gsm;
	}

	//this method creates the game states used in the game
	@Override
	public GameState createGameState(String name) {
		if(name.equals("start"))
			return new StartState(gsm);
		else if (name.equals("play"))
			return new PlayState(gsm);
		else
		return null;
	}

}
