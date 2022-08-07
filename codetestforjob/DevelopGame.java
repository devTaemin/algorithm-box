package com.ssafy.cfj.implemantation;

import java.util.Scanner;

/**
 * 게임 개발
 * @author devTaemin
 *
 */
public class DevelopGame {
	static int N, M, X, Y, D, Count;
		
	/** North, East, South, West */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };

	static int[][] Map;
	static boolean[][] Visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt() + 1;
		Y = sc.nextInt() + 1;
		D = sc.nextInt();
		
		Map = new int[N + 1][M + 1];
		Visited = new boolean[N + 1][M + 1];
		
		//Check current point
		Visited[X][Y] = true;
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				Map[row][col] = sc.nextInt();
			}
		}
		
		play();
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				if (Visited[row][col]) Count++;
			}
		}
		
		System.out.println(Count);
		sc.close();
	}
	
	public static void play() {
		while (true) {
			boolean found = false;
			
			for (int i = 0; i < 4; i++) {
				rotate();
				
				int checkRow = X + Dx[D];
				int checkCol = Y + Dy[D];
				
				if (!validateRange(checkRow, checkCol)) continue;
				
				if (Visited[checkRow][checkCol]) continue;
				
				if (Map[checkRow][checkCol] == 1) continue;
				
				X = checkRow;
				Y = checkCol;
				found = true;
				Visited[X][Y] = true;
				break;
			}
			
			if (!found) {
				int b = backward(D);
				
				int checkRow = X + Dx[b];
				int checkCol = Y + Dy[b];
				
				if (!validateRange(checkRow, checkCol)) break;
				
				if (Map[checkRow][checkCol] == 1) break;
				
				X = checkRow;
				Y = checkCol;
			}
		}
	}
	
	public static int backward(int current) {
		// 0 --> 2
		// 1 --> 3
		// 2 --> 0
		// 3 --> 1
		return (current + 2) % 4;
	}
	
	public static void rotate() {
		D -= 1;
		if (D < 0) D = 3;
	}
	
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 || row <= N || col >= 1 || col <= M);
	}

}