#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> s(n + 1, 0);  // 前缀和
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }

        int ans = 0;
        unordered_map<int, int> m;
        for (int j : s) {
            ans += m.contains(j - k) ? m[j - k] : 0;
            m[j]++;  // 晚插自身避免k = 0 重复计算
        }
        return ans;
    }
};