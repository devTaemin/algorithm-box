package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 스도쿠
 * @author devTaemin
 *
 */
public class BJ_2580 {
	
	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int N;
	static boolean Quit;
	static int[][] Map;
	static ArrayList<Node> Nodes;
	
	static boolean isPromising(Node node) {
		int row = node.row;
		int col = node.col;
		
		// Horizontal
		for (int c = 0; c < 9; c++) {
			if (col == c) continue;
			if (Map[row][c] == Map[row][col]) {
				return false;
			}
		}
		
		// Vertical
		for (int r = 0; r < 9; r++) {
			if (row == r) continue;
			if (Map[r][col] == Map[row][col]) {
				return false;
			}
		}
		
		// Square
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;
		
		for (int r = startRow; r < startRow + 3; r++) {
			for (int c = startCol; c < startCol + 3; c++) {
				if (row == r && col == c) continue;
				if (Map[r][c] == Map[row][col]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void printSudoku() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {				
				System.out.printf("%d ", Map[r][c]);
			}
			System.out.println();
		}
	}
	
	public static void backtracking(int depth) {
		if (Quit) return;
		
		if (depth == N) {
			printSudoku();
			Quit = true;
			return;
		}
		
		for (int check = 1; check <= 9; check++) {
			Node node = Nodes.get(depth);
			int row = node.row;
			int col = node.col;
			int current = Map[row][col];
			Map[row][col] = check;
			
			if (isPromising(Nodes.get(depth))) {
				backtracking(depth + 1);
			}
			/** Restore */
			Map[row][col] = current;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Map = new int[9][9];
		Nodes = new ArrayList<Node>();
		
		for (int row = 0; row < 9; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < 9; col++) {
				int number = Integer.parseInt(st.nextToken());
				Map[row][col] = number;
				
				if (number == 0) {
					Nodes.add(new Node(row, col));
				}
			}
		}
			
		N = Nodes.size();
		Quit = false;
		backtracking(0);
	}
}
