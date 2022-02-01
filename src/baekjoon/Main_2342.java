package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2342 {
	static int[] opposite = { 0, 3, 4, 1, 2 };
	static int[][][] dp = new int[100001][5][5];
	static int[] input;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2342.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		System.out.println(solve(0, 0, 0));
	}
	static int solve(int n, int l, int r) {
		if(input[n] == 0) {
			return 0;
		}
		if(dp[n][l][r] != 0) {
			return dp[n][l][r];
		}
		
		return dp[n][l][r] = Math.min(solve(n+1, input[n], r) + move(l, input[n])
									, solve(n+1, l, input[n]) + move(r, input[n]));
	}
	
	static int move(int from, int to) {
		if (from == to) 			return 1;
		if (from == 0) 				return 2;
		if (to == opposite[from]) 	return 4;
		return 3;
	}
}
