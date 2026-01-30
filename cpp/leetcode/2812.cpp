#include<bits/stdc++.h>
using namespace std;

class Solution {
    static constexpr int dirs[4][2] = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
public:
    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();
        if (grid[0][0] || grid[n - 1][n - 1]) return 0;
        vector<pair<int, int>> q;
        vector<vector<int>> dis(n, vector<int>(n, -1));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    q.emplace_back(i, j);
                    dis[i][j] = 0;
                }
            }
        }

        vector<vector<pair<int, int>>> groups = { q };
        while (!q.empty()) {
            vector<pair<int, int>> nq;
            for (auto& [i, j] : q) {  // 多源bfs
                for (auto& d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] < 0) {
                        nq.emplace_back(x, y);
                        dis[x][y] = groups.size();
                    }
                }
            }
            groups.push_back(nq);  //同距离点分组记录
            q = move(nq);  // q迭代
        }

        // 并查集模板
        vector<int> fa(n * n);
        iota(fa.begin(), fa.end(), 0);  // iota 设置初值递增初始化
        function<int(int)> find = [&](int x) -> int {
            return fa[x] == x ? x : fa[x] = find(fa[x]);
            };

        for (int ans = (int)groups.size() - 2; ans > 0; ans--) {
            for (auto& [i, j] : groups[ans]) {
                for (auto& d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] >= dis[i][j])
                        fa[find(x * n + y)] = find(i * n + j);
                }
            }
            if (find(0) == find(n * n - 1))
                return ans;
        }
        return 0;
    }
};