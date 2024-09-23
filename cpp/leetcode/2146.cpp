#include<bits/stdc++.h>
using namespace std;

bool cmp_val(vector<int>& a, vector<int>& b) {
    if (a[0] == b[0]) {
        if (a[1] == b[1]) {
            if (a[2] == b[2]) {
                return a[3] < b[3];
            }
            else {
                return a[2] < b[2];
            }
        }
        else {
            return a[1] < b[1];
        }
    }
    else {
        return a[0] < b[0];
    }
}

class Solution {
    vector<vector<int>> visited;
    vector <vector<int>> ans;
    int m = 0, n = 0;
    int max = 0, min = 0;
    void bfs(int i, int j, vector<vector<int>>& grid, int length, queue<vector<int>>& q) {
        if (i < 0 || j < 0 || i >= m || j >= n)
            return;
        if (visited[i][j])
            return;
        visited[i][j] = 1;
        if (grid[i][j]) {
            length++;
            if (grid[i][j] > 1 && grid[i][j] <= max && grid[i][j] >= min) {
                ans.push_back({ length, grid[i][j], i, j });
            }
            q.push({ i + 1, j, length });
            q.push({ i - 1, j, length });
            q.push({ i, j + 1, length });
            q.push({ i, j - 1, length });
        }
    }
public:
    vector<vector<int>> highestRankedKItems(vector<vector<int>>& grid, vector<int>& pricing, vector<int>& start, int k) {
        m = grid.size(), n = grid[0].size();
        max = pricing[1], min = pricing[0];
        visited.resize(m, vector<int>(n, 0));
        queue<vector<int>> q;
        q.push({ start[0], start[1], 0 });
        while (!q.empty()) {
            bfs(q.front()[0], q.front()[1], grid, q.front()[2], q);
            q.pop();
        }
        sort(ans.begin(), ans.end(), cmp_val);
        vector<vector<int>> res;
        int i = 0;
        for (auto it : ans) {
            if (i >= k)
                break;
            res.push_back({ it[2], it[3] });
            i++;
        }
        return res;
    }
};

int main() {
    Solution s;
    vector<vector<int>> grid = { {1,2,0,1},{1,3,0,1},{0,2,5,1} };
    vector<int> pricing = { 2,5 };
    vector<int> start = { 0,0 };
    int k = 3;

    vector<vector<int>> res = s.highestRankedKItems(grid, pricing, start, k);
    for (auto i : res) {
        for (auto j : i) {
            cout << j << " ";
        }
        cout << endl;
    }
}


class best_Solution {
public:
    vector<vector<int>> highestRankedKItems(vector<vector<int>>& grid, vector<int>& pricing, vector<int>& start, int k) {
        int m = grid.size(), n = grid[0].size();
        int low = pricing[0], high = pricing[1];
        int dx[] = { 1,0,-1,0 };
        int dy[] = { 0,1,0,-1 };
        queue<tuple<int, int, int>> q;
        vector<tuple<int, int, int, int>> res;
        int i = start[0], j = start[1];
        q.emplace(i, j, 0);
        if (grid[i][j] >= low && grid[i][j] <= high) {
            res.emplace_back(0, grid[i][j], i, j);
        }
        grid[i][j] = 0;
        while (!q.empty()) {
            auto [x, y, step] = q.front();
            q.pop();
            for (int k = 0; k < 4; k++) {
                int a = x + dx[k];
                int b = y + dy[k];
                if (a >= 0 && b >= 0 && a < m && b < n && grid[a][b] > 0) {
                    if (grid[a][b] >= low && grid[a][b] <= high) {
                        res.emplace_back(step + 1, grid[a][b], a, b);
                    }
                    q.emplace(a, b, step + 1);
                    grid[a][b] = 0;
                }
            }
        }
        sort(res.begin(), res.end());
        vector<vector<int>> ans;
        int s = min(k, static_cast<int>(res.size()));
        for (int i = 0; i < s; i++) {
            auto [step, v, x, y] = res[i];
            ans.push_back({ x,y });
        }
        return ans;
    }
};