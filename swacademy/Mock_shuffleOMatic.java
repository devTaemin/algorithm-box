package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * [A형 연습문제] Shuffle-O-Matic
 * @author devTaemin
 * @소요시간 100분...
 * 
 * 핵심요소
 * - 주어진 셔플의 조건을 완성시켜야 한다
 * - 0 ~ N-1을 최대 5번까지 선택하는  중복순열이 정상적으로 돌아가야 한다.
 */
public class Mock_shuffleOMatic {
	/***
	 * @variable	INF			정수형 무한 표현
	 * @variable	T			테스트 케이스
	 * @variable	N			카드 수
	 * @variable	Find		셔플 결과로 답을 찾았는지를 표시, DFS 중간에 벗어나기 위해 사용
	 * @Array		Cards		입력된 카드의 배열
	 * @Array		Shuffled	섞어 놓은 카드 배열
	 * @Array		Ascending	입력된 카드의 오름차순 정렬 배열
	 * @Array		Descending	입력된 카드의 내림차순 정렬 배열
	 * @Array		Sequence	중복 순열 저장 배열
	 *
	 */
	static final int INF = (int)1e9;
	static int T, N;
	static boolean Find;
	static int[] Cards, Shuffled;
	static int[] Ascending, Descending, Sequence;
	
	/** 문제의 조건에 맞춰서 정렬 진행 */
	public static void shuffle(int x) {
		/** 임시 배열 */
		int[] shuffled = new int[Shuffled.length];
		
		/** 카드 절반 앞쪽 */
		ArrayList<Integer> front = new ArrayList<>();		
		for (int i = 0; i < Shuffled.length / 2; i++) {
			front.add(Shuffled[i]);
		}
		
		/** 카드 절반 뒤쪽 */
		ArrayList<Integer> rear = new ArrayList<>();
		for (int i = Shuffled.length / 2; i < Shuffled.length; i++) {
			rear.add(Shuffled[i]);
		}
		
		/** 뒤쪽 카드의 위치를 먼저 잡아준다 */
		for (int i = Shuffled.length / 2; i < Shuffled.length; i++) {
			/** 원래 배열 기준 뒤쪽 카드의 인덱스 */
			int index = i;
			/** 인덱스에 카드 수의 절반을 줄인다 
			 * 	예시) N = 8
			 * 
			 *  - Suffled 배열  원본 
			 *    front     rear
			 *  [0 1 2 3] [4 5 6 7]
			 *  
		     *  - Shuffled 배열 뒤쪽을 잘라서 아래와 같이 생각하자
			 *  shuffled(index)     rear(check)
			 *     [4 5 6 7]         [0 1 2 3]
			 * 
			 * */
			
			/** check는 해당 인덱스의 카드가 갈 수 있는 최대 위치라고 볼 수 있다*/
			int check = index - Shuffled.length / 2;
			
			/** 만약 입력된 x보다 check가 크면 원래 index에 그대로 넣는다 */
			if (check >= x) {
				shuffled[index] = rear.get(check);
			}
			/** 만약 입력된 x가 check보다 크면 원래 index에 (x - check)를 해준 위치가 입렫된 x에서 움직인 위치이다 */
			else {
				int move = index - (x - check);
				/** 그런데 만약 check보다 작게 되면 check에 고정시켜준다. */
				if (move < check) {
					shuffled[check] = rear.get(check);
				} else {
					shuffled[move] = rear.get(check);
				}
			}
		}
		
		/** rear 배열에 들어있는 카드가 모두 포지셔닝되면 front 배열에 있는 카드를 빈 공간에 차례대로 넣어준다 */
		for (int i = 0; i < front.size(); i++) {
			int value = front.get(i);
			for (int j = 0; j < Shuffled.length; j++) {
				if (shuffled[j] == 0) {
					shuffled[j] = value;
					break;
				}
			}
		}
		
		/** 로컬 셔플 배열을 다음 셔플을 위해 전역 셔플 배열에 할당해 준다 */
		Shuffled = shuffled;
	}
	
	/** 셔플 x의 순서를 만들어내는 중복 순열 */
	public static void DFS(int target, int depth) {
		if (depth == target) {
			/** 셔플을 진행한다 */
			Shuffled = Arrays.copyOf(Cards, Cards.length);
			for (int i = 0; i < depth; i++) {
				shuffle(Sequence[i]);
			}
			
			/** 오름차순을 확인한다 */
			boolean find = true;
			for (int i = 0; i < N; i++) {
				if (Ascending[i] != Shuffled[i]) {
					find = false;
					break;
				}
			}
			
			if (find) {
				Find = true;
				return;
			}
			
			
			/** 내림차순을 확인한다 */
			find = true;
			for (int i = 0; i < N; i++) {
				if (Descending[i] != Shuffled[i]) {
					find = false;
					break;
				}
			}
			
			if (find) {
				Find = true;
				return;
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			Sequence[depth] = i;
			DFS(target, depth + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("sample_input_3.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Cards[i] = Integer.parseInt(st.nextToken());
			}
			
			
			/** 오름차순 정렬을 통해 기준을 만든다. */
			Ascending = Arrays.copyOf(Cards, Cards.length);
			Arrays.sort(Ascending);
			/** 내림차순 정렬을 통해 기준을 만든다 */
			Descending = new int[N];
			int idx = 0;
			for (int i = N - 1; i >= 0; i--) {
				Descending[idx++] = Ascending[i];
			}
			
			/** 첫 배열 먼저 확인하고 시작, 왜냐하면 내 코드에서 target이 1부터 시작해서 x = 0일때를 못잡아 내더라 그래서 사전에 미리 검사했다 */
			// 오름차순 확인
			boolean find = true;
			for (int i = 0; i < N; i++) {
				if (Ascending[i] != Cards[i]) {
					find = false;
					break;
				}
			}
			
			if (find) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			
			
			// 내림차순 확인
			find = true;
			for (int i = 0; i < N; i++) {
				if (Descending[i] != Cards[i]) {
					find = false;
					break;
				}
			}
			
			if (find) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			
			
			/** Shuffle은 최소 1회, 최대 5회 시행한다. */
			int min = INF;
			Find = false;
			for (int target = 1; target <= 5; target++) {
				Sequence = new int[target];
				/** 각 횟수에 대하여 DFS를 이용하여 중복 순열 경우의 수를 구현한다 */
				DFS(target, 0);
				
				/** 만약 현재 shuffle 횟수에서 발견이 되었다면 횟수를 저장하고 탐색을 종료한다. */
				if (Find) {
					min = target;
					break;
				}
			}
			
			
			/** 만약 찾지 못했다면 -1을 저장한다 */
			if (min == INF) min = -1;
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
	}

}
