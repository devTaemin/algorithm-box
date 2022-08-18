package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1987_2 {
	
	static class Node {
		int row;
		int col;
		ArrayList<Character> history;
		
		public Node(int row, int col, ArrayList<Character> history) {
			this.row = row;
			this.col = col;
			this.history = history;
		}
		
		public boolean checkHistory(char alphabet) {
			if (history == null || history.isEmpty()) {
				return true;
			}
			
			for (Character c : history) {
				if (c == alphabet) {
					return false;
				}
			}
			
			return true;
		}
		
		public ArrayList<Character> getHistory() {
			ArrayList<Character> result = new ArrayList<>();
			for (Character c : history) {
				result.add(c);
			}
			
			return result;
		}
		
		public void addHistory(Character alphabet) {
			history.add(alphabet);
		}
	}
	
	static int R, C, Maximum;
	static char[][] Map;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= R && col >= 1 && col <= C);
	}
	
	public static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		ArrayList<Character> array = new ArrayList<Character>();
		array.add(Map[1][1]);
		queue.add(new Node(1, 1, array));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int row = cur.row;
			int col = cur.col;
			
			Maximum = Math.max(Maximum, cur.history.size());				
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				if (!validateRange(checkRow, checkCol)) continue;
				if (!cur.checkHistory(Map[checkRow][checkCol])) continue;				
				ArrayList<Character> now = cur.getHistory();
				Node next = new Node(checkRow, checkCol, now);
				next.addHistory(Map[checkRow][checkCol]);
				queue.add(next);
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R + 1][C + 1];
		for (int row = 1; row <= R; row++) {
			String line = br.readLine();
			for (int col = 1; col <= C; col++) {
				Map[row][col] = line.charAt(col - 1);
			}
		}
		
		Maximum = 0;
		BFS();
		System.out.println(Maximum);
	}
}
