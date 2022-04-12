package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 메모리 28732 KB
 * 시간 252 ms
 */
public class Main_7432_Trie {
	
	static class TrieNode {
		private Map<String, TrieNode> childNodes = new TreeMap<>();
	}
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		TrieNode root = new TrieNode();
		for(int i = 0; i < N; i++) {
			StringTokenizer dirs = new StringTokenizer(br.readLine(), "\\");
			TrieNode nodePointer = root;
			while(dirs.hasMoreTokens()) {
				String name = dirs.nextToken();
				if(!nodePointer.childNodes.containsKey(name)) {
					nodePointer.childNodes.put(name, new TrieNode());
				}
				nodePointer = nodePointer.childNodes.get(name);
			}
		}
		StringBuilder output = new StringBuilder();
		printTreeMap(root, 0, output);
		System.out.println(output);
	}
	
	public static void printTreeMap(TrieNode node, int depth, StringBuilder output) {
		StringBuilder space = new StringBuilder();
		for(int i = 0; i < depth; i++) space.append(" ");
		
		for(String name : node.childNodes.keySet()) {
			output.append(space).append(name).append("\n");
			printTreeMap(node.childNodes.get(name), depth+1, output);
		}
	}

}
