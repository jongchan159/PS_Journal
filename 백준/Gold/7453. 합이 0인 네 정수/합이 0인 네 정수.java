import java.io.*;
import java.util.*;

public class Main {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		long ans = 0;
		
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		// 문제 X, T(4N)
		
		int[] AB = new int[N*N];
		int[] CD = new int[N*N];
		for(int i=0; i<N*N; i+=N) {
			for(int j=0; j<N; j++) {
				AB[i+j] = A[i/N] + B[j];
			}
		}
		for(int i=0; i<N*N; i+=N) {
			for(int j=0; j<N; j++) {
				CD[i+j] = C[i/N] + D[j];
			}
		}
        // T(2 N^2)
        Arrays.sort(AB);
		Arrays.sort(CD);
		//for(int k=0; k<N*N; k++) {
            //int key = -AB[k];
            // 여러 값이 있을 때 순서 보장 X (중복 허용 시 X)
            // if(Arrays.binarySearch(CD, key) >= 0) {
            // 	ans++;
            // }
            // int high = highSearch(CD, key);
            // int low = lowSearch(CD, key);
            ans = twoPointerSearch(AB, CD);
		//}
		
		bw.write(String.valueOf(ans));
		bw.close();
	}

    static long twoPointerSearch(int[] AB, int[] CD){
        int l = 0, r = AB.length-1;
        long ans = 0;

        while (l < N*N && r >= 0) {
            if (AB[l] + CD[r] < 0) {
                l++;
            } else if (AB[l] + CD[r] > 0) {
                r--;
            } else {
                // 같은 값 개수 세기
                long lCount = 1, rCount = 1;
                while (l + 1 < N*N && (AB[l] == AB[l+1])) {
                    lCount++;
                    l++;
                }
                while (r > 0 && (CD[r] == CD[r-1])) {
                    rCount++;
                    r--;
                }
                ans += lCount * rCount;
                l++;
                r--;
            }
        }

        return ans;
    }
    
    static int highSearch(int[] arr, int key){
        int l = 0, r = arr.length;
        int mid;

        while(l < r){
            mid = (l+r) / 2;
            if(arr[mid] > key) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        return r;
    }

    static int lowSearch(int[] arr, int key){
        int l = 0, r = arr.length;
        int mid;

        while(l < r){
            mid = (l+r) / 2;
            if(arr[mid] >= key) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        return r;
    }
}