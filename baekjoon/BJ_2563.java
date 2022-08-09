package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이
 * @author devTaemin
 *
 */
public class BJ_2563 {
	static int N;
	static boolean[][] Map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		/** Initialize 110 x 110 map */
		Map = new boolean[111][111];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int col = 1 + Integer.parseInt(st.nextToken());
			int row = 100 - Integer.parseInt(st.nextToken());
			
			/** Check the area covered by color paper */
			for (int r = row; r < row + 10; r++) {
				for (int c = col; c < col + 10; c++) {
					Map[r][c] = true;
				}
			}
		}
		
		/** Count the covered area */
		int count = 0;
		for (int r = 1; r <= 110; r++) {
			for (int c = 1; c <= 110; c++) {
				if (Map[r][c]) {
					count++;
				}
			}
		}
		
		/** Print out the result */
		System.out.println(count);
	}
}
