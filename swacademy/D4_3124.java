package com.ssafy.swea.day17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 	최소 스패닝 트리 
 */
public class D4_3124 {
	/** Edge class */
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	/** Number of test case, Number of vertex, Number of edges */
	static int T, V, E;
	
	/** Parents of each node */
	static int[] Parents;
	
	/** Edge array */
	static ArrayList<Edge> Edges;
	
	/** Initialize parents array */
	public static void make() {
		Parents = new int[V + 1];
		
		for (int i = 0; i <= V; i++) {
			Parents[i] = i;
		}
	}
	
	/** find the parent of node */
	public static int findParent(int x) {
		if (Parents[x] != x) {
			return Parents[x] = findParent(Parents[x]);
		}
		
		return Parents[x];
	}
	
	/** Unite two nodes */
	public static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if (x < y) {
			Parents[y] = x; 
		} else {
			Parents[x] = y;
		}
	}
	
	/** Check whether the given edge would create cycle or not*/
	public static boolean checkCycle(int x, int y) {
		return findParent(x) == findParent(y);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Edges = new ArrayList<Edge>();
			
			/** Store given edges */
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");

				Edges.add(new Edge(Integer.parseInt(st.nextToken()), 
						           Integer.parseInt(st.nextToken()), 
						           Integer.parseInt(st.nextToken())));
			}
			
			/** Sort in an ascending order of weights */
			Collections.sort(Edges);
			
			/** Initialize parents array */
			make();
			
			/** Count for saving the number of selected edges */
			int count = 0;
			
			/** Total for saving the accumulated weights */
			long total = 0;
			
			for (int i = 0; i < Edges.size(); i++) {
				/** If the number of selected edges equals to the number of vertex -1, terminate the loop */
				if (count == V - 1) break;
				Edge e = Edges.get(i);
				
				/** Avoid cycle */
				if (checkCycle(e.from, e.to)) continue;
				
				union(e.from, e.to);
				total+= e.weight;
				count++;				
			}
			
			
			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}
		
		System.out.println(sb);
	}
}
