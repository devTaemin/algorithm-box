package com.ssafy.swea.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 최적경로
 * @author devTaemin
 *
 */
public class D5_1247 {
	
	/** Node for containing location */
	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/** Number of testcase, Number of clients */
	static int T, N;
	
	/** Minimum distance for a test case */
	static int Minimum;
	
	/** Node for corporation and house */
	static Node Corp, House;
	
	/** Result of permutation */
	static int[] Sequence;
	
	/** Check if selected */
	static boolean[] IsSelected;
	
	/** Store clients */
	static Node[] Clients;
	
	/** Check possible cases */
	public static void permutation(int depth) {
		if (depth == N) {
			int distance = 0;
			int curRow = Corp.row;
			int curCol = Corp.col;
			
			for (int i = 0; i < N; i++) {
				Node next = Clients[Sequence[i]];
				int nextRow = next.row;
				int nextCol = next.col;
				
				distance += (Math.abs(curRow - nextRow) + Math.abs(curCol - nextCol));
				curRow = nextRow;
				curCol = nextCol;
			}
			
			distance += (Math.abs(curRow - House.row) + Math.abs(curCol - House.col));
			
			Minimum = Math.min(Minimum, distance);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (IsSelected[i]) continue;
			Sequence[depth] = i;
			IsSelected[i] = true;
			permutation(depth + 1);
			IsSelected[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		// T - > 1
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			Corp = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			House = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Sequence = new int[N];
			IsSelected = new boolean[N];
			Clients = new Node[N];
			
			for (int i = 0; i < N; i++) {
				Clients[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			Minimum = Integer.MAX_VALUE;
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(Minimum).append("\n");
		}
		
		System.out.println(sb);
	}

}
