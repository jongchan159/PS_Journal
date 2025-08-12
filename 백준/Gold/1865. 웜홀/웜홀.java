import java.io.*;
import java.util.*;

// 1865 - BellmanFordMoore
public class Main
{
    static int N, M, W, INF = 10001;
    static List<int[]> edges;
    static int[] dist;
    
    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            dist = new int[N+1];
            // Arrays.fill(dist, INF);

            for(int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new int[] {u, v, w});
                edges.add(new int[] {v, u, w});
            }
            for(int w=0; w<W; w++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new int[] {u, v, -t});
            }

            // BFM
            if(Bellman(1)) sb.append("NO\n");
            else sb.append("YES\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean Bellman(int start) {
        // N-1번 반복 후 N번째에 음수 체크
        dist[start] = 0;
        
        for(int i = 1; i <= N; i++) {
            for(int[] edge: edges) { // u, v, w
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                
                if(dist[u] == INF) continue;
                if(dist[v] > dist[u] + w) {
                    if(i >= N) return false;
                    dist[v] = dist[u] + w;
                }
            }
        }
        
        return true;
    }
}