package battleship;

import java.util.Scanner;

public class BattleshipGameMain {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/** Create 10-by-10 char array board */
		char[][] board = new char[10][10];
		
		/** Create 10-by-10 int matrix for ship deployment */
		int[][] ships = new int[10][10];
		
		/** Initialize ships with values: 1(carrier), 2(destroyer), 3(submarine) */
		int[] carrier = {1};
		int[] destroyer = {2};
		int[] submarine = {3};
		
		/** Instantiate battleship object */
		BattleshipGame battleship = new BattleshipGame();
		
		/** Display grid */
		BattleshipGame.battleshipGrid(board);
		
		/** Invoke method to deploy ships randomly */
		BattleshipGame.deployShips(ships, carrier, destroyer, submarine);
		
		/** Declare boolean for game over */
		boolean gameOver = false;
		
		while(!gameOver) {
			/** Prompt the user to enter a guess for ship location */
			System.out.println("\nEnter row and column to guess location of the ship: ");
			int row = input.nextInt();
			int column = input.nextInt();
			
			/** Check if cell is already guessed */
			for(int i = 0; i < board.length; i++) {
				/** Keep looping until guessed cell is empty */
				while(board[column][row] == 'X' || board[column][row] == '*') {
					System.out.println("You already guessed cell at row: " + row + " column: " + column);
					System.out.println("\nTry another location of the grid: ");
					row = input.nextInt();
					column = input.nextInt();
				}
			}
			
			/** Invoke method that checks if ship is got hit */
			BattleshipGame.checkHit(board, ships, row, column);
			
			/** Check if all three ships has been destroyed, if so, game is over and exit the program */
			if(battleship.isCarrierSunk() && battleship.isDestroyerSunk() && battleship.isSubmarineSunk()) {
				System.out.println("Game is over! You won!");
				System.exit(0);
			}
			
			/** Display grid */
			BattleshipGame.battleshipGrid(board);
		}
		
		input.close();
	}

}
