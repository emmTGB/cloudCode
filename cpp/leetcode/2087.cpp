#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minCost(vector<int>& startPos, vector<int>& homePos, vector<int>& rowCosts, vector<int>& colCosts) {
        int x0 = startPos[0], y0 = startPos[1];
        int x1 = homePos[0], y1 = homePos[1];
        int ans = -rowCosts[x1] - colCosts[y1];
        if (x0 > x1) swap(x0, x1);
        if (y0 > y1) swap(y0, y1);
        for (int i = x0; i <= x1; i++) ans += rowCosts[i];
        for (int i = y0; i <= y1; i++) ans += colCosts[i];
        return ans;
    }

};