#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    const int dr[4] = { 1, 1, -1, -1 };
    const int dc[4] = { 1, -1, -1, 1 };

    vector<vector<int>> allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int maxDist = max(rCenter, rows - 1 - rCenter) + max(cCenter, cols - 1 - cCenter);
        vector<vector<int>> ret;
        int row = rCenter, col = cCenter;
        ret.push_back({ row, col });
        for (int dist = 1; dist <= maxDist; dist++) {
            row--;
            for (int i = 0; i < 4; i++) {
                while ((i % 2 == 0 && row != rCenter) || (i % 2 != 0 && col != cCenter)) {
                    if (row >= 0 && row < rows && col >= 0 && col < cols) {
                        ret.push_back({ row, col });
                    }
                    row += dr[i];
                    col += dc[i];
                }
            }
        }
        return ret;
    }
};
