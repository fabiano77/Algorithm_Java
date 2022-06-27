package baekjoon;

import java.io.*;
import java.util.*;

public class Main_25192 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		Set<String> set = new HashSet<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			if(str.equals("ENTER")) {
				ans += set.size();
				set = new HashSet<>();
			}
			else {
				set.add(str);
			}
		}
		
		System.out.println(ans + set.size());

		br.close();
		

	}

}
