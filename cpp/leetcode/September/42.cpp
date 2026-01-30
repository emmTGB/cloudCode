#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int trap(vector<int>& h) {
        int res = 0, l = 0, r = h.size() - 1;
        int pm = 0, sm = 0;
        while (l < r) {
            pm = max(pm, h[l]);
            sm = max(sm, h[r]);
            res += pm < sm ? pm - h[l++] : sm - h[r--];  // 计算移动侧每个可以接多少，先移动低端侧，保证计算的接水数可以达到所见即所得不需要考虑另一侧
        }
        return res;
    }
};