package com.ssafy.bj.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 탑
 * @author devTaemin
 *
 */
public class BJ_2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> Tops = new Stack<>();
		Stack<Integer> Highest = new Stack<>();
		
		Tops.push(0);
		Highest.push(0);

		st = new StringTokenizer(br.readLine());
		
		/** Play */
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			
			while (!Highest.isEmpty()) {
				int top = Tops.peek(); 
				int index = Highest.peek();
				
				if (top < value) {
					Tops.pop();
					Highest.pop();
				} else {
					sb.append(index + " ");
					Tops.push(value);
					Highest.push(i);
					break;
				}
			}
			
			// Check
			if (Highest.isEmpty()) {
				sb.append(0 + " ");
				Tops.push(value);
				Highest.push(i);
			}
		}
		
		System.out.println(sb);
		
	}

}
