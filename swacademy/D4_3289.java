package com.ssafy.swea.day17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 서로소 집합
 */
public class D4_3289 {
	
	/** Representative node for the set */
	static int[] Parents;
	
	/** Initialize the array of parents */
	public static void make(int n) {
		Parents = new int[n + 1];
		
		for (int i = 0; i <= n; i++) {
			Parents[i] = i;
		}
	}
	
	/** Find the parent of certain node */
	public static int findParent(int x) {
		if (Parents[x] != x) {
			return Parents[x] = findParent(Parents[x]);
		}
		
		return Parents[x];
	}
	
	/** Unite two set */
	public static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if (x < y) {
			Parents[y] = x; 
		} else {
			Parents[x] = y;
		}
	}
	
	/** Check if two nodes are in the same set */
	public static boolean checkSet(int x, int y) {
		return findParent(x) == findParent(y);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		/** Test case */
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringBuilder local = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			/** Number of nodes */
			int n = Integer.parseInt(st.nextToken());
			
			/** Number of commands */
			int m = Integer.parseInt(st.nextToken());
			
			/** Initialize parents */
			make(n);
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int command = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				/** Unite */
				if (command == 0) {
					union(x, y);
				} 
				/** Check */
				else if (command == 1) {
					if(checkSet(x, y)) {
						local.append(1);
					} else {
						local.append(0);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(local).append("\n");
		}
		
		System.out.println(sb);
	}

}
