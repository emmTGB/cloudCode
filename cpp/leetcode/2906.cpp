#include<bits/stdc++.h>
using namespace std;

//前后缀和

class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        const int MOD = 12345;
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> p(m, vector<int>(n));

        long long suf = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                p[i][j] = suf;
                suf = suf * grid[i][j] % MOD;
            }
        }
        long long pre = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = p[i][j] * pre % MOD;
                pre = pre * grid[i][j] % MOD;
            }
        }
        return p;
    }
};