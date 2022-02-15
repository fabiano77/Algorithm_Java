package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074 {
	static int N;
	static int R;
	static int C;
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		solve(N, R, C);
		System.out.println(answer);

	}
	
	static void solve(int n, int r, int c) {
		if(n == 0) return;
		int temp = (int)Math.pow(2, n-1);
		if(r < temp) {
			if(c < temp) {
				// 좌상단
				solve(n-1, r, c);
			}
			else {
				// 우상단
				answer += temp*temp;
				solve(n-1, r, c-temp);
			}
		}
		else {
			if(c < temp) {
				// 좌하단
				answer += 2*(temp*temp);
				solve(n-1, r-temp, c);
			}
			else {
				// 우하단
				answer += 3*(temp*temp);
				solve(n-1, r-temp, c-temp);
			}
		}
		
	}

}
