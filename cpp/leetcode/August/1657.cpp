#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool closeStrings(string w1, string w2) {
        if (w1.length() != w2.length()) return false;
        int m1 = 0, m2 = 0;
        vector<int> c1(26), c2(26);
        for (char c : w1) {
            m1 |= 1 << (c - 'a');
            c1[c - 'a']++;
        }
        for (char c : w2) {
            m2 |= 1 << (c - 'a');
            c2[c - 'a']++;
        }
        sort(c1.begin(), c1.end());
        sort(c2.begin(), c2.end());
        return m1 == m2 && c1 == c2;
    }
};