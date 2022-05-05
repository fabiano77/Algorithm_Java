import java.io.*;
import java.util.*;

class Main_6497 {
    static class Edge implements Comparable<Edge>{
        int a, b;
        int dist;
        public Edge(int a, int b, int dist){
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
        public int compareTo(Edge o){
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
		    st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(V == 0 && E == 0) break;

            int[] parents = new int[V+1];
            for(int i = 1; i <= V; i++){
                parents[i] = i;
            }
            
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            int total = 0;
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                total += d;
                pq.offer(new Edge(a, b, d));
            }

            int sum = 0;
            for(int i = 0; i < V-1; i++){
                Edge cur = pq.poll();
                int pA = find(parents, cur.a);
                int pB = find(parents, cur.b);
                if(pA == pB) {
                    i--;
                    continue;
                }
                union(parents, cur.a, cur.b);
                sum += cur.dist;    
            }

            System.out.println(total - sum);


            
            }

    }

    public static int find(int[] parents, int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents, parents[a]);
    }
    public static void union(int[] parents, int a, int b){
        int pA = find(parents, a);
        int pB = find(parents, b);
        if(pA < pB){
            parents[pB] = pA;
        }
        else{
            parents[pA] = pB;
        }
    }
}
