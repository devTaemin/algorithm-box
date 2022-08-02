package com.ssafy.swea.day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * [S/W 문제해결 기본] 1일차 - Flatten
 * @author devTaemin
 *
 */
public class D3_1208 {
	public static int T, NumOfFlatten;
	public static ArrayList<Integer> Boxes;
	
	public static void main(String[] args) {
		/** Read inputs from file*/
//		File file = new File("input.txt");
//		try {
//			System.setIn(new FileInputStream(file));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		Scanner sc = new Scanner(System.in);
		T = 10;
		int[] results = new int[T];
		
		/** loop the test case */
		for (int tc = 0; tc < T; tc++) {
			/** Array for storing the number of boxes */
			Boxes = new ArrayList<>();
			
			/** Variable for the number of flatten*/
			NumOfFlatten = sc.nextInt();
			
			/** Store the number of boxes */
			for (int idx = 0; idx < 100; idx++) {
				Boxes.add(sc.nextInt());
			}
			
			/** Start */
			for (int i = 0; i < NumOfFlatten; i++) {
				// Ascending Order
				Collections.sort(Boxes);
				
				/** Check the degree of flatten */
				if (Boxes.get(99) - Boxes.get(0) == 1) {
					break;
				}
				
				/** Flatten */
				Boxes.set(0, Boxes.get(0) + 1);
				Boxes.set(99, Boxes.get(99) - 1);
			}
			
			/** Final sorting */
			Collections.sort(Boxes);
			
			results[tc] = Boxes.get(99) - Boxes.get(0);

		}
		
		/** Print out the results */
		for (int tc = 0; tc < T; tc++) {
			System.out.printf("#%d %d%n", tc + 1, results[tc]);
		}
	}
}
