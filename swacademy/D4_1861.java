package com.ssafy.swea.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 정사각형방
 * @author devTaemin
 *
 */
public class D4_1861 {
	static int T, N;
	static int[] Result;
	static int[][] Map;
	static ArrayList<ArrayList<Integer>> graph;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	/** BFS */
	public static void BFS(int start) {
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		
		boolean[] visited = new boolean[N * N + 1];
		
		while(!queue.isEmpty()) {
			int room = queue.poll();
			visited[room] = true;
			count++;
			
			for (int i = 0; i < graph.get(room).size(); i++) {
				int checkRoom = graph.get(room).get(i);
				
				/** As the edge is bothsides, visited room should be omitted*/
				if (!visited[checkRoom]) {
					queue.add(checkRoom);
				}
			}
		}
		
		Result[start] = count;
	}

	public static void main(String[] args) throws NumberFormatException, IOException  {
		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		/** Play test case */
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Result = new int[N * N + 1];
			Map = new int[N + 1][N + 1];
			graph = new ArrayList<ArrayList<Integer>>();
			
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					Map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			/** Initialize adjacency list */
			for (int i = 0; i <= N * N; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			/** Store adjacency list */
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					int number = Map[row][col];
					
					for (int d = 0; d < 4; d++) {
						int checkRow = row + Dx[d];
						int checkCol = col + Dy[d];
						
						if (!(validateRange(checkRow, checkCol))) {
							continue;
						}
						
						if (Math.abs(number - Map[checkRow][checkCol]) == 1) {
							graph.get(number).add(Map[checkRow][checkCol]);
						}
					}
				}
			}
			
			/** BFS for all rooms */
			for (int i = 1; i <= N * N; i++) {
				BFS(i);
			}
			
			int max = 0;
			int index = 0;
			for (int i = 1; i <= N * N; i++) {
				if (max < Result[i]) {
					max = Result[i];
					index = i;
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(index).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
}
