package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *	 창용 마을 무리의 개수
 */
public class D4_7465 {
	
	/** Number of test case, Number of person, Number of relations*/
	static int T, N, M;
	
	/** Parents array for implementing union-find data structure*/
	static int[] Parents;
	
	/** Initialize parents array */
	static void make() {
		Parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			Parents[i] = i;
		}
	}
	
	/** Find parent for certain person */
	static int findParent(int x) {
		/** If x does not equals to the parents of x */
		if (x != Parents[x]) {
			/** Find the parents of (x's parents) for finding the parent of x */
			return Parents[x] = findParent(Parents[x]);
		}
		
		/** If x equals to the parents of x */
		return Parents[x];
	}
	
	/** Union two person in one group */
	static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		/** if x is smaller than y, assign the parent of y with the parent of x*/
		if (x < y) {
			Parents[y] = x;
		/** Vice versa*/
		} else {
			Parents[x] = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				union(x, y);
			}
			
			/** Gather unique number of group */
			Set<Integer> set = new HashSet<Integer>();
			for (int i = 1; i <= N; i++) {
				set.add(findParent(i));
			}
			
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		
		System.out.println(sb);
	}
}
