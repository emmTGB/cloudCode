#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int vowelStrings(vector<string>& words, int left, int right) {
        int ans = 0;
        const string v = "aeiou";
        for (int i = left; i <= right; i++) {
            string s = words[i];
            if (v.find(s[0]) != string::npos&& v.find(s.back()) != string::npos)
                ans ++;
        }
        return ans;
    }
};