#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& n) {
        int l = n.size();
        vector<int> pre(l, 1), suf(l, 1);
        for (int i = l - 2; i >= 0; --i) {
            suf[i] = suf[i + 1] * n[i + 1];
        }
        for (int i = 1; i < l; ++i) {
            pre[i] = pre[i - 1] * n[i - 1];
            pre[i - 1] *= suf[i - 1];
        }
        pre[l - 1] *= suf[l - 1];
        return pre;
    }
};