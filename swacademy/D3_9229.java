package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 한빈이와 Spot Mart
 * @author devTaemin
 *
 */
public class D3_9229 {
	static int T, N, M, MaxWeight;
	static int[] Weights;
	static int[] Index;
	
	
	public static void DFS(int start, int depth) {
		if (depth == 2) {
			int total = 0;
			for (int i = 0; i < 2; i++) {
				total += Weights[Index[i]];
			}
			
			if (total <= M) {
				MaxWeight = Math.max(MaxWeight, total);
			}
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			Index[depth] = i;
			DFS(i + 1, depth + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			MaxWeight = -1;
			
			Weights = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				Weights[i] = Integer.parseInt(st.nextToken());
			}
			
			Index = new int[2];
			DFS(1, 0);
			
			sb.append("#").append(tc).append(" ").append(MaxWeight).append("\n");
		}
		
		System.out.println(sb);
	}

}
