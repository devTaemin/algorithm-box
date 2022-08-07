package com.ssafy.cfj.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 미로 탈출 
 * @author taeminim
 *
 */
class Location {
	int row;
	int col;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class EscapeMaze {
	static int N, M;
	static int[][] Map;
	
	/** Direction (North, East, South, West */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	static Queue<Location> queue = new LinkedList<>();
	
	static void BFS() {
		while (!queue.isEmpty()) {
			Location l = queue.poll();
			int row = l.row;
			int col = l.col;
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				// Validate range
				if (checkRow < 1 || checkRow > N || checkCol < 1 || checkCol > M) {
					continue;
				}
				
				// check minimum distance
				if (Map[checkRow][checkCol] == 1) {
					Map[checkRow][checkCol] = Map[row][col] + 1;
					queue.add(new Location(checkRow, checkCol));
				}
			}
			
		}
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		/** Initailize map */
		Map = new int[N + 1][M + 1];
		for (int row = 1; row <= N; row++) {
			String line = sc.next();
			for (int col = 1; col <= M; col++) {
				Map[row][col] = (line.charAt(col - 1) - '0');
			}
		}
		
		queue.add(new Location(1, 1));
		BFS();
		
		System.out.println(Map[N][M]);
	}

}
