import java.io.*;
import java.util.*;

public class Main
{
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int[][] DP = new int[s1.length][s2.length]; // (max, preAlpa)
		int ans = 0;		
		
		for(int i = s1.length-1; i >= 0; i--) {
			for(int j = s2.length-1; j >= 0; j--) {
				if(s1[i] == s2[j]) {
					DP[i][j] = 1;
				}
				else {
					DP[i][j] = 0;
				}
			}
		}
		
		for(int i = s1.length-2; i >= 0; i--) {
			for(int j = s2.length-2; j >= 0; j--) {
				if(s1[i] == s2[j]) {
					if(s1[i+1] == s2[j+1]) {
						DP[i][j] = DP[i+1][j+1] + 1;
					}
					else {
						DP[i][j] = 1;
					}
					ans = Math.max(DP[i][j], ans);
				}
			}
		}
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		
	}

	
}