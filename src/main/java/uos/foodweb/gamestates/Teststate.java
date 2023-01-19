package uos.foodweb.gamestates;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uos.foodweb.gameobjects.GameObject;

public class Teststate extends GameState{
	GameObject player = null;//create player object for player in the game
	GameObject mouse = null;
	public Teststate(GameStateManager gsm) {
		super("test", gsm);
//		System.out.println("play please");
//		player = factory.createProduct("player", 350, 500, 120, 90);//create player
//		list.add(player);//add player to object list
		mouse = factory.createProduct("mouse", 350, 500, 120, 90);
		list.add(mouse);
		bImg = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/background/background.jpg"));//assign background image
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.W) {//to go up
			if(player.getY() > 10)//if greater than extreme top
			player.moveUp();
		}
		if(event.getCode() == KeyCode.SPACE) {//when space is pressed
			bImg = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/background/startcover.jpg"));//assign background image
		}
		if(event.getCode() == KeyCode.M) {//when space is pressed
			bImg = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/background/background.jpg"));//assign background image
		}
		super.keyPressed(event);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(bImg, 0, 0, 800, 600);
		for(GameObject obj : list) {
			obj.render(gc);//show objects in list
		}
		//list for Mouse objects
//				for(GameObject mice : mouseList) {
//					//for every mouse in run code in code block
////					mouse = mice;//assign mouse to mouse object
////					mouseCollision(mice);
//					//show and move mouse object
//					mice.render(gc);
//				}
		super.render(gc);
	}

	
	
}
