#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxVowels(string s, int k) {
        string aw = "aeiou";
        vector<int> vis(26);
        for (auto c : aw) vis[c - 'a'] = 1;
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            if (vis[s[i] - 'a']) {
                ++ans;
            }
        }
        int now = ans;
        for (int i = k; i < s.length(); ++i) {
            now = now + (vis[s[i] - 'a']) - (vis[s[i - k] - 'a']);
            ans = max(ans, now);
        }
        return ans;
    }
};
