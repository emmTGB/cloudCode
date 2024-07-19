#include<bits/stdc++.h>
using namespace std;


class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int n = matrix.size(), m = matrix[0].size();
        int maxi = 0;
        vector<vector<int>> dp(n);
        for (int i = 0; i < n; ++i) {
            dp[i] = vector<int>(m);
            for (int j = 0; j < m; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    maxi = 1;
                }
                else dp[i][j] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] > 0) {
                    if (dp[i - 1][j - 1] > 0 && dp[i][j - 1] > 0 && dp[i - 1][j] > 0) {
                        dp[i][j] += min(min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    }
                    maxi = max(maxi, dp[i][j]);
                }
            }
        }
        return maxi * maxi;
    }
};