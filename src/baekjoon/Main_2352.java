package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2352 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int len = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int l = 1; l <= N; l++) {
			// 순서대로 숫자 하나씩 입력받음
			int num = Integer.parseInt(st.nextToken());
			// arr에서 들어가야할 idx를 찾음.
			int idx = Arrays.binarySearch(arr, 0, len, num);
			// key가 없을 경우 들어가야할 자리를 보정
			if(idx < 0) idx = -(idx+1);
			arr[idx] = num;
			// 길이 1 증가
			if(idx == len) len++;
		}
		
		// 길이 출력
		System.out.println(len);

	}

}
