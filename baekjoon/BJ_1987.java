package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987 {
	
	static int R, C, Maximum;
	static boolean[] Visited;
	static char[][] Map;
	
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= R && col >= 1 && col <= C);
	}
	
	public static boolean isAvailable(int row, int col) {
		for (int d = 0; d < 4; d++) {
			int checkRow = row + Dx[d];
			int checkCol = col + Dy[d];
			if (validateRange(checkRow, checkCol)) {
				int index = Map[checkRow][checkCol] - 'A';
				if (!Visited[index]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void DFS(int row, int col, int count) {
		if (!isAvailable(row, col)) {
			Maximum = Math.max(Maximum, count);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int checkRow = row + Dx[d];
			int checkCol = col + Dy[d];
			
			if (!validateRange(checkRow, checkCol)) continue;
			int index = Map[checkRow][checkCol] - 'A';
			if (Visited[index]) continue;
			
			Visited[index] = true;
			DFS(checkRow, checkCol, count + 1);
			Visited[index] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Visited = new boolean[26];
		Map = new char[R + 1][C + 1];
		
		
		for (int row = 1; row <= R; row++) {
			String line = br.readLine();
			for (int col = 1; col <= C; col++) {
				Map[row][col] = line.charAt(col - 1);
			}
		}
		
		Maximum = 0;
		Visited[Map[1][1] - 'A'] = true;
		DFS(1, 1, 1);
		System.out.println(Maximum);
	}

}
