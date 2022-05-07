package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2565 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
		}
		
		Arrays.sort(arr, (o1, o2)->Integer.compare(o1[0], o2[0]));
		
		int[] lis = new int[N];
		int len = 0;
		
		for(int[] pair : arr) {
			int num = pair[1];
			int idx = Arrays.binarySearch(lis, 0, len, num);
			if(idx < 0) {
				idx = -(idx+1);
			}
			if(idx == len) {
				len++;
			}
			lis[idx] = num;
		}
		System.out.println(N-len);
		
		
		br.close();
	}

}
