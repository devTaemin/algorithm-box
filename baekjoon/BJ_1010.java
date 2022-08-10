package com.ssafy.bj.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다리 놓기 
 * @author devTaemin
 *
 */
public class BJ_1010 {
	static int T, West, East;
	static int[][] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		DP = new int[31][31];
		
		// When row equals to column
		for (int r = 1; r <= 30; r++) {
			for (int c = 1; c <= 30; c++) {
				DP[r][c] = 1;
			}
		}
		
		// When row equals to one
		for (int c = 1; c <= 30; c++) {
			DP[1][c] = c;
		}
		
		// Calculate the number of opportunities
		for (int r = 2; r <= 30; r++) {
			for (int c = r; c <= 30; c++) {
				if (r == c) continue;

				int findRow = r - 1;				
				for (int findCol = c - 1; findCol > findRow; findCol--) {
					DP[r][c] += DP[findRow][findCol];
				}
				
			}
		}
		
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			West = Integer.parseInt(st.nextToken());
			East = Integer.parseInt(st.nextToken());
			
			/** Get the result */
			sb.append(DP[West][East]).append("\n");
		}
		
		/** Print out the result */
		System.out.println(sb);
	}
}
