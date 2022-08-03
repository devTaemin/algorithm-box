package com.ssafy.swea;

import java.util.Scanner;

/**
 * 파리퇴치
 * @author devTaemin
 *
 */
class D2_2001 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] result = new int[T + 1];
		
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int max = 0;
			
			/** Initialize the map */
			int[][] map = new int[N + 1][N + 1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			/** Find out the max number of kill  */
			for (int row = 1; row <= N - M + 1; row++) {
				for (int col = 1; col <= N - M + 1; col++) {
					max = Math.max(max, total(row, col, M, map));
				}
			}
			
			/** Save the result */
			result[testCase] = max;
		}
		
		/** Print out the result */
		for (int i = 1; i <= T; i++) {
			System.out.print("#" + i + " " + result[i] + "\n");
		}
	}	
	
	/**
	 * Count the total number of kill
	 * @param row
	 * @param col
	 * @param M
	 * @param map
	 * @return
	 */
	public static int total(int row, int col, int M, int[][] map) {
		int total = 0;
		
		for (int i = row; i < row + M; i++) {
			for (int j = col; j < col + M; j++) {
				total += map[i][j];
			}
		}
		
		return total;
	}
}