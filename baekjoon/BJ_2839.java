package com.ssafy.bj.day11;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 설탕 배달
 * @author devTaemin
 *
 */
public class BJ_2839 {
	
	static final int INF = (int)1e9;
	static int N;
	static int[] Kilos;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		Kilos = new int[N + 1];
		Arrays.fill(Kilos, INF);
		
		/** Use 3 kg*/
		for (int i = 1; i <= N; i++) {
			if (i % 3 == 0) {
				Kilos[i] = Math.min(Kilos[i], i / 3);
			}
		}
		
		/** Use 5 kg*/
		for (int i = 1; i <= N; i++) {
			if (i % 5 == 0) {
				Kilos[i] = Math.min(Kilos[i], i / 5);
			}
		}
		
		/** Find out minimum number of sugar packs*/
		int min = Kilos[N];
		for (int i = 1; i < N; i++) {
			if (Kilos[i] != INF && Kilos[N - i] != INF) {
				min = Math.min(min, Kilos[i] + Kilos[N - i]);
			}
		}
		
		/** If INF, Unable to make exact kilogram */
		if (min == INF) {
			System.out.println(-1);
		} else {
			System.out.println(min);			
		}
	}
}
