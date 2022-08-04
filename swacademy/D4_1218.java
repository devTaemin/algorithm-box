package com.ssafy.swea.day04;

import java.util.Scanner;
import java.util.Stack;

/**
 * [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
 * @author devTaemin
 *
 */
public class D4_1218 {
	static int T, N;
	static char[] Inputs;
	static Stack<Character> Container;
	
	static final char LeftParenthesis = '(';
	static final char LeftBrace = '{';
	static final char LeftBracket = '[';
	static final char LeftArrow = '<';
	
	static final char RightParenthesis = ')';
	static final char RightBrace = '}';
	static final char RightBracket = ']';
	static final char RightArrow = '>';

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = 10;
		int[] results = new int[T];
		
		for (int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			Inputs = sc.next().toCharArray();
			Container = new Stack<>();
			
			loop: 
			for (int i = 0; i < Inputs.length; i++) {
				char c = Inputs[i];
				switch(c) {
				case LeftParenthesis:
					Container.add(LeftParenthesis);
					break;
				case LeftBrace:
					Container.add(LeftBrace);
					break;
				case LeftBracket:
					Container.add(LeftBracket);
					break;
				case LeftArrow:
					Container.add(LeftArrow);
					break;
				case RightParenthesis:
					if (!Container.isEmpty() && Container.peek() == LeftParenthesis) {
						Container.pop();
					} else {
						Container.add(LeftParenthesis);
						break loop;
					}
					break;
				case RightBrace:
					if (!Container.isEmpty() && Container.peek() == LeftBrace) {
						Container.pop();
					} else {
						Container.add(LeftBrace);
						break loop;
					}
					break;
				case RightBracket:
					if (!Container.isEmpty() && Container.peek() == LeftBracket) {
						Container.pop();
					} else {
						Container.add(LeftBracket);
						break loop;
					}
					break;
				case RightArrow:
					if (!Container.isEmpty() && Container.peek() == LeftArrow) {
						Container.pop();
					} else {
						Container.add(LeftArrow);
						break loop;
					}
					break;
				}
			}
			
			if (Container.isEmpty()) {
				results[tc] = 1;
			} else {
				results[tc] = 0;
			}
		}
		
		for (int tc = 0; tc < T; tc++) {
			System.out.printf("#%d %d%n", tc + 1, results[tc]);
		}
	}
}
