#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<int> f(k + 1);
        int sum_n = 0;
        for (auto& s : piles) {
            partial_sum(s.begin(), s.end(), s.begin());
            int n = s.size();
            sum_n = min(sum_n + n, k);  // 限制长度，初始加速
            for (int i = sum_n; i; --i) {
                for (int w = 0; w < min(n, i); ++w) {
                    f[i] = max(f[i], f[i - w - 1] + s[w]);
                }
            }
        }
        return f[k];
    }
};