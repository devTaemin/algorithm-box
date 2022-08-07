package com.ssafy.cfj.sort;

import java.util.Scanner;

/**
 * 위에서 아래로 
 * @author taeminim
 *
 */
public class UpToDown {
	static int N;
	static int[] Array;
	
	/** 1. Selection sorting (Descending order) */
	public static void selection() {
		for (int index = 0; index < N; index++) {
			int maxIndex = index;
			for (int pick = index; pick < N; pick++) {
				maxIndex = (Array[maxIndex] > Array[pick]) ? maxIndex : pick;
			}
			
			swap(index, maxIndex);
		}
	}
	
	public static void swap(int from, int to) {
		int temp = Array[from];
		Array[from] = Array[to];
		Array[to] = temp;
	}
	
	public static void print() {
		for (int elem : Array) {
			System.out.printf("%d ", elem);
		}
		System.out.println();
	}
	
	
	/** 2. Insertion sorting (Descending order) */
	public static void insertion() {
		for (int pick = 1; pick < N; pick++) {
			for (int compare = pick; compare >= 1; compare--) {
				if (Array[compare] > Array[compare - 1]) {
					swap(compare, compare - 1);
				} else {
					break;
				}
			}
		}
	}
	
	
	/** 3. Quick sorting (Descending order) */
	public static void quick(int start, int end) {
		if (start >= end) {
			return;
		}
		
		int pivot = start;
		int left = start;
		int right = end;
		
		while (left <= right) {
			// left should find out the smaller value
			while (Array[left] > Array[pivot] && left + 1 <= end) {
				left++;
			}
			
			// right should find out the bigger value
			while (Array[right] < Array[pivot] && right - 1 >= start) {
				right--;
			}
			
			if (left > right) {
				swap(pivot, right);
				break;
			} else {
				swap(left, right);
			}
		}
		
		quick(start, right - 1);
		quick(right + 1, end);
	}
	
	
	public static void main(String[] args) {
		// Sort numbers in a descending order
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Array = new int[N];
		
		for (int i = 0; i < N; i++) {
			Array[i] = sc.nextInt();
		}
		
		System.out.print("Choose selection(0), insertion(1), quick(2): ");
		int command = sc.nextInt();
		
		switch(command) {
		case 0:
			selection();
			break;
		case 1:
			insertion();
			break;
		case 2:
			quick(0, N - 1);
			break;
		}
		
		print();
	}
}
