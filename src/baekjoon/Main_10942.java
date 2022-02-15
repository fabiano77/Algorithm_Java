package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10942 {
	static int N;
	static int M;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N개의 수열을 정수형 배열로 입력받기 (~2,000)
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// DP 배열
		int[][] dp = new int[N+1][N+1];

		// 내부 부분수열 의 길이
		for(int i = 0; i <= N; i++) {
			// 시작점 1 ~ N
			for(int s = 1; s+i <= N; s++) {
				int e = s+i;
				// 같은 수여서 팰린드롬이면 2
				if(s == e) {
					dp[s][e] = 2;
				}
				// 같은 수여서 팰린드롬이면 2
				else if(dp[s][e-1] == 2 && arr[s] == arr[e]) {
					dp[s][e] = 2;
				}
				// 대칭이어서 팰린드롬이면 1
				else if(dp[s+1][e-1] != 0 && arr[s] == arr[e]) {
					dp[s][e] = 1;
				}
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		// 질문의 수 M개 (~100,000)
		M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// M개의 질문 입력받기
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(dp[s][e] != 0) sb.append("1\n");
			else sb.append("0\n");
		}
		System.out.print(sb);
		

	}

}
