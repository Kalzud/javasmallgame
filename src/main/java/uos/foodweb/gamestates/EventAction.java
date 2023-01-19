package uos.foodweb.gamestates;

import javafx.scene.input.KeyEvent;
/**
 * 
 * @author Emmanuel Uduma
 * This interface would help with controlling 
 * what happens when keyboard keys are pressed
 */
public interface EventAction {
  //when user presses key
	public void keyPressed(KeyEvent event);
}
