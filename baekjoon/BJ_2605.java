package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2605 {
	
	static int N;
	static int[] Array;
	static int[] Sequence;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Array = new int[N + 1];
		Sequence = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			Array[i] = Sequence[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			int number = Integer.parseInt(st.nextToken());
			Sequence[i] -= number;
			for (int j = 1; j < i; j++) {
				if (Sequence[j] >= Sequence[i]) {
					Sequence[j] += 1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Sequence[j] == i) {
					System.out.printf("%d ", Array[j]);
					break;
				}
			}
		}
	}
}
