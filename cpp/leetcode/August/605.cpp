#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool canPlaceFlowers(vector<int>& fb, int n) {
        int i = 0, pre = -2, ans = 0;
        for (; i < fb.size(); ++i) {
            if (fb[i]) {
                ans += (i - pre - 2) / 2;
                pre = i;
            }
        }
        ans += (i - pre - 1) / 2;
        return ans >= n;
    }
};
