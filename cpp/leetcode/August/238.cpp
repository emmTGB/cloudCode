#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        vector<int> res = { 1 };
        int pre = 1;
        for (int i = 1; i < nums.size(); ++i) {
            pre *= nums[i - 1];
            res.push_back(pre);
        }
        int bck = 1;
        for (int i = nums.size() - 2; i >= 0; --i) {
            bck *= nums[i + 1];
            res[i] *= bck;
        }
        return res;
    }
};


// 最基本的前后缀
