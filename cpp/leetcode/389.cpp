#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    char findTheDifference(string s, string t) {
        char ans = 0;
        for (int i = 0; i < s.length();++i) {
            ans ^= s[i] ^ t[i];
        }
        ans ^= 0 ^ t[t.length() - 1];
        return ans;
    }
};