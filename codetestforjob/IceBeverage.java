package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 음료수 얼려 먹기 
 * @author taeminim
 *
 *
 *
15 14
00000111100000
11111101111110
11011101101110
11011101100000
11011111111111
11011111111100
11000000011111
01111111111111
00000000011111
01111111111000
00011111111000
00000001111000
11111111110011
11100011111111
11100011111111
 */

class Ice {
	int row;
	int col;
	
	public Ice(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class IceBeverage {
	
	static int Row, Col, Count;
	static Queue<Ice> queue;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	static int[][] Map;
	static boolean[][] IsSelected;
	
	public static void BFS(int row, int col) {
		while (!queue.isEmpty()) {
			Ice ice = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int checkRow = ice.row + Dx[d];
				int checkCol = ice.col + Dy[d];
				
				// Validate range
				if (checkRow < 0 || checkRow >= Row || checkCol < 0 || checkCol >= Col) {
					continue;
				}
				
				// Check condition
				if (Map[checkRow][checkCol] == 0 && !IsSelected[checkRow][checkCol]) {
					IsSelected[checkRow][checkCol] = true;
					queue.add(new Ice(checkRow, checkCol));
				}
			}
		}
		
		Count++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Row = Integer.parseInt(st.nextToken());
		Col = Integer.parseInt(st.nextToken());
		
		Map = new int[Row][Col];
		IsSelected = new boolean[Row][Col];
		
		for (int row = 0; row < Row; row++) {
			String[] inputs = br.readLine().split("");
			
			for (int col = 0; col < Col; col++) {
				Map[row][col] = Integer.parseInt(inputs[col]);
			}
		}
		
		for (int row = 0; row < Row; row++) {
			for (int col = 0; col < Col; col++) {
				if (Map[row][col] == 0 && !IsSelected[row][col]) {
					queue = new LinkedList<Ice>();
					queue.add(new Ice(row, col));
					IsSelected[row][col] = true;
					BFS(row, col);
				}
			}
		}
		
		System.out.println(Count);
	}

}
