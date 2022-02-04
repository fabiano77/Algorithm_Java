package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230 {
	static int N;		// 1 ~ 100,000
	static int M;		// 0 ~ 20억
	static int[] arr;	// 0 ~ 10억

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2230.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N, M, 배열 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		
		// 오름차순 정렬
		Arrays.sort(arr);
		
		// 정답 변수 최대값 초기화
		int answer = (int)2e9;
		
		// 투 포인터
		int first = 0;
		int last = 0;
		while (last < N && first < N) {
			int dif = arr[last] - arr[first];
			// 차이가 M보다 크면 answer에 최소값일경우 기록하고 first를 증가시킴
			if(dif >= M) {
				first++;
				answer = Math.min(answer, dif);
			}
			// 차이가 M보다 작으면 last를 증가시켜서 차이를 크게함
			else {
				last++;
			}
		}
		
		// 정답 출력
		System.out.println(answer);
	
		

	}

}
