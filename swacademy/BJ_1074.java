package com.ssafy.bj.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * Z 
 * @author devTaemin
 *
 */
public class BJ_1074 {
	
	/** Exponent */
	static int N;
	
	/** Row, Col */
	static int R, C;
	
	/** Count */
	static int Count;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		/** Size */
		int size = (int) Math.pow(2, N);
		
		/** Count */
		Count = 0;
		find(size, 0, 0);
		
		/** Print out the result */
		
	}
	
	/** Traverse the map int the recursive z direction */
	public static void find(int size, int row, int col) {
		
		if (size == 1) {
			System.out.println(Count);
			return;
		}
		
		// 1st quarter
		if (R < row + size / 2 && C < col + size / 2) {
			find(size / 2, row, col);
		}
		// 2nd quarter
		else if (R < row + size /2 && C >= col + size / 2) {
			Count += size / 2 * size / 2;
			find(size / 2, row, col + size / 2);
		}
		// 3rd quarter
		else if (R >= row + size / 2 && C < col + size / 2) {
			Count += 2 * size / 2 * size / 2;
			find(size / 2, row + size / 2, col);
		}
		// 4th quarter
		else {
			Count += 3 * size / 2 * size / 2;
			find(size / 2, row + size / 2, col + size / 2);
		}
	}

}
