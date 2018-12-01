/**
 * 
 */
package versus.view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import versus.controller.CharacterController;
import versus.model.PlayerModel;



/**
 * @author Aires David, Quentin Lebrun
 *
 */
public class GameViewConsole extends GameView {
	
	protected Scanner sc;
	private  String [][] board= new String[11][11];
	
	private String reponse="Bienvenue";
	
// for example 9,6 IS EQUAL TO 10,7 in X,Y  
	private int [][]trap={{9,10},{6,5}};
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println(player);
		initBoard();
		
	}
	
	
	public void affiche(String string) {
		System.out.println(string);
	}
		
	public GameViewConsole(PlayerModel player,CharacterController controller) {
		
		super(player,controller);
		update(null,null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}
 
	
	private  void initBoard() {
		
		for(int j=0;j<board.length;j++) {
			for(int i=0;i<board.length;i++) {
				board[j][i]= "[   ]";
			}
		}
		board[player.getLY()][player.getLX()]="[ + ]";
		board[player.getRY()][player.getRX()]="[ + ]";
		printHelp();
		
	
	}
	
	private void isTrapped(){
		
			for(int i=0;i<2;i++) {
				if(player.getLX()==trap[0][i] && player.getLY()==trap[1][i]){
					controller.mouvementLocal(0,5);
					System.out.println("Vous �tes tomb�s dans un pi�ge(!Retenez son emplacement!)");

				}
			}
	}
// it's won when the player arrives a the last y which is 11 (10 in the array)
	private void isWon(){
		if(player.getLX()==10){
			System.out.println("Vous avez gagn�");
		}
	}
	
	private void printHelp(){
		printBoard();
		affiche("Pour se t�l�porter : tp + coordX + coordY");
		affiche("Pour se d�placer : ");
		affiche("H : haut");
		affiche("B: bas");
		affiche("A : avant");
		affiche("R: arri�re");
		
	}
	
	
	
	 public  void printBoard() {
		 
		 	        
		 
		 	        
		 			System.out.println();
		 	        for (int i = 0; i < board.length; i++) {
		 
		 	           
		 	        	System.out.println();
		 	            for (int j = 0; j < board.length; j++) {
		 
		 	                System.out.print(board[i][j] + " ");
		 
		 	            }
		 
		 	            System.out.println();
		 	            
		 
		 	           
		 
		 	        }
		 
		 	    }
	 
	 private class ReadInput implements Runnable{
			public void run() {
				while(true){
					try{
						
						String c = sc.next();
						if(c.length()<1){
							affiche("Format d'input incorrect");
							printHelp();
						}
						
						
						
						
						
						//console's commands to move the players
						switch(c){
							case "tp" :
								int i = sc.nextInt();
								int a= sc.nextInt();
								if(i<0 || i> 11 || a<0 || a>11){
									affiche("Emplacement incorrect");
									printHelp(); 
									break;
								}else {
									
								reponse= controller.mouvementLocal(i, a);;
								break;
								}
							
							case "A" : 
								reponse= controller.mouvementLocal((player.getLX()+1<board.length?player.getLX()+1:player.getLX()),player.getLY());
								isTrapped();
								isWon();
								break;
							case "R" : 
								reponse= controller.mouvementLocal((player.getLX()-1<0?player.getLX():player.getLX()-1),player.getLY());
								isTrapped();

								break;
								
							case "B" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()+1>board.length-1?player.getLY():player.getLY()+1));
								isTrapped();

								break;
								
							case "H" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()-1<0?player.getLY():player.getLY()-1));
								isTrapped();

								break;
							default : 
								affiche("Op�ration incorrecte");
								printHelp();
						}
						affiche(reponse);
					}
					catch(InputMismatchException e){
						affiche("Format d'input incorrect");
						printHelp();
					}
				}
			}
		}

	
	
}

