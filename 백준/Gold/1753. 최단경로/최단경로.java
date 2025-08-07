import java.io.*;
import java.util.*;

public class Main
{
	static int V, E, K, INF = 1 << 30;
	static List<int[]>[] adjList;
	static int[][] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()); // start
		
		adjList = new ArrayList[V+1];
		for(int i=0; i < V+1; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for(int i=0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new int[] {v, weight});
		}
		
		// 다익스트라
		int[] distance = new int[V+1];
		Arrays.fill(distance, INF);
		distance[K] = 0;
		
		boolean[] visited = new boolean[V+1];

		// pq 처리
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				Comparator.comparingInt((int[] o) -> o[1])
				);
		pq.offer(new int[] {K, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int u = cur[0];
			if(visited[u] == true) continue;
			visited[u] = true;
			
			for(int[] edge: adjList[u]) {
				int v = edge[0];
				int w = edge[1];
				
            
				if(distance[v] > distance[u] + w) {
					distance[v] = distance[u] + w;
					pq.offer(new int[] {v, distance[v]});
				}
			}
		}
		
		// 출력
		for(int i=1; i < V+1; i++) {
			if(distance[i] == INF) 
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}
}