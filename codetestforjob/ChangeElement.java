package com.ssafy.cfj.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 두 배열의 원소 교
 * @author taeminim
 *
 */
public class ChangeElement {
	static int N, K;
	static Integer[] A, B;
	
	public static void swap(int from, int to) {
		int temp = A[from];
		A[from] = B[to];
		B[to] = temp;
	}

	public static void main(String[] args) {
		//A 배열의 최소 자연수와 B배열의최대 자연수를 교환하는 작업  
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		A = new Integer[N];
		B = new Integer[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		for (int i = 0; i < N; i++) {
			B[i] = sc.nextInt();
		}
		
		for (int i = 0; i < K; i++) {
			Arrays.sort(A);
			Arrays.sort(B, Collections.reverseOrder());
			
			/** Only if the element of A is smaller than the element of B */
			if (A[0] < B[0]) {
				swap(0, 0);				
			} else {
				break;
			}
		}
		
		int total = 0;
		for (int i = 0; i < N; i++) {
			total += A[i];
		}
		
		System.out.println(total);
	}

}
