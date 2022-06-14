package programmers;

import java.util.*;

class lv3_카카오_합승택시요금 {
    final static int INF = 100_000_000;
    
    static class Edge{
        public int node;
        public int dist;
        
        public Edge(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
        
        @Override
        public String toString() {
            return "Edge[node="+node+", dist="+dist+"]";
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        // 비방향그래프
        // 합승을 적적히 이용하여 택시요금 아끼기
        // 지점 개수 n  (~300)
        // A의 도착지 a 
        // B의 도착지 b
        // 지점사이 요금 fares (~45000)
        // 출발지 s
        
        // a에서 다익스트라, b에서 다익스트라, s에서 다익스트라
        // (s->x) + (x->a) + (x->b)
        
        List<List<Edge>> graph = makeGraph(n, fares);
        
        int[] distanceFromS = dijkstra(n, s, graph);
        int[] distanceFromA = dijkstra(n, a, graph);
        int[] distanceFromB = dijkstra(n, b, graph);
       
        int minNode = -1;
        int minDist = INF;
        
        for(int node = 1; node <= n; node++){
            int totalDist =  distanceFromS[node]
                            +distanceFromA[node]
                            +distanceFromB[node];
            if(minDist > totalDist){
                minDist = totalDist;
                minNode = node;
            }
        }
        
        return minDist;
    }
    
    public List<List<Edge>> makeGraph(int n, int[][] fares) {
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares){
            int node1 = fare[0];
            int node2 = fare[1];
            int dist = fare[2];
            graph.get(node1).add(new Edge(node2, dist));
            graph.get(node2).add(new Edge(node1, dist));
        }
        
        return graph;
    }
    
    public int[] dijkstra(int n, int start, List<List<Edge>> graph) {
        int[] distances = new int[n+1];
        Arrays.fill(distances, INF);
        
        distances[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.dist, o2.dist));
        for(Edge edge : graph.get(start)) {
            pq.offer(edge);
        }
        
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(distances[edge.node] != INF) continue;
            distances[edge.node] = edge.dist;
            for(Edge next : graph.get(edge.node)) {
                pq.offer(new Edge(next.node, edge.dist + next.dist));
            }
        }
        
        return distances;
    }
}