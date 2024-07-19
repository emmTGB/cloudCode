#include<bits/stdc++.h>
using namespace std;

// 前缀和
class Solution {
public:
    vector<vector<int>> rangeAddQueries(int n, vector<vector<int>>& queries) {
        // 二维差分模板
        vector<vector<int>> diff(n + 2, vector<int>(n + 2));
        for (auto& q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            ++diff[r1 + 1][c1 + 1];
            --diff[r1 + 1][c2 + 2];
            --diff[r2 + 2][c1 + 1];
            ++diff[r2 + 2][c2 + 2];
            // 标记并使其影响包括自身的右下矩形区域
        }

        // 用二维前缀和复原（原地修改）
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
        // 保留中间 n*n 的部分，即为答案
        diff.pop_back(), diff.erase(diff.begin());
        for (auto& row : diff)
            row.pop_back(), row.erase(row.begin());
        return diff;
    }
};