package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11054 {
	static int N;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] dpUp = new int[N];
		int[] dpDown = new int[N];
		for(int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		dpUp[0] = 1;
		dpDown[N-1] = 1;
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {					
					if(dpUp[i] < dpUp[j]) dpUp[i] = dpUp[j];
				}
			}
			dpUp[i]++;
		}
		for(int i = N-2; i >= 0; i--) {
			for(int j = N-1; i < j; j--) {
				if(arr[j] < arr[i]) {					
					if(dpDown[i] < dpDown[j]) dpDown[i] = dpDown[j];
				}
			}
			dpDown[i]++;
		}
		
		int sol = 0;
		for(int i = 0; i < N; i++) {
			if(dpUp[i] + dpDown[i] > sol) sol = dpUp[i] + dpDown[i];
		}
		
		System.out.println(sol-1);


	}

}
