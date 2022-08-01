package com.ssafy.bj.day01;

import java.util.Scanner;

/**
 * 재귀함수가 뭔가요?
 * @author devTaemin
 *
 */
public class BJ_17478 {
	public static int N;
	public static String[] story = new String[7];
	
	/** Print underscore(_) according to the number of argument*/
	public static void printLine(int count) {
		for (int i = 0; i < 4 * count; i++) {
			System.out.print("_");
		}
	}
	
	public static void tellStory(int count) {
		/** If count reaches N, print out the answer */
		if (count == N) {
			printLine(N);
			System.out.println(story[1]);
			printLine(N);
			System.out.println(story[5]);
			printLine(N);
			System.out.println(story[6]);
			return;
		}
		
		for (int i = 1; i <= 4; i++) {
			printLine(count);
			System.out.println(story[i]);
		}
		
		/** Recursive point */
		tellStory(count + 1);
		
		/** If count reaches N, activate the below line */
		printLine(count);
		System.out.println(story[6]);
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		story[0] = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
		story[1] = "\"재귀함수가 뭔가요?\"";
		story[2] = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
		story[3] = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
		story[4] = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
		story[5] = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
		story[6] = "라고 답변하였지.";
		
		System.out.println(story[0]);
		tellStory(0);
	}

}
