package com.ssafy.cfj.graph;

import java.util.Scanner;

/**
 * 팀 결성 
 * @author taeminim
 *
 */
public class TeamFormation {
	static int NumOfStudent, NumOfCommand;
	static int[] Parent;
	static StringBuilder Sb = new StringBuilder();
	
	public static int findParent(int child) {
		if (Parent[child] != child) {
			Parent[child] = findParent(Parent[child]);
		}
		
		return Parent[child];
	}
	
	public static void union(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if (pa < pb) {
			Parent[b] = pa;
		} else {
			Parent[a] = pb;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NumOfStudent = sc.nextInt();
		NumOfStudent += 1;
		NumOfCommand = sc.nextInt();
		
		/** Range 0 to NumOfStudent */
		Parent = new int[NumOfStudent];
		
		/** Initialize parent */
		for (int i = 0; i < NumOfStudent; i++) {
			Parent[i] = i;
		}
		
		for (int i = 0; i < NumOfCommand; i++) {
			int command = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			switch(command) {
			case 0:
				union(a, b);
				break;
			case 1:
				int parentA = findParent(a);
				int parentB = findParent(b);
				if (parentA == parentB) {
					Sb.append("YES").append("\n");
				} else {
					Sb.append("NO").append("\n");
				}
				break;
			}
		}
		
		System.out.println(Sb);
	}

}
