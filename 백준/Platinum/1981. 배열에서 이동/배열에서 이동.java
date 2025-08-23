import java.io.*;
import java.util.*;

// 1981
public class Main
{
    static int n, min = 200, max = 0, result = 200;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1}; // 상하좌우
    
    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;
                min = Math.min(v, min);
                max = Math.max(v, max);
            }
        }
        
        // binary search
        result = max - min;
        binarySearch(0, max - min);

        bw.write(Integer.toString(result));
        bw.close();
    }

    // 차잇값에 대해 이분탐색
    static void binarySearch(int s, int e) {
        if(s > e) {
            return;
        }
        
        int mid = (s+e) / 2;
        // BFS 성공 -> 더 낮은값 확인하기
        if(bfs(mid)) {
            result = Math.min(result, mid);
            binarySearch(s, mid-1);
        }
        // 실패 시 높은 값으로 다시 탐색
        else {
            binarySearch(mid+1, e);
        }

        return;
    }

    static boolean bfs(int gap) {
        int s = min, e = s + gap;
        
        while(e <= max) {
            if(map[0][0] < s || map[0][0] > e) {
                s++; e++;
                continue;
            }
            
            ArrayDeque<int[]> dq = new ArrayDeque<>(); // (y,x)
            boolean[][] visited = new boolean[n][n];
            
            dq.offer(new int[] {0,0});
            visited[0][0] = true;
            
            while(!dq.isEmpty()) {
                int[] cur = dq.poll();
                int cy = cur[0];
                int cx = cur[1];
                // System.out.println("cur x = " + cx + " cur y = " + cy);
                
                for(int d=0; d<4; d++) {
                    int ny = cy + dy[d], nx = cx + dx[d];
                    if(ny < 0 || ny > n-1 || nx < 0 || nx > n-1) continue; // 지도 범위 벗어남
                    if(visited[ny][nx]) continue; // 이미 방문 or 최대값 체크
                    // 최대값보다 크거나 최소값보다 작으면 방문한것처럼 표시
                    if(map[ny][nx] > e || map[ny][nx] < s) {
                        visited[ny][nx] = true;
                        continue;
                    }
                    // 다음 지역이 도착지
                    if(ny == n-1 && nx == n-1) {
                        return true;
                    }
                    
                    visited[ny][nx] = true;
                    dq.offer(new int[] {ny, nx});
                }
            }
            
            s++; e++;
        }
        
        return false;        
    }
}