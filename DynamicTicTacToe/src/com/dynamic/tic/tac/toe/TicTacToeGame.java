package com.dynamic.tic.tac.toe;

import java.util.Scanner;

public class TicTacToeGame {
	
	private static Scanner sc;
    private static int row, row_p;
    private static int col, col_p;
    private static char[][] board;
    private static String player1, player2;
    private static boolean player1Turn = true;
    // default board size
    private static final int BOARD_SIZE = 3;

    public static void main(String[] args) {
    	sc = new Scanner(System.in);

    	System.out.println("Enter number of rows and columns for the board :\n");
    	row = sc.nextInt();
    	col = sc.nextInt();

    	// Input validations
    	inputValidations();

    	// Initialize board
    	initializeBoard();

    	// Print initial board
    	printBoard(row);

    	// Ask for Player Names
    	getPlayerNames();
    	
    	System.out.println("Lets start the Game");
    	while (true) {
    	    playGame();

    	    // Insert the value
    	    if (getPlayingPlayerName().equals(player1)) {
    		board[row_p][col_p] = 'X';
    	    } else {
    		board[row_p][col_p] = 'O';
    	    }

    	    // Print the board
    	    printBoard(row);

    	    // Switch the players
    	    player1Turn = !player1Turn;

    	    // Check if game is over
    	    // Check for Player1
    	    if (IsGameWon('X')) {
    		System.out.println("Player 1 " + player1 + " has won the Game!!");
    		System.out.println("Congratulation " + player1 + " !!!!");
    		break;
    	    }
    	    // Check for Player2
    	    if (IsGameWon('O')) {
    		System.out.println("Player 2 " + player2 + " has won the Game!!");
    		System.out.println("Congratulation " + player1 + " !!!!");
    		break;
    	    }

    	    // Check if the game is tie
    	    if (boardIsFull()) {
    		System.out.println("Game is over and its a tie!!!");
    		break;
    	    }
    	}

        }

        public static void inputValidations() {

    	if (row != col) {
    	    System.out.println("Row and Column numbers should be same. Try adding same row and coulmn number");
    	    return;
    	}

    	if (row < BOARD_SIZE || col < BOARD_SIZE) {
    	    System.out.println("Row and Column numbers should be 3 or greater!");
    	    return;
    	}

    	if (row % 2 == 0 || col % 2 == 0) {
    	    System.out.println("Usually for tic tac toe game we prefer odd number as row and column. Try again!");
    	    return;
    	}
        }
        
        public static void initializeBoard() {
    	// Initialize board and put values as ' '
    	board = new char[row][col];
    	for (int i = 0; i < row; i++) {
    	    for (int j = 0; j < col; j++) {
    		board[i][j] = ' ';
    	    }
    	}
        }
        
        public static void getPlayerNames() {
    	System.out.println("Enter Player 1 Name :");
    	player1 = sc.next();
    	System.out.println("Enter Player 2 Name :");
    	player2 = sc.next();
        }

        public static void playGame() {
    	while (true) {
    	    System.out.println("Enter row and column number for Player : " + getPlayingPlayerName());
    	    row_p = sc.nextInt();
    	    col_p = sc.nextInt();

    	    row_p--;
    	    col_p--;

    	    // Check if the entered row col is valid
    	    if (row_p < 0 || row_p > row - 1 || col_p < 0 || col_p > col - 1) {
    		System.out.println("You have Entered wrong row or col number, \n" + " Enter row number between 1 to "
    			+ row + " and column between 1 to " + col + " Try Again!");
    	    }
    	    // Check if the position is already filled or not
    	    else if (board[row_p][col_p] == 'X' || board[row_p][col_p] == 'O') {
    		System.out.println(
    			"Someone has alreay made move at this position try with different row and column number");
    	    } else {
    		// Player has entered valid row and col number.
    		break;
    	    }

    	}
        }

        public static boolean IsGameWon(char player) {
    	boolean result_main = false;

    	result_main = checkAllRows(player);
    	if (result_main) {
    	    return result_main;
    	}

    	result_main = checkAllColumns(player);
    	if (result_main) {
    	    return result_main;
    	}

    	// check for diagonals
    	result_main = diagonalDone(player);
    	return result_main;
        }

        public static boolean checkAllRows(char player) {
    	boolean result_main = false;
    	// check all rows
    	for (int i = 0; i < row; i++) {
    	    boolean result = rowDone(i, player);
    	    if (result == true) {
    		result_main = true;
    		break;
    	    }
    	}
    	return result_main;
        }

        public static boolean rowDone(int row_here, char player) {
    	boolean result = true;
    	for (int i = 0; i < col; i++) {
    	    if (board[row_here][i] != player) {
    		result = false;
    		break;
    	    }
    	}
    	return result;

        }

        public static boolean checkAllColumns(char player) {
    	boolean result_main = false;
    	// check all columns
    	for (int i = 0; i < col; i++) {
    	    boolean result = colDone(i, player);
    	    if (result == true) {
    		result_main = true;
    		break;
    	    }
    	}
    	return result_main;
        }

        public static boolean colDone(int col_here, char player) {
    	boolean result = true;
    	for (int i = 0; i < row; i++) {
    	    if (board[i][col_here] != player) {
    		result = false;
    		break;
    	    }
    	}
    	return result;
        }

        public static boolean diagonalDone(char player) {
    	boolean result = false;
    	result = diagonalDonePrimary(player);
    	if (result) {
    	    return result;
    	}
    	result = diagonalDoneSecondary(player);
    	return result;
        }

        public static boolean diagonalDonePrimary(char player) {
    	boolean result = true;
    	// Primary diagonal check
    	for (int i = 0; i < row; i++) {
    	    for (int j = 0; j < col; j++) {
    		if (i == j) {
    		    if (board[i][j] != player) {
    			result = false;
    			break;
    		    }
    		}
    	    }
    	}
    	return result;
        }

        public static boolean diagonalDoneSecondary(char player) {
    	boolean result = true;
    	// Secondary diagonal check
    	for (int i = 0; i < row; i++) {
    	    for (int j = 0; j < col; j++) {
    		// Condition for secondary diagonal
    		if ((i + j) == (row - 1)) {
    		    if (board[i][j] != player) {
    			result = false;
    			break;
    		    }
    		}
    	    }
    	}
    	return result;
        }

        public static boolean boardIsFull() {
    	for (int i = 0; i < row; i++) {
    	    for (int j = 0; j < col; j++) {
    		if (board[i][j] == ' ') {
    		    return false;
    		}
    	    }
    	}
    	return true;
        }

        public static String getPlayingPlayerName() {
    	if (player1Turn) {
    	    return player1;
    	} else {
    	    return player2;
    	}
        }

        public static void printBoard(int row) {
    	System.out.println("Board :");

    	for (int i = 0; i < row; i++) {
    	    if (i == 0) {
    		System.out.println(" ");
    	    } else {
    		if (row == BOARD_SIZE) {
    		    System.out.println("---|---|---");
    		} else if (row == 5) {
    		    System.out.println("---|---|---|---|---");
    		} else if (row == 7) {
    		    System.out.println("---|---|---|---|---|---|---");
    		} else {
    		    System.out.println("---|---|---");
    		}

    	    }

    	    for (int j = 0; j < col; j++) {
    		if (j == col - 1) {
    		    System.out.print(" " + board[i][j] + " ");
    		} else {
    		    System.out.print(" " + board[i][j] + " |");
    		}
    	    }
    	    System.out.println("");
    	}

        }

}

