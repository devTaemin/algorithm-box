package com.ssafy.bj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/***
 * 숨바꼭질
 * @author devTaemin
 *
 */
public class BJ_1697 {
	
	/** Infinite value for initialization */
	static final int INF = (int)1e9;
	
	/** Start position, Target position */
	static int N, K;
	
	/** Minimum times taken to reach certain position */
	static int[] Distances;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		Distances = new int[200_001];
		Arrays.fill(Distances, INF);
		
		/** BFS */
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		
		/** Initialize start position with 0 time */
		Distances[N] = 0;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			/** If current position equals to the target position */
			if (cur == K) {
				System.out.println(Distances[cur]);
				return;
			}
			
			/** If current position is over 0, it can traverse to cur - 1 position*/
			if (cur > 0) {
				/** Compare */
				if (Distances[cur - 1] > Distances[cur] + 1) {
					Distances[cur - 1] = Distances[cur] + 1;
					queue.add(cur - 1);					
				}
			}
			
			/** If current position is below 200_000, it can traverse to cur + 1 position */
			if (cur < 200_000) {
				/** Compare */
				if (Distances[cur + 1] > Distances[cur] + 1) {
					Distances[cur + 1] = Distances[cur] + 1;
					queue.add(cur + 1);
				}
			}
			
			/** If current position * 2 is below or equals to 200_000, It can traverse to cur * 2 position */
			if (cur * 2 <= 200_000) {
				/** Compare */
				if (Distances[cur * 2] > Distances[cur] + 1) {
					Distances[cur * 2] = Distances[cur] + 1;
					queue.add(cur * 2);
				}
			}
		}
	}

}
