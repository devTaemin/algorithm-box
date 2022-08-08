package com.ssafy.swea.day06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 암호문 
 * @author devTaemin
 *
 */
public class D3_1228 {
	/** Number of cryptogram */
	static int N;
	
	/** Number of command*/
	static int C;
	
	/** cryptogram */
	static ArrayList<String> Crypt;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream(new File("input.txt")));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		/** Loop test case */
		for (int tc = 0; tc < 10; tc++) {
			N = sc.nextInt();
			Crypt = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				Crypt.add(sc.next());
			}
			
			C = sc.nextInt();
			for (int i = 0; i < C; i++) {
				String command = sc.next();
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				/** Insert additional ciphertext to the target index */
				for (int j = 0; j < y; j++) {
					String additional = sc.next();
					Crypt.add(x + j, additional);
				}
			}
			
			sb.append("#").append(tc + 1);
			for (int i = 0; i < 10; i++) {
				sb.append(" ").append(Crypt.get(i));
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}
}
