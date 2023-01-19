package uos.foodweb.gamestates;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uos.foodweb.factory.Factory;
import uos.foodweb.gameobjects.GameObject;
import uos.foodweb.strategy.Context;
/**
 * 
 * @author Emmanuel Uduma
 * This class is the parent class for all the game states it 
 * possess variables applicable to all the game states, it controls 
 * the sound, text drawing and event actions of states
 *
 */
public class GameState extends GameObject implements EventAction{
protected Image bImg;
protected Factory factory;
protected Context context;
protected GameStateManager gsm;
protected int score = 0;
protected Clip clip;//for background sound
protected Clip effect;//for sound effect
protected int soundTimer = 0;
protected Random rnd = new Random(System.currentTimeMillis());//to enable appearance in random places

	//animal list variables
protected ArrayList<GameObject> list = new ArrayList<GameObject>();//make an array list of game objects
protected ArrayList<GameObject> lionList = new ArrayList<GameObject>();//make an array list of lion objects
protected ArrayList<GameObject> goatList = new ArrayList<GameObject>();//make an array list of goat objects
protected ArrayList<GameObject> grassList = new ArrayList<GameObject>();//make an array list of grass objects
protected ArrayList<GameObject> jackalList = new ArrayList<GameObject>();//make an array list of jackal objects
protected ArrayList<GameObject> kiteList = new ArrayList<GameObject>();//make an array list of kite objects
protected ArrayList<GameObject> mouseList = new ArrayList<GameObject>();//make an array list of mouse objects
protected ArrayList<GameObject> owlList = new ArrayList<GameObject>();//make an array list of owl objects
protected ArrayList<GameObject> rabbitList = new ArrayList<GameObject>();//make an array list of rabbit objects
protected ArrayList<GameObject> snakeList = new ArrayList<GameObject>();//make an array list of snake objects
	
	
	/**
	 * Constructor
	 * @param name
	 * @param gsm
	 */
	public GameState(String name, GameStateManager gsm) {
		super(0, 0, 0, 0);
		this.name = name;
		bImg = null;
		clip = null;
		this.gsm = gsm;
		factory = Factory.getInstance(gsm);
		context = Context.getInstance();
		
	}
	
	//return name
	public String getName() {
		return name;
	}
    
	//get score
	public int getScore() {
		return score;
	}
	
	//set score
		public void setScore(int score) {
			this.score = score;
		}
		
	//to help render text onto screen
	protected void drawText(String text, double x, double y, int fontSize, Color color, GraphicsContext gc) {
		gc.setFill(color);
		gc.setFont(new Font(fontSize));
		gc.fillText(text, x, y);
	}
	
	//method to assign sound to clip variable and loop it continuously
	public void loopClip(URL urlPath) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(urlPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	//calls on the loop keep method and passes into it the file path of sound
	public void playBackgroundSong(String filepath) {
		loopClip(this.getClass().getResource(filepath));
	}
	
	//stops the sound playing
	public void stopClip(Clip clip) {
		clip.stop();
	}
	
	//assigns sound to effect clip
	public void sound(URL urlPath) {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(urlPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		effect.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	//calls sound method
	public void soundEffect(String filepath) {
		sound(this.getClass().getResource(filepath));
	}
	
	//stop effect sound
	public void stopSoundEffect(Clip effect) {
		effect.stop();
	}
	
	//implemented method from event action
	@Override
	public void keyPressed(KeyEvent event) {

	}
    
	//extended method from gameobject
	@Override
	public void update() {
		super.update();
	}

	//extended method from game object
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
	}

}
