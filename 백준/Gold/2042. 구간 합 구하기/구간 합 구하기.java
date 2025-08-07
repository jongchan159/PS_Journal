import java.io.*;
import java.util.*;

public class Main {
	static long[] indexed;
	static int offset;
	static int n, k, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		initTree(arr);
		
		for(int i=0; i < m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				// tree[b] -> c
				updateTree(b, c);
			}
			else {
				// sum[b ~ c]
				long sum = searchTree(b, (int)c);
				System.out.println(sum);
			}
		}
	}
	
	public static void initTree(long[] arr) {
		// initialize indexed tree
		offset = 1;
		while(offset < n) offset *= 2;
		
		indexed = new long[offset*2]; // s = 0
		offset--;
		Arrays.fill(indexed, 0);
		
		// leaf
		for(int i = 1; i<=n; i++) {
			indexed[offset + i] = arr[i-1];
		}
		
		// inner
		for(int i = offset; i > 0; i--) {
			indexed[i] = indexed[2*i] + indexed[2*i + 1];
		}
		
	}
	
	public static void updateTree(int idx, long value) {
		idx += offset;
		
		// leaf
		indexed[idx] = value;
		
		// inner(ancestor)
		while(idx > 0) {
			idx /= 2;
			indexed[idx] = indexed[2*idx] + indexed[2*idx + 1];
		}
	}
	
	public static long searchTree(int s, int e) { // two-pointer : (i, j)
		// a ~ b
		int l = s + offset, r = e + offset;

		long ans = 0;
		while(l <= r) {
			if(l % 2 == 1) { // l -> right node
				ans += indexed[l++];
			}
			if(r % 2 == 0) { // r -> left node
				ans += indexed[r--];
			}
			l /= 2;
			r /= 2;
		}
		
		return ans;
	}
	
}