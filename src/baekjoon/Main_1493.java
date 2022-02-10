package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1493 {
	// 입력 변수
	static int L;
	static int W;
	static int H;
	static int N;
	static int[] cubeSize;
	static int[] cubeCnt;
	// 임의 생성 변수
	static int[] dpPow = new int[20];
	static int used;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// L, W, H 입력
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		// N 입력
		N = Integer.parseInt(br.readLine());
		
		// Box 크기와 Box 개수 각각 배열에 입력.
		cubeSize = new int[N];
		cubeCnt = new int[N];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			cubeSize[n] = Integer.parseInt(st.nextToken());
			cubeCnt[n] = Integer.parseInt(st.nextToken());
		}

		// 박스를 채울 수 있으면(true) 사용한 큐브의 개수 출력
		if(fillBox(L, W, H, N-1)) {
			System.out.println(used);
		}
		// 아니라면 -1 출력
		else {
			System.out.println(-1);			
		}
		
	}
	
	private static boolean fillBox(int length, int weight, int height, int cubeNum) {
		// 부피가 존재하지않는 박스라면 true 리턴(박스를 채운걸로 여긴다)
		if(length == 0 || weight == 0 || height == 0) {
			return true;
		}
		
		// 박스에 빈공간이 남았지만 채울 수 있는 큐브가 없으면 false 리턴(못 채움)
		if(cubeNum < 0) {
			return false;
		}

		
		// 큐브의 한 변의 길이보다 박스의 가로,세로,높이가 하나라도 작을 때  || 또한 현재 큐브를 다 사용했을때 작은 큐브를 사용한다
		int cubeLength = length(cubeSize[cubeNum]);
		if(length < cubeLength || weight < cubeLength || height < cubeLength || cubeCnt[cubeNum] == 0) {
			
			// 더 작은 큐브로 박스를 채운다
			return fillBox(length, weight, height, cubeNum-1);
		}
		else { // else: 큐브의 한 변의 길이보다 박스의 모든 가로,세로,높이가 같거나 크다면
			
			// 큐브를 하나 사용하여 채우고 박스를 4개로 쪼갠다
			used++;
			cubeCnt[cubeNum]--;
			// 4개로 나눠진 박스를 채운 결과(true, false)를 and연산하여 반환한다.
			return 	fillBox(cubeLength			, cubeLength			, height - cubeLength	, cubeNum) &&	// 큐브를 채운 부분 (높이 감소)
					fillBox(cubeLength			, weight - cubeLength	, height				, cubeNum) &&
					fillBox(length - cubeLength , cubeLength			, height				, cubeNum) &&
					fillBox(length - cubeLength	, weight - cubeLength	, height				, cubeNum);
		}

	}
	
	
	private static int length(int boxSize) {
		if(boxSize == 0) return 1;
		else if(dpPow[boxSize] != 0) return dpPow[boxSize];
		else return dpPow[boxSize] = length(boxSize-1) * 2;
	}

}
