package uos.foodweb.gamestates;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uos.foodweb.gameobjects.GameObject;

/**
 * 
 * @author Emmanuel Uduma
 * This class controls the game play of the game it is 
 * the play state where the action in game takes place
 * The class would involve interfaces during and after game play
 */
public class PlayState extends GameState{
	GameObject player = null;//create player object for player in the game
	GameObject life = null;//create life bar object5 for the player
	boolean gameover = false;//this boolean value would be used as a check to run codes when game is over
	ArrayList<String> tips = new ArrayList<String>();//an array of helpful tips for player
	Random random_tip = new Random();//help randomise the tips
	String message = null;//the tip message
	
	//timing controls
	int rabbitSecond = 1500;//sense of time for rabbit object
	int mouseSecond = 1000;//sense of time for mouse object
	int goatSecond = 200;//sense of time for goat object
	int snakeSecond = 2000;//sense of time for snake object
	int jackalSecond = 1000;//sense of time for jackal object
	int owlSecond = 5000;//sense of time for owl object
	int kiteSecond = 0;//sense of time for kite object
	int lionSecond = 0;//sense of time for lion object
	int second = 0;//this variable would be used to control and give a sense of time
	int rainfallSecond =0;//control how often rain falls
	int raining =0; //controls how long it rains
	int flashTimer =0;//flash controls
	int tipTimer =8500000; //control timer for tips
	int timeout =0;//helps know how long the game over interface is seen
	int eating =0;//helps control the eat effect sound
		
	//controls when player can plant
	int plantPermit =0;
	
	//variable objects to be removed from animals array list would be assigned to
	GameObject temp = null;
	//variables to help with collision detection
	GameObject lion = null;
	GameObject goat = null;
	GameObject mouse = null;
	GameObject jackal = null;
	GameObject rabbit = null;
	GameObject kite = null;
	GameObject owl = null;
	GameObject snake = null;
	
	//animation timer for rainfall animation
	AnimationTimer rainTimer = new AnimationTimer() {

		@Override
		public void handle(long now) {
			//make plant permit int 1 to enable planting during rainfall
			plantPermit = 1;
			if(raining ++>10 ) {
				//add rain drops repeatedly
				list.add(factory.createProduct("rain", rnd.nextInt(800), 0, 10, 10));
			}
			
			if(second ++> 1000) {
				//stop rainfall by stopping timer 
				//and change plant permit to int zero to prevent player planting when there is no rainfall
				rainTimer.stop();
				second = 0;
				plantPermit = 0;
			}
			
		}};
		
	//animation timer for eating sound effect
	AnimationTimer eat = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(eating == 0) {
					//when eating is zero play eat sound and increse int eating
					soundEffect("/uos/foodweb/gameassets/audios/eat.wav");
					eating++;
				}
				if(eating ++> 7) {
					//after 7 stop eat sound effect and stop timer
					stopSoundEffect(effect);
					eat.stop();
					eating =0;
				}
				
			}
		};
		
	/**
	 * Constructor
	 * @param gsm
	 */
	public PlayState(GameStateManager gsm) {
		super("play", gsm);
		player = factory.createProduct("player", 350, 500, 120, 90);//create player
		list.add(player);//add player to object list
		life = factory.createProduct("life", 0, 0, 120, 30);//create life bar
//		list.add(factory.createProduct("lion", 350, 500, 120, 90));
		list.add(life);//add life bar to object list
		bImg = new Image(this.getClass().getResourceAsStream("/uos/foodweb/gameassets/images/background/background.jpg"));//assign background image
		addTips();//add tips to tips array list
	}
    

	/**
	 * Controls events that happen when keyboard 
	 * keys are pressed during play state
	 * this include player movement and actions 
	 * and commands like quit
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		// code to run when keys are pressed
	if(event.getCode() == KeyCode.W) {//to go up
		if(player.getY() > 10)//if greater than extreme top
		player.moveUp();
	}
	if(event.getCode() == KeyCode.A) {//to go left
		if(player.getX() > 0)//if greater than extreme left
		player.moveLeft();
	}	
	if(event.getCode() == KeyCode.S) {//to go down
		if(player.getY() < 500)//if greater than extreme bottom
		player.moveDown();
	}	
	if(event.getCode() == KeyCode.D) {//to go right
		if(player.getX() < 700)//if greater than extreme right
		player.moveRight();
	}
	if(event.getCode() == KeyCode.V) {//to plant
		if(plantPermit == 1) {//if plant permission is allowed
			//add grass object to context
			grassList.add(factory.createProduct("grass", player.getX(), player.getY(), 50, 50));
			//Increase score or calories as seen on screen
			setScore(getScore()+1);
			//add more mouse objects
			mouseList.add(factory.createProduct("mouse", rnd.nextInt(800), rnd.nextInt(600), 70, 40));//create mouse object
			mouseList.add(factory.createProduct("mouse", rnd.nextInt(800), rnd.nextInt(600), 70, 40));//create mouse object
		}
	}
	if(event.getCode() == KeyCode.R) {//restart game
		if(gameover == true) {//only when game over is true
			//stop clip sound currently playing
			stopClip(clip);
			//reset timer so another clip can start without errors
			soundTimer = 0;
			//take it back to play state beginning
			gsm.pushState(factory.createGameState("play"));
		}
	}
	if(event.getCode() == KeyCode.Q) {//quit to start
		//alert pop up to confirm if the player wants to go to main menu or stay
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("This is to confirm if you want to go to main menu");
		alert.setTitle("Leaving?");
		alert.setHeaderText("You sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			//stop clip playing rest sound timer and change to start state
			stopClip(clip);
			soundTimer =0;
			gsm.pushState(factory.createGameState("start"));
		}
	}
		super.keyPressed(event);
	}

	//over ride the update method to customise for play state
	@Override
	public void update() {
		//control entry times and frequency of various game objects 
	    entry();
		super.update();
	}

	//over ride and customise the render method for play state
	@Override
	public void render(GraphicsContext gc) {
		//render for play state
		if(gameover == true) {
			//if game over run code in game over method
			gameover(gc);
		}else {
			// part of code running in animation timer during play state
			if(soundTimer == 0) {
				//if sound timer is 0 play background song and increase sound timer
				playBackgroundSong("/uos/foodweb/gameassets/audios/gameplay.wav");
				soundTimer++;
			}
			
		//redraw background
		gc.drawImage(bImg, 0, 0, 800, 600);
		//draw the quit instruction and calories score text
		 drawText("Q - QUIT", 640, 50, 40, Color.WHITE, gc);
		 drawText("Calories: " + getScore(), 10, 50, 20, Color.WHITE, gc);

		//list for other game objects
		for(GameObject obj : list) {
			obj.render(gc);//show objects in list
			lifeBar(gc);//life bar change method according to player eating pattern
			life.setAge(life.getAge()-1);//prevents life object from being removed from the list if age is greater than 4000
			if(obj.getAge() > 4000) {
				temp = obj;
			}
		}
		
		//list for lion objects
		for(GameObject lions : lionList) {
			//for every lion in list run code in code block
			lion = lions;//assign lion to lion object
			lionCollision(lions);
			//show and move lion object
			lions.render(gc);
		}
		
		//list for Jackal objects
		for(GameObject jackals : jackalList) {
			//for every jackal in list run code in code block
			jackal = jackals;//assign jackal to jackal object
            jackalCollision(jackals);
			//show and move jackal object
			jackals.render(gc);
		}
		
		//list for Kite objects
		for(GameObject kites : kiteList) {
			//for every kite in list run code in code block
			kite = kites;//assign kite to kite object
		    kiteCollision(kites);
			//show and move kite object
			kites.render(gc);
		}
		
		//list for owl objects
		for(GameObject owls : owlList) {
			//for every owl in list run code in code block
			owl = owls;//assign owl to owl object
	        owlCollision(owls);
			//show and move owl object
			owls.render(gc);
		}
		
		//list for snake objects
		for(GameObject snakes : snakeList) {
			//for every snake in list run code in code block
			snake = snakes;//assign snake to snake object
            snakeCollision(snakes);
			//show and move snake object
			snakes.render(gc);
		}
		
		//list for goat objects
		for(GameObject goats : goatList) {
			//for every goat in list run code in code block
			goat = goats;//assign goat object to goat variable
          goatCollision(goats);
       //show and move goat object
			goats.render(gc);
		}
		
		//list for Mouse objects
		for(GameObject mice : mouseList) {
			//for every mouse in run code in code block
			mouse = mice;//assign mouse to mouse object
			mouseCollision(mice);
			//show and move mouse object
			mice.render(gc);
		}
		
		//list for Rabbit objects
		for(GameObject rabbits : rabbitList) {
			//for every rabbit in list run code in code block
			rabbit = rabbits;//assign rabbit to rabbit object
            rabbitCollision(rabbits);
			//show and move rabbit object
			rabbits.render(gc);
		}
		
		//list for grass objects
		for(GameObject grasses : grassList) {
			//for every grass in list run code in code block
			grassCollision(grasses);
			//show grass object
			grasses.render(gc);
		}
		//this method removed "eaten" collided objects from list according to collision instructions
	    eat();
	    }
		super.render(gc);
	}
	
	public void entry() {
		//object entrance timing
		//for rain
		if(rainfallSecond ++> 3000) {
		        rainTimer.start();//start rain timer animation timer
				rainfallSecond = 0;//restart timer
		}
		//for rabbit
		if(rabbitSecond ++> 3000) {
			for(int i=0; i<3; i++) {
			rabbitList.add(factory.createProduct("rabbit", rnd.nextInt(800), rnd.nextInt(600), 70, 40));//create rabbit object
			}
			rabbitSecond = 0;//restart timer
		}
		
		//for mouse
		if(mouseSecond ++> 1000) {   
			for(int i=0; i<6; i++) {
				mouseList.add(factory.createProduct("mouse", rnd.nextInt(800), rnd.nextInt(600), 70, 40));//create mouse object
			}
			mouseSecond = 0;//restart timer
	    }
		
		//for goat
		if(goatSecond ++> 4000) {
			for(int i=0; i<2; i++) {
				goatList.add(factory.createProduct("goat", rnd.nextInt(800), rnd.nextInt(600), 90, 80));//create goat object
			}
			goatSecond = 0;//restart timer
		}
		
		//for snake
		if(snakeSecond ++> 4000) {
			for(int i=0; i<2; i++) {
			snakeList.add(factory.createProduct("snake", rnd.nextInt(800), rnd.nextInt(600), 120, 40));//create snake object
			}
			snakeSecond = 0;//restart timer
		}
		
		//for jackal
		if(jackalSecond ++> 2800) {
			jackalList.add(factory.createProduct("jackal", rnd.nextInt(10), rnd.nextInt(600), 120, 90));//create jackal object
			jackalSecond = 0;//restart timer
		}
		
		//for owl
		if(owlSecond ++> 7000) {
			owlList.add(factory.createProduct("owl", rnd.nextInt(800), rnd.nextInt(10), 100, 70));//create owl object
			owlSecond = 0;//restart timer
		}
		
		//for kite
		if(kiteSecond ++> 10000) {
			kiteList.add(factory.createProduct("kite", rnd.nextInt(800), rnd.nextInt(10), 120, 90));//create kite object
			kiteSecond = 0;//restart timer
		}
		
		//for lion
		if(lionSecond++>7000) {
			lionList.add(factory.createProduct("lion", rnd.nextInt(10), rnd.nextInt(600), 170, 120));//create lion object
			lionSecond = 0;
		}
	}
	
	/**
	 * This method deals on life bar changes based on the age of player object
	 * @param gc
	 */
	public void lifeBar(GraphicsContext gc) {
		if(player != null) {
			if(player.getAge() < 1001) {
				life.displayFullLife();
			}
			if(player.getAge() > 1000 && player.getAge() < 2001) {
				life.display4Life();
			}
			if(player.getAge() > 2000 && player.getAge() < 2501) {
				life.display3Life();
			}
			if(player.getAge() > 2500 && player.getAge() < 3001) {
				life.display2Life();
			}
			if(player.getAge() > 3000 && player.getAge() < 3501) {
				life.display1Life();
				drawText("LifeBar Low", 300, 20, 25, Color.RED, gc);//draw text when age is at this point
				if(flashTimer ++> 20) {
					drawText("LifeBar Low", 300, 20, 25, Color.WHITE, gc);//redraw text in different color for flash illusion
					flashTimer = 0;
				}
			}
			if(player.getAge() > 3500 && player.getAge() < 3600) {
				life.displayDeadLife();
				if(flashTimer ++> 20) {
					drawText("LifeBar critically Low", 300, 20, 25, Color.RED, gc);//redraw text with spaced timing to give poping out illusion
					flashTimer = 0;
				}
			}	
		}
		
		if(player.getAge() > 3700) {
			//at this point if player object is removed from list and game over interface is put
			temp = player;
			gameover = true;
			//stop clip and rest timer
			stopClip(clip);
			soundTimer =0;
		}
	}
	
	/**
	 * Any time this method is called if temp is 
	 * not null and the temp 
	 * belongs to any of the array list in this 
	 * method the array list would remove it from the list
	 */
	public void eat() {
		if(temp != null) {
		list.remove(temp);
		lionList.remove(temp);
		goatList.remove(temp);
		grassList.remove(temp);
		mouseList.remove(temp);
		rabbitList.remove(temp);
		jackalList.remove(temp);
		kiteList.remove(temp);
		owlList.remove(temp);
		snakeList.remove(temp);
	}
	}
	
	
	
	/**
	 * 
	 * The next 9 methods have to do with instruction of collisions of the various game objects
	 * this include making temporary locations null, sound, and removal from array list
	 * 
	 * In the next 9 methods when an object is assigned to "temp" variable it would be removed from the list
	 */
	
	
    public void grassCollision(GameObject grasses) {
    	//goat object interaction with grass object
		if(goat != null) {
			if(goat.getR().intersects(grasses.getR().getX(), grasses.getR().getY(), grasses.getR().getWidth(), grasses.getR().getHeight())) {
				temp =grasses;
				goat.setAge(goat.getAge()-500);//reduce age of goat object
			}
		}
		//mouse object interaction with grass object
		if(mouse != null) {
			if(mouse.getR().intersects(grasses.getR().getX(), grasses.getR().getY(), grasses.getR().getWidth(), grasses.getR().getHeight())) {
				temp =grasses;
				mouse.setAge(mouse.getAge()-3000);//reduce age of mouse object
			}
		}
		//rabbit object interaction with grass object
		if(rabbit != null) {
			if(rabbit.getR().intersects(grasses.getR().getX(), grasses.getR().getY(), grasses.getR().getWidth(), grasses.getR().getHeight())) {
				temp =grasses;
				rabbit.setAge(rabbit.getAge()-1000);//reduce age of rabbit object
			}
		}

		//life span control of grass object
		if(grasses.getAge() > 4000) {
			temp = grasses;
		}
	}
    
	public void goatCollision(GameObject goats) {
		//player object interaction with goat object
		if(player != null) {
			if(player.getR().intersects(goats.getR().getX(), goats.getR().getY(), goats.getR().getWidth(), goats.getR().getHeight())) {
				temp = goats;
				goat = null;
				eat.start();
				setScore(getScore()+10);
				player.setAge(player.getAge()-2000);//reduce age of player object
			}	
		}
		
		//lion object interaction with goat object
		if(lion != null) {
			if(lion.getR().intersects(goats.getR().getX(), goats.getR().getY(), goats.getR().getWidth(), goats.getR().getHeight())) {
				temp = goats;
				goat = null;
				lion.setAge(lion.getAge()-500);
			}
		}
		//jackal object interaction with goat object
		if(jackal != null) {
			if(jackal.getR().intersects(goats.getR().getX(), goats.getR().getY(), goats.getR().getWidth(), goats.getR().getHeight())) {
				temp = goats;
				goat = null;
				jackal.setAge(jackal.getAge()-300);
			}
		}
		//kite object interaction with goat object
		if(kite != null) {
			if(kite.getR().intersects(goats.getR().getX(), goats.getR().getY(), goats.getR().getWidth(), goats.getR().getHeight())) {
				temp = goats;
				goat = null;
				kite.setAge(kite.getAge()-50);
			}
		}
//		life span control of goat object
		if(goats.getAge() > 500) {
			temp = goats;
			goat = null;
		}
	}
	
	
	public void jackalCollision(GameObject jackals) {
		//player object interaction with jackal object
		if(player != null) {
		   if(player.getR().intersects(jackals.getR().getX(), jackals.getR().getY(), jackals.getR().getWidth(), jackals.getR().getHeight())) {
				temp = player;
				//game ends and the point and game over procedure initialised
				gameover = true;
				stopClip(clip);
				soundTimer =0;
			}	
		}
		//lion object interaction with jackal object
		if(lion != null) {
		   if(lion.getR().intersects(jackals.getR().getX(), jackals.getR().getY(), jackals.getR().getWidth(), jackals.getR().getHeight())) {
				temp = jackals;
				jackal = null;
				lion.setAge(lion.getAge()-1000);
			}	
		}
		
		//life span control of jackal object
		if(jackals.getAge() > 1000) {
			temp = jackals;
//			System.out.println(jackal.getAge());
			jackal = null;
		}
	}
	
	
	public void kiteCollision(GameObject kites) {
		//player object interaction with kite object
		if(player != null) {
		   if(player.getR().intersects(kites.getR().getX(), kites.getR().getY(), kites.getR().getWidth(), kites.getR().getHeight())) {
				temp = player;
				//game ends and the point and game over procedure initialised
				gameover = true;
				stopClip(clip);
				soundTimer =0;
			}	
		}
		
		//life span control of kite object
		if(kites.getAge() > 400) {
			temp = kites;
			kite = null;
		}
	}
	
	
	public void lionCollision(GameObject lions) {
		//player object interaction with lion object
		if(player != null) {
		   if(player.getR().intersects(lions.getR().getX(), lions.getR().getY(), lions.getR().getWidth(), lions.getR().getHeight())) {
				temp = player;
				//game ends and the point and game over procedure initialised
				gameover = true;
				stopClip(clip);
				soundTimer =0;
			}	
		}
		
		//life span control of lion object
		if(lions.getAge() > 1000) {
			temp = lions;
			lion = null;
		}
	}
	
	
	public void mouseCollision(GameObject mice) {
		//player object interaction with mouse object
		if(player != null) {
		   if(player.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
				temp = mice;
				mouse = null;
				eat.start();
				setScore(getScore()+1);
				player.setAge(player.getAge()-50);//reduce age of player object
			}	
		}
		//lion object interaction with mouse object
		if(lion != null) {
			   if(lion.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
				   double newX = lion.getX()-150;
                    double newY = lion.getY()-150;
                    if(newX > 0) {
                    	mice.setX(newX);
                    }
                    if(newY > 0) {
                    	mice.setY(newY);
                    }
				}	
			}
		//kite object interaction with mouse object
		if(kite != null) {
			   if(kite.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
					temp = mice;
					mouse = null;
				}	
			}
		//snake object interaction with mouse object
		if(snake != null) {
			   if(snake.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
					temp = mice;
					mouse = null;
					snake.setAge(snake.getAge()-10);
				}	
			}
		//jackal object interaction with mouse object
		if(jackal != null) {
			   if(jackal.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
					temp = mice;
					mouse = null;
					jackal.setAge(jackal.getAge()-10);
				}	
			}
		//owl object interaction with mouse object
		if(owl != null) {
			   if(owl.getR().intersects(mice.getR().getX(), mice.getR().getY(), mice.getR().getWidth(), mice.getR().getHeight())) {
					temp = mice;
					mouse = null;
					owl.setAge(owl.getAge()-10);
				}	
			}
		//life span control of mouse object
		if(mice.getAge() > 1000) {
			temp = mice;
			mouse = null;
		}
	}
	
	
	public void owlCollision(GameObject owls) {
		//player object interaction with owl object
		if(player != null) {
		   if(player.getR().intersects(owls.getR().getX(),owls.getR().getY(), owls.getR().getWidth(), owls.getR().getHeight())) {
                double newX = player.getX()-150;
                double newY = player.getY()-150;
                //shift owl object away from player
                if(newX > 0) {
                	owls.setX(newX);
                }
                if(newY > 0) {
                	owls.setY(newY);
                }
			}	
		}
		
		//life span control of owl object
		if(owls.getAge() > 800) {
			temp = owls;
			owl = null;
		}
	}
	
	
	public void rabbitCollision(GameObject rabbits) {
		//player object interaction with rabbit object
		if(player != null) {
		   if(player.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
				temp = rabbits;
				rabbit = null;
				eat.start();
				setScore(getScore()+5);
				player.setAge(player.getAge()-500);//reduce age of player object
			}	
		}
		//lion object interaction with rabbit object
		if(lion != null) {
			   if(lion.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
				   double newX = lion.getX()-150;
                    double newY = lion.getY()+150;
                    if(newX > 0) {
                    	rabbit.setX(newX);
                    }
                    if(newY > 600) {
                    	rabbit.setY(newY);
                    }
				}	
			}
		//kite object interaction with rabbit object
		if(kite != null) {
			   if(kite.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
					temp = rabbits;
					rabbit = null;
					kite.setAge(kite.getAge()-1000);
				}	
			}
		//owl object interaction with rabbit object
		if(owl != null) {
			   if(owl.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
					temp = rabbits;
					rabbit = null;
					owl.setAge(owl.getAge()-500);
				}	
			}
		//jackal object interaction with rabbit object
		if(jackal != null) {
			   if(jackal.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
					temp = rabbits;
					rabbit = null;
					jackal.setAge(jackal.getAge()-300);
				}	
			}
		//snake object interaction with rabbit object
		if(snake != null) {
			   if(snake.getR().intersects(rabbits.getR().getX(), rabbits.getR().getY(), rabbits.getR().getWidth(), rabbits.getR().getHeight())) {
					temp = rabbits;
					rabbit = null;
					snake.setAge(snake.getAge()-300);
				}	
			}
		//life span control of rabbit object
		if(rabbits.getAge() > 500) {
			temp = rabbits;
			rabbit = null;
		}
	}
	
	
	public void snakeCollision(GameObject snakes) {
		//player object interaction with snake object
		if(player != null) {
		   if(player.getR().intersects(snakes.getR().getX(), snakes.getR().getY(), snakes.getR().getWidth(), snakes.getR().getHeight())) {
			   double newX = player.getX()+150;
               double newY = player.getY()+150;
               //shift snake object away from player
               if(newX < 800) {
               	snakes.setX(newX);
               }
               if(newY < 600) {
               	snakes.setY(newY);
               }
			}	
		}
		//lion object interaction with snake object
		if(lion != null) {
			   if(lion.getR().intersects(snakes.getR().getX(), snakes.getR().getY(), snakes.getR().getWidth(), snakes.getR().getHeight())) {
				   double newX = lion.getX()-150;
                    double newY = lion.getY()-150;
                    if(newX > 0) {
                    	snakes.setX(newX);
                    }
                    if(newY > 0) {
                    	snakes.setY(newY);
                    }
				}	
			}
		//jackal object interaction with snake object
		if(jackal != null) {
			   if(jackal.getR().intersects(snakes.getR().getX(), snakes.getR().getY(), snakes.getR().getWidth(), snakes.getR().getHeight())) {
					temp = snakes;
					snake = null;
					jackal.setAge(jackal.getAge()-10);
				}	
			}
		//kite object interaction with snake object
		if(kite != null) {
			   if(kite.getR().intersects(snakes.getR().getX(), snakes.getR().getY(), snakes.getR().getWidth(), snakes.getR().getHeight())) {
					temp = snakes;
					snake = null;
					kite.setAge(kite.getAge()-100);
				}	
			}
		//owl object interaction with snake object
		if(owl != null) {
			   if(owl.getR().intersects(snakes.getR().getX(), snakes.getR().getY(), snakes.getR().getWidth(), snakes.getR().getHeight())) {
					temp = snakes;
					snake = null;
					owl.setAge(owl.getAge()-1000);
				}	
			}
		
		//life span control of snake object
		if(snakes.getAge() > 1000) {
			temp = snakes;
			snake = null;
		}
	}

	
	/**
	 * When game over is true this is the method that runs
	 * The controls the game over text shown and the tips shown 
	 * and goes back to start state after a certain point 
	 * it also controls instructions list when its game over
	 * and plays the game over song
	 * @param gc
	 */
	public void gameover(GraphicsContext gc) {
		if(soundTimer == 0) {
			playBackgroundSong("/uos/foodweb/gameassets/audios/gameover.wav");
			soundTimer++;
		}
		if(tipTimer ++> 8500000) {
			//show tip
			tipTimer = 0;
			tips(gc);
		}
		if(timeout ++> 8500000) {
			//at this point of time out go back to start 
			stopClip(clip);
			soundTimer =0;
			gsm.pushState(factory.createGameState("start"));
		}
		//draw game over and calories point and tip text
		drawText(message, 200, 470, 25, Color.YELLOW, gc);//tip text
		drawText("Calories: "+ getScore(), 250, 170, 40, Color.WHITE, gc);
        drawText("GAMEOVER", 150, 280, 100, Color.YELLOW, gc);
		//draw restart instruction
		drawText("Press", 240, 350, 40, Color.CHOCOLATE, gc); 
		drawText("R", 340, 350, 40, Color.WHITE, gc); 
		drawText("to", 375, 350, 40, Color.CHOCOLATE, gc);
		drawText("RESTART", 420, 350, 40, Color.WHITE, gc);
		//draw quit instruction
		drawText("Press", 240, 410, 40, Color.CHOCOLATE, gc);
		drawText("Q", 340, 410, 40, Color.WHITE, gc);
		drawText("for main menu", 380, 410, 40, Color.CHOCOLATE, gc);
		//flash text
		if(flashTimer ++> 30) {
			drawText("GAMEOVER", 150, 280, 100, Color.WHITE, gc);
			drawText("R", 340, 350, 40, Color.YELLOW, gc); 
			drawText("RESTART", 420, 350, 40, Color.YELLOW, gc);
			drawText("Q", 340, 410, 40, Color.YELLOW, gc);
			flashTimer = 0;
		}
	}
	
	//for random tips at game over
	public void tips(GraphicsContext gc) {
		//make message any random tip in array 
		for(int i = 0; i < tips.size(); i++) {
			int index = random_tip.nextInt(tips.size());
			message = tips.get(index);
		}
	}
	
	//method to add tips to tip list
	public void addTips() {
		tips.add("Plant when it rains you can only plant then");
		tips.add("Goats give higher calories.");
		tips.add("Do well to avoid the edges haha.");
		tips.add("Pay attention to your Life bar.");
		tips.add("Plant more to get more Animals.");
		tips.add("Push snakes into other predators so they dont eat your prey.");
		tips.add("Oof watch out for Jackals");
		tips.add("Oof watch out for Kites");
		tips.add("Oof watch out for Lions");
	}


}
