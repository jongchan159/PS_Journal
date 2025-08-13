import java.io.*;
import java.util.*;

// 2504
class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String args[]) throws Exception
    {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] input = br.readLine().toCharArray();
        long tmp = 1, ans = 0;
        char pre = ' ';

        for(char c : input) {
            switch(c) {
                case '(':
                    stack.push(c);
                    tmp *= 2;
                    break;

                case '[':
                    stack.push(c);
                    tmp *= 3;
                    break;

                case ')':
                    if(stack.isEmpty() || stack.peek() != '(') {
                        System.out.println(0);
                        return;
                    }
                    stack.pop();
                    if(pre == '(')
                        ans += tmp;
                    tmp /= 2;
                    break;

                case ']':
                    if(stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    }
                    stack.pop();
                    if(pre == '[')
                        ans += tmp;
                    tmp /= 3;
                    break;
            }
            pre = c;
        }
            if(!stack.isEmpty()) {
                System.out.println(0);
                return;
            }
        System.out.println(ans);
    }
}