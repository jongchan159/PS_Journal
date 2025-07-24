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

    cin >> N >> src >> dst >> M;

    // 그래프 입력
    for(int i = 0; i < M; i++) {
        int u, v, cost;
        cin >> u >> v >> cost;
        graph.push_back({u, v, cost});
        adj[u].push_back(v); // 정방향 인접 리스트 구성
    }

    int profit[N];
    for(int i = 0; i < N; i++) {
        cin >> profit[i];
    }

    long long total[N];
    fill(total, total + N, MIN_LL);
    total[src] = profit[src];

    // 벨만 포드: N-1번 relax
    for(int i = 0; i < N - 1; i++) {
        for(auto edge : graph) {
            int u, v, cost;
            tie(u, v, cost) = edge;
            int w = profit[v] - cost;
            if (total[u] != MIN_LL && total[u] + w > total[v]) {
                total[v] = total[u] + w;
            }
        }
    }

    if (total[dst] == MIN_LL) {
        cout << "gg\n";
        return 0;
    }

    // 음수 사이클 여부 + DFS를 통한 도착지 영향 판별
    for (auto edge : graph) {
        int u, v, cost;
        tie(u, v, cost) = edge;
        int w = profit[v] - cost;
        if (total[u] != MIN_LL && total[u] + w > total[v]) {
            // 사이클이 감지된 정점에서 DFS 시도
            fill(visited, visited + N, false);
            reachable = false;
            dfs(v);
            if (reachable) {
                cout << "Gee\n";
                return 0;
            }
        }
    }

    cout << total[dst] << '\n';
    return 0;
}