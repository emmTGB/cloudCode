#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string minWindow(string s, string t) {
        int cnt[128]{};
        int less = 0;
        for (char c : t) {
            if (!cnt[c]) ++less;  // 表示需求字符种数，用于加速算法
            cnt[c]++;
        }

        int l = 0;
        int n = s.length();
        int al = -1, ar = n;
        for (int r = 0; r < n; ++r) {
            char c = s[r];
            cnt[c]--;
            if (!cnt[c]) {  // 当目标字符足够时，将其从需求字符种数中提出，其他字符进入窗口后不会得到0
                --less;
            }
            while (!less) {  // 当一直满足题意时
                if (r - l < ar - al) {  // 记录最小窗口
                    al = l;
                    ar = r;
                }
                char h = s[l];  // 左侧字符
                if (cnt[h] == 0) {  // 移除前检查是否为必须字符，对于不存在于t中的字符在移出窗口前不会>=0，所以此处仅针对t中字符
                    ++less;
                }
                ++cnt[h];  // 移除，右移窗口
                ++l;
            }
        }
        return al < 0 ? "" : s.substr(al, ar - al + 1);
    }
};
