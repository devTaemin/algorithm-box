package com.ssafy.swea;

import java.util.Scanner;

/***
 * 달팽이 숫자
 * @author devTaemin
 *
 */
public class D2_1954 {
	/** The number of test case, The size of  */
	public static int T, N;
	public static int[][] Map;
	public static boolean[][] Visited;
	
	/** Direction (East, South, West, North) */
	public static int[] Dx = { 0, 1, 0, -1 };
	public static int[] Dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		/** Start */
		for (int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			Map = new int[N][N];
			Visited = new boolean[N][N];
			
			int value = 1;
			int d = 0;
			int row = 0;
			int col = 0;
			Map[row][col] = value++;
			Visited[row][col] = true;
			
			/** Loop while the value is below N squared*/
			while (value <= N * N) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				/** Validate range */
				if (checkRow < 0 || checkRow >= N || checkCol < 0 || checkCol >= N) {
					/** Change the direction */
					d = (d + 1) % 4;
					continue;
				}
				
				/** Check if visited */
				if (Visited[checkRow][checkCol]) {
					/** Change the direction */
					d = (d + 1) % 4;
					continue;
				}
				
				row = checkRow;
				col = checkCol;
				
				Map[row][col] = value++;
				Visited[row][col] = true;
			}
			
			/** Print out the results */
			System.out.printf("#%d%n", tc + 1);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					System.out.printf("%d ", Map[r][c]);
				}
				System.out.println();
			}
		}
		
	}
}
