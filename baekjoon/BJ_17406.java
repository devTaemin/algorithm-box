package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 4
 * @author devTaemin
 */
public class BJ_17406 {
	/** Size of row, Size of column, Number of command, Minimum summation*/
	static int N, M, K, Minimum;
	
	/** Map */
	static int[][] Map;
	static int[][] Copied;
	
	/** Direction (East, South, West, North) */
	static int[] Dx = { 0, 1, 0, -1 };
	static int[] Dy = { 1, 0, -1, 0 };
	
	/** Store command information */
	static ArrayList<int[]> Array = new ArrayList<>();
	
	/** Sequence of command */
	static int[] Sequence;
	
	/** Check if visited */
	static boolean[] Visited;
	
	/** Sort the sequence with permutation method */
	public static void permutation(int depth) {
		if (depth == K) {
			for (int i = 0; i < K; i++) {
				// Get the command information 
				int r = Array.get(Sequence[i])[0];
				int c = Array.get(Sequence[i])[1];
				int s = Array.get(Sequence[i])[2];
				
				// Rotate the map
				rotate(r,c,s);
			}
			
			// Calculate the local minimum
			int localMinimum = calculateMin();
			
			// Compare with global minimum
			Minimum = Math.min(Minimum, localMinimum);
			
			// Initialize the map
			initializeMap();

			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!Visited[i]) {
				Visited[i] = true;
				Sequence[depth] = i;
				permutation(depth + 1);
				Visited[i] = false;
			}
		}
		
	}
	
	/** Calculate minimum total among the rows */
	public static int calculateMin() {		
		int result = Integer.MAX_VALUE;
		
		for (int row = 1; row <= N; row++) {
			int total = 0;
			for (int col = 1; col <= M; col++) {
				total+=Map[row][col];
			}
			
			result = Math.min(result, total);
		}
		
		return result;
	}
	
	/** Rotate map with given information */
	public static void rotate(int r, int c, int s) {
		int row = r - s;
		int col = c - s;
		
		for (int i = 0; i <= s; i++) {
			int curRow = row + i;
			int curCol = col + i;			
			int original = Map[curRow][curCol];
			
			for (int d = 0; d < 4; d++) {
				while (true) {
					int checkRow = curRow + Dx[d];
					int checkCol = curCol + Dy[d]; 
					
					// Validate the range
					if (checkRow < r - s + i || checkRow > r + s - i || checkCol < c - s + i || checkCol > c + s - i) {
						break;
					}
					
					int temp = Map[checkRow][checkCol];
					Map[checkRow][checkCol] = original;
					original = temp;
					
					curRow = checkRow;
					curCol = checkCol;
				}
			}
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Minimum = Integer.MAX_VALUE;
		int r, c, s = 0;
		
		/** Initialize Map (Skip 0th index) */
		Map = new int[N + 1][M + 1];
		Copied = new int[N + 1][M + 1];
		
		
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= M; col++) {
				int elem = Integer.parseInt(st.nextToken());
				Map[row][col] = elem;
				Copied[row][col] = elem;
			}
		}
		
		/** Implement command */
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			Array.add(new int[] { r, c, s } );
		}
		
		Sequence = new int[K];
		Visited = new boolean[K];
		permutation(0);
		
		System.out.println(Minimum);
	}
	
	/** Initialize the map */
	public static void initializeMap() {
		Map = new int[N + 1][M + 1];
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				Map[row][col] = Copied[row][col];
			}
		}
	}
	
	/** Print out the map*/
	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.printf("%d ", Map[i][j]);
			}
			System.out.println();
		}
	}
}
