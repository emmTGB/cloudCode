#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<long long> unmarkedSumArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        vector<int> ids(n);
        iota(ids.begin(), ids.end(), 0);
        ranges::stable_sort(ids, [&](int i, int j) { return nums[i] < nums[j]; });

        vector<long long> ans;
        int j = 0;
        for (auto& q : queries) {
            int i = q[0], k = q[1];
            s -= nums[i];
            nums[i] = 0;
            for (; j < n && k; j++) {
                i = ids[j];
                if (nums[i] > 0) {
                    s -= nums[i];
                    nums[i] = 0;
                    k--;
                }
            }
            ans.push_back(s);
        }
        return ans;
    }
};