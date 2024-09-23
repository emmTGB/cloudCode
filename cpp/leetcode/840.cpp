#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numMagicSquaresInside(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();

        auto magic = [&](vector<int> v) -> bool {
            vector<int> e(10, 0);
            for (auto x : v) {
                if ((x > 9 || x < 1) || e[x]) return false;
                e[x] = 1;
            }

            return (v[0] + v[1] + v[2] == 15
                && v[3] + v[4] + v[5] == 15
                && v[6] + v[7] + v[8] == 15
                && v[0] + v[3] + v[6] == 15
                && v[1] + v[4] + v[7] == 15
                && v[2] + v[5] + v[8] == 15
                && v[2] + v[4] + v[6] == 15
                && v[0] + v[4] + v[8] == 15
                );
            };
        if (n < 3 || m < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < n - 2; ++i) {
            for (int j = 0; j < m - 2; ++j) {
                if (grid[i + 1][j + 1] != 5) continue;
                if (magic({ grid[i][j], grid[i][j + 1], grid[i][j + 2], grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2], grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2] })) {
                    ++count;
                }
            }
        }
        return count;
    }
};