package com.ssafy.bj.day16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * ABCDE
 * @author devTaemin
 *
 */
public class BJ_13023 {
	
	/** Number of student, Number of relationship */
	static int N, M;
	
	/** Check if answer is found */
	static boolean Found;
	
	/** AdjList */
	static ArrayList<ArrayList<Integer>> AdjList;
	
	/** Find the relationship */
	public static void DFS(int cur, int depth, boolean[] visited) {
		/** If answer is found, quit method */
		if (Found) return;
		
		/** If five nodes are found */
		if (depth == 5) {
			Found = true;
			return;
		}
		
		/** Check connected nodes for current node */
		for (int i = 0; i < AdjList.get(cur).size(); i++) {
			/** Next is potentially connect-able node */
			int next = AdjList.get(cur).get(i);
			/** If the node is not visited */
			if (!visited[next]) {
				/** Copy current visited array */
				boolean[] copy = Arrays.copyOf(visited, visited.length);
				/** Check 'next' node is visited */
				copy[next] = true;
				/** Find more nodes */
				DFS(next, depth + 1, copy);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/** Initialize adjacency list */
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			AdjList.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			AdjList.get(from + 1).add(to + 1);
			AdjList.get(to + 1).add(from + 1);
		}
		
		
		Found = false;
		/** Set start point */
		for (int cur = 1; cur <= N; cur++) {
			boolean[] visited = new boolean[N + 1];
			visited[cur] = true;
			DFS(cur, 1, visited);
			if(Found) break;
		}
		
		if (Found) System.out.println(1);
		else System.out.println(0);
	}
}
