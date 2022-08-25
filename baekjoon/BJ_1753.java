package com.ssafy.bj.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *	최단 경로 
 */
public class BJ_1753 {
	/** Node */
	static class Node {
		int to, distance;
		Node next;
		
		public Node(int to, int distance, Node next) {
			this.to = to;
			this.distance = distance;
			this.next = next;
		}
	}
	
	/** Infinite number */
	static final int INF = (int)1e9;
	
	/** Number of vertex, Number of edge, Start vertex */
	static int V, E, K;
	
	/** Minimum distance for target node */
	static int[] minDistance;
	
	/** adjList */
	static Node[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		/** Initialize minimum distance with infinite values */
		minDistance = new int[V + 1];
		Arrays.fill(minDistance, INF);
		
		/** Save adjList */
		adjList = new Node[V + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, distance, adjList[from]);
		}
		
		/** Set the distance of start vertex */
		minDistance[K] = 0;
		
		/** Check if visited */
		boolean[] visited = new boolean[V + 1];

		for (int c = 0; c < V; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			/** Find the minimum vertex */
			for (int i = 1; i <= V; i++) {
				if (!visited[i] && min > minDistance[i]) {
					minVertex = i;
					min = minDistance[i];
				}
			}
			
			/** Terminate if not found */
			if (minVertex == -1) break;
			
			/** Check visited */
			visited[minVertex] = true;
			
			/** Update the routes with minimum distance */
			for (Node node = adjList[minVertex]; node != null; node = node.next) {
				if (minDistance[node.to] > minDistance[minVertex] + node.distance) {
					minDistance[node.to]= minDistance[minVertex] + node.distance; 
				}
			}
		}
		
		/** Print out the result */
		for (int i = 1; i <= V; i++) {
			int distance = minDistance[i];
			if (distance == INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance);
			}
		}
	}
}
