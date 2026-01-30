#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    bool increasingTriplet(vector<int>& n) {
        if (n.size() < 3) return false;

        int s = INT32_MAX, m = INT32_MAX;
        for (auto num : n) {
            if (num <= s) {
                s = num;
            }
            else if (num <= m) {
                m = num;
            }
            else if (num > m) {
                return true;
            }
        }
        return false;
    }
};