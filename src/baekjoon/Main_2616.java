package baekjoon;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2616 {
	static int N;
	static int M;
	static int[] arr;
	static int[] sum;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2616.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(args.getBytes())));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		
		sum = new int[N+1];
		dp = new int[4][N+1];
		
		// M 개의 누적합
		int temp = 0;
		for(int i = 0; i <= N; i++) {
			if(i < M) temp += arr[i];
			else {
				temp += arr[i];
				sum[i] = temp;
				temp -= arr[i-M+1];				
			}
		}
		
		
		for(int i = 1; i <= 3; i++) {
			for(int j = 0+(M*i); j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j-1],
									dp[i-1][j-M]+sum[j]);
			}
		}
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(sum));
//		for(int[] row : dp) {
//			System.out.println(Arrays.toString(row));
//		}
		System.out.println(dp[3][N]);
	}
	
}
