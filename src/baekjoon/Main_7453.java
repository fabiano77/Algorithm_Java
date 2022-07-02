package baekjoon;

import java.io.*;
import java.util.*;

public class Main_7453 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[4][N]; 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[0][i] = Integer.parseInt(st.nextToken());
			arr[1][i] = Integer.parseInt(st.nextToken());
			arr[2][i] = Integer.parseInt(st.nextToken());
			arr[3][i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] newArr = new int[2][N*N];
		
		int idx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newArr[0][idx] = arr[0][i] + arr[1][j];
				newArr[1][idx++] = arr[2][i] + arr[3][j];
			}
		}
		
		Arrays.sort(newArr[0]);
		Arrays.sort(newArr[1]);
		
		int left = 0;
		int right = N*N-1;
		
		long ans = 0;
		
		while(left < newArr[0].length && right >= 0) {
			int sum = newArr[0][left] + newArr[1][right];
			if(sum == 0) {
				int leftCnt = 1;
				int rightCnt = 1;
				while(left+1 < newArr[0].length && newArr[0][left] == newArr[0][left+1]) {
					left++;
					leftCnt++;
				}
				while(right-1 >= 0 && newArr[1][right] == newArr[1][right-1]) {
					right--;
					rightCnt++;
				}
				ans += (long)leftCnt * rightCnt;
				left++;
				right--;
			}
			else if(sum > 0) {
				right--;
			}
			else {
				left++;
			}
		}

		System.out.println(ans);
		
		br.close();
		

	}

}
