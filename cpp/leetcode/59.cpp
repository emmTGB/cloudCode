#include<bits/stdc++.h>
using namespace std;

class Solution {
    int dir[4][2] = { {0, 1}, {1,0}, {0, -1}, {-1, 0} };
public:
    vector<vector<int>> generateMatrix(int n) {
        int d = 0, i = 0, j = 0;
        vector<vector<int>> matrix(n, (vector<int>(n, 0)));
        for (int x = 1; x <= n * n; ++x) {
            matrix[i][j] = x;
            int ni = i + dir[d][0], nj = j + dir[d][1];
            if (ni < 0 || ni >= n || nj < 0 || nj >= n || matrix[ni][nj]) d = (d + 1) % 4;
            i += dir[d][0];
            j += dir[d][1];
        }
        return matrix;
    }
};