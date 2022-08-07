package com.ssafy.cfj.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 성적이 낮은 순서로 학생 출력하기  
 * @author taeminim
 */
class Student implements Comparable {
	String name;
	int score;
	
	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public int compareTo(Object o) {
		Student other = (Student)o;
		return Integer.valueOf(this.score).compareTo(Integer.valueOf(other.score));
	}
}

public class TestScore {
	static int N;
	static ArrayList<Student> Array = new ArrayList<Student>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			String student = sc.next();
			int score = sc.nextInt();
			
			Array.add(new Student(student, score));
		}
		
		Collections.sort(Array);
		
		for (int i = 0; i < N; i++) {
			Student student = Array.get(i);
			System.out.printf("%s ", student.name);
		}
	}
	
	
	
	static ArrayList<ArrayList<String>> ScoreBoard = new ArrayList<ArrayList<String>>();

	public static void main2(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		/** Initialize Score Board */
		for (int i = 0; i <= 100; i++) {
			ScoreBoard.add(new ArrayList<String>());
		}
		
		/** Add Student */
		for (int i = 0; i < N; i++) {
			String student = sc.next();
			int score = sc.nextInt();
			
			ArrayList<String> updateBoard = ScoreBoard.get(score);
			updateBoard.add(student);
			ScoreBoard.set(score, updateBoard);
		}
		
		for (int i = 1; i <= 100; i++) {
			ArrayList<String> students = ScoreBoard.get(i);
			for (String student : students) {
				System.out.printf("%s ", student);
			}
		}
	}
}
