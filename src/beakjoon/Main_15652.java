package beakjoon;

import java.util.Scanner;

public class Main_15652 {
	static int n;
	static int m;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		solve("", 1, m);
		System.out.println("끝");
		
	}
	static void solve(String hist, int i, int cnt) {
		if (cnt == 0) {
			System.out.println(hist);
			return;
		}
		for(int x = i; x <= n; x++) {
			solve(hist+x+" ", x, cnt-1);
		}
	}

}
