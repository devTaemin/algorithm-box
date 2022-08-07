package com.ssafy.cfj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 커리큘럼 
 * @author taeminim
 *
 */

public class Curriculum {
	static int N;
	static Queue<Integer> Q = new LinkedList<>();
	static ArrayList<ArrayList<Integer>> PreLesson = new ArrayList<ArrayList<Integer>>();
	static int[] CountIn;
	static int[] Times;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		CountIn = new int[N + 1];
		Times = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			Times[i] = sc.nextInt();
			
			while (true) {
				int lesson = sc.nextInt();
				if (lesson == -1) break;
				else {
					CountIn[lesson]++;
					PreLesson.get(lesson).add(i);
				}
			}
		}
		
		int lesson = -1;
		for (int i = 1; i <= N; i++) {
			if (CountIn[i] == 0) {
				lesson = i;
				break;
			}
		}
		
		if (lesson == -1) {
			System.out.println("Cannot find the result");
			return;
		}
		
		Q.add(lesson);
		while (!Q.isEmpty()) {
			int pre = Q.poll();
			CountIn[pre] = -1;
			
			ArrayList<Integer> lessons = PreLesson.get(pre);
			int maxTime = 0;
			for (int i = 0; i < lessons.size(); i++) {
				int checkLesson = lessons.get(i);
				CountIn[checkLesson]--;
				
			}
			
			
		}
		
		
		
		
		
		
	}

}
