package com.ssafy.swea.day03;

import java.util.Scanner;

/**
 * 상호의 배틀필드
 * @author devTaemin
 *
 */
public class D3_1873 {
	/** Number of test case, Height, Width, Number of command*/
	public static int T, H, W, N;
	
	/** Location of tank */
	public static int Row, Col;
	
	/** Direction of tank [North(0), East(1), South(2), West(3)]*/
	public static int Direction;
	
	/** State of the field */
	public static char[][] Map;
	
	/** List of command */
	public static char[] Command;
	
	/** Direction (North, East, South, West) */
	public static int[] Dx = { -1, 0, 1, 0 };
	public static int[] Dy = { 0, 1, 0, -1 };
	
	/**
	 * Check if the tank is found from the map
	 *<pre>
	 *	If the tank is found, Store the location of tank with the variable 'Row', 'Col'
	 *  In addition, Store the direction of the tank according to the given mark
	 *</pre>
	 * @param row
	 * @param col
	 */
	public static void findTank(int row, int col) {
		char mark = Map[row][col];
		if (mark == '^') {
			Direction = 0;
			Row = row;
			Col = col;
		} else if (mark == '>') {
			Direction = 1;
			Row = row;
			Col = col;
		} else if (mark == 'v') {
			Direction = 2;
			Row = row;
			Col = col;
		} else if (mark == '<') {
			Direction = 3;
			Row = row;
			Col = col;
		}
	}
	
	/**
	 * Check if the given index is valid or not
	 * @param row
	 * @param col
	 * @return If valid return true, otherwise return false
	 */
	public static boolean checkRange(int row, int col) {
		return (row >= 1 && row <= H && col >= 1 && col <= W);
	}
	
	/**
	 * Play Game
	 */
	public static void playGame() {
		/** Play */
		for (int i = 0; i < N; i++) {
			
//			// DEBUG
//			for (int r = 1; r <= H; r++) {
//				for (int c = 1; c <= W; c++) {
//					System.out.printf("%c", Map[r][c]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			/** Check the command */
			char cmd = Command[i];
			int checkRow = Row;
			int checkCol = Col;
			
			switch (cmd) {
			case 'U':
				/** Change the state of direction according to the command */
				Direction = 0;
				/** Change the state of the tank on the map */
				Map[Row][Col] = '^';
				/** Check the location that the tank tries to move */
				checkRow = Row + Dx[Direction];
				checkCol = Col + Dy[Direction];
				
				
				// Validate Range
				if (!checkRange(checkRow, checkCol)) {
					continue;
				}
				
				// Move
				if (Map[checkRow][checkCol] == '.') {
					Map[Row][Col] = '.';
					Row = checkRow;
					Col = checkCol;
					Map[Row][Col] = '^';
				}

				break;
				
			case 'R':
				/** Change the state of direction according to the command */
				Direction = 1;
				/** Change the state of the tank on the map */
				Map[Row][Col] = '>';
				/** Check the location that the tank tries to move */
				checkRow = Row + Dx[Direction];
				checkCol = Col + Dy[Direction];
				
				// Validate Range
				if (!checkRange(checkRow, checkCol)) {
					continue;
				}
				
				// Move
				if (Map[checkRow][checkCol] == '.') {
					Map[Row][Col] = '.';
					Row = checkRow;
					Col = checkCol;
					Map[Row][Col] = '>';
				}
				
				break;
				
			case 'D':
				/** Change the state of direction according to the command */
				Direction = 2;
				/** Change the state of the tank on the map */
				Map[Row][Col] = 'v';
				/** Check the location that the tank tries to move */
				checkRow = Row + Dx[Direction];
				checkCol = Col + Dy[Direction];
				
				// Validate Range
				if (!checkRange(checkRow, checkCol)) {
					continue;
				}
				
				// Move
				if (Map[checkRow][checkCol] == '.') {
					Map[Row][Col] = '.';
					Row = checkRow;
					Col = checkCol;
					Map[Row][Col] = 'v';
				}
				
				break;
				
			case 'L':
				/** Change the state of direction according to the command */
				Direction = 3;
				/** Change the state of the tank on the map */
				Map[Row][Col] = '<';
				/** Check the location that the tank tries to move */
				checkRow = Row + Dx[Direction];
				checkCol = Col + Dy[Direction];
				
				// Validate Range
				if (!checkRange(checkRow, checkCol)) {
					continue;
				}
				
				// Move
				if (Map[checkRow][checkCol] == '.') {
					Map[Row][Col] = '.';
					Row = checkRow;
					Col = checkCol;
					Map[Row][Col] = '<';
				}
				
				break;
			case 'S':
				/** Check the location that the canon is going to be shot */
				int canonRow = Row;
				int canonCol = Col;
				
				while (true) {
					canonRow += Dx[Direction];
					canonCol += Dy[Direction];
					
					// Validate Range
					if (!checkRange(canonRow, canonCol)) {
						break;
					}
					
					// Check brick wall
					if (Map[canonRow][canonCol] == '*') {
						Map[canonRow][canonCol] = '.';
						break;
					}
					
					// Check steel wall
					if (Map[canonRow][canonCol] == '#') {
						break;
					}
				}
				
				
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for (int tc = 0; tc < T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			Row = 0;
			Col = 0;
			
			/**
			 * Index range
			 * -- row (1 ~ H), column (1 ~ W) 
			 * */
			Map = new char[H + 1][W + 1];
			
			/**
			 * Check information of map and the location of tank
			 * */
			for (int row = 1; row <= H; row++) {
				char[] elems = sc.next().toCharArray();
				for (int col = 1; col <= W; col++) {
					Map[row][col] = elems[col - 1];
					
					// Find the location of tank
					if (Row == 0 && Col == 0) {
						findTank(row, col);
					}
				}
			}
			
			
			N = sc.nextInt();
			Command = sc.next().toCharArray();
			
			playGame();
			
			/** Print out the result*/
			System.out.printf("#%d ", tc + 1);
			for (int r = 1; r <= H; r++) {
				for (int c = 1; c <= W; c++) {
					System.out.printf("%c", Map[r][c]);
				}
				System.out.println();
			}
			
		}
	}
}
