package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1461 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> posQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> negQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                posQ.offer(num);
            } else {
                negQ.offer(Math.abs(num));
            }
        }


        int maxV = 0;
        if (posQ.isEmpty()) {
            maxV = negQ.peek();
        } else if (negQ.isEmpty()) {
            maxV = posQ.peek();
        } else {
            maxV = Math.max(posQ.peek(), negQ.peek());
        }

        int ans = 0;

        while (!posQ.isEmpty()) {
            int temp = posQ.poll();
            for (int i = 0; i < M - 1; i++) {
                posQ.poll();

                if (posQ.isEmpty()) {
                    break;
                }
            }
            ans += temp * 2;
        }
        
        while (!negQ.isEmpty()) {
            int temp = negQ.poll();
            for (int i = 0; i < M - 1; i++) {
                negQ.poll();

                if (negQ.isEmpty()) {
                    break;
                }
            }
            ans += temp * 2;
        }

        ans -= maxV;
        System.out.println(ans);

		br.close();
		

	}

}
