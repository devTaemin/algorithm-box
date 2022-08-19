package com.ssafy.swea.day14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3234 {
	
	static int N, T, Total, Count;
	static int Left, Right;
	static boolean[] Visited;
	static boolean[] Check;
	static int[] Sequence;
	static int[] Weights;
	
	public static void play(int depth) {
		if (depth == N) {
			Count++;
			return;
		}
		
		Left += Weights[Sequence[depth]];
		play(depth + 1);
		Left -= Weights[Sequence[depth]];
		
		if (Right + Weights[Sequence[depth]] <= Left) {
			Right += Weights[Sequence[depth]];
			play(depth + 1);
			Right -= Weights[Sequence[depth]];
		}
	}

	
	public static void permutation(int depth) {
		if (depth == N) {
			Check = new boolean[N];
			play(0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (Visited[i]) continue;
			Sequence[depth] = i;
			Visited[i] = true;
			permutation(depth + 1);
			Visited[i] = false;
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Total = 0;
			Weights = new int[N];
			Sequence = new int[N];
			Visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Weights[i] = Integer.parseInt(st.nextToken());
				Total += Weights[i];
			}
			
			Left = 0;
			Right = 0;
			Count = 0;
			permutation(0);
			sb.append("#").append(tc).append(" ").append(Count).append("\n");
		}
		
		System.out.println(sb);
	}

}
