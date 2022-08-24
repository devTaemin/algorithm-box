package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 적록색약
 */
public class BJ_10026 {
	
	/** Size of the map */
	static int N;
	
	/** Map */
	static char[][] Map;
	
	/** Check if visited */
	static boolean[][] Visited;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Check if the range is valid or not */
	public static boolean validateRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	/** For color blind */
	public static int colorBlind() {
		Visited = new boolean[N][N];
		int count = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!Visited[r][c]) {
					count++;
					
					Queue<Integer[]> queue = new LinkedList<>();
					queue.offer(new Integer[] {r, c});
					Visited[r][c] = true;
					
					while (!queue.isEmpty()) {
						Integer[] index = queue.poll();
						
						for (int d = 0; d < 4; d++) {
							int checkRow = index[0] + Dx[d];
							int checkCol = index[1] + Dy[d];
							
							if (!validateRange(checkRow, checkCol)) continue;
							if (Visited[checkRow][checkCol]) continue;
							
							/** For color blind, 'R' and 'G' is same */
							if (Map[r][c] == 'R' || Map[r][c] == 'G') {
								if (Map[checkRow][checkCol] == 'B') continue;
							} else {
								if (Map[r][c] != Map[checkRow][checkCol]) {
									continue;
								}
							}
							
							queue.add(new Integer[] {checkRow, checkCol});
							Visited[checkRow][checkCol] = true;
						}
					}
				}
			}
		}
		
		return count;
	}
	
	public static int normal() {
		Visited = new boolean[N][N];
		int count = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!Visited[r][c]) {
					count++;
					
					Queue<Integer[]> queue = new LinkedList<>();
					queue.offer(new Integer[] {r, c});
					Visited[r][c] = true;
					
					while (!queue.isEmpty()) {
						Integer[] index = queue.poll();
						
						for (int d = 0; d < 4; d++) {
							int checkRow = index[0] + Dx[d];
							int checkCol = index[1] + Dy[d];
							
							if (!validateRange(checkRow, checkCol)) continue;
							if (Visited[checkRow][checkCol]) continue;
							if (Map[r][c] != Map[checkRow][checkCol]) continue;
							
							queue.add(new Integer[] {checkRow, checkCol});
							Visited[checkRow][checkCol] = true;
						}
					}
				}
			}
		}
		
		return count;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		Map = new char[N][N];
		
		for (int r = 0; r < N; r++) {
			String line = in.readLine();
			for (int c = 0; c < N; c++) {
				Map[r][c] = line.charAt(c);
			}
		}
		
		sb.append(normal()).append(" ").append(colorBlind());
		System.out.println(sb);
	}
}
