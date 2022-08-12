package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로 2
 * @author devTaemin
 *
 */
public class Mock_1227 {
	static final int T = 10;
	static final int N = 10;
	
	static int Result;
	static int[][] Map;
	static boolean[][] Visited;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	static class Pos {
		int row;
		int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public boolean equals(Object obj) {
			Pos other = (Pos)obj;
			return (this.row == other.row && this.col == other.col);
		}
	}
	
	public static void BFS(Pos start, Pos dest) {		
		Queue<Pos> queue = new LinkedList<>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Pos pos = queue.poll();
			int row = pos.row;
			int col = pos.col;
			Visited[row][col] = true;
			
			// Check destination
			if (pos.equals(dest)) {
				Result = 1;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				// Validate range
				if (checkRow < 0 || checkRow >= 100 || checkCol < 0 || checkCol >= 100) {
					continue;
				}
				
				// Check visited
				if (Visited[checkRow][checkCol]) {
					continue;
				}
				
				// Check wall
				if (Map[checkRow][checkCol] == 1) {
					continue;
				}
				
				queue.add(new Pos(checkRow, checkCol));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
						
		for (int tc = 1; tc <= T; tc++) {
			Map = new int[100][100];
			Visited = new boolean[100][100];
			Pos start = null;
			Pos dest = null;
			
			String dummy = br.readLine();
			
			for (int r = 0; r < 100; r++) {
				char[] list = br.readLine().toCharArray();
				for (int c = 0; c < 100; c++) {
					int type = list[c] - '0';
					Map[r][c] = type;
					
					if (type == 2) {
						start = new Pos(r, c);
					}
					
					if (type == 3) {
						dest = new Pos(r, c);
					}
				}
			}
			
			Result = 0;
			BFS(start, dest);
			sb.append("#").append(tc).append(" ").append(Result).append("\n");
		}
		
		System.out.println(sb);
	}

}
