import java.io.*;
import java.util.*;

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int L, C;
	static char[] carr; // 여기
	static char[] pwd; // 여기
	
	public static void main(String args[]) throws Exception
	{
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		carr = new char[C];
		pwd = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			carr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(carr);
		password(0, 0);
		
		br.close();
		bw.close();
	}
	
	static void password(int idx, int stage) throws Exception 
	{
		if(stage == L) {
			// 모음 자음 체크
			int v = 0; // 모음
			int c = 0; // 자음
			for(int i=0; i<L; i++) {
				if(pwd[i] == 'a' || pwd[i] == 'e' || pwd[i] == 'i' || pwd[i] == 'o' || pwd[i] == 'u') {
					v++;
				}
				else c++;
			}
			if(v >= 1 && c >= 2) {
				bw.write(String.valueOf(pwd) + "\n"); // 여기
			}
			return;
		}
		else {
			for(int i=idx; i<C; i++) { // 여기
				pwd[stage] = carr[i];
				password(i+1, stage+1); // ++stage vs stage+1
			}
		}
	}
}