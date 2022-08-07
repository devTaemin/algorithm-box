package com.ssafy.cfj.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 떡볶이 떡 만들
 * @author taeminim
 *
 */
public class RiceCake {
	static int N, M;
	static Integer[] Array;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		Array = new Integer[N];
		for (int i = 0; i < N; i++) {
			Array[i] = sc.nextInt();
		}
		
		Arrays.sort(Array);
		
		int start = 0;
		int end = Array[N - 1];
		int mid = 0;
		int max = 0;
		
		while (start <= end) {
			mid = (start + end) / 2; 
			
			int total = 0;
			for (int i = 0; i < N; i++) {
				int riceCake = Array[i];
				if (riceCake > mid) {
					total += riceCake - mid;
				}
			}
			
			if (M == total) {
				break;
			}
			else if (M < total) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(mid);
	}

}
