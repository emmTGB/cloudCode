#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minSwaps(string s) {
        int c00 = 0, c01 = 0, c10 = 0, c11 = 0;
        for (int i = 0; i < s.length(); i += 2) {
            if (s[i] - '0') {
                c01++;
            }
            else {
                c00++;
            }
        }
        for (int i = 1; i < s.length(); i += 2) {
            if (s[i] - '0') {
                c11++;
            }
            else {
                c10++;
            }
        }
        // cout << c00 << c01 << c10 << c11 << endl;
        if (abs(c10 + c00 - c01 - c11) > 1) return -1;
        int m = INT32_MAX;
        if (c10 == c01) m = min(c10, m);
        if (c11 == c00) m = min(c11, m);
        return m;
    }
};