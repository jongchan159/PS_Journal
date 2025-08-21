import java.io.*;
import java.util.*;

// 2252
class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static int[] inbound;
	static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String args[]) throws Exception
	{
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		inbound = new int[n];
		Arrays.fill(inbound, 0);
		
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		// sort
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) -1;
			int b = Integer.parseInt(st.nextToken()) -1;
			inbound[a]++;
			graph.get(b).add(a);
		}
		
		// print
		int[] sorted = tSort(n);
		for(int i=n; i>0; i--) {
			bw.write(sorted[i-1] + " ");
			bw.flush();
		}
		
		bw.close();
		return;
	}
	
	static int[] tSort(int n) {
		int[] sorted = new int[n];
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		int idx=0;
		
		for(int i=0; i<n; i++) {
			if(inbound[i] == 0) {
				dq.add(i);
			}
		}
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			sorted[idx++] = cur+1;
			
			for(Integer p : graph.get(cur)) {
				inbound[p]--;
				if(inbound[p] == 0) {
					dq.add(p);
				}
			}
		}
		
		return sorted;
	}
	
	
}