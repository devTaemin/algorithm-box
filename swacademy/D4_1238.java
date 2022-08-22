package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 기본] 10일차 - Contact
 * @author devTaemin
 *
 */
public class D4_1238 {
	
	/** Number of node, Number of start node, Result sequence, Result number*/
	static int NumOfNode, Start, Sequence, Number;
	
	/** Array for check if visited and the sequence when the node is reached */	 
	static int[] Visited;
	
	/** adjList for storing connection */
	static ArrayList<ArrayList<Integer>> adjList;
	
	public static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(Start);
		Visited[Start] = 1;
		
		while (!queue.isEmpty()) {
			int from = queue.poll();
			
			boolean find = false;
			
			/** Traverse for connected nodes */
			for (int i = 0; i < adjList.get(from).size(); i++) {
				int to = adjList.get(from).get(i);
				
				/** If the node is not visited */
				if (Visited[to] == 0) {
					find = true;
					/** Accumulate the sequence from start + 1 */
					Visited[to] = Visited[from] + 1;
					queue.offer(to);
				}
			}
			
			/** If not found, the node is end */
			if (!find) {
				if (Sequence < Visited[from]) {
					Sequence = Visited[from];
					Number = from;
				} else if (Sequence == Visited[from]) {
					Number = (Number > from) ? Number : from;
				}
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			NumOfNode = Integer.parseInt(st.nextToken());
			Start = Integer.parseInt(st.nextToken());
			
			Visited = new int[101];
			adjList = new ArrayList<ArrayList<Integer>>();
			
			/** Initialize adjList */
			for (int i = 0; i <= 100; i++) {
				adjList.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < NumOfNode / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList.get(from).add(to);
			}
			
			Sequence = 0;
			Number = 0;
			BFS();
			sb.append("#").append(tc).append(" ").append(Number).append("\n");
		}
		
		System.out.println(sb);
	}
}
