#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> findAnagrams(string st, string pt) {
        vector<int> ans;
        array<int, 26> p{};
        array<int, 26> s{};
        for (char c : pt) {
            p[c - 'a']++;
        }
        for (int r = 0; r < st.length(); ++r) {
            s[st[r] - 'a']++;
            int l = r - pt.length() + 1;
            if (l < 0) continue;  // 窗口长度不够
            if (s == p) {
                ans.push_back(l);  // 符合题意
            }
            s[st[l] - 'a']--;  //将当前左侧剔除
        }
        return ans;
    }
};