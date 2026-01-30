#include<bits/stdc++.h>
using namespace std;

// elegant
class Solution {
public:
    int longestBeautifulSubstring(string word) {
        int len = word.length();
        if (len < 5) return 0;
        int ans = 0, rlen = 1, v = 1;
        for (int i = 1; i < len; ++i) {
            if (word[i] >= word[i - 1])rlen++;
            if (word[i] > word[i - 1])v++;
            if (word[i] < word[i - 1]) { rlen = 1, v = 1; }
            if (v == 5)ans = rlen > ans ? rlen : ans;
        }
        return ans;
    }
};