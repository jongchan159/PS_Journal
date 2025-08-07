import java.io.*;
import java.util.*;

// 1932 - DP
public class Main
{
    static final int INF = 100*100+1;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mem = new int[N];
        int[] cost = new int[N];
        int maxCost = 0;
        // memory
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }
        // cost
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];
        }

        int[] DP = new int[maxCost+1];
        // 첫번째 앱 부터 빼보고 모든 비용에서의 최대 메모리 체크
        for(int i = 0; i < N; i++){
            // 위부터 아래로 내려가면서 DP 채우기
            for(int j = maxCost; j >= cost[i]; j--) {
                DP[j] = Math.max(DP[j], DP[j - cost[i]] + mem[i]);
            }
        }

        for(int i = 0; i <= maxCost; i++) {
            if(DP[i] >= M) {
                System.out.println(i);
                return;
            }
        }
        return;
    }
    
}