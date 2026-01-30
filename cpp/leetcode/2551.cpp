#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long putMarbles(vector<int>& weights, int k) {
        int n = weights.size();
        vector<long long> ans(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            ans[i] = weights[i] + weights[i + 1];
        }
        sort(ans.begin(), ans.end());
        long long res = 0;
        for (int i = 0; i < k - 1; ++i) {
            res += ans[n - 2 - i];
            res -= ans[i];
        }
        return res;
    }
};