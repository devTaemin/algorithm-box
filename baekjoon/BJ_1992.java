package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
8
00000000
00000000
00001111
00001111
00011111
00111111
00111111
00111111


 * */

public class BJ_1992 {
	
	public static int N;
	public static int[][] Map;
	public static StringBuilder SB = new StringBuilder();
	
	public static void quadTree(int row, int col, int n) {
		if (isCompressable(row, col, n)) {
			SB.append(Map[row][col]);
			return;
		}
		
		SB.append('(');
		
		quadTree(row, col, n/2);
		quadTree(row, col + n/2, n/2);
		quadTree(row + n/2, col, n/2);
		quadTree(row + n/2, col + n/2, n/2);
		
		SB.append(')');
	}
	
	public static boolean isCompressable(int row, int col, int n) {
		int standard = Map[row][col];
		
		for (int r = row; r < row + n; r++) {
			for (int c = col; c < col + n; c++) {
				if (standard != Map[r][c]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		
		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0; col < N; col++) {
				Map[row][col] = line.charAt(col) - '0';
			}
		}
		
		quadTree(0, 0, N);
		System.out.println(SB);
	}

}
