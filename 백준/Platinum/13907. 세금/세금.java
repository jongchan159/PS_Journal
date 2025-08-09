import java.io.*;
import java.util.*;

// 다익스트라
// 간접리스트
public class Main
{
    static int N, M, K, INF = Integer.MAX_VALUE;
    static List<int[]>[] adjList;
    static int[][] dist;
    
    public static void main(String[] args) throws Exception { 

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // V
        M = Integer.parseInt(st.nextToken()); // E
        K = Integer.parseInt(st.nextToken()); // 세금 인상

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        adjList = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[] {b, cost});
            adjList[b].add(new int[] {a, cost});
        }

        int[] tax = new int[K+1];
        // 세금은 누적된다 -> point 1
        for(int i=1; i<K+1; i++) {
            tax[i] = tax[i-1] + Integer.parseInt(br.readLine()); 
        }
        
        // O(MlogN)
        djikstra(start);

        // O(K*N)
        for(int k=0; k<K+1; k++){ // 세금 인상 라운드
            long minDist = Long.MAX_VALUE;
            for(int i=1; i<N; i++) { // N-1간선 수까지 체크
                if(dist[end][i] != INF){
                    minDist = Math.min(minDist, (long)dist[end][i] + (long)i*tax[k]);
                }
            }
            sb.append(minDist).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void djikstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1[1], o2[1]) // cost min heap
        );

        dist = new int[N+1][N]; // (node num, # of edges(간선 수는 최대 n-1)
        for(int i=0; i<N+1; i++){
            Arrays.fill(dist[i], INF);
        }

        // boolean[] visited = new boolean[N+1];
        pq.offer(new int[] {start, 0, 0}); // u, min, # of edges
        dist[start][0] = 0; // cost
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int cost = cur[1];
            int numEdges = cur[2];

            if(numEdges >= N-1) continue;
            // 현재 코스트가 갱신된 코스트인지 확인 -> point 2
            if(cost != dist[u][numEdges]) continue;
            // if(visited[u] == true) continue;
            // visited[u] = true;
            // 시간 초과가 난 이유 : 거리가 무한대인 간선을 굳이 할 이유가 없음 (visited 대신 변경) -> point 4
            if(dist[u][numEdges] == INF) continue;

            for(int[] edge : adjList[u]) {
                int next = edge[0];
                int nextCost = edge[1];

                // 메모리 초과가 난 이유 : 큐에 너무 많이 넣어서 - 개선될 때만 삽입 -> point 3
                if(dist[next][numEdges+1] > dist[u][numEdges] + nextCost){
                    dist[next][numEdges+1] = dist[u][numEdges] + nextCost;
                    pq.offer(new int[] {next, dist[next][numEdges+1], numEdges+1});
                }
            }
        }
    }
}