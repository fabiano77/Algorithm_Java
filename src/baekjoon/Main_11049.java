package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11049 {
	static int N;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N][N];
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N-1; i++) {
			dp[i][i+1] = arr[i][0] * arr[i][1] * arr[i+1][1];
		}
		
		
		for(int i = 2; i < N; i++) {
			for(int j = 0; j < N-i; j++) {
				for(int k = 0; k < i; k++) {
					if(k == 0) dp[j][i+j] = dp[0+j][k+j] + dp[1+j+k][i+j] + (arr[0+j][0] * arr[k+j][1] * arr[i+j][1]);
					else dp[j][i+j] = Math.min(dp[j][i+j], dp[0+j][k+j] + dp[1+j+k][i+j] + (arr[0+j][0] * arr[k+j][1] * arr[i+j][1]));
				}				
			}
		}
		
		System.out.println(dp[0][N-1]);

		
		
		
	}
	
}
