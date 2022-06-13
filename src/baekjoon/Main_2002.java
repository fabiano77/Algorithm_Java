package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2002 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		int ans = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			map.put(str, i);
		}
		int[] out = new int[N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			out[i] = map.get(str);
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (out[i] > out[j]) {
					ans++;
					break;
				}
			}
		}

		System.out.println(ans);
		br.close();
	}
}
