import java.io.*;
import java.util.*;

// 1717
class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static int[] u;
	
	public static void main(String args[]) throws Exception
	{
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		u = new int[n+1];
		for(int i=0; i<n+1; i++) u[i] = i;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) { // union
				union(a, b);
			}
			else { // find
				String ans = "NO";
				
				if(find(a) == find(b)) ans = "YES";
				
				bw.write(ans + "\n");
				bw.flush();
			}
		}
		bw.close();
		return;
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
        
        if(a < b)
		    u[a] = b;
        else
            u[b] = a;
	}
	
	static int find(int target) {
		if(u[target] == target) return target; // root
		else return u[target] = find(u[target]);
	}
}