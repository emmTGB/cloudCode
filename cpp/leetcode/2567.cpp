#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimizeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int m = INT32_MAX, n = nums.size();
        m = min(m, nums[n - 3] - nums[0]);
        m = min(m, nums[n - 2] - nums[1]);
        m = min(m, nums[n - 1] - nums[2]);
        return m;
    }
};