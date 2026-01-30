#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<bool> kidsWithCandies(vector<int>& c, int e) {
        int max = -1;
        for (int k : c) {
            if (max < k) max = k;
        }
        vector<bool> r(c.size());
        max -= e;
        for (int i = 0; i < c.size(); ++i) {
            r[i] = c[i] >= max;
        }
        return r;
    }
};