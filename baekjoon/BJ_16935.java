package com.ssafy.bj.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 3
 * @author devTaemin
 *
 */
public class BJ_16935 {
	
	/** Size of row, Size of column, Number of rotation */
	static int N, M, R;
	
	/** Command list */
	static String[] Command;
	
	/** Map */
	static int[][] Map;
	
	/** State after move */
	static int[][] Moved;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		/** Initialize Map */
		Map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				Map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Command = br.readLine().split(" ");
		
		for (int r = 1; r <= R; r++) {
			Moved = new int[N + 1][M + 1];
			int temp = 0;
			switch(Integer.parseInt(Command[r - 1])) {
			case 1:
				upSideDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				rotateRight();
				temp = N;
				N = M;
				M = temp;
				break;
			case 4:
				rotateLeft();
				temp = N;
				N = M;
				M = temp;
				break;
			case 5:
				moveDivisionRight();
				break;
			case 6:
				moveDivisionLeft();
				break;
			}	
		}
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				sb.append(Map[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void upSideDown() {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				int checkRow = N - row + 1;
				int checkCol = col;
				
				Moved[checkRow][checkCol] = Map[row][col];
			}   
		}
		
		Map = Moved;
	}
	
	public static void leftRight() {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				int checkRow = row;
				int checkCol = M - col + 1;
				
				Moved[checkRow][checkCol] = Map[row][col];
			}   
		}
		
		Map = Moved;
	}
	
	public static void rotateRight() {
		Moved = new int[M + 1][N + 1];
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				int checkRow = col;
				int checkCol = N - row + 1;
				
				Moved[checkRow][checkCol] = Map[row][col];
			}
		}
		
		Map = Moved;
	}
	
	public static void rotateLeft() {
		Moved = new int[M + 1][N + 1];
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				int checkRow = M - col + 1;
				int checkCol = row;
				
				Moved[checkRow][checkCol] = Map[row][col];
			}
		}
		
		Map = Moved;
	}
	
	public static void moveDivisionRight() {
		int halfRow = N / 2;
		int halfCol = M / 2;
		
		// first quarter division
		for (int r = 1; r <= halfRow; r++) {
			for (int c = 1; c <= halfCol; c++) {
				Moved[r][c + halfCol] = Map[r][c];
			}
		}
		
		// second quarter division
		for (int r = 1; r <= halfRow; r++) {
			for (int c = halfCol + 1; c <= M; c++) {
				Moved[r + halfRow][c] = Map[r][c];
			}
		}
		
		// third quarter division
		for (int r = halfRow + 1; r <= N; r++) {
			for (int c = 1; c <= halfCol; c++) {
				Moved[r - halfRow][c] = Map[r][c];
			}
		}
		
		// fourth quarter division
		for (int r = halfRow + 1; r <= N; r++) {
			for (int c = halfCol + 1; c <= M; c++) {
				Moved[r][c - halfCol] = Map[r][c];
			}
		}
		
		Map = Moved;
	}
	
	public static void moveDivisionLeft() {
		int halfRow = N / 2;
		int halfCol = M / 2;
		
		// first quarter division
		for (int r = 1; r <= halfRow; r++) {
			for (int c = 1; c <= halfCol; c++) {
				Moved[r + halfRow][c] = Map[r][c];
			}
		}
		
		// second quarter division
		for (int r = 1; r <= halfRow; r++) {
			for (int c = halfCol + 1; c <= M; c++) {
				Moved[r][c - halfCol] = Map[r][c];
			}
		}
		
		// third quarter division
		for (int r = halfRow + 1; r <= N; r++) {
			for (int c = 1; c <= halfCol; c++) {
				Moved[r][c + halfCol] = Map[r][c];
			}
		}
		
		// fourth quarter division
		for (int r = halfRow + 1; r <= N; r++) {
			for (int c = halfCol + 1; c <= M; c++) {
				Moved[r - halfRow][c] = Map[r][c];
			}
		}
		
		Map = Moved;
	}

}
