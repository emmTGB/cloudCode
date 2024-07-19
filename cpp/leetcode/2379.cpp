#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumRecolors(string blocks, int k) {
        int cnt_w = 0;
        for (int i = 0; i < k; ++i)
            cnt_w += blocks[i] & 1;
        int ans = cnt_w;
        for (int i = k; i < blocks.length(); ++i) {
            cnt_w += (blocks[i] & 1) - (blocks[i - k] & 1);
            ans = min(ans, cnt_w);
        }
        return ans;
    }
};
