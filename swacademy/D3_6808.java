package com.ssafy.swea.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 규영이와 인영이의 카드게임
 * @author devTaemin
 *
 */
public class D3_6808 {
	/** Number of test case */
	static int T;
	
	/** Accumulate the number of win, lose */
	static int Win, Lose;
	
	/** Array for storing cards */
	static int[] My, Opponent, Sequence;
	
	/** Check if visited or not */
	static boolean[] Visited;
	
	/** Mix the sequence of opponent's card */
	public static void DFS(int depth) {
		/** If all cards are done */
		if (depth == 9) {			
			int myScore = 0;
			int oppScore = 0;
			
			/** Compare the point */
			for (int i = 0; i < 9; i++) {
				int myPoint = My[i];
				int oppPoint = Opponent[Sequence[i]];
				
				if (myPoint > oppPoint) {
					myScore += (myPoint + oppPoint);
				} else if (myPoint < oppPoint) {
					oppScore += (myPoint + oppPoint);
				}
			}
			
			if (myScore > oppScore) {
				Win++;
			} else if (myScore < oppScore) {
				Lose++;
			}
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!Visited[i]) {
				Sequence[depth] = i;
				Visited[i] = true;
				DFS(depth + 1);
				Visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		/** Play */
		for (int tc = 1; tc <= T; tc++) {
			My = new int[9];
			Opponent = new int[9];
			Sequence = new int[9];
			Visited = new boolean[19];
			Win = 0;
			Lose = 0;
			
			/** Get my cards */
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				My[i] = Integer.parseInt(st.nextToken());
				Visited[My[i]] = true;
			}
			
			/** Get opponent cards */
			int index = 0;
			for (int i = 1; i <= 18; i++) {
				if (!Visited[i]) {
					Opponent[index++] = i;
				}
			}
			
			/** Initialize visited array */
			Visited = new boolean[9];
			
			/** Mix the sequence */
			DFS(0);
			
			
			sb.append("#").append(tc).append(" ").append(Win).append(" ").append(Lose).append("\n");
		}
		
		System.out.println(sb);
	}
}
