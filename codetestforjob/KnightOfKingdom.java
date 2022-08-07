package com.ssafy.cfj.implemantation;

import java.util.Scanner;

/**
 * 왕실의 나이트
 * @author devTaemin
 *
 */
public class KnightOfKingdom {
	static int Count, Row, Col;
	
	static int[] Dvx = { -2, 2 };
	static int[] Dvy = { -1, 1 };
	
	static int[] Dhx = { -1, 1 };
	static int[] Dhy = { -2, 2 };
	
	static int[][] Chess = new int[9][9];
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] input = sc.next().toCharArray();
		Col = input[0] - 'a' + 1;
		Row = input[1] - '0';
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int checkRow = Row + Dvx[i];
				int checkCol = Col + Dvy[j];
				if (checkRow < 1 || checkRow > 8 || checkCol < 1 || checkCol > 8) continue;
				Count++;
			}
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int checkRow = Row + Dhx[i];
				int checkCol = Col + Dhy[j];
				if (checkRow < 1 || checkRow > 8 || checkCol < 1 || checkCol > 8) continue;
				Count++;
			}
		}
		
		System.out.println(Count);
	}

}
