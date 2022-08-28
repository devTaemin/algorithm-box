package com.ssafy.bj.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 녹색 옷입은 애가 젤다지?
 */
public class BJ_4485 {
	
	static final int INF = (int)1e9;
	static int N;
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	static int[][] Map;
	static int[][] Distance; 
	
	/** Validate range */
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N);
	}
	
	/** Update distance table */
	public static void BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		Distance[0][0] = Map[0][0];
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int row = cur[0];
			int col = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				/** check valid range */
				if (!validateRange(checkRow, checkCol)) continue;
				
				/** Update distance table */
				if (Distance[checkRow][checkCol] > Distance[row][col] + Map[checkRow][checkCol]) {
					Distance[checkRow][checkCol] = Distance[row][col] + Map[checkRow][checkCol];
					queue.add(new int[] {checkRow, checkCol});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			testCase++;
			Map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					Map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			/** Initialize distance table with infinite values */
			Distance = new int[N][N];
			for (int r = 0; r < N; r++) {
				Arrays.fill(Distance[r], INF);
			}
		
			BFS();
			sb.append("Problem ").append(testCase).append(": ").append(Distance[N-1][N-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
