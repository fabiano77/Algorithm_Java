package baekjoon;

import java.io.*;
import java.util.*;

public class Main_6597 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(br.ready()) {
			StringBuilder output = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			String preorder = st.nextToken();
			String inorder = st.nextToken();
			
			postOrder(preorder, inorder, output);					
			System.out.println(output);
		}

		br.close();
		

	}
	
	public static void postOrder(String preorder, String inorder, StringBuilder output) {
		if(preorder.length() == 0) return;
		
		char root = preorder.charAt(0);
		int idx = inorder.indexOf(root)+1;
		
		
		
		postOrder(preorder.substring(1, idx),					inorder.substring(0, idx-1),				output);
		postOrder(preorder.substring(idx, preorder.length()), 	inorder.substring(idx, inorder.length()), 	output);
		output.append(root);
		
	}

}
