package uos.foodweb.gamestates;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * 
 * @author Emmanuel Uduma
 * This class is the class that programs the start interface of the game application
 * it the the first page that would be entered upon opening the application
 */
public class StartState extends GameState{
int colorTimer =0;//variable used to control flashing of instructions

//assigning images to image variables for decorating the start state
Image mouse = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/mouse/Mouse.png"));
Image rabbit = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/rabbit/Rabbit.png"));
Image goat = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/goat/Goat.png"));
Image lion = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/lion/Lion.png"));
Image jackal = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/jackal/Jackal.png"));
Image kite = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/kite/Kite.png"));
Image W = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterW.png"));
Image A = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterA.png"));
Image S = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterS.png"));
Image D = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterD.png"));
Image V = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterV.jpg"));
Image Q = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/letters/letterQ.jpg"));
Image welcome = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/welcome.png"));
	public StartState(GameStateManager gsm) {
		super("start", gsm);
		bImg = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/background/startcover.jpg")); 
	}

	//controls key events that happen in the start state
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.SPACE) {//when space is pressed
			//stop the intro music playing
			stopClip(clip);
			//reset sound timer
			soundTimer =0;
			//change to the play state
			gsm.pushState(factory.createGameState("play"));
		}
		if(event.getCode() == KeyCode.Q) {//when Q is pressed
			//create alert confirmation to confirm exit
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("This is to confirm if you want to leave the game");
			alert.setTitle("Leaving?");
			alert.setHeaderText("You sure?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.OK) {
				//close game
				System.exit(0);
			}
		}
		super.keyPressed(event);
	}

	@Override
	public void update() {
		//play the intro song
		if(soundTimer == 0) {
			playBackgroundSong("/uos/foodweb/gameassets/audios/gameintro.wav");
			soundTimer++;
		}
		super.update();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		//add images to the start state graphics context
		gc.drawImage(bImg, 0, 0, 800, 600);
		gc.drawImage(mouse, 50, 50, 100, 100);
		gc.drawImage(rabbit, 50, 200, 150, 150);
		gc.drawImage(goat, 50, 400, 300, 300);
		gc.drawImage(lion, 600, 300, 300, 300);
		gc.drawImage(jackal, 600, 150, 200, 200);
		gc.drawImage(kite, 600, 20, 150, 150);
		gc.drawImage(W, 375, 240, 50, 50);
		gc.drawImage(A, 290, 290, 50, 50);
		gc.drawImage(S, 450, 290, 50, 50);
		gc.drawImage(D, 375, 340, 50, 50);
		gc.drawImage(welcome, 150, 0, 500, 300);
		gc.drawImage(V, 150, 450, 50, 50);
		gc.drawImage(Q, 10, 10, 50, 50);
		
	    //write the following text on the start page
		drawText("UP", 375, 230, 54, Color.CHOCOLATE, gc);
		drawText("LEFT", 170, 335, 54, Color.CHOCOLATE, gc);
		drawText("RIGHT", 500, 335, 54, Color.CHOCOLATE, gc);
		drawText("DOWN", 320, 440, 54, Color.CHOCOLATE, gc);
		drawText("To plant when it rains and score extra", 210, 480, 20, Color.CHOCOLATE, gc);
		drawText("Press SPACE to play", 180, 570, 54, Color.YELLOW, gc);
		//code responsible for the flashing of the written instructions
		//changes the text colour based on colour timer count creating a flash illusion
        if(colorTimer ++> 30) {
        	drawText("Press SPACE to play", 180, 570, 54, Color.WHITE, gc);
        	colorTimer = 0;
		}  
        drawText("- QUIT", 60, 55, 54, Color.CHOCOLATE, gc);
		super.render(gc);
	}
	
	

}
