#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    void setZeroes(vector<vector<int>>& g) {
        bool hz = false, vz = false;
        int m = g.size(), n = g[0].size();
        for (int i = 0; i < m; ++i) {
            if (g[i][0] == 0) {
                vz = true;
                break;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (g[0][i] == 0) {
                hz = true;
                break;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (g[i][j] == 0) {
                    g[i][0] = 0;
                    g[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (!g[i][0] || !g[0][j]) {
                    g[i][j] = 0;
                }
            }
        }
        if(hz){
            for(int i = 0; i < n; ++i) g[0][i] = 0;
        }
        if(vz){
            for(int i = 0; i < m; ++i) g[i][0] = 0;
        }

    }
};