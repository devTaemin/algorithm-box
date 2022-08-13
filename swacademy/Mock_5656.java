package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 벽돌 깨기 
 * @author devTaemin
 *
 */
public class Mock_5656 {
	
	/** Brick class */
	static class Brick {
		int number;
		int row;
		int col;
		
		public Brick(int number, int row, int col) {
			this.number = number;
			this.row = row;
			this.col = col;
		}
	}
	
	/** Number of test case*/
	static int T;
	
	/** Minimum number of bricks */
	static int Minimum;
	
	/** Number of shoot, Length of column, Length of row*/
	static int N, W, H;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Sequence for duplicated permutation */
	static int[] Sequence;
	
	/** Top brick of each column */
	static Brick[] Top;
	
	/** Map */
	static int[][] Map;
	static int[][] Copied;
	
	/** Initialize the map */
	public static void initializeMap() {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				Map[r][c] = Copied[r][c];
			}
		}
	}
	
	/** Find the top brick of each column */
	public static void findTop() {
		for (int col = 0; col < W; col++) {
			/** Initialize top as null */
			Top[col] = null;
			for (int row = 0; row < H; row++) {
				if (Map[row][col] != 0) {
					Top[col] = new Brick(Map[row][col], row, col);
					break;
				}
			}
		}
	}
	
	/** Duplicated permutation */
	public static void permutation(int depth) {
		if (depth == N) {
			initializeMap();
			findTop();
			for (int i = 0; i < N; i++) {
				Brick brick = Top[Sequence[i]];
				if (brick == null) {
					if (countBricks() == 0) {
						Minimum = 0;
					}
					return;
				}
				
				crushBricks(brick);
				arrangeBricks();
				findTop();
			}
			
			int localMinimum = countBricks();
			Minimum = Math.min(Minimum, localMinimum);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			Sequence[depth] = i;
			permutation(depth + 1);
		}
	}
	
	/** Count the number of left bricks */
	public static int countBricks() {
		int count = 0;
		
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (Map[r][c] != 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	/** Crush the brick */
	public static void crushBricks(Brick brick) {
		Queue<Brick> queue = new LinkedList<>();
		queue.add(brick);
		while(!queue.isEmpty()) {
			Brick target = queue.poll();
			int row = target.row;
			int col = target.col;
			int number = target.number;
			
			Map[row][col] = 0;
			for (int d = 0; d < 4; d++) {
				int checkRow = row;
				int checkCol = col;
				for (int k = 0; k < number - 1; k++) {
					checkRow += Dx[d];
					checkCol += Dy[d];
					
					/** Validate the range */
					if (!validateRange(checkRow, checkCol)) break;
					
					/** Check if the number of brick is over 2 */
					if (Map[checkRow][checkCol] >= 2) {
						queue.add(new Brick(Map[checkRow][checkCol], checkRow, checkCol));
					}
					
					/** Crush the brick */
					Map[checkRow][checkCol] = 0;
				}
			}
		}
	}
	
	/** Arrange bricks for each column */
	public static void arrangeBricks() {
		Stack<Integer> stack = new Stack<>();
		for (int col = 0; col < W; col++) {
			for (int row = 0; row < H; row++) {
				if (Map[row][col] != 0) {
					stack.push(Map[row][col]);
					Map[row][col] = 0;
				}
			}
			
			int startRow = H - 1;
			while(!stack.isEmpty()) {
				int number = stack.pop();
				Map[startRow--][col] = number;
			}
		}
	}
	
	/** Validate the range */
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < H && col >= 0 && col < W);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		// For Test
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			/** Initialize Minimum */
			Minimum = Integer.MAX_VALUE;
			
			/** Initialize Map */
			Map = new int[H][W];
			Copied = new int[H][W];
			for (int row = 0; row < H; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < W; col++) {
					int number = Integer.parseInt(st.nextToken());
					Map[row][col] = number;
					Copied[row][col] = number;
				}
			}
			
			/** Initialize Top */
			Top = new Brick[W];
			findTop();
			
			/** Calculate duplicated permutation */
			Sequence = new int[N];
			permutation(0);
			
			
			
			sb.append("#").append(tc).append(" ").append(Minimum).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static void printMap() {
		System.out.println("\n------------------------------------");
		for (int r = 0 ; r < H; r++) {
			for (int c = 0; c < W; c++) {
				System.out.printf("%d ", Map[r][c]);
			}
			System.out.println();
		}
		System.out.println("------------------------------------\n");
	}

}
