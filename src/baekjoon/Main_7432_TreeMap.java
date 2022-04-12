package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 메모리 28168 KB
 * 시간 236 ms
 */
public class Main_7432_TreeMap {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		TreeMap<String, Object> root = new TreeMap<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer dirs = new StringTokenizer(br.readLine(), "\\");
			TreeMap<String, Object> treePointer = root;
			while(dirs.hasMoreTokens()) {
				String name = dirs.nextToken();
				if(!treePointer.containsKey(name)) {
					treePointer.put(name, new TreeMap<>());
				}
				treePointer = (TreeMap)treePointer.get(name);
			}
		}
		StringBuilder output = new StringBuilder();
		printTreeMap(root, 0, output);
		System.out.println(output);
	}
	
	public static void printTreeMap(TreeMap<String, Object> tree, int depth, StringBuilder output) {
		StringBuilder space = new StringBuilder();
		for(int i = 0; i < depth; i++) space.append(" ");
		
		for(Object obj : tree.keySet()) {
			String str = (String)obj;
			output.append(space).append(str).append("\n");
			printTreeMap((TreeMap)tree.get(str), depth+1, output);
		}
	}

}
