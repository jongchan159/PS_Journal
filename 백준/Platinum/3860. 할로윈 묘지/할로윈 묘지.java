import java.io.*;
import java.util.*;

public class Main
{
    static int W, H, exit, ent;
    static List<int[]> edges;
    static Set<Integer> graves;
    static Set<Integer> holes;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String cmd = br.readLine();
        while(cmd.charAt(0) != '0') {
            edges = new ArrayList<>();
            graves = new HashSet<>();
            holes = new HashSet<>();
            
            st = new StringTokenizer(cmd);
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            exit = W*H; // 총 노드 개수
            ent = 1;

            // graves
            int G = Integer.parseInt(br.readLine()); // 묘비 개수
            for(int i=0; i<G; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int grave = y*W + x+1;
                graves.add(grave);
            }

            // add hole
            int E = Integer.parseInt(br.readLine()); // 구멍 개수
            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                int s = y1*W + x1+1; // 1,2 -> 2*3 + 2 = 8
                int e = y2*W + x2+1;

                holes.add(s);
                edges.add(new int[] {s, e, weight});
            }

            // graph
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            for (int y=0; y < H; y++) {
                for (int x=0; x < W; x++) {
                    int cur = y*W + x+1;
                    if(graves.contains(cur) || holes.contains(cur) || cur == exit){
                        continue;
                    }

                    for(int d=0; d<4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if(nx >= 0 && nx < W && ny >= 0 && ny < H) {
                            int next = ny*W + nx+1;
                            if(!graves.contains(next)) {
                                edges.add(new int[] {cur, next, 1});
                            }
                        }
                    }
                }
            }

            // bellman
            bellman(ent, exit);

            cmd = br.readLine();
        }

        System.out.println(sb.toString());
        return;
    }

    static void bellman(int start, int dest) {
        // dist 초기화
        dist = new int[exit+1];
        for(int i=1; i<exit+1; i++) {
            dist[i] = INF;
        }
        dist[start] = 0;

        // V-1번까지 확인 후 V번째에서 최단경로 갱신 -> 음수 싸이클
        for(int i=0; i<exit-1; i++) { 
            // 모든 간선 확인 -> E
            for(int[] edge: edges) {
                // u -> v (w)
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(dist[u] == INF) continue;

                // 최단거리 갱신
                if(dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // exit 번째에서 값이 갱신되면 음수 싸이클
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(dist[u] == INF) continue;

            if(v != dest && dist[v] > dist[u] + w) {
                sb.append("Never\n");
                return;
            }
        }

        if(dist[dest] != INF) 
            sb.append(dist[dest]).append("\n");
        else
            sb.append("Impossible\n");
    }


}