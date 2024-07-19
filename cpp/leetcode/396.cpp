#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        int n = nums.size();
        int sum = 0;
        int pre = 0, maxi = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            pre += i * nums[i];
        }
        maxi = pre;
        for (int i = 1; i < n; ++i) {
            pre -= sum - n * nums[i - 1];
            maxi = max(maxi, pre);
        }
        return maxi;
    }
};