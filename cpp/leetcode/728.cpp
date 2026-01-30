#include<bits/stdc++.h>
using namespace std;

class Solution {
    bool isSelfDividing(int x) {
        int tmp = x;
        while (tmp) {
            if (tmp % 10 == 0 || x % (tmp % 10)) {
                return false;
            }
            tmp /= 10;
        }
        return true;
    }
public:
    vector<int> selfDividingNumbers(int left, int right) {
        vector<int> ans;
        for (int i = left; i <= right; ++i) {
            if (isSelfDividing(i)) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};