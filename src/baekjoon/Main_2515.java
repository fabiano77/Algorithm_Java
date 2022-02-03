package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2515 {
	
	// 그림 정보를 저장할 클래스 작성
	static class Paint implements Comparable<Paint>{
		public int height;
		public int price;
		
		public Paint(int height, int price) {
			this.height = height;
			this.price = price;
		}
		
		@Override
		public String toString() {
			return "Paint [height=" + height + ", price=" + price + "]";
		}
		@Override
		public int compareTo(Paint o) {
			// height 같을 경우 가격 내림차순
			if(this.height == o.height) return Integer.compare(this.price, o.price)*-1;
			// 우선 height 오름차순
			return Integer.compare(this.height, o.height);
		}
	}
	
	static int N;
	static int S;
	static int[] dp;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2515.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 그림 개수 N과 보여야할 최소 높이 S
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		// 최선의 가격을 저장할 dp테이블
		dp = new int[N+1];
		Paint[] paints = new Paint[N+1];
		paints[0] = new Paint(0, 0);
		
		// 그림당 높이와 가격 입력받음
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			paints[i] = new Paint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 그림 객체 배열 height를 기준으로 오름차순 정렬
		Arrays.sort(paints);
		
		// 첫 번째 그림부터 dp 진행
		for(int i = 1; i <= N; i++) {
			
			// paint[i]와 겹치지 않는 가장 가까운 paint[j] 찾음
			
			// 방법1 <순차 탐색 -> 시간초과>
			// int j = i-1;
			// while(j > 0 && paints[i].height - paints[j].height < S) {
			// 	j--;
			// }
			
			// 방법2 <이분 탐색 -> 통과>
			int k = Arrays.binarySearch(paints, 0, i, new Paint(paints[i].height-S, 0));
			if(k < 0) {
				k = -(k+2);
				if(k < 0) {
					k = 0;
				}
			}

			// 현재 그림에 대해 dp 테이블 갱신
			dp[i] = Math.max(	dp[i-1],				// 현재 그림을 선택하지 않는 경우 
								dp[k]+paints[i].price);	// 현재 그림을 선택할 경우.
		}
		
		// 정답 출력
		System.out.println(dp[N]);
		
	}

}
