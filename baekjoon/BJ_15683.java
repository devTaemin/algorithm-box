package com.ssafy.bj.day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 감시
 * @author devTaemin
 *
 */
public class BJ_15683 {
	
	/** CCTV */
	static class CCTV {
		int type;
		int row;
		int col;
		
		public CCTV(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
		}
	}
	
	/** Size of row, column*/
	static int N, M;
	
	/** Minimum number of blind zone from CCTV*/
	static int Minimum;
	
	/** Sequence for containing directions */
	static int[] Sequence;
	
	/** Map */
	static int[][] Map;
	
	/** Array for containing CCTV */
	static ArrayList<CCTV> Array;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Validate range */
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M);
	}
	
	/** Check North */
	public static void checkNorth(int[][] copy, CCTV cctv) {
		int row = cctv.row;
		int col = cctv.col;
		
		while (true) {
			row += Dx[0];
			col += Dy[0];
			
			if (!validateRange(row, col)) break;
			
			if (copy[row][col] == 6) break;
			
			if (copy[row][col] >= 1 && copy[row][col] <= 5) continue;
			
			copy[row][col] = 1;
		}
	}
	
	/** Check East */
	public static void checkEast(int[][] copy, CCTV cctv) {
		int row = cctv.row;
		int col = cctv.col;
		
		while (true) {
			row += Dx[1];
			col += Dy[1];
			
			if (!validateRange(row, col)) break;
			
			if (copy[row][col] == 6) break;
			
			if (copy[row][col] >= 1 && copy[row][col] <= 5) continue;
			
			copy[row][col] = 1;
		}
	}
	
	/** Check South */
	public static void checkSouth(int[][] copy, CCTV cctv) {
		int row = cctv.row;
		int col = cctv.col;
		
		while (true) {
			row += Dx[2];
			col += Dy[2];
			
			if (!validateRange(row, col)) break;
			
			if (copy[row][col] == 6) break;
			
			if (copy[row][col] >= 1 && copy[row][col] <= 5) continue;
			
			copy[row][col] = 1;
		}
	}
	
	/** Check West */
	public static void checkWest(int[][] copy, CCTV cctv) {
		int row = cctv.row;
		int col = cctv.col;
		
		while (true) {
			row += Dx[3];
			col += Dy[3];
			
			if (!validateRange(row, col)) break;
			
			if (copy[row][col] == 6) break;
			
			if (copy[row][col] >= 1 && copy[row][col] <= 5) continue;
			
			copy[row][col] = 1;
		}
	}
	
	
	public static void firstCCTV(int[][] copy, CCTV cctv, int direction) {
		switch(direction) {
		case 0:
			checkEast(copy, cctv);
			break;
		case 1:
			checkSouth(copy, cctv);
			break;
		case 2:
			checkWest(copy, cctv);
			break;
		case 3:
			checkNorth(copy,cctv);
			break;
		}
	}
	
	public static void secondCCTV(int[][] copy, CCTV cctv, int direction) {
		switch(direction) {
		case 0: case 2:
			checkEast(copy, cctv);
			checkWest(copy, cctv);
			break;
		case 1: case 3:
			checkNorth(copy, cctv);
			checkSouth(copy, cctv);
			break;
		}
	}
	
	public static void thirdCCTV(int[][] copy, CCTV cctv, int direction) {
		switch(direction) {
		case 0:
			checkNorth(copy, cctv);
			checkEast(copy, cctv);
			break;
		case 1:
			checkEast(copy, cctv);
			checkSouth(copy, cctv);
			break;
		case 2:
			checkSouth(copy, cctv);
			checkWest(copy, cctv);
			break;
		case 3:
			checkWest(copy, cctv);
			checkNorth(copy,cctv);
			break;
		}
	}
	
	public static void fourthCCTV(int[][] copy, CCTV cctv, int direction) {
		switch(direction) {
		case 0:
			checkWest(copy, cctv);
			checkNorth(copy, cctv);
			checkEast(copy, cctv);
			break;
		case 1:
			checkNorth(copy, cctv);
			checkEast(copy, cctv);
			checkSouth(copy, cctv);
			break;
		case 2:
			checkEast(copy, cctv);
			checkSouth(copy, cctv);
			checkWest(copy, cctv);
			break;
		case 3:
			checkSouth(copy,cctv);
			checkWest(copy, cctv);
			checkNorth(copy, cctv);
			break;
		}
	}
	
	public static void fifthCCTV(int[][] copy, CCTV cctv, int direction) {
		checkNorth(copy, cctv);
		checkEast(copy, cctv);
		checkSouth(copy, cctv);
		checkWest(copy, cctv);
	}
	
	
	
	public static void DFS(int numOfCCTV, int depth) {
		if (depth == numOfCCTV) {
			int[][] Copy = new int[N][M];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					Copy[row][col] = Map[row][col];
				}
			}
			
			for (int i = 0; i < numOfCCTV; i++) {
				CCTV cctv = Array.get(i);
				int type = cctv.type;
				int direction = Sequence[i];
				
				switch(type) {
				case 1:
					firstCCTV(Copy, cctv, direction);
					break;
				case 2:
					secondCCTV(Copy, cctv, direction);
					break;
				case 3:
					thirdCCTV(Copy, cctv, direction);
					break;
				case 4:
					fourthCCTV(Copy, cctv, direction);
					break;
				case 5:
					fifthCCTV(Copy, cctv, direction);
					break;
				}
			}
			
			int localMinimum = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (Copy[row][col] == 0) {
						localMinimum++;
					}
				}
			}
			
			Minimum = Math.min(Minimum, localMinimum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			Sequence[depth] = d;
			DFS(numOfCCTV, depth + 1);
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		Array = new ArrayList<CCTV>();
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int type = Integer.parseInt(st.nextToken());
				if (type >= 1 && type <= 5) {
					Array.add(new CCTV(type, row, col));
				}
				Map[row][col] = type;
			}
		}
		
		Minimum = Integer.MAX_VALUE;
		int numOfCCTV = Array.size();
		Sequence = new int[numOfCCTV];
		DFS(numOfCCTV, 0);
		
		System.out.println(Minimum);
		
	}

}
