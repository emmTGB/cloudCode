#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        int diagonal = 0;
        int area = 0;
        for (auto& r : dimensions) {
            int a = r[0], b = r[1];
            int d = a * a + b * b;
            if (d > diagonal) {
                area = a * b;
                diagonal = d;
            }
            else if (d == diagonal) {
                area = max(area, a * b);
            }
        }
        return area;
    }
};