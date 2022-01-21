package beakjoon;

import java.util.Scanner;

public class Main_4256 {
	static int T;
	static int[] preorder;
	static int[] inorder;
	
	public static void postorder(int start, int end, int pos) {
		// start 탐색할 중위 순회의 시작점
		// end 중위 순회의 끝점
		// pos 전위 순회의 루트
		for (int i = start; i < end; i++) {
			if(preorder[pos] == inorder[i]) {
				// 좌측 서브트리 탐색
				postorder(start, i, pos+1);
				// 우측 서브트리 탐색
				postorder(i+1, end, pos+((i+1)-start));
				System.out.printf(preorder[pos]+" ");
			}
		}
	}

	public static void main(String[] args) {
		//System.setIn(Main_4256.class.getResourceAsStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			// 전위, 중위 순회 배열 생성
			preorder = new int[n];
			inorder = new int[n];
			for (int i = 0; i < n; i++) {
				preorder[i] = sc.nextInt();
			}
			for (int i = 0; i < n; i++) {
				inorder[i] = sc.nextInt();
			}
			postorder(0, n, 0);
			System.out.println();
		}
	}
}
