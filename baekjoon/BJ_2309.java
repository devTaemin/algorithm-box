package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2309 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int total = 0;
		int[] dwarf = new int[9];
		for (int i = 0; i <= 8; i++) {
			int height = Integer.parseInt(br.readLine());
			total += height;
			dwarf[i] = height;
		}
		
		
		
		Arrays.sort(dwarf);
		total -= 100;
		int i, j = 0;
		boolean find = false;
		for (i = 0; i < 8; i++) {
			for (j = i + 1; j <= 8; j++) {
				if (dwarf[i] + dwarf[j] == total) {
					find = true;
					break;
				}
			}
			if (find) break;
		}
		
		
		for (int k = 0; k < 9; k++) {
			if (k != i && k != j) {
				System.out.println(dwarf[k]);
			}
		}
		
	}

}
