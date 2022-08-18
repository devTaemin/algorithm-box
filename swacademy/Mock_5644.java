package com.ssafy.swea.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 무선 충전
 * @author devTaemin
 *
 */
public class Mock_5644{
	
	static int T, NumOfMove, NumOfCharger, Result;
	static int[] A, B;
	static int[][] Chargers;
	
	/** Direction (Stay, North, East, South, West) */
	static int[] Dx = { 0, -1, 0, 1, 0 };
	static int[] Dy = { 0, 0, 1, 0, -1 };
	
	public static int calDistance(int[] position, int[] charger) {
		int px = position[0];
		int py = position[1];
		int cx = charger[0];
		int cy = charger[1];
		
		return ( Math.abs(px - cx) + Math.abs(py - cy) );
	}
	
	public static void charge(int[] positionA, int[] positionB) {
		ArrayList<Integer> aChargerList = new ArrayList<>();		
		for (int i = 0; i < NumOfCharger; i++) {
			int distance = calDistance(positionA, Chargers[i]);
			if (distance <= Chargers[i][2]) {
				aChargerList.add(i);
			}
		}
		
		ArrayList<Integer> bChargerList = new ArrayList<>();
		for (int i = 0; i < NumOfCharger; i++) {
			int distance = calDistance(positionB, Chargers[i]);
			if (distance <= Chargers[i][2]) {
				bChargerList.add(i);
			}
		}
		
		int maxPower = 0;
		if (aChargerList.size() != 0 && bChargerList.size() != 0) {
			for (int i = 0; i < aChargerList.size(); i++) {
				for (int j = 0; j < bChargerList.size(); j++) {
					int choiceA = aChargerList.get(i);
					int choiceB = bChargerList.get(j);
					
					if (choiceA == choiceB) {
						maxPower = Math.max(maxPower, Chargers[choiceA][3]);
					} else {
						maxPower = Math.max(maxPower, Chargers[choiceA][3] + Chargers[choiceB][3]);
					}
				}
			}
		} else if (aChargerList.size() != 0 && bChargerList.size() == 0) {
			for (int i = 0; i < aChargerList.size(); i++) {
				int choiceA = aChargerList.get(i);
				maxPower = Math.max(maxPower, Chargers[choiceA][3]);
			}
		} else if (aChargerList.size() == 0 && bChargerList.size() != 0) {
			for (int i = 0; i < bChargerList.size(); i++) {
				int choiceB = bChargerList.get(i);
				maxPower = Math.max(maxPower, Chargers[choiceB][3]);
			}
		}
		
		Result += maxPower;
	}
	
	public static void move() {
		int[] positionA = {1, 1};
		int[] positionB = {10, 10};
		charge(positionA, positionB);
		
//		System.out.println(Result);
		
		for (int i = 0; i < NumOfMove; i++) {
			int Da = A[i];
			int Db = B[i];
			
			positionA[0] += Dy[Da];
			positionA[1] += Dx[Da];
			positionB[0] += Dy[Db];
			positionB[1] += Dx[Db];
			
			charge(positionA, positionB);
//			System.out.println(Result);
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			NumOfMove = Integer.parseInt(st.nextToken());
			NumOfCharger = Integer.parseInt(st.nextToken());
			
			A = new int[NumOfMove];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < NumOfMove; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			B = new int[NumOfMove];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < NumOfMove; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			Chargers = new int[NumOfCharger][4];
			for (int i = 0; i < NumOfCharger; i++) {
				st = new StringTokenizer(br.readLine());
				Chargers[i][0] = Integer.parseInt(st.nextToken());
				Chargers[i][1] = Integer.parseInt(st.nextToken());
				Chargers[i][2] = Integer.parseInt(st.nextToken());
				Chargers[i][3] = Integer.parseInt(st.nextToken());
			}
			
			Result = 0;
			move();
			sb.append("#").append(tc).append(" ").append(Result).append("\n");
		}
		
		System.out.println(sb);
	}
}
