package baekjoon;
 import java.io.*;
    import java.util.*;


    public class Main_1253 {


        public static void main(String[] args) throws IOException {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            int cnt = 0;
            for (int i = n - 1; i >= 0; i--) {
                int val = arr[i];
                boolean flag= false;
                    for (int j = n-1; j >= 0; j--) {

                        int l = 0;
                        int r = j - 1;
                        if(j == i)
                            continue;
                        while (l <= r) {
                            int mid = (l + r) / 2;
                            int val2 = val - arr[j];
                            if (arr[mid] > val2) {
                                r = mid - 1;
                            } else if (arr[mid] == val2 && mid != i && mid != j) {
                                flag = true;
                                break;
                            } else {
                                l = mid + 1;
                            }
                        }

                    }
                    if (flag)
                        cnt++;
            }
            System.out.println(cnt);
        }


    }