import java.io.*;
import java.util.*;

// 2243 사탕상자 - 인덱스 트리
public class Main
{
    static int offset = 1, depth = 1;
    static int[] tree;
    public static void main(String[] args) throws Exception { 

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        initTree(1000000);

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            // System.out.println("round: " + (i+1));
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 1) {
                sb.append(queryTree(b)).append("\n");
            }
            else {
                int c = Integer.parseInt(st.nextToken());
                updateTree(b, c);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void initTree(int len) {
        while(offset < len) {
            offset <<= 1;
            depth++;
        }
        tree = new int[offset*2 + 1];
        offset--;
    }

    static int queryTree(int rank) {
        // two pointer
        int idx = 1;
        for(int i=1; i<depth; i++) { 
            int l = idx*2;
            int r = idx*2 + 1;
            // System.out.println("rank = " + rank + " tree[l] = " + tree[l]);
            if(rank > tree[l]) {
                idx = r;
                rank -= tree[l];
                // System.out.println("go r");
            }
            else {
                idx = l;
                // System.out.println("go l");
            }
            // System.out.println("idx = " + idx);
        }
        int ans = idx - offset;
        updateTree(ans, -1);

        // System.out.println("answer = " + (idx - offset));
        return ans;
    }

    static void updateTree(int idx, int value){
        idx += offset;

        // leaf
        tree[idx] += value;
        // System.out.println("leaf - idx: " + idx + " tree[idx]: " + tree[idx]);

        // inner
        while(idx > 0) {
            idx /= 2;
            tree[idx] = tree[2*idx] + tree[2*idx + 1];
        }
        // System.out.println("tree[1] = " + tree[1]);
    }
}
