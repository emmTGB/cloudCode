#include<bits/stdc++.h>
using namespace std;

// 前缀和
class Solution {
public:
    int maxAbsoluteSum(vector<int>& nums) {
        int s = 0, ub = 0, lb = 0;
        for (int x : nums) {
            s += x;
            ub = max(ub, s);
            lb = min(lb, s);
        }
        return ub - lb;
    }
};