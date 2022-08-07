package com.ssafy.cfj.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 큰 수의 법칙
 * @author devTaemin
 *
 */
public class BigNumberRule {
	static int N, M, K;
	static int[] Array;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		Array = new int[N];
		for (int i = 0; i < N; i++) {
			Array[i] = sc.nextInt();
		}
		
		Arrays.sort(Array);
		
		int biggest = Array[N - 1];
		int nextBiggest = Array[N - 2];			
		
		int times = M / (K + 1);
		int left = M % (K + 1);
		
		int total = biggest * times * K + nextBiggest * times + biggest * left;
		System.out.println(total);
	}

}
