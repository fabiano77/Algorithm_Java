package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2263 {
	static int N;
	static int[] inorder;
	static int[] postorder;
	static int bias;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2263.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, 중위 순회, 후위 순회 입력받기
		N = Integer.parseInt(br.readLine());
		inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		preorder(0, N, 0);
	}
	
	static void preorder(int start, int end, int bias) {
		//System.out.println("start = "+start+", end = "+end+", bias = "+bias);
		if(end <= start) {
			return;
		}
		
		// Post에서 가장 마지막에 있는 숫자가 트리의 root
		int rootIdxInPost = end-1;
		int rootNum = postorder[rootIdxInPost];
		
		// root 출력 (preorder)
		System.out.print(rootNum+" ");
		
		// inorder에서 root의 인덱스를 찾기
		int rootIdxInInorder = -1;
		for(int i = start; i <= rootIdxInPost&& i+bias < N; i++) {
			//System.out.println(i);
			// 우측 트리의 경우에는 postorder배열에 비해 inorder배열에서 bias만큼 차이난다.
			if(inorder[i+bias] == rootNum) {
				//System.out.println("this");
				rootIdxInInorder = i;
				break;
			}
		}

		
		// inorder에서의 root index를 찾고, 좌측트리, 오른쪽 트리로 나누어 전위순회한다.
		preorder(start, rootIdxInInorder, bias);
		preorder(rootIdxInInorder, end-1, bias+1);
		
	}

}
