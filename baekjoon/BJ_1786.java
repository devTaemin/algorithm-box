package com.ssafy.bj.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/***
 * 찾기
 */
public class BJ_1786 {
	/** 몇 개의 반복된 패턴을 문자로부터 찾았는지 저장하는 변수 */
	static int Count;
	
	/** 문자열과 패턴 */
	static String Text, Pattern;
	
	/** 패턴 인덱스 저장 배열 */
	static int[] Indices;
	
	/** 매칭 시작점을 저장 */
	static ArrayList<Integer> Results = new ArrayList<>();
	
	/** KMP 문자열 매칭 알고리즘 */
	public static void KMP() {
		// 인덱스를 이용해서 패턴을 읽을 시작점을 선택한다.
		int index = 0;
		for (int i = 0; i < Text.length(); i++) {
			
			// 패턴이 맞아오던 중, 매칭이 안되는 것을 만난다면 패턴을 뒤로 돌린다.
			while(index > 0 && Text.charAt(i) != Pattern.charAt(index)) {
				index = Indices[index - 1];
			}
			
			// 현재 가리키는 문자와 패턴에서 가리키는 문자가 같은 경우
			if (Text.charAt(i) == Pattern.charAt(index)) {
				// 패턴의 끝을 가리킨다면
				if (index == Pattern.length() - 1) {
					// 시작점을 저장한다.
					// i가 0부터 시작했으므로 + 1을 해서 1부터 시작하도록 한다.
					Results.add(i - index + 1);
					Count++;
					index = Indices[index];
				}
				else {
					index++;
				}
			}
		}
	}
	
	/** 패턴 좌표 배열 만들기 */
	public static void checkPattern() {
		Indices = new int[Pattern.length()];
		/** 패턴의 시작점을 가리킨다 */
		int index = 0;
		/** 1부터 시작한다. 0번째 인덱스는 어차피 0이다. */
		for (int i = 1; i < Pattern.length(); i++) {
			
			/***
			 * 일단 index가 0보다 크다는 것은 패턴이 있다는 것이다.
			 * 패턴이 있는 상태에서 현재 가리키는 문자와 패턴에서 반복되는 문자가 다르다면 패턴이 중단
			 * 되었다고 볼 수 있으니까 패턴을 가리키는 index 이전의 문자가 가리키는 위치로 index를 
			 * 이동 시키고 혹시 현재 위치의 i와 매칭되는 것이 있는지 찾는다.
			 * 받아온 index 이전까지는 현재 i가 가리키고 있는 이전의 것과 매칭이 된다.
			 */
			while(index > 0 && Pattern.charAt(i) != Pattern.charAt(index)) {
				index = Indices[index - 1];
			}
			
			/***
			 * 현재 가리키고 있는 패턴의 문자와 패턴의 문자가 같으면 패턴의 인덱스 + 1을 할당해주고
			 * 패턴을 가리키는 인덱스를 + 1 해준다.
			 */
			if (Pattern.charAt(i) == Pattern.charAt(index)) {
				// index 까지 맞았으니까
				index += 1;
				// i번째는 index + 1를 넣어준다
				// index의 의미는 index 이전까지는 매칭이 되었다는 것이다!!!
				// 매칭이 안되면 이전의 좌표의 index를 가져오는 거니까 이전의 좌표의 index는
				// 패턴이 유지된다면 다음을 가져와야 하는 것이다.
				Indices[i] = index;
			}
			
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Text = br.readLine();
		Pattern = br.readLine();
		
		checkPattern();		
		KMP();
		System.out.println(Count);
		for (int i : Results) {
			System.out.println(i);
		}
		
	}

}
