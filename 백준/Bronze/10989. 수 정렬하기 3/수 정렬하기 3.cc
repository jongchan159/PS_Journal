#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(0), cin.tie(0);

    int cnt[10001] = {0}; // 0 ~ 10000
    int N;
    cin >> N;

    for(int i=0; i<N; i++){
        int num;
        cin >> num;
        cnt[num]++;
    }

    for(int i=0; i<10001; i++){
        if(cnt[i] != 0){
            for(int j=0; j<cnt[i]; j++) cout << i << '\n';
        }
    }
}