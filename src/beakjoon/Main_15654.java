package beakjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main_15654 {
	static int n;
	static int m;
	static int[] array;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		Arrays.sort(array);
		int[] temp_hist = new int[m];
		Arrays.fill(temp_hist, -1);
		solve(temp_hist, 0);
	}
	static void solve(int[] hist, int cnt) {
		if(cnt == m) {
			for(int idx : hist) {
				System.out.print(array[idx]+" ");
			}
			System.out.println();
			return;
		}
		out: for(int i = 0; i < n; i++) {
			for(int x = 0; x < cnt; x++) {
				if(hist[x] == i)continue out;
			}
			int[] new_hist = new int[m];
			Arrays.fill(new_hist, -1);
			for(int x = 0; x < cnt; x++) {
				new_hist[x] = hist[x];
			}
			new_hist[cnt] = i;
			solve(new_hist, cnt+1);
		}
	}

}
