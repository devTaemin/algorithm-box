package com.ssafy.bj.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 1
 * @author devTaemin
 *
 */
public class BJ_16926 {
	/** Size of row, Size or column, Number of rotation */
	static int N, M, R;
	
	/** Map */
	static int[][] Map;
	
	/** Store state in a rotation */
	static int[][] Moved;
	
	/** Direction (South, East, North, West) */
	static int[] Dx = { 1, 0, -1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		/** Skip 0th index */
		Map = new int[N + 1][M + 1];
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= M; col++) {
				Map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		/** Rotate */
		for (int r = 1; r <= R; r++) {
			int startRow = 1;
			int startCol = 1;
			Moved = new int[N + 1][M + 1];
			
			while (true) {
				// Termination condition
				if (startRow > N/2 || startCol > M/2) break;
				
				int row = startRow;
				int col = startCol;
				
				// South
				/** Use breakpoint!!! */
				int breakPoint = N - startRow + 1;
				while (true) {
					int checkRow = row + Dx[0];
					int checkCol = col + Dy[0];
					Moved[checkRow][checkCol] = Map[row][col];
					
					row = checkRow;
					col = checkCol;
					
					if (row == breakPoint) {
						break;
					}
				}
				
				// East
				breakPoint = M - startCol + 1;
				while (true) {
					int checkRow = row + Dx[1];
					int checkCol = col + Dy[1];
					Moved[checkRow][checkCol] = Map[row][col];
					
					row = checkRow;
					col = checkCol;
					
					if (col == breakPoint) {
						break;
					}
				}
				
				// North
				breakPoint = startRow;
				while (true) {
					int checkRow = row + Dx[2];
					int checkCol = col + Dy[2];
					Moved[checkRow][checkCol] = Map[row][col];
					
					row = checkRow;
					col = checkCol;
					
					if (row == breakPoint) {
						break;
					}
				}
				
				// West
				breakPoint = startCol;
				while (true) {
					int checkRow = row + Dx[3];
					int checkCol = col + Dy[3];
					Moved[checkRow][checkCol] = Map[row][col];
					
					row = checkRow;
					col = checkCol;
					
					if (col == breakPoint) {
						break;
					}
				}
				
				startRow += 1;
				startCol += 1;
			}
			
			/** Change reference */
			Map = Moved;
		}
		
		/** Print out the result */
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				sb.append(Map[row][col]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	

}
