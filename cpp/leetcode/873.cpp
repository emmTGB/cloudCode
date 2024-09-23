#include<bits/stdc++.h>
using namespace std;
class Solution {
public:
    int lenLongestFibSubseq(vector<int>& A) {
        int n = A.size();
        if (n == 0) { return 0; }
        unordered_map<int, int> intMap;
        for (int i = 0; i < n; i++) {
            intMap[A[i]] = i;
        }
        vector<vector<int>> dp(n, vector<int>(n, 0));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = 2;
            }
        }
        int MAX = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = A[j] - A[i];
                if (intMap.count(diff)) {
                    int index = intMap[diff];
                    if (index < i) {
                        dp[i][j] = max(dp[i][j], dp[index][i] + 1);
                    }
                }
                MAX = max(MAX, dp[i][j]);
            }
        }

        return MAX > 2 ? MAX : 0;
    }
};
