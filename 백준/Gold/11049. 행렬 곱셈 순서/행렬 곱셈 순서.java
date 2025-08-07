import java.io.*;
import java.util.*;

public class Main {
	static int[][] D, matrix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		matrix = new int[2][n];
		D = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[0][i] = Integer.parseInt(st.nextToken());
			matrix[1][i] = Integer.parseInt(st.nextToken());
		}
		
		D = new int[n][n];
		System.out.print(cal(0, n-1));
	}
	
	static int cal(int s, int e) {
		if(s == e) return 0;
		if(D[s][e] != 0) return D[s][e];
		
		int min = Integer.MAX_VALUE;
		for(int i = s; i < e; i++) {
			min = Math.min(min, cal(s, i) + cal(i+1, e) + matrix[0][s] * matrix[1][i] * matrix[1][e]);
		}
		
		return D[s][e] = min;
	}

}