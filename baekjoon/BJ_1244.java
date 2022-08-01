package com.ssafy.bj;

import java.util.Scanner;

/**
 * 백준 1244번, 스위치 켜고 끄기
 * @author devTaemin
 *
 */
public class BJ_1244 {
	public static int NumOfSwitch, NumOfStudent;
	public static boolean[] Switch;
	
	/** The logic how male plays the rule */
	public static void maleAction(int location) {
		int idx = location;
		int i = 1;
		while (idx * i <= NumOfSwitch) {
			/** Change the state*/
			Switch[idx * i] = !Switch[idx * i];
			i++;
		}
	}
	
	/** The logic how female plays the rule */
	public static void femaleAction(int location) {
		/** Change the current state*/
		int idx = location;
		Switch[idx] = !Switch[idx];
		
		/** Check the symmetric case*/
		int front = idx - 1;
		int rear = idx + 1;
		while (true) {
			// Validate range
			if (front < 1 || front > NumOfSwitch || rear < 1 || rear > NumOfSwitch) {
				return;
			}
				
			// Compare the state
			if (Switch[front] == Switch[rear]) {
				Switch[front] = !Switch[front]; 
				Switch[rear] = !Switch[rear];
			} else {
				return;
			}
			
			// Change the index
			front -= 1;
			rear += 1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NumOfSwitch = sc.nextInt();
		Switch = new boolean[NumOfSwitch + 1];
		
		/** 
		 * Store the state of switch in the boolean array
		 * If the swtich is turn on (1), store true in the array
		 * Else, store false in the array
		 * */
		for (int idx = 1; idx <= NumOfSwitch; idx++) {
			int state = sc.nextInt();
			if (state == 0) {
				Switch[idx] = false;
			} else {
				Switch[idx] = true;
			}
		}
		
		NumOfStudent = sc.nextInt();
		for (int i = 0; i < NumOfStudent; i++) {
			int gender = sc.nextInt();
			int location = sc.nextInt();
			
			/** Male */
			if (gender == 1) {
				maleAction(location);
			} 
			/** Female */
			else if (gender == 2) {
				femaleAction(location);
			}
		}
		
		/** Print out the result*/
		for (int i = 1; i <= NumOfSwitch; i++) {
			/** Convert true to 1, false to 0 */
			int state = (Switch[i] == true) ? 1 : 0;
			System.out.printf("%d ", state);
			
			if (i % 20 == 0) {
				System.out.println();
			}
		}
	}
}
