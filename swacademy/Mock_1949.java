package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 등산로 조성
 * @author devTaemin
 *
 */
public class Mock_1949 {
	
	static class Slope {
		int height;
		int row;
		int col;
		int depth;
		ArrayList<Slope> routes;
		
		public Slope(int height, int row, int col, int depth) {
			this.height = height;
			this.row = row;
			this.col = col;
			this.depth = depth;
			routes = new ArrayList<>();
		}
		
		public void addRoute(Slope slope) {
			routes.add(slope);
		}
		
		public ArrayList<Slope> getRouteList() {
			return routes;
		}
		
		public void setRouteList(ArrayList<Slope> routelist) {
			routes = routelist;
		}
		
		public boolean checkVisited(int row, int col) {
			for (Slope s : routes) {
				if (s.row == row && s.col == col) {
					return true;
				}
			}
			
			return false;
		}
		
		public boolean isVisited(Slope slope) {
			for (Slope s : routes) {
				if (s.equals(slope)) {
					return true;
				}
			}
			
			return false;
		}
		
		@Override
		public boolean equals(Object obj) {
			Slope other = (Slope)obj;
			return (this.row == other.row && this.col == other.col);
		}	
	}
	
	/** Global maximum route */
	static int Maximum;
	
	/** Number of test case*/
	static int T;
	
	/** Size of map */
	static int N;
	
	/** Decrease the height of slope */
	static int K;
	
	/** Map */
	static int[][] Map;
	
	/** Array for the top slopes */
	static ArrayList<Slope> Array;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** BFS for checking routes */
	public static void BFS(Slope slope) {
		Queue<Slope> queue = new LinkedList<>();
		queue.add(new Slope(slope.height, slope.row, slope.col, slope.depth));
		
		while (!queue.isEmpty()) {
			Slope current = queue.poll();
			int height = current.height;
			int row = current.row;
			int col = current.col;
			int depth = current.depth;
			boolean stop = true;
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				if (!validateRange(checkRow, checkCol)) continue;

				if (current.checkVisited(checkRow, checkCol)) continue;
				
				if (Map[checkRow][checkCol] >= height) continue;
				
				
				stop = false;
				Slope next = new Slope(Map[checkRow][checkCol], checkRow, checkCol, depth + 1);
				next.setRouteList(current.getRouteList());
				queue.add(next);
			}
			
			if (stop) {
				Maximum = Math.max(Maximum, depth);
			}
		}
	}
	
	/** Validate range */
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >=1 && col <= N);
	}
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Maximum = 0;
			
			Map = new int[N + 1][N + 1];
			int top = 0;
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					int slope = Integer.parseInt(st.nextToken());
					Map[row][col] = slope;
					top = Math.max(slope, top);
				}
			}
			
			Array = new ArrayList<>();
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					if (Map[row][col] == top) {
						Array.add(new Slope(top, row, col, 1));
					}
				}
			}
			
			
			for (int i = 0; i < Array.size(); i++) { 
				Slope slope = Array.get(i);
				
				for (int k = 1; k <= K; k++) {
					for (int row = 1; row <= N; row++) {
						for (int col = 1; col <= N; col++) {
							if (slope.row == row && slope.col == col) continue;
							Map[row][col] -= k;
							BFS(slope);
							Map[row][col] += k;
						}
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(Maximum).append("\n");
		}
		
		System.out.println(sb);
	}
}
