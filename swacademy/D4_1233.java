package com.ssafy.swea.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Node
 * - index : 노드 번호
 * - left : 좌측 자식 번호
 * - right: 우측 자식 번호
 * */
class Node {
	int index;
	int left;
	int right;
	
	public Node(int index, int left, int right) {
		this.index = index;
		this.left = left;
		this.right = right;
	}
}

/**
 * 사칙연산 유효성 검사
 * @author devTaemin
 *
 */
public class D4_1233 {
	static int N;
	static Stack<Node> stack;
	static boolean[] IsNumber;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream(new File("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		/** Play 10 test cases */
		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			
			/** Stack for storing parent node */
			stack = new Stack<>();
			
			/** Check number or not */
			IsNumber = new boolean[N + 1];
			
			/** Check if the test is over during the data inputs*/
			boolean isEnd = false;
			
			// Check the size of N
			if (N % 2 == 0) {
				isEnd = true;
			}
			
			for (int i = 1; i <= N; i++) {
				String[] line = br.readLine().split(" ");
				
				// Check operator
				if (line[1].equals("+") || line[1].equals("-") || line[1].equals("*") || line[1].equals("/")) {
					if (line.length != 4) {
						isEnd = true; 
					} else {
						stack.add(new Node(Integer.parseInt(line[0]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
					}
				}
				// Check operand
				else {
					if (line.length != 2) {
						isEnd = true;
					} else {
						IsNumber[Integer.parseInt(line[0])] = true;
					}
				}
			}
			
			if (isEnd) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			
			boolean isAvailable = true;
			if (!isEnd) {
				while (!stack.isEmpty()) {
					Node node = stack.pop();
					int index = node.index;
					int leftChild = node.left;
					int rightChild = node.right;
					
					if (IsNumber[leftChild] && IsNumber[rightChild]) {
						IsNumber[index] = true;
					} else {
						isAvailable = false;
						break;
					}
				}
			}
			
			if (isAvailable) {
				sb.append("#").append(tc).append(" ").append(1).append("\n");
			}
		}
		
		System.out.println(sb);
		
		
		
	}
}
