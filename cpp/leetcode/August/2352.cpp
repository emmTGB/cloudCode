#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int equalPairs(vector<vector<int>>& g) {
        int n = g.size();
        map<vector<int>, int> c;
        for (const auto& r : g) {
            c[r]++;
        }
        int res = 0;
        for (int j = 0; j < n; ++j) {
            vector<int> col;
            for (int i = 0; i < n; ++i) {
                col.push_back(g[i][j]);
            }
            if (c.find(col) != c.end()) {
                res += c[col];
            }
        }
        return res;
    }
};