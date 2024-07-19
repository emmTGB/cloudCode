#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isPossibleToCutPath(vector<vector<int>>& g) {
        int m = g.size(), n = g[0].size();
        function<bool(int, int)> dfs = [&](int x, int y) -> bool { // 返回能否到达终点
            if (x == m - 1 && y == n - 1) return true;
            g[x][y] = 0; // 直接修改
            return x < m - 1 && g[x + 1][y] && dfs(x + 1, y) /*先dfs x+1向右，并在这一趟将所有优先向右的路径全部置0*/
                || y < n - 1 && g[x][y + 1] && dfs(x, y + 1) /*再向下dfs*/;
            };
        return !dfs(0, 0) || !dfs(0, 0); // 若先右和先左交集为空，则true
    }
};