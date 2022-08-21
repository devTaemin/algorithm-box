package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468 {
	
	static int N, Max, Count;
	static int[][] Map;
	static boolean[][] Visited;
	
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	public static void BFS(int height) {
		int count = 0;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				
				if (Map[r][c] > height && !Visited[r][c]) {
					count++;
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[]{r, c});
					Visited[r][c] = true;
					
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int row = cur[0];
						int col = cur[1];

						
						for (int d = 0; d < 4; d++) {
							int checkRow = row + Dx[d];
							int checkCol = col + Dy[d];
							
							if (!validateRange(checkRow, checkCol)) continue;
							if (Map[checkRow][checkCol] <= height) continue;
							if (Visited[checkRow][checkCol]) continue;
							queue.add(new int[] {checkRow, checkCol});
							Visited[checkRow][checkCol] = true;
						}
					}
				}
			}
		}
		
		Count = Math.max(Count, count);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Count = 0;
		Map = new int[N + 1][N + 1];
		Visited = new boolean[N + 1][N + 1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int height = Integer.parseInt(st.nextToken());
				Map[r][c] = height;
				Max = Math.max(Max, height);
			}
		}
		
		for (int h = 0; h <= Max; h++) {
			Visited = new boolean[N + 1][N + 1];
			BFS(h);
		}
		
		System.out.println(Count);
	}
}
