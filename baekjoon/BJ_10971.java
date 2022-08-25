package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 외판원 순회
 */
public class BJ_10971 {
	
	/** Number of city, Minimum cost, Start point */
	static int N, Min, Start;
	
	/** Check if the city is visited */
	static boolean[] Visited;
	
	/** All routes from city to city */
	static int[][] Map;
	
	/** Find out all possible routes */
	public static void DFS(int current, int depth, int total) {
		if (depth == N) {
			if (Map[current][Start] != 0) {
				Min = Math.min(Min, total + Map[current][Start]);
			}
			
			return;
		}
		
		for (int next = 1; next <= N; next++) {
			/** If already visited, pass */
			if (Visited[next]) continue;
			/** If there are no route, pass */
			if (Map[current][next] == 0) continue;
			Visited[next] = true;
			DFS(next, depth + 1, total + Map[current][next]);
			Visited[next] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine().trim());
		Map = new int[N + 1][N + 1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int c = 1; c <= N; c++) {
				Map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		/** Initialize minimum value */
		Min = Integer.MAX_VALUE;
		for (int start = 1; start <= N; start++) {
			Start = start;
			Visited = new boolean[N + 1];
			Visited[start] = true;
			DFS(start, 1, 0);
		}
		
		System.out.println(Min);
	}

}
