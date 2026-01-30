#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<bool> prefixesDivBy5(vector<int>& nums) {
        long long num = 0;
        vector<bool> res(nums.size(), false);
        for (int i = 0; i < nums.size(); ++i) {
            num <<= 1;
            num += nums[i];
            res[i] = num % 5 == 0;
            num %= 5;
        }
        return res;
    }
};