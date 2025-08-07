import java.io.*;
import java.util.*;

// 1932 - DP
public class Main
{
    static final int INF = 100000*1000+1;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] weight = new int[N];
        int[] value = new int[N];
        int maxValue = 0;
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
            maxValue += v;
        }

        int[] DP = new int[maxValue+1];
        Arrays.fill(DP, INF);
        DP[0] = 0;

        // 모든 짐에 대해서
        for(int i=0; i<N; i++) {
            // 최대 가치부터 짐의 가치를 빼보면서 점검
            for(int j = maxValue; j >= value[i]; j--) {
                DP[j] = Math.min(DP[j], DP[j  - value[i]] + weight[i]);
            }
        }

        for(int i = maxValue; i >= 0; i--) {
            // System.out.println("DP[" + i + "] = " + DP[i]);
            if(DP[i] <= K) {
                System.out.print(i);
                return;
            }
        }
        return;
    }
    
}