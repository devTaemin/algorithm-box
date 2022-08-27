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
 * [A형 연습문제] 낚시터 자리잡기
 * @author devTaemin
 * @소요시간 300분...
 * 
 * 핵심요소
 * - 현재 위치에서 목표 위치까지의 최소 비용을 구해야 한다 (BFS)
 * - 몬스터와 고객을 방문하는 순서를 순열로 구현하는데, 방문 순서 규칙에 맞는 순열만을 허용한다.
 */
public class Mock_fishing {
	
	/** 
	 * 입구 정보
	 * @variable  no 		입구 번호
	 * @variable  people 	사람 수
	 * */
	static class Entrance {
		int no, people;
		public Entrance(int no, int people) {
			this.no = no;
			this.people = people;
		}
	}
	
	/***
	 * @variable	T			테스트 케이스 수
	 * @variable	N			자리 수 (1 ~ N)
	 * @variable	Distance	해당 케이스에서 가져올 수 있는 최소 거리
	 * @Array		Sequence	순열 저장 배열
	 * @ArrayList	Entrances	3개의 입구를 저장할 리스트
	 */
	static int T, N, Distance;
	static int[] Sequence;
	static ArrayList<Entrance> Entrances;
	
	/** 거리 계산 함수 */
	public static int calDistance(int entrance, int pos) {
		/** 현재 위치에서 해당 위치까지 떨어진 거리에 + 1*/
		return Math.abs(entrance - pos) + 1;
	}
	
	/** 현재 입구에서 왼쪽으로 가장 가까운 곳의 위치를 가져오는 함수 */
	public static int findLeft(int entrance, int[] space) {
		for (int i = entrance; i >= 1; i--) {
			/** 만약 비어있다면 해당 위치를 가져오기 */
			if (space[i] == 0) {
				return i;
			}
		}
		/** 비어 있는 위치가 없다면 -1을 리턴 */
		return -1;
	}
	
	/** 현재 입구에서 오른쪽으로 가장 가까운 위치를 가져오는 함수 */
	public static int findRight(int entrance, int[] space) {
		for (int i = entrance; i <= N; i++) {
			/** 만약 비어있다면 해당 위치를 가져오기 */
			if (space[i] == 0) {
				return i;
			}
		}
		/** 비어 있는 위치가 없다면 -1을 리턴 */
		return -1;
	}
	
	/** 순열로 만든 순서를 바탕으로 시뮬레이션을 시작
	 *  그런데 DFS로 구현한 이유는 분기점을 구현할 필요가 있기 때문이다.
	 *  사람 수 -1까지는 현재 위치에서 가까운 곳에 넣으면 되기 때문에 문제가 없다.
	 *  단, 만약 마지막 사람의 경우 오른쪽으로 가는 거리와 왼쪽으로 가는 거리가 같다면 둘 중 하나를 골라줘야 한다
	 *  그것을 임의로 하려고 하지 말고 DFS를 통해 자식 트리 노드를 뻗어 가는 것 것과 같이 왼쪽으로 보내는 것 하나, 오른쪽으로 보내는 것 하나
	 *  나눠서 DFS로 시뮬레이션을 처리한다.
	 * */
	public static void play(int depth, int[] space, int distance) {
		/** 3개의 입구에서 다 들어온 경우 */
		if (depth == 3) {
			/** 전역 거리와 로컬 거리 중 작은 것을 전역 거리에 저장한다 */
			Distance = Math.min(Distance, distance);
			return;
		}
		
		/** Sequence에 순서를 담았기 때문에 depth가 0, 1, 2로 증가하는 것에 맞춰 입구를 가져오는데 사용한다 */
		Entrance e = Entrances.get(Sequence[depth]);
		int no = e.no;
		int people = e.people;
		int accDistance = distance;
		int[] copy = Arrays.copyOf(space, space.length);
		
		/** 들어가야하는 인원 수 -1까지 자리를 배치한다*/
		int left, right;
		for (int p = 0; p < people - 1; p++) {
			left = findLeft(no, copy);
			right = findRight(no, copy);
			
			/** 왼쪽에 들어갈 수 있는 자리가 없으면 오른쪽 위치에 넣고 추가된 거리를 저장한다 */
			if (left == -1) {
				copy[right] = 1;
				accDistance += calDistance(no, right);
			} 
			/** 오른쪽에 들어갈 수 있는 자리가 없으면 왼쪽 위치에 넣고 추가된 거리를 저장한다 */
			else if (right == -1) {
				copy[left] = 1;
				accDistance += calDistance(no, left);
			} 
			/** 둘 다 들어갈 수 있는 경우 */
			else {
				/** 왼쪽 오른쪽의 거리가 같으면 어느쪽에 넣어도 상관이 없다*/
				if (calDistance(no, left) == calDistance(no, right)) {	
					copy[left] = 1;
					accDistance += calDistance(no, left);											
				} 
				else {
					/** 왼쪽이 더 작으면 왼쪽에 넣고 오른쪽이 더 작으면 오른쪽에 넣는다 */
					if (calDistance(no, left) < calDistance(no, right)) {
						copy[left] = 1;
						accDistance += calDistance(no, left);
					} else {
						copy[right] = 1;
						accDistance += calDistance(no, right);
					}
				}
			}
		}
		
		/** 마지막 사람을 넣자 */
		left = findLeft(no, copy);
		right = findRight(no, copy);
		/** 왼쪽에 넣을 수 없으면 오른쪽에 넣자*/
		if (left == -1) {
			copy[right] = 1;
			accDistance += calDistance(no, right);
			// DEBUG
//			System.out.println("\n-----------------------------");
//			System.out.print("Depth: " + depth + " ");
//			System.out.println(Arrays.toString(copy));
//			System.out.println("-----------------------------\n");

			/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
			play(depth + 1, copy, accDistance);	
		} 
		/** 오른쪽에 넣을 수 없으면 왼쪽에 넣자 */
		else if (right == -1) {
			copy[left] = 1;
			accDistance += calDistance(no, left);
			// DEBUG
//			System.out.println("\n-----------------------------");
//			System.out.print("Depth: " + depth + " ");
//			System.out.println(Arrays.toString(copy));
//			System.out.println("-----------------------------\n");
			
			/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
			play(depth + 1, copy, accDistance);
		} else {
			/** 양쪽의 거리가 다르면 더 가까운 곳에 넣자 */
			if (calDistance(no, left) != calDistance(no, right)) {
				if (calDistance(no, left) < calDistance(no, right)) {
					copy[left] = 1;
					accDistance += calDistance(no, left);
					// DEBUG
//					System.out.println("\n-----------------------------");
//					System.out.print("Depth: " + depth + " ");
//					System.out.println(Arrays.toString(copy));
//					System.out.println("-----------------------------\n");
					
					/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
					play(depth + 1, copy, accDistance);
				} else {
					copy[right] = 1;
					accDistance += calDistance(no, right);
					// DEBUG
//					System.out.println("\n-----------------------------");
//					System.out.print("Depth: " + depth + " ");
//					System.out.println(Arrays.toString(copy));
//					System.out.println("-----------------------------\n");
					
					/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
					play(depth + 1, copy, accDistance);
				}
			} else {
				/** 만약 양쪽의 거리가 같다면 두 갈래로 분기를 해줘야 한다 
				 *  각 각의 방향에는 좌우 다르게 적용된 space 정보와 누적 거리 정보가 전달되어야 하므로
				 *  원래 데이터를 훼손하지 말고 새로 선언하고 만들어서 넘겨주도록 하자
				 * */
				
				// Left
				int[] leftCopy = Arrays.copyOf(copy, copy.length);
				leftCopy[left] = 1;
				int leftAccDistance = accDistance + calDistance(no, left);
				// DEBUG
//				System.out.println("\n-----------------------------");
//				System.out.print("Depth: " + depth + " ");
//				System.out.println(Arrays.toString(copy));
//				System.out.println("-----------------------------\n");
				
				/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
				play(depth + 1, leftCopy, leftAccDistance);
				
				// Right
				int[] rightCopy = Arrays.copyOf(copy, copy.length);
				rightCopy[right] = 1;
				int rightAccDistance = accDistance + calDistance(no, right);
				// DEBUG
//				System.out.println("\n-----------------------------");
//				System.out.print("Depth: " + depth + " ");
//				System.out.println(Arrays.toString(copy));
//				System.out.println("-----------------------------\n");
				
				/** 마지막 사람이 들어갔으니 다음 입구로 넘어가자 */
				play(depth + 1, rightCopy, rightAccDistance);
			}
		}
	}

	public static void Permutation(int depth, int flag) {
		if (depth == 3) {
			int[] space = new int[N + 1];
			// DEBUG
//			System.out.println(Sequence[0] + " " + Sequence[1] + " " + Sequence[2]);
//			System.out.println("==================================");
			play(0, space, 0);
//			System.out.println("==================================\n");
			
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if ((flag & 1 << i) != 0) continue;
			Sequence[depth] = i;
			Permutation(depth + 1, flag | 1 << i);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Entrances = new ArrayList<>();
			
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				Entrances.add(new Entrance(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			
			Distance = Integer.MAX_VALUE;
			Sequence = new int[3];
			Permutation(0, 0);
			
			sb.append("#").append(tc).append(" ").append(Distance).append("\n");
			
		}
		
		System.out.println(sb);
		
	}

}
