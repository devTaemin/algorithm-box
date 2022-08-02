package com.ssafy.swea.day02;

import java.util.Scanner;

/**
 * 농작물 수확하기
 * @author devTaemin
 *
 */
public class D3_2805 {
	/** Number of test case, Size of farm*/
	public static int T, N;
	
	/** Farm */
	public static int[][] Farm;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int[] results = new int[T];
		int total;
		
		/** Start */
		for (int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			Farm = new int[N][N];
			total = 0;
			
			/** Store values in the map */
			for (int row = 0; row < N; row++) {
				char[] line = sc.next().toCharArray();
				
				for (int col = 0; col < N; col++) {
					Farm[row][col] = line[col] - '0';
				}
			}
			
			/** The number of unchecking area */
			int numOfSpace = (N - 1) / 2;
			/** The number of checking area */
			int count = 1;
			
			/** false (expand), true (contract)*/
			boolean swap = false;
			for (int row = 0; row < N; row++) {
				for (int col = numOfSpace; col < numOfSpace + count; col++) {
					total += Farm[row][col];
				}
				
				if (count == N) {
					swap = true;
				}
				
				if (swap) {
					numOfSpace += 1;
					count -= 2;
				} else {
					numOfSpace -= 1;
					count += 2;
				}
			}
			
			/** Contain the results */
			results[tc] = total;
			
		}
		
		for (int tc = 0; tc < T; tc++) {
			System.out.printf("#%d %d%n", tc + 1, results[tc]);
		}
	}
}
