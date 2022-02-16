package baekjoon;

import java.io.*;

public class Main_10157 {

	static int R, C, K;
	static int[][] arr;
	static int cnt = 1;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] sarr = br.readLine().split(" ");
		C = Integer.parseInt(sarr[0]);
		R = Integer.parseInt(sarr[1]);
		K = Integer.parseInt(br.readLine());
		arr = new int[R + 1][C + 1];

		if (K > R * C) {
			bw.write(0 + "\n");
			bw.flush();
		}

		else {
			solve(1, 1, R, C);

			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if (arr[i][j] == K) {
						bw.write(j + " " + i + "\n");
						bw.flush();
						return;
					}
				}
			}

		}

	}

	public static void solve(int sr, int sc, int r, int c) {

		if (r == 1) {
			for (int i = sc; i < sc + c; i++)
				arr[sr][i] = cnt++;
		}

		else if (c == 1) {
			for (int i = sr; i < sr + r; i++)
				arr[i][sc] = cnt++;
		}

		else {
			for (int i = sr; i < sr + r; i++)
				arr[i][sc] = cnt++;

			for (int i = sc + 1; i < sc + c; i++)
				arr[sr + r - 1][i] = cnt++;

			for (int i = sr + r - 2; i >= sr; i--)
				arr[i][sc + c - 1] = cnt++;

			for (int i = sc + c - 2; i >= sc + 1; i--)
				arr[sr][i] = cnt++;
		}

		if (r > 2 && c > 2)
			solve(sr + 1, sc + 1, r - 2, c - 2);

	}

}