#include<bits/stdc++.h>
using namespace std;


// 贪心，试图去找到比目前更好的

class Solution {
public:
    int maxArea(vector<int>& h) {
        int res = 0;
        int i = 0;
        int j = h.size() - 1;
        while (i < j) {
            int a = (j - i) * min(h[i], h[j]);
            res = max(a, res);
            if (h[i] < h[j]) {
                ++i;
            }
            else {
                --j;
            }
        }
        return res;
    }
};