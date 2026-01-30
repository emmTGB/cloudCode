#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int n = s.length(), res = 0, l = 0;
        unordered_map<char, int> m;
        for (int r = 0; r < n; ++r) {
            char c = s[r];
            m[c]++;
            while (m[c] > 1) {  // 进入窗口的字符导致了重复
                m[s[l]]--;  // 移除左侧计数直至没有重复字符
                l++;  // 缩小窗口
            }
            res = max(r - l + 1, res);
        }

        return res;
    }
};