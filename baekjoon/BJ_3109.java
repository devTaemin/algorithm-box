package com.ssafy.bj.day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 빵집
 * @author devTaemin
 *
 */
public class BJ_3109 {
	static int R, C;
	static int Maximum;
	static char[][] Map;
	static boolean[][] Visited;
	
	/** Direction */
	static int[] Dx = { -1, 0, 1 };
	static int[] Dy = { 1, 1, 1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < R && col >= 0 && col < C);
	}
	
	public static boolean findRoute(int row, int col) {		
		if (col == C - 1) {
			return true;
		}
		
		for (int d = 0; d < 3; d++) {
			int checkRow = row + Dx[d];
			int checkCol = col + Dy[d];
			
			if (!validateRange(checkRow, checkCol)) continue;
			if (Map[checkRow][checkCol] == 'x') continue;
			if (Visited[checkRow][checkCol]) continue;
			
			Visited[checkRow][checkCol] = true;
			if (findRoute(checkRow, checkCol)) return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R][C];
		for (int row = 0; row < R; row++) {
			String line = br.readLine();
			for (int col = 0; col < C; col++) {
				Map[row][col] = line.charAt(col);
			}
		}
		
		Maximum = 0;
		Visited = new boolean[R][C];
		for (int root = 0; root < R; root++) {
			if (findRoute(root, 0)) {
				Maximum++;
			}
		}
		
		System.out.println(Maximum);
		
	}

}
