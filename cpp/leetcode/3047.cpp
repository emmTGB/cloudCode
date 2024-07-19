#include<bits/stdc++.h>
using namespace std;
class Solution {
public:
    long long largestSquareArea(vector<vector<int>>& bottomLeft, vector<vector<int>>& topRight) {
        long long ans = 0;
        for (int i = 0; i < bottomLeft.size(); i++) {
            auto& b1 = bottomLeft[i];
            auto& t1 = topRight[i];
            for (int j = i + 1; j < bottomLeft.size(); j++) {
                auto& b2 = bottomLeft[j];
                auto& t2 = topRight[j];
                int h = min(t1[1], t2[1]) - max(b1[1], b2[1]);
                int w = min(t1[0], t2[0]) - max(b1[0], b2[0]);
                long long size = min(w, h);
                if (size > 0)
                    ans = max(ans, size * size);
            }
        }
        return ans;
    }
};