package com.ssafy.bj;

import java.util.Scanner;

public class BJ_3040 {
	
	static final int N = 9;
	static int Total;
	static boolean IsFound;
	static int[] Array;
	static int[] Exclude;
	
	public static void DFS(int start, int depth) {
		if (IsFound) return;
		
		if (depth == 2) {
			int localTotal = Total;
			for (int i = 0; i < 2; i++) {
				localTotal -= Array[Exclude[i]];
			}
			
			if (localTotal == 100) {
				for (int i = 0; i < N; i++) {
					if (i != Exclude[0] && i != Exclude[1]) {
						System.out.println(Array[i]);
					}
				}
				
				IsFound = true;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			Exclude[depth] = i;
			DFS(i + 1, depth + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Array = new int[N];
		Exclude = new int[N];
		
		for (int i = 0; i < N; i++) {
			int number = sc.nextInt();
			Array[i] = number;
			Total += number;
		}
		
		IsFound = false;
		DFS(0, 0);
	}
}
