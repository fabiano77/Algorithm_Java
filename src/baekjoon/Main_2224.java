package baekjoon;

import java.util.Scanner;

public class Main_2224 {
	private static final int SIZE = 52;
	private static int N;

	public static int toOrder(char c) {
		if (Character.isUpperCase(c)) {
			return c - 'A';
		} else {
			return c - 'a' + (('Z' - 'A') + 1);
		}
	}
	public static char toChar(int i) {
		if (i <= ('Z' - 'A')) {
			return (char) (i + 'A');
		} else {
			return (char) (i - 1 + 'a' - ('Z' - 'A'));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setIn(Main_2224.class.getResourceAsStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int[][] adjMat = new int[SIZE][SIZE];
		int cnt = 0;

		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int start = toOrder(sc.next().charAt(0));
			String trash = sc.next();
			int end = toOrder(sc.next().charAt(0));
			if(start == end || adjMat[start][end] == 1) continue;
			adjMat[start][end] = 1;
			cnt++;
		}
		

		for (int t = 0; t < SIZE; t++) {
			for (int s = 0; s < SIZE; s++) {
				for (int e = 0; e < SIZE; e++) {
					if (s == e)
						continue;
					if (adjMat[s][t] == 1 && adjMat[t][e] == 1 && adjMat[s][e] != 1) {
						adjMat[s][e] = 1;
						cnt++;
					}
				}

			}
		}

		System.out.println(cnt);
		for (int s = 0; s < SIZE; s++) {
			for (int e = 0; e < SIZE; e++) {
				if (s != e && adjMat[s][e] == 1)
					System.out.println(toChar(s) + " => " + toChar(e));
			}
		}
	}
}
