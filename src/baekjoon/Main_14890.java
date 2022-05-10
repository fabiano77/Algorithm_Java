package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14890 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++){
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		int[] arr = new int[N];
		// 세로줄 검사
		for(int c = 0; c < N; c++) {
			for(int r = 0; r < N; r++) {
				arr[r] = map[r][c];
			}
			if(checkLine(arr, L)) {
				ans++;
			}
		}
		// 가로줄 검사
		for(int r = 0; r < N; r++) {
			arr = map[r];
			if(checkLine(arr, L)) {
				ans++;
			}
		}
		System.out.println(ans);

		br.close();
	}
	public static boolean checkLine(int[] arr, int L) {
		int cur = arr[0];
		int len = 1;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == cur) {
				len++;
				continue;
			}
			else if(cur < arr[i]) {
				if(i-L < 0 || cur + 1 < arr[i] || len < L) {
					return false;
				}				
				len = 1;
			}
			else {
				if(i+(L-1) >= arr.length || arr[i] < cur - 1) {
					return false;
				}
				for(int j = 1; j < L; j++) {
					if(arr[i+j] != arr[i]) {
						return false;
					}
				}
				i = i+(L-1);
				len = 0;
			}
			cur = arr[i];
		}
		
		
		return true;
	}

}
