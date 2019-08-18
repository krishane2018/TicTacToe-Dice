package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Krishan Easparan
 *
 */

public class TicTacToe {
	private int nRows; // declaration of instance variables
	private int nColumns;
	private int numToWin;
	private char[][] grid;
	private char turn;
	private TicTacToeEnum gameState;
	private int nMarks;

	/**
	 * Constructs a new TicTacToe object with a 3 by 3 grid, sets 3 to be the number
	 * in a row needed to win and sets which player goes first.
	 * 
	 * @param initialTurn Character representing which player goes first.
	 */

	TicTacToe(char initialTurn) {
		this(3, 3, 3, initialTurn); // uses 4 parameter constructor to construct TicTacToe object
	} 								

	/**
	 * Constructs a new TicTacToe object with a specified 2D sized grid, sets a
	 * specified number to be the number in a row needed to win and sets who goes
	 * first.
	 * 
	 * @param nRows       Integer representing the number of rows of the grid.
	 * @param nColumns    Integer representing the number of columns of the grid.
	 * @param numToWin    Integer representing the number of characters in a row
	 *                    needed to win.
	 * @param initialTurn Character representing which player goes first.
	 */

	TicTacToe(int nRows, int nColumns, int numToWin, char initialTurn) {
		//ensures no invalid input is entered
		if (nRows < 1 || nColumns < 1 || numToWin < 1 || numToWin > nRows || numToWin > nColumns) 
			throw new IllegalArgumentException("Invalid input.");

		this.nRows = nRows;
		this.nColumns = nColumns;
		this.numToWin = numToWin;
		grid = new char[nRows][nColumns];
		reset(initialTurn);
	}

	/**
	 * Resets the tic-tac-toe game.
	 * 
	 * @param initialTurn Character representing which player goes first.
	 */
	public void reset(char initialTurn) {
		for (int i = 0; i < grid.length; i++) {
			Arrays.fill(grid[i], ' '); // fills grid with spaces
		}
		nMarks = 0;
		turn = initialTurn;
		gameState = TicTacToeEnum.IN_PROGRESS;
	}

	/**
	 * Takes in the player character and returns a game state of won.
	 * 
	 * @param player Character representing the player that has taken their turn.
	 * @return TicTacToeEnum representing the game state.
	 */

	public TicTacToeEnum charToEnum(char player) {
		if (player == 'X') {
			return TicTacToeEnum.X_WON;
		}
		if (player == 'O') {
			return TicTacToeEnum.O_WON;
		} 
		else {
			return TicTacToeEnum.IN_PROGRESS;
		}

	}

	/**
	 * Returns a string representation of the tic-tac-toe grid.
	 * 
	 * @return String containing the tic-tac-toe grid.
	 */

	public String toString() {
		String output = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) { 	//iterates through grid and 
				output += (grid[i][j] + " | ");			//outputs string representation of it.
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * Helper method, determines if there is a winner or draw and returns the game
	 * state of the tic-tac-toe game.
	 * 
	 * @return TicTacToeEnum containing the gameState of the game after determining
	 *         whether the game has ended or not.
	 */

	private TicTacToeEnum findWinner() {
		String row = "";
		String column = "";
		nMarks++;
		char[] winnerArr = new char[numToWin]; 	// Uses numToWin and turn to determine what and
		Arrays.fill(winnerArr, turn); 			// how many characters are needed to be in a row
		String winnerString = new String(winnerArr); //and converts it into a String.

		
		// iterates through rows of grid and converts row char[]
		// to String and determines if row String contains winning String.
		for (int i = 0; i < grid.length; i++) { 
			row = new String(grid[i]); 
			if (row.contains(winnerString)) { 
				return charToEnum(turn);
			}
		}
		
		// iterates through columns of grid and converts column char[]
		// to String and determines if column String contains winning String.
		
		for (int i = 0; i < grid[0].length; i++) { 
			column = new String(columnArray(i, grid.length, grid)); 
																	
			if (column.contains(winnerString)) { 
				return charToEnum(turn);
			}
		}
		if (nMarks == nRows * nColumns) {
			return TicTacToeEnum.DRAW; // if the grid is full without a winner, the gamestate is set
		} 							   // to DRAW.
		return TicTacToeEnum.IN_PROGRESS;

	}

	/**
	 * Returns a column of a 2D array as a 1D array.
	 * 
	 * @param column      Integer representing the column of the 2D array that will
	 *                    be returned.
	 * @param arrayLength Integer representing the length of the column
	 * @param array       The 2D Integer array which is being used.
	 * @return int[] representing the specified column of the 2D grid.
	 */

	private char[] columnArray(int column, int arrayLength, char[][] array) {
		char[] myColumnArray = new char[arrayLength]; // returns a column array from a 2D grid.
		for (int i = 0; i < arrayLength; i++) {
			myColumnArray[i] = array[i][column];
		}
		return myColumnArray;
	}

	/**
	 * Takes the turn of the current player and places the player's character in the
	 * specified position. Returns the game state.
	 * 
	 * @param row    Integer representing the row at which to place character.
	 * @param column Integer representing the column at which to place character.
	 * @return TicTacToeEnum containing the game state.
	 */
	public TicTacToeEnum takeTurn(int row, int column) {

		if (row < 0 || row >= nRows || column < 0 || column >= nColumns) {
			System.out.println("Invalid input."); // determines if valid inputs are used.
			return gameState;
		}

		if (grid[row][column] != ' ') { // determines if spot on grid is already filled.
			System.out.println("Spot has already been filled"); 
			return gameState; 
		}

		grid[row][column] = turn;

		gameState = findWinner();

		if (turn == 'O') {
			turn = 'X';
		} 
		else {
			turn = 'O';
		}
		return gameState;
	}

	/**
	 * Returns which player's turn it is.
	 * 
	 * @return Character representing the current player's turn.
	 */

	public char getTurn() {
		return turn;
	}

	/**
	 * Returns the game state..
	 * 
	 * @return TicTacToeEnum representing the gameState.
	 */

	public TicTacToeEnum getGameState() {
		return gameState;
	}

	/**
	 * Executes a game of Tic-Tac-Toe using the TicTacToe class.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe('X');
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println(game.toString()); // prints out grid
			System.out.println(game.getTurn() + ": Where do you want to mark? Enter row column");
			int row = scanner.nextInt();
			int column = scanner.nextInt();
			scanner.nextLine(); // takes input
			game.takeTurn(row, column); // takes turn

			// code runs while gameState is IN-PROGRESS.
		} while (game.getGameState() == TicTacToeEnum.IN_PROGRESS); 
		System.out.println(game.getGameState());

	}

}
