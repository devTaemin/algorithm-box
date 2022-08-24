package com.ssafy.swea.day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 	[S/W 문재해결 응용] 4일차 - 하나로
 */
public class D4_1251 {
	
	/** Edge class */
	static class Edge implements Comparable<Edge>{
		/** Number of from node*/
		int from;
		/** Number of to Node */
		int to;
		/** Distance between from and to node */
		double distance;
		
		public Edge(int from, int to, double distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	/** Number of test case */
	static int T, N;
	
	/** Tax rate */
	static double E;
	
	/** Parent of node */
	static int[] Parents;
	
	/** Array of node */
	static ArrayList<Integer[]> Nodes;
	
	/** Array of edges */
	static ArrayList<Edge> Edges;
	
	/** Initialize parents array */
	public static void make() {
		Parents = new int[N];
		for (int i = 0; i < N; i++) {
			Parents[i] = i;
		}
	}
	
	/** Find parent of node */
	public static int findParent(int x) {
		if (x != Parents[x]) {
			return Parents[x] = findParent(Parents[x]);
		}
		
		return Parents[x];
	}
	
	/** Union the parents of node */
	public static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if (x < y) {
			Parents[y] = x;
		} else {
			Parents[x] = y;
		}
	}
	
	/** Calculate the distance between nodes */
	public static double calDistance(Integer[] from, Integer[] to) {
		return (Math.pow(from[0] - to[0], 2) + Math.pow(from[1] - to[1], 2));
	}
	

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Nodes = new ArrayList<Integer[]>();
			Edges = new ArrayList<Edge>();
			
			int[] x = new int[N];
			int[] y = new int[N];
			
			int idx = 0;
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				x[idx++] = Integer.parseInt(st.nextToken());					
			}
			
			idx = 0;
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				y[idx++] = Integer.parseInt(st.nextToken());
			}
			
			
			for (int i = 0; i < N; i++) {
				Nodes.add(new Integer[] {x[i], y[i], i});
			}
			
			
			E = Double.parseDouble(br.readLine());
			
			/** Make edges */
			for (int from = 0; from < N - 1; from++) {
				for (int to = from + 1; to < N; to++) {
					Integer[] fromIsland = Nodes.get(from);
					Integer[] toIsland = Nodes.get(to);
					
					double distance = calDistance(fromIsland, toIsland);
					Edges.add(new Edge(fromIsland[2], toIsland[2], distance));
				}
			}
			
			/** Sort in an ascending order */
			Collections.sort(Edges);
			double total = 0;
			int select = 0;
			
			make();
			for (int i = 0; i < Edges.size(); i++) {
				if (select == N - 1) break;
				
				Edge e = Edges.get(i);
				int fromParent = findParent(e.from);
				int toParent = findParent(e.to);
				
				/** 
				 * If both parents are same, edge could make cycle!!!
				 * You should avoid to add this edge  
				 * */
				if (fromParent == toParent) continue;
				
				union(e.from, e.to);
				select++;
				total += e.distance;
			}
			
			
			long result = Math.round(total * E);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}

}
