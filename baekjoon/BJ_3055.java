package com.ssafy.bj.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 탈출
 */
public class BJ_3055 {
	
	/** 위치 정보 */
	static class Pos {
		int row;
		int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static final int INF = (int)1e9;
	static int R, C, Min;
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Map information */
	static char[][] Map;
	
	/** Distance information */
	static int[][] Distance;
	
	/** Time for movement of hedgehog */
	static int[][] Time;
	
	/** Check if visited by hedgehog*/
	static boolean[][] Visited;
	
	/** Location of hedgehog, cave */
	static Pos Hedgehog, Cave;
	
	/** Queue for BFS */
	static Queue<Pos> Movement;
	static Queue<Pos> Waters;
	
	/** Validate range */
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= R && col >= 1 && col <= C);
	}
	
	/** Check the time for flood */
	/** Store the time information of flooding in the distance table */
	public static void flood() {
		while (!Waters.isEmpty()) {
			Pos water = Waters.poll();
			
			for (int d = 0; d < 4; d++) {
				int checkRow = water.row + Dx[d];
				int checkCol = water.col + Dy[d];
				
				if (!validateRange(checkRow, checkCol)) continue;
				
				if (Map[checkRow][checkCol] == 'D' || Map[checkRow][checkCol] == 'X') continue;
				
				if (Distance[checkRow][checkCol] > Distance[water.row][water.col] + 1) {
					Distance[checkRow][checkCol] = Distance[water.row][water.col] + 1;
					Waters.offer(new Pos(checkRow, checkCol));
				}
			}
		}
	}
	
	/** Check the time for movement */
	/** Store the time information of hedgehog movement in the Time table */
	public static void move() {
		while (!Movement.isEmpty()) {
			Pos cur = Movement.poll();
			
			for (int d = 0; d < 4; d++) {
				int checkRow = cur.row + Dx[d];
				int checkCol = cur.col + Dy[d];
				
				if (!validateRange(checkRow, checkCol)) continue;
				
				if (Visited[checkRow][checkCol]) continue;
				
				if (Map[checkRow][checkCol] == '.' || Map[checkRow][checkCol] == 'D') {
					if (Distance[checkRow][checkCol] > Time[cur.row][cur.col] + 1) {
						Time[checkRow][checkCol] = Time[cur.row][cur.col] + 1;
						Visited[checkRow][checkCol] = true;
						Movement.offer(new Pos(checkRow, checkCol));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Min = INF;
		Map = new char[R + 1][C + 1];
		Waters = new LinkedList<Pos>();
		Movement = new LinkedList<Pos>();
		Visited = new boolean[R + 1][C + 1];
		
		Distance = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			Arrays.fill(Distance[r], INF);
		}
		
		Time = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			Arrays.fill(Time[r], INF);
		}
		
		for (int r = 1; r <= R; r++) {
			String line = br.readLine();
			for (int c = 1; c <= C; c++) {
				char type = line.charAt(c - 1);
				if (type == 'S') {
					Hedgehog = new Pos(r, c);
					Movement.offer(Hedgehog);
					Time[r][c] = 0;
					Visited[r][c] = true;
				} else if (type == 'D') {
					Cave = new Pos(r, c);
				} else if (type == '*') {
					Waters.offer(new Pos(r, c));
					Distance[r][c] = 0;
				}
				
				Map[r][c] = type;
			}
		}
		
		flood();
		move();
		
		if (Time[Cave.row][Cave.col] == INF) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(Time[Cave.row][Cave.col]);
		}
	}
}
