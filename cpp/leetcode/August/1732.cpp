#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int largestAltitude(vector<int>& g) {
        int m = max(0, g[0]);
        for (int i = 1; i < g.size(); ++i) {
            g[i] += g[i - 1];
            m = max(m, g[i]);
        }
        return m;
    }
};