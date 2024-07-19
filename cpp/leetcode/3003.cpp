#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        if (k == 26) return 1;

        int n = s.length();
        vector<pair<int, int>> suff(n + 1);

        int seg = 1, mask = 0, size = 0;

        auto update = [&](int i) {
            int bit = 1 << (s[i] - 'a');
            if (mask & bit) return;
            if (++size > k) {
                seg++;
                size = 1;
                mask = bit;
            }
            else {
                mask |= bit;
            }
            };

        for (int i = n - 1; i >= 0; i--) {
            update(i);
            suff[i] = { seg, mask };
        }

        int ans = seg;
        seg = 1, mask = 0, size = 0;
        for (int i = 0; i < n; ++i) {
            auto [suf_seg, suf_mask] = suff[i + 1];
            int res = seg + suf_seg;
            int union_size = __builtin_popcount(mask | suf_mask);
            if (union_size < k) {
                res--;
            }
            else if (union_size < 26 && size == k && __builtin_popcount(suf_mask) == k) {
                res++;
            }
            ans = max(ans, res);
            update(i);
        }

        return ans;
    }
};