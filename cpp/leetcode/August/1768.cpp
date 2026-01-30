#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string mergeAlternately(string word1, string word2) {
        int m = min(word1.length(), word2.length());
        string res = "";
        for (int i = 0; i < m; ++i) {
            res += word1[i];
            res += word2[i];
        }
        for (int i = m; i < word1.length(); ++i) {
            res += word1[i];
        }
        for (int i = m; i < word2.length(); ++i) {
            res += word2[i];
        }
        return res;
    }
};