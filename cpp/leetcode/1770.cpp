#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        int n = nums.size(), m = multipliers.size();
        vector<vector<int>> dp(m + 1, vector<int>(m + 1));
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = dp[i - 1][0] + multipliers[i - 1] * nums[i - 1];
            dp[0][i] = dp[0][i - 1] + multipliers[i - 1] * nums[n - i];
        }
        int ans = max(dp[m][0], dp[0][m]);
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= m - i; ++j) {
                dp[i][j] = max(dp[i - 1][j] + multipliers[i + j - 1] * nums[i - 1], dp[i][j - 1] + multipliers[i + j - 1] * nums[n - j]);
            }
            ans = max(ans, dp[i][m - i]);
        }
        return ans;
    }
};

int main() {
    Solution s;
    vector<int> n(3), m(3);
    n = { 1,2 ,3 };
    m = { 3,2,1 };
    cout << s.maximumScore(n, m);
}