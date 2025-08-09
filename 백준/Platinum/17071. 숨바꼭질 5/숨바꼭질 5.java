import java.io.*;
import java.util.*;

public class Main
{
    static int N, K, round = 1;
    static int[] loc;
    static boolean[][] visited = new boolean[2][500001];
    
    public static void main(String[] args) throws Exception { 

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 동생이 방문 가능한 지점
        findLocation(K);

        // bfs -> 짝수 초 방문 / 홀수 초 방문
        int ans = bfs(N, loc, round);

        System.out.println(ans);
    }

    static void findLocation(int start) {
        int max = start;
        while(max <= 500000) {
            max += round;
            round++;
        }
        round--;

        loc = new int[round]; // 0, 1, ... round
        loc[0] = start; // round 0 -> 제자리

        for(int i=1; i < round ; i++) {
            loc[i] = loc[i-1] + i;
            // System.out.println("i = " + i + " loc = " + loc[i]);
        }
    }

    static int bfs(int start, int loc[], int round) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {start, 0});
        visited[0][start] = true; // 0은 짝에 포함

        // -1, +1, *2
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int curValue = cur[0];
            int curRound = cur[1];

            int parity = curRound % 2; // 0이면 짝, 1이면 홀
            if(curRound >= round) {
                return -1;
            }
            int end = loc[curRound];

            // 현재 라운드의 값이 방문한적 있으면 끝
            if(visited[parity][end]) { 
                return curRound;
            }

            int nextRound = curRound + 1;
            int nextValue = curValue - 1;
            int nextParity = nextRound % 2;
            if(nextRound > round) break;

            if(nextValue >= 0 && !visited[nextParity][nextValue]) {
                visited[nextParity][nextValue] = true;
                dq.offer(new int[] {nextValue, nextRound});
            }
            nextValue = curValue + 1;
            if(nextValue <= 500000 && !visited[nextParity][nextValue]) {
                visited[nextParity][nextValue] = true;
                dq.offer(new int[] {nextValue, nextRound});
            }
            nextValue = curValue * 2;
            if(nextValue <= 500000 && !visited[nextParity][nextValue]) {
                visited[nextParity][nextValue] = true;
                dq.offer(new int[] {nextValue, nextRound});
            }
        }

        return -1;
    }
}