package com.ssafy.bj.day16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 토마토
 * @author devTaemin
 *
 */
public class BJ_7576 {
	
	/** Size of column, Size of row, Count the number of un-riped tomatoes*/
	static int M, N, Count;
	
	/** Map */
	static int[][] Map;
	
	/** How many days the tomato in certain position takes to ripe */
	static int[][] Days;
	
	/** For BFS logic */
	static Queue<Integer[]> queue = new LinkedList<Integer[]>();
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Check if the range is valid */
	static boolean validateRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M);
	}
	
	/** BFS logic */
	static void BFS() {
		
		while (!queue.isEmpty()) {
			/** Current riped tomato */
			Integer[] cur = queue.poll();
			int row = cur[0];
			int col = cur[1];
			
			/** Influence near tomatoes in four directions */
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				/** Check the range validation */
				if (!validateRange(checkRow, checkCol)) continue;
				
				/** Check if it is not un-riped tomato */
				if (Map[checkRow][checkCol] != 0) continue;
				
				/** Check if target is already selected */
				if (Days[checkRow][checkCol] != -1) continue;
				
				/** Accumulate day */
				Days[checkRow][checkCol] = Days[row][col] + 1;
				
				/** Add riped tomato */
				queue.add(new Integer[] {checkRow, checkCol});
				
				/** Decrement the number of un-riped tomatoes */
				Count--;
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		Days = new int[N][M];
		for (int r = 0; r < N; r++) {
			Arrays.fill(Days[r], -1);
		}
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int type = Integer.parseInt(st.nextToken());
				
				/** Increment the number of un-riped tomatoes */
				if (type == 0) Count++;
				
				/** In case of riped tomato */
				else if (type == 1) {
					queue.offer(new Integer[] {r, c});
					Days[r][c] = 0;
				}
				Map[r][c] = type;
			}
		}
		
		
		BFS();
		
		/** If All of tomatoes are riped */
		if (Count == 0) {
			/** Find out the maximum days */
			int max = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					max = Math.max(Days[r][c], max);
				}
			}
			System.out.println(max);
			return;
		} else {
			System.out.println(-1);
			return;
		}
	}
}
