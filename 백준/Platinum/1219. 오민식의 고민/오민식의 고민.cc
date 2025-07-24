#include <bits/stdc++.h>
#define MIN_LL -99999999LL

using namespace std;

int N, src, dst, M;
vector<tuple<int, int, int>> graph; // u->v, cost
vector<int> adj[100]; // 정방향 인접 리스트
bool visited[100], reachable = false;

void dfs(int u) {
    visited[u] = true;
    if (u == dst) {
        reachable = true;
        return;
    }
    for (int v : adj[u]) {
        if (!visited[v]) dfs(v);
    }
}

int main() {
    ios::sync_with_stdio(0), cin.tie(0);

    // int N, src, dst, M;

    cin >> N >> src >> dst >> M; // N == Vertex, M == Edge

    // init graph and dist array -> 여기는 꼭 암기
    // vector<tuple<int, int, int>> graph; // u->v, cost(weight)

    // input edge info
    for(int i=0; i<M; i++){
        int u, v, cost;
        cin >> u >> v >> cost;
        graph.push_back({u, v, cost});
        adj[u].push_back(v); // *정방향 인접 리스트 구성
    }

    // init & input profit -> weight = profit - cost (이후 연산에서만 활용해보자)
    int profit[N];
    for(int i=0; i<N; i++){
        cin >> profit[i];
    }

    long long total[N]; // total money (src -> all v)
    fill(total, total + N, MIN_LL);
    total[src] = profit[src];

    // Relaxation, src부터 시작
    for(int i=0; i<N-1; i++){ // 최단거리의 조건은 V-1
        // u까지의 거리 + w가 v까지의 현재 거리보다 작으면 갱신
        for(auto edge : graph){
            // int u = get<0>(edge);
            // int v = get<1>(edge);
            // int w = profit[v] - get<2>(edge);
            int u, v, cost;
            tie(u, v, cost) = edge;
            int w = profit[v] - cost;
            if(total[u] != MIN_LL && total[u] + w > total[v]) { 
                // printf("%d->%d, update total[%d]: %d -> %d\n",u, v, v, total[v], total[u] + w);
                total[v] = total[u] + w;
            }
        }
    }

    // can't visit -> gg
    // printf("total[dst:%d] = %lld\n", dst, total[dst]);
    if(total[dst] == MIN_LL) {
        cout << "gg" << endl;
        return 0;
    }

    // detect infinite cycle -> gee
    for(auto edge : graph){
        // int u = get<0>(edge);
        // int v = get<1>(edge);
        // int w = profit[v] - get<2>(edge);
        int u, v, cost;
        tie(u, v, cost) = edge;
        int w = profit[v] - cost;
        if(total[u] != MIN_LL && total[u] + w > total[v]) {
            fill(visited, visited + N, false);
            reachable = false;
            dfs(v);
            if (reachable) {
                cout << "Gee\n";
                return 0;
            }
        }
    }

    cout << total[dst] << endl;
    return 0;
}
