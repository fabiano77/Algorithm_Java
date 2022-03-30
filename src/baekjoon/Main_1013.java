package baekjoon;

import java.io.*;
import java.util.regex.Pattern;

public class Main_1013 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			System.out.println(Pattern.matches("(100+1+|01)+", br.readLine())? "YES" : "NO");
		}
	}
}
