package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/***
 * 격자판의 숫자 이어 붙이기
 * @author devTaemin
 *
 */
public class D4_2819 {
	static int T;
	static Node[] Sequence;
	static Set<String> Unique;
	static int[][] Map;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void DFS(int row, int col, int depth) {
		if (depth == 7) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 7; i++) {
				Node node = Sequence[i];
				sb.append(Map[node.row][node.col]);
			}
			
			Unique.add(sb.toString());
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int checkRow = row + Dx[d];
			int checkCol = col + Dy[d];
			
			if (checkRow < 1 || checkRow > 4 || checkCol < 1 || checkCol > 4) {
				continue;
			}
			
			Sequence[depth] = new Node(checkRow, checkCol);
			DFS(checkRow, checkCol, depth + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			Map = new int[5][5];
			Unique = new HashSet<String>();
			
			for (int row = 1; row <= 4; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= 4; col++) {
					Map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int row = 1; row <= 4; row++) {
				for (int col = 1; col <= 4; col++) {
					Sequence = new Node[7];
					DFS(row, col, 0);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(Unique.size()).append("\n");	
		}
		
		System.out.println(sb);
	}
}
