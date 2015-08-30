package battleship;

import java.util.Random;

public class BattleshipGame {
	
	/**
	 * (Game Battleship)
	 * 
	 * GOAL: Sink all of the computer's ships in the fewest number of guesses. 
	 * SETUP: When the game program is launched, the computer places three ships 
	 * (Aircraft Carrier(length 4 cells), Destroyer(length 3 cells), Submarine(length 2 cells))
	 * on a virtual 10x10 grid (NOTE: ships can't overlap each other). When that's complete, 
	 * the game asks for your first guess.
	 * HOW TO PLAY: The computer will prompt you to enter a guess (a cell), 
	 * that you'll type in a console or command-line as "0 3", "1 5"(first number represents row
	 * while second number represents column of the grid), etc. 
	 * In response to your guess, you'll see either "Hit", "Miss", or "You sunk the ship".
	 * When you sink all ships, game is over, and displays your rating.
	 * 
	 * @author Bojan Aleksic
	 */
	
	/** Declare counters for guess, miss and hit */
	public static int guessCounter = 0;
	public static int missCounter = 0;
	public static int hitCounter = 0;
	
	/** Declare counters for carrier, destroyer and submarine */
	public static int hitCarrier = 0;
	public static int hitDestroyer = 0;
	public static int hitSubmarine = 0;
	
	/** Boolean data fields for ships */
	private static boolean carrierSunk = false;
	private static boolean destroyerSunk = false;
	private static boolean submarineSunk = false;
	
////////////////////////////////////////////////////////////////////////////	
	
	/** Empty constructor */
	public BattleshipGame() {
		
	}
	
////////////////////////////////////////////////////////////////////////////	
	
	/** 
	 * Boolean methods that returns true or false depending on the
	 * current status of the ships, whether ship is destroyed or not
	 */
	
	public boolean isCarrierSunk() {
		return carrierSunk;
	}
	
	public boolean isDestroyerSunk() {
		return destroyerSunk;
	}
	
	public boolean isSubmarineSunk() {
		return submarineSunk;
	}
	
////////////////////////////////////////////////////////////////////////////	
	
	/** Create a board grid */
	public static void battleshipGrid(char[][] board) {
		
		System.out.print("   ");
		for (int i = 0; i < board.length; i++ ){
			System.out.printf("%2d  ", i);  
		}
		
		System.out.println();
		
		for (int j = 0; j < board.length; j++) {
			System.out.print("  +");
			for(int i = 0; i < board.length; i++) {
				 System.out.print("---+");
			}
			System.out.println();
			System.out.print(j + " |");
			
			for(int i = 0; i < board.length; i++) {
				System.out.print(" " + board[i][j]+ " |");
			}
			System.out.println();
		}
		System.out.println("  +---+---+---+---+---+---+---+---+---+---+");
	}
	
////////////////////////////////////////////////////////////////////////////
	
	public static void deployShips(int[][] ships, int[] carrier, int[] destroyer, int[] submarine) {
		
		/** Create Random object */
		Random rand = new Random();
		
		/** Obtain random numbers for row and column positions of the Carrier, in range 0 - 6 */
		int randRowCar = rand.nextInt(6) + 4; // random for row
		int randColCar = rand.nextInt(6) + 4; // random for column
		
		/** Obtain random numbers for row and column positions of the Destroyer, in range 0 - 7 */
		int randRowDes = rand.nextInt(7) + 3; // random for row
		int randColDes = rand.nextInt(7) + 3; // random for column
		
		/** Obtain random numbers for row and column positions of the Submarine, in range 0 - 8 */
		int randRowSub = rand.nextInt(8) + 2; // random for row
		int randColSub = rand.nextInt(8) + 2; // random for column
		
		/** 0 represents horizontal, 1 represents vertical */
		int xyCarrier = rand.nextInt(2); // Carrier
		int xyDestroyer = rand.nextInt(2); // Destroyer
		int xySubmarine = rand.nextInt(2); // Submarine
		
		////////////////// Carrier ///////////////////////
		
		/** Deploy Aircraft Carrier horizontally */
		if(xyCarrier == 0) {
			ships[randRowCar][randColCar] = carrier[0];
			ships[randRowCar][randColCar - 1] = carrier[0];
			ships[randRowCar][randColCar - 2] = carrier[0];
			ships[randRowCar][randColCar - 3] = carrier[0];
		/** Deploy Aircraft Carrier vertically */	
		} else if(xyCarrier == 1) {
			ships[randRowCar][randColCar] = carrier[0];
			ships[randRowCar - 1][randColCar] = carrier[0];
			ships[randRowCar - 2][randColCar] = carrier[0];
			ships[randRowCar - 3][randColCar] = carrier[0];
		}
		
		/////////////////// Destroyer /////////////////////
		
			/** Deploy Destroyer horizontally */
		if(xyDestroyer == 0) {
			/** Check if two ships overlapping each other */
			while(ships[randRowDes][randColDes] != 0 || ships[randRowDes][randColDes - 1] != 0 || ships[randRowDes][randColDes - 2] != 0) {
			//	System.out.println("Overlaps at position: " + randRowDes + " " + randColDes);
				/** 
				 * If two ships overlapping each other, generate again row and column integers until 
				 * their positions get separated 
				 */
				randRowDes = rand.nextInt(7) + 3;
				randColDes = rand.nextInt(7) + 3;
			} 
			
			/** Positions are now fine, and we can place the ship horizontally */
			ships[randRowDes][randColDes] = destroyer[0];
			ships[randRowDes][randColDes - 1] = destroyer[0];
			ships[randRowDes][randColDes - 2] = destroyer[0];
			
		/** Deploy Destroyer vertically */	
		} else if(xyDestroyer == 1){
			/** Check if two ships overlapping each other */
			while(ships[randRowDes][randColDes] != 0 || ships[randRowDes - 1][randColDes] != 0 || ships[randRowDes - 2][randColDes] != 0) {
			//	System.out.println("Overlaps at position: " + randRowDes + " " + randColDes);
				/** 
				 * If two ships overlapping each other, generate again row and column integers until 
				 * their positions get separated 
				 */
				randRowDes = rand.nextInt(7) + 3;
				randColDes = rand.nextInt(7) + 3;
			}
			
			/** Positions are now fine, and we can place the ship vertically */
			ships[randRowDes][randColDes] = destroyer[0];
			ships[randRowDes - 1][randColDes] = destroyer[0];
			ships[randRowDes - 2][randColDes] = destroyer[0];
		}
		
		
        /////////////////// Submarine /////////////////////
			
		/** Deploy Submarine horizontally */
		if(xySubmarine == 0) {
			/** Check if two ships overlapping each other */
			while(ships[randRowSub][randColSub] != 0 || ships[randRowSub][randColSub - 1] != 0) {
			//	System.out.println("Overlaps at position: " + randRowSub + " " + randColSub);
				/** 
				 * If two ships overlapping each other, generate again row and column integers until 
				 * their positions get separated 
				 */
				randRowSub = rand.nextInt(8) + 2;
				randColSub = rand.nextInt(8) + 2;
			} 
			
			/** Positions are now fine, and we can place the ship horizontally */
			ships[randRowSub][randColSub] = submarine[0];
			ships[randRowSub][randColSub - 1] = submarine[0];
				
		/** Deploy Submarine vertically */	
		} else if(xySubmarine == 1){
			/** Check if two ships overlapping each other */
			while(ships[randRowSub][randColSub] != 0 || ships[randRowSub - 1][randColSub] != 0) {
			//	System.out.println("Overlaps at position: " + randRowSub + " " + randColSub);
				/** 
				 * If two ships overlapping each other, generate again row and column integers until 
				 * their positions get separated 
				 */
				randRowSub = rand.nextInt(8) + 2;
				randColSub = rand.nextInt(8) + 2;
			}
			
			/** Positions are now fine, and we can place the ship vertically */
			ships[randRowSub][randColSub] = submarine[0];
			ships[randRowSub - 1][randColSub] = submarine[0];
		}

		/** Display actual locations of the Carrier, Destroyer and Submarine */
		/**	
		for(int i = 0; i < ships.length; i++) {
			for(int j = 0; j < ships[i].length; j++) {
				System.out.print(ships[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
	}

///////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void checkHit(char[][] board, int[][] ships, int row, int column) {
		
		/** Create boolean "hit" and set it initially to false */
		boolean hit = false;
		
		/** Check if ship is got hit */
		for(int i = 0; i < ships.length;) {
			if(ships[row][column] == 1 || ships[row][column] == 2 || ships[row][column] == 3) {
				board[column][row] = '*'; // display asterisk as hit sign
				hit = true; // if ship got hit, set hit to true
				break;
			} else {
				board[column][row] = 'X'; // display "X" as miss sign
				hit = false; // otherwise set it to false
				break;
			}
		}
		/** If ship is hit, display "Hit" message and increment hit counter */
		if(hit) {
			System.out.println("\n  ----------------- Hit! -----------------");
			System.out.println();
			if(ships[row][column] == 1) {
				hitCarrier++;
			} else if(ships[row][column] == 2) {
				hitDestroyer++;
			} else if(ships[row][column] == 3) {
				hitSubmarine++;
			}
		/** Ship is not hit, display "Miss" message */	
		} else {
			System.out.println("\n  ---------------- Miss! -----------------");
			System.out.println();
		}
		/** If Carrier's counter has reached number 4, the ship has been destroyed */
		if(hit == true && hitCarrier == 4) {
			System.out.println("-------------------------------------------");
			System.out.println("       You sunk the Aircraft Carrier!");
			System.out.println("-------------------------------------------");
			carrierSunk = true;
			hitCarrier = 0; // Reset counter to zero to prevent this message from repeating
		/** If Destroyer's counter has reached number 3, the ship has been destroyed */	
		} else if(hit == true && hitDestroyer == 3) {
			System.out.println("-------------------------------------------");
			System.out.println("           You sunk Destroyer!");
			System.out.println("-------------------------------------------");
			destroyerSunk = true;
			hitDestroyer = 0; // Reset counter to zero to prevent this message from repeating
		/** If Submarine's counter has reached number 2, the ship has been destroyed */	
		} else if(hit == true && hitSubmarine == 2) {
			System.out.println("-------------------------------------------");
			System.out.println("         You sunk the Submarine!");
			System.out.println("-------------------------------------------");
			submarineSunk = true;
			hitSubmarine = 0; // Reset counter to zero to prevent this message from repeating
		}
		
	}
	
}
