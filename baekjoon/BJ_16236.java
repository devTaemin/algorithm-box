package com.ssafy.bj.day16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 아기 상어
 * @author devTaemin
 *
 */
public class BJ_16236 {
	
	/** Node for storing information of fish, shark*/
	static class Node {
		int row;
		int col;
		int size;
		int accumulate;
		
		public Node(int row, int col, int size, int accumulate) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.accumulate = accumulate;
		}
	}
	
	/** Infinite number */
	static final int INF = (int)1e9;
	
	/** Size of map, Total time */
	static int N, Time;
	
	/** Map */
	static int[][] Map;
	
	/** Times to take when going to certain position */
	static int[][] Times;
	
	/** Shark */
	static Node Shark;
	
	/** Fish in the map */
	static ArrayList<Node> Fish;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Validate the range */
	static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	/** Calculate the times for going to certain position */
	static void BFS() {
		/** Initialize times with INF (Unable to go) */
		Times = new int[N + 1][N + 1];
		for (int r = 0; r <= N; r++) {
			Arrays.fill(Times[r], INF);
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		/** Start with shark */
		queue.offer(Shark);
		/** 0 time with start position */
		Times[Shark.row][Shark.col] = 0;
		
		while (!queue.isEmpty()) {
			/** Current position of shark */
			Node cur = queue.poll();
			
			/** Check four directions */
			for (int d = 0; d < 4; d++) {
				int checkRow = cur.row + Dx[d];
				int checkCol = cur.col + Dy[d];
				
				/** Check if the range is valid */
				if (!validateRange(checkRow, checkCol)) continue;
				
				/** Check if the fish is bigger than the shark */
				if (Map[checkRow][checkCol] > cur.size) continue;
				
				/** Check if the position is already visited */
				if (Times[checkRow][checkCol] != INF) continue;
				
				/** Set the current time with prior time + 1*/
				Times[checkRow][checkCol] = Times[cur.row][cur.col] + 1;
				
				/** Offer current position */
				queue.offer(new Node(checkRow, checkCol, cur.size, cur.accumulate));
			}
		}
	}
	
	static void play() {
		while(!Fish.isEmpty()) {
			
			/** Terminate condition */
			int numOfFish = Fish.size();
			if (numOfFish == 0) break;
			
			/** Make time table */
			BFS();
			
			/** Find the fish */
			Node target = null;
			for (Node fish : Fish) {
				int row = fish.row;
				int col = fish.col;
				
				/** Unable to eat the fish */
				if (Times[row][col] == INF) continue;
				
				/** Check the conditions */
				if (target == null) {
					if (fish.size < Shark.size) {
						target = fish;						
					}
					continue;
				} else {
					if (fish.size >= Shark.size) continue;  
					if (Times[row][col] < Times[target.row][target.col]) {
						target = fish;							
					} else if (Times[row][col] > Times[target.row][target.col]) {
						continue;
					} else {
						if (row < target.row) {
							target = fish;								
						} else if (row > target.row) {
							continue;
						} else {
							if (col < target.col) {
								target = fish;									
							} else {
								continue;
							}
						}
					}
				}
			}
			
			/** If available fish is not found, quit */
			if (target == null) break;
			
			/** Move shark, gain weight, check size */
			int row = target.row;
			int col = target.col;
			int time = Times[row][col];
			
			Shark.row = row;
			Shark.col = col;
			Shark.accumulate += 1;
			if (Shark.accumulate % Shark.size == 0) {
				Shark.accumulate = 0;
				Shark.size += 1;
			}
			
			Map[row][col] = 0;
			
			/** Remove eaten fish */
			Fish.remove(target);
			
			/** Accumulate the time taken to eat the fish */
			Time += time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N + 1][N + 1];
		Fish = new ArrayList<Node>();
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");			
			for (int c = 1; c <= N; c++) {
				int type = Integer.parseInt(st.nextToken());
				if (type >= 1 && type <= 6) {
					Fish.add(new Node(r, c, type, 0));
					Map[r][c] = type;
				} else if (type == 9) {
					Shark = new Node(r, c, 2, 0);
				}
			}
		}
		
		Time = 0;
		play();
		System.out.println(Time);
	}
	
	public static void print(int[][] map) {
		System.out.println("-------------------------------------");
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------\n");
	}
}
