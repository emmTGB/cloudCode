#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxArea(vector<int>& h) {
        int i = 0, j = h.size() - 1;
        int m = 0;
        while (i < j) {
            m = max((j - i) * min(h[i], h[j]), m);
            if (h[i] <= h[j]) {
                ++i;
            }
            else {
                --j;
            }
        }
        return m;
    }
};