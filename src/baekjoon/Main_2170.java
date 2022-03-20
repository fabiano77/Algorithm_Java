package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2170 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2)->{
			if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[0], o2[0]);
		});

		
		int answer = 0;
		int last = -1_000_000_000;
		for(int i = 0; i < N; i++) {
			if(arr[i][0] < last) {
				if(arr[i][1] > last) {
					answer += (arr[i][1] - last);
					last = arr[i][1];
				}
			}
			else {
				answer += arr[i][1] - arr[i][0];
				last = arr[i][1];
			}
			
		}
		System.out.println(answer);

	}

}
