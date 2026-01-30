#include<bits/stdc++.h>
using namespace std;
#define __int long long
#define ll long long

// 滑动窗口 模拟
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        ll total = 0;
        int res = 1, l = 0;
        for (int r = 1; r < nums.size(); r++) {
            total += (ll)(nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = max(res, r - l + 1);
        }
        return res;
    }
};

// 前缀和
class SolutionPreSum {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        vector<__int> sum(nums.size() + 1, 0);
        for (__int i = 1; i <= nums.size(); ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        __int l = 0, r = nums.size();
        while (l < r) {
            __int pt = l + r + 1;
            pt >>= 1;
            if (check(pt, k, nums, sum)) l = pt;
            else r = pt - 1;
        }
        return r;
    }

    bool check(__int len, __int k, vector<int>& nums, vector<__int>& sum) {
        for (__int i = 0; i < nums.size() - len + 1; ++i) {
            __int j = i + len - 1;
            __int cur = sum[j + 1] - sum[i];
            __int tar = (__int)nums[j] * len;
            if (tar - cur <= k) return true;
        }
        return false;
    }
};