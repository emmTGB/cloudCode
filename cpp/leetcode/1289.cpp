#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> dp(n, vector<int>(n, 0));
        for(int i = 0; i < n; i++){
            dp[0][i] = grid[0][i];
        }
        for(int j = 1; j < n; j++){
            for(int i = 0; i < n; i++){
                int mini = INT_MAX;
                for(int k = 0; k < n; k++){
                    if(i != k){
                        mini = min(mini, dp[j-1][k]);
                    }
                }
                dp[j][i] = mini + grid[j][i];
            }
        }
        int ans = INT_MAX;
        for(int i = 0; i < n; i++){
            ans = min(ans, dp[n-1][i]);
        }
        return ans;
    }
};