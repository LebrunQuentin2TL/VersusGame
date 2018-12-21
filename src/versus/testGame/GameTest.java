/**
 * (?).
 */
package versus.testGame;

/**
 * This imported class is used to provides simple ways to create modal elemental dialogs.
 */
import javax.swing.JOptionPane;

import versus.controller.CharacterController;
import versus.controller.NetworkController;
import versus.model.PlayerModel;
import versus.view.GameViewConsole;
import versus.view.GameViewMap;

/**
 * This class represents the Game Test.
 * @author Aires David, Quentin Lebrun
 */
public class GameTest {
	/**
	 * (?)
	 */
	static boolean isServer;

	/**
	 * This method (?).
	 * @return (?)
	 */
	public boolean openSession(){
		JOptionPane jop = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Serveur(1) ou client(2) ?", "Ouverture de session", JOptionPane.QUESTION_MESSAGE);
	    
	    //serveur
	    if(Integer.parseInt(nom)==1){
	    	return true;
	    }
	    //client
	    else if(Integer.parseInt(nom)==2){
		return false;
	    }
		return true;    
	}
	
	/**
	 * This constructor creates the Game Test.
	 */
	public GameTest() {
		//Creation of model 
		PlayerModel player = new PlayerModel();
		//Creation of controllers : one for each view	
		//Each controller must have a reference to the model in order to be able to order it
		NetworkController GameControlNetwork= new NetworkController(player,openSession()) ;
		CharacterController GamecontrolConsole = new CharacterController(player, GameControlNetwork);
		CharacterController GamecontrolMap = new CharacterController(player, GameControlNetwork);
		//Creation of views
		//Each view must know its controller and have a reference to the model to be able to observe it
		GameViewMap map = new GameViewMap(player, GamecontrolMap,GameControlNetwork);
		GameViewConsole console = new GameViewConsole(player, GamecontrolConsole,GameControlNetwork);
		//The reference to the view is given for each controller
		GamecontrolMap.addview(map);
		GamecontrolConsole.addview(console);
	}
	
	/**
	 * @param args represente the arguments passed to the main method
	 */
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//Creating and showing this application's GUI.
		//isServer = (args[0].equals("true")? true : false);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameTest();
			}
		});
	}
}