#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isIsomorphic(string s, string t) {
        vector<int> map(255, -1), rmap(255, -1);
        int l = s.length();
        for (int i = 0; i < l; ++i) {
            if (map[s[i]] < 0) {
                if (rmap[t[i]] < 0) {
                    map[s[i]] = t[i];
                    rmap[t[i]] = s[i];
                }
                else {
                    return false;
                }
            }
            else {
                if (map[s[i]] != t[i])
                    return false;
            }
        }
        return true;
    }
};