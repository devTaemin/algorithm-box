package com.ssafy.cfj.dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 효율적인 화폐 구성 
 * @author taeminim
 *
 */
public class MonetaryComposition {
	static int N, M;
	static Integer[] Currency;
	static int[] Minimum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		/** Types of currency */
		Currency = new Integer[N];
		
		/** Minimum requirement for composing money */
		Minimum = new int[M + 1];
		
		/** Initialize composition */
		Arrays.fill(Minimum, -1);
		
		for (int i = 0; i < N; i++) {
			int currency = sc.nextInt();
			Currency[i] = currency;
			
			if (currency <= M) {
				Minimum[currency] = 1;				
			}
		}
		
		/** Sort the order of currency in an ascending order */
		Arrays.sort(Currency);
		
		/** From 1 won to M won */
		for (int i = 1; i <= M; i++) {
			
			/** Find out the optimal currency */
			for (int j = 0; j < N; j++) {
				int currency = Currency[j];
				
				/** If currency is more expensive than target money */
				if (i < currency) {
					break;
				}
				
				/** If currency could not be adopted */
				if (Minimum[i - currency] == -1) {
					continue;
				}
				
				/** Initialize minimum total of composition */
				if (Minimum[i] == -1) {
					Minimum[i] = Minimum[i - currency] + 1;
				} else {
					/** Compare and choose the minimum composition */
					int minCount = Math.min(Minimum[i - currency] + 1, Minimum[i]);
					Minimum[i] = minCount;
				}
			}

		}
		
		for (int i = 0; i <= M; i++) {
			System.out.printf("%d ", Minimum[i]);
		}
	}

}
