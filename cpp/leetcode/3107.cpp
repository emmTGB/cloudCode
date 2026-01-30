#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long minOperationsToMakeMedianK(vector<int>& nums, int k) {
        int m = nums.size() / 2;
        sort(nums.begin(), nums.end());
        long long ans = 0;
        if (nums[m] > k) {
            for (int i = m; i >= 0 && nums[i] > k; --i) {
                ans += nums[i] - k;
            }
        }
        else {
            for (int i = m; i < nums.size() && nums[i] < k; ++i) {
                ans += k - nums[i];
            }
        }
        return ans;
    }
};