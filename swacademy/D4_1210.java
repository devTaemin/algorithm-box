package com.ssafy.swea.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 기본] 2일차 - Ladder1 
 * @author devTaemin
 *
 */
public class D4_1210 {
	/** Number of testcase, Answer column*/
	public static int T, Column;
	
	/** Check if the answer is found */
	public static boolean Found;
	
	/** Map of ladders */
	public static int[][] Map;
	
	/** Check if visited or not */
	public static boolean[][] Visited;
	
	/** Direction (down, left, right)*/
	public static int[] Dx = { 1, 0, 0 };
	public static int[] Dy = { 0, -1, 1 };
	
	public static void main(String[] args) throws NumberFormatException, IOException  {
		/** Read file */
		File file = new File("input2.txt");
		try {
			System.setIn(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = 10;
		int[] results = new int[T];
		
		/** Start */
		for (int tc = 0; tc < 10; tc++) {
			Map = new int[100][100];
			Column = 0;
			Found = false;
			int testcase = Integer.parseInt(br.readLine());
			
			for (int row = 0; row < 100; row++) {
				st = new StringTokenizer(br.readLine());
				
				for (int col = 0; col < 100; col++) {
					Map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
//			DEBUG
//			System.out.println(testcase);
//			for (int row = 0; row < 100; row++) {
//				for (int col = 0; col < 100; col++) {
//					System.out.print(Map[row][col] + " ");
//				}
//				System.out.println();
//			}
			
			/** Check the column from 0 to 99*/
			for (int col = 0; col <= 99; col++) {
				Visited = new boolean[100][100];
				
				/** Check ladder*/
				if (Map[0][col] == 1) {
					Column = col;
					int checkRow = 0;
					int checkCol = col;
					
					/** Check if visited */
					Visited[checkRow][checkCol] = true;
					
					while (true) {
						// Initialize direction
						int direction = 0;
						
						// check side (right)
						int checkRight = checkCol + 1;
						if (checkRight < 100) {
							if (Map[checkRow][checkRight] == 1 && !Visited[checkRow][checkRight]) {
								direction = 2;
							}
						}
						
						// check side (left)
						int checkLeft = checkCol - 1;
						if (checkLeft >= 0) {
							if (Map[checkRow][checkLeft] == 1 && !Visited[checkRow][checkLeft]) {
								direction = 1;
							}
						}	
						
						// check down
						if (direction == 0) {
							if (checkRow + Dx[direction] > 99) {
								break;
							}
						}

						// move
						checkRow += Dx[direction];
						checkCol += Dy[direction];
						

						// check
						if (checkRow == 99) {
							if (Map[checkRow][checkCol] == 2) {
								Found = true;
								break;
							}
							else {
								break;
							}
						}
						
						/** Check if visited */
						Visited[checkRow][checkCol] = true;	
					}
					
					/** If found, stop the loop */
					if (Found) break;
				}
			}
			
			/** Store the answer */
			results[tc] = Column;
		}
		
		/** Print out the results */
		for (int tc = 0; tc < 10; tc++) {
			System.out.printf("#%d %d%n", tc + 1, results[tc]);
		}
	}
}
