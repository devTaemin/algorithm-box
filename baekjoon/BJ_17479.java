package com.ssafy.bj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 정식당
 * @author devTaemin
 *
 */
public class BJ_17479 {
	public static int A, B, C, N;
	public static int CountNormal, CountSpecial, CountService;
	public static long Charge, NormalCharge;
	public static Map<String, Integer> TypeOfFood = new HashMap<String, Integer>();
	public static ArrayList<Map<String, Integer>> Menu = new ArrayList<Map<String, Integer>>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		Menu.add(new HashMap<String, Integer>());
		Menu.add(new HashMap<String, Integer>());
		Menu.add(new HashMap<String, Integer>());
		
		
		/** Set the normal food on menu */
		for (int i = 0; i < A; i++) {
			String food = sc.next();
			int price = sc.nextInt();
			TypeOfFood.put(food, 0);
			Menu.get(0).put(food, price);
		}
		
		/** Set the special food on menu*/
		for (int i = 0; i < B; i++) {
			String food = sc.next();
			int price = sc.nextInt();
			TypeOfFood.put(food, 1);
			Menu.get(1).put(food, price);
		}
		
		/** Set the service food on menu*/
		for (int i = 0; i < C; i++) {
			String food = sc.next();
			int price = 0;
			TypeOfFood.put(food, 2);
			Menu.get(2).put(food, price);
		}
		
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			String food = sc.next();
			int type = TypeOfFood.get(food);
			int charge = Menu.get(type).get(food);
			
			/** If normal menu */
			if (type == 0) {
				CountNormal++;
				NormalCharge += charge;
			} 
			/** If special menu */
			else if (type == 1) {
				CountSpecial++;	
			} 
			/** if service menu */
			else if (type == 2) {
				CountService++;
			}
			/** Accumulate the charge*/
			Charge += charge;
		}
		
		/** Judge the result*/
		/** Check if the number of service is over 2*/
		if (CountService >= 2) {
			System.out.println("No");
			return;
		}
		
		/** Check if the number of special is over 0*/
		/** Then the total charge of normal menu should be more or equal to 20_000*/
		if (CountSpecial > 0) {
			if (NormalCharge < 20_000) {
				System.out.println("No");
				return;
			}
		}
		
		/** Check if the number of service is 1 */
		/** Then the total charge should be more or equal to 50_000 */
		if (CountService == 1) {
			if (Charge < 50_000) {
				System.out.println("No");
				return;
			}
		}
		
		System.out.println("Okay");
	}

}
