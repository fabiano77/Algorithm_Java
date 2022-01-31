package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1588 {
	static int firstNum;
	static long left;
	static long right;
	static int N;
	// 1, 2, 3이 각각 1초뒤 쪼개지는 수열 (1 -> 1, 3, 2)
	static int[][] pattern = { {}, { 0, 1, 3, 2 }, { 0, 2, 1, 1 }, { 0, 2, 3, 2 } };
	// 1,2,3의 개수를 담을 배열
	static int[] answer = new int[4];

	public static void main(String[] args) throws IOException {
		System.setIn(Main_1588.class.getResourceAsStream("input_1588.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		firstNum = Integer.parseInt(br.readLine());
		left = Integer.parseInt(br.readLine());
		right = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		divide(firstNum, N, 0, (long)Math.pow(3, N));

		for (int i = 1; i <= 3; i++) {
			System.out.print(answer[i] + " ");
		}
	}

	// first와 last는 3의 제곱수의 배수
	static void divide(int num, int power, long first, long last) { // right 포함하지않음
		// left와 right를 벗어난 경우 리턴
		if (right < first || last <= left) {
			return;
		}
		// 현재 범위가 left와 right 내부일 경우
		if (left <= first && last-1 <= right) {
			//System.out.println("search [num = "+num+", power = "+power+", first = "+first+", last = "+last+"]");
			// 현재 num을 이용해서 결과값에 더해주고 리턴
			count(power, num);
			return;
		}
		
		// 더 작은 3의 제곱수로 분할 탐색
		power--;
		// 탐색 간격
		long interval = (long)Math.pow(3, power);
		// 3부분으로 쪼개어, 현재 num이 1초후 지나 분열된 num을 각각 넘겨주고 위 과정을 반복한다.
		for(int i = 1; i <= 3; i++) {
			divide(pattern[num][i], power, first+(interval)*(i-1), first+interval*(i));
		}
	}

	//현재 number의 N초후 수열의 1,2,3 의 개수를 세는 과정.
	static void count(long power, int number) {
		int[] count = new int[4];
		count[number]++;
		for (int i = 0; i < power; i++) {
			int[] temp = new int[4];
			temp[1] = count[1] + count[2] * 2;
			temp[2] = count[1] + count[2] + count[3] * 2;
			temp[3] = count[1] + count[3];
			count = temp;
		}

		for (int i = 1; i <= 3; i++) {
			answer[i] += count[i];
		}
	}
}
