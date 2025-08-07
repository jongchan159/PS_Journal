import java.io.*;
import java.util.*;

// 1932 - DP
public class Main
{
    static final int INF = 100000*1000+1;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i=0; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] DP = new int[k+1];
        DP[0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = coins[i]; j <= k; j++) {
                DP[j] += DP[j - coins[i]];
            }
        }

        System.out.print(DP[k]);
    }
    
}