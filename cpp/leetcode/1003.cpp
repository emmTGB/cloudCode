#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isValid(string s) {
        if (s.length() % 3) return false;
        int i = 0;
        for (char c : s) {
            if (c > 'a' && (i == 0 || c - s[--i] != 1)) {
                return false;
            }
            if (c < 'c') {
                s[i++] = c;
            }
        }
        return i == 0;   // 最后栈空为true，否则不符合题意
    }
};