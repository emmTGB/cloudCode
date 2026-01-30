#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& g) {
        vector<vector<int>> ans;
        sort(g.begin(), g.end());
        for (int i = 1; i < g.size(); ++i) {
            if (g[i - 1][1] < g[i][0]) {
                ans.push_back(g[i - 1]);
            }
            else {
                g[i][0] = g[i - 1][0];
                if (g[i - 1][1] > g[i][1]) g[i][1] = g[i-1][1];
            }
        }
        ans.push_back(g[g.size() - 1]);
        return ans;
    }
};