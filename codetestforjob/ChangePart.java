package com.ssafy.cfj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 부품 찾기 
 * @author taeminim
 *
 */
public class ChangePart {
	static int N, M;
	static Integer[] Parts;
	static StringBuilder Sb = new StringBuilder();
	
	public static void binarySearch(int find, int start, int end) {
		if (start > end) {
			Sb.append("no").append(" ");
			return;
		}
		
		int mid = (start + end) / 2;
		if (find == Parts[mid]) {
			Sb.append("yes").append(" ");
			return;
		} else if (find > Parts[mid]) {
			binarySearch(find, mid + 1, end);
		} else {
			binarySearch(find, start, mid - 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Parts = new Integer[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			Parts[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(Parts);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			int find = Integer.parseInt(st.nextToken());
			binarySearch(find, 0, N - 1);
		}
		
		System.out.println(Sb);
	
	}

}
