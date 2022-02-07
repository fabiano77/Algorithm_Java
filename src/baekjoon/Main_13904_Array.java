package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13904_Array {
	static int N;
	
	static class HomeWork implements Comparable<HomeWork>{
		public int deadline;
		public int score;
		public HomeWork(int deadline, int score) {
			this.deadline = deadline;
			this.score = score;
		}
		@Override
		public int compareTo(HomeWork o) {
			if(this.score == o.score) return Integer.compare(this.deadline, o.deadline);
			return Integer.compare(this.score, o.score)*-1;
		}
		
	}

	public static void main(String[] args) throws IOException{
		System.setIn(Main_13904_Array.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 과제의 배열 입력받음
		N = Integer.parseInt(br.readLine());
		HomeWork[] arr = new HomeWork[N];
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			arr[n] = new HomeWork(deadline, score);
		}
		
		// 과제 배열 score 기준 내림차순 정렬 (score 같으면 deadline 오름차순)
		Arrays.sort(arr);
		
		// 점수 총합을 담을 변수, day를 체크할 변수
		int answer = 0;
		boolean[] day = new boolean[1001];
		
		// 가장 점수가 큰 과제부터 순회
		for(int i = 0; i < N; i++) {
			
			// 현재 과제의 데드라인과 가까운 날부터 1일까지 탐색한다
			for(int d = arr[i].deadline; d > 0; d--) {
				
				// 비어있는 날이 있다면? 일정을 채우고 점수를 더한다.
				if(day[d]==false) {
					day[d] = true;
					answer += arr[i].score;
					break;
				}
			}
		}
		System.out.println(answer);

	}

}
