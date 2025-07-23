#include <bits/stdc++.h>

using namespace std;

// sort(v.begin(), v.end()) -> 날먹치트키
// selection sort -> 성공 but 시간 초과
void s_sort(vector<int>& arr, int len){
    for(int i=0; i<len-1; i++){
        int min = arr[i];
        int min_i = i;
        for(int j=i+1; j<len; j++){
            if(arr[j] < min) {
                min = arr[j];
                min_i = j;
            }     
        }
        swap(arr[i], arr[min_i]);
    }
}
// insertion sort -> 성공 but 시간 초과
void i_sort(vector<int>& v, int len){
    for(int i=1; i<len; i++){
        for(int j=i; j>0; j--){
            if(v[j-1] > v[j]) swap(v[j], v[j-1]);
            else break;
        }
    }
}
// merge sort -> 성공 but 시간 초과
void m_sort(vector<int>& v, int left, int right){
    if(left >= right) return; // minimum unit

    int mid = (left+right) / 2;

    m_sort(v, left, mid);
    m_sort(v, mid+1, right);
    
    // merge procedure
    vector<int> tmp_v(right - left + 1);

    // sorted insertion
    int i=left, j=mid+1, k=0;
    while (i <= mid && j <= right) {
        if (v[i] <= v[j]) 
            tmp_v[k++] = v[i++];
        else              
            tmp_v[k++] = v[j++];
    }
    // rest
    while (i <= mid)   
        tmp_v[k++] = v[i++];
    while (j <= right) 
        tmp_v[k++] = v[j++];

    // merge
    for(int i=left; i<=right; i++) 
        v[i] = tmp_v[i - left];
}

// bubble sort -> 성공 but 시간 초과
void b_sort(vector<int>& v, int len){
    for(int i=len-1; i>=0; i--){
        for(int j=0; j<i; j++){
            if(v[j] > v[j+1]) swap(v[j], v[j+1]);
            else break;
        }
    }
}

// quick sort -> 성공 but 시간 초과
vector<int> q_sort(vector<int>& v){
    if (v.size() <= 1) return v;

    int pivot = v[v.size() / 2]; // 임시로 중앙값
    vector<int> left, right, equal;

    for (int x : v) {
        if (x < pivot) left.push_back(x);
        else if (x > pivot) right.push_back(x);
        else equal.push_back(x);
    }

    left = q_sort(left);
    right = q_sort(right);

    // 붙이기
    left.insert(left.end(), equal.begin(), equal.end());
    left.insert(left.end(), right.begin(), right.end());

    return left;
}

int main() {
    ios::sync_with_stdio(0), cin.tie(0);

    int N;
    cin >> N;

    vector<int> arr(N); // 이 경우엔 배열이 유리하지만 vector 연습 겸
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    m_sort(arr, 0, N-1);

    for(int i=0; i<N; i++){
        cout << arr[i] << '\n';
    }
}