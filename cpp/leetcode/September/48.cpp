#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    void rotate(vector<vector<int>>& g) {
        int n = g.size();
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - i - 1; ++j) {
                int tmp = g[i][j];
                g[i][j] = g[n - j - 1][i];
                g[n - j - 1][i] = g[n - i - 1][n - j - 1];
                g[n - i - 1][n - j - 1] = g[j][n - i - 1];
                g[j][n - i - 1] = tmp;
            }
        }
    }
};