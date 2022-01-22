package beakjoon;

import java.util.Scanner;

class Node{
	char value;
	Node left;
	Node right;
	
	public Node(){
		this.value = ' ';
		this.left = null;
		this.right = null;
	}
}

public class Main_1991 {
	static int N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setIn(Main_1991.class.getResourceAsStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		Node[] nodes = new Node[N];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node();
		}
		for(int i = 0; i < N; i++) {
			String input = sc.nextLine();
			int root = toOrder(input.charAt(0));
			int left = toOrder(input.charAt(2));
			int right = toOrder(input.charAt(4));
			nodes[root].value = input.charAt(0);
			if (left != -1) {
				nodes[root].left = nodes[left];
			}
			if (right != -1) {
				nodes[root].right = nodes[right];
			}
		}
		preOrder(nodes[0]);
		System.out.println();
		inOrder(nodes[0]);
		System.out.println();
		postOrder(nodes[0]);
		System.out.println();
	}
	public static void preOrder(Node n) {
		if(n == null) return;
		System.out.print(n.value);
		preOrder(n.left);
		preOrder(n.right);
	}
	public static void inOrder(Node n) {
		if(n == null) return;
		inOrder(n.left);
		System.out.print(n.value);
		inOrder(n.right);
	}
	public static void postOrder(Node n) {
		if(n == null) return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n.value);
	}
	public static int toOrder(char c) {
		if (c == '.') {
			return -1;
		}
		return c - 'A';
	}

}
