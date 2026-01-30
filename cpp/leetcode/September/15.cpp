#include<bits/stdc++.h>
using namespace std;

// 找到最小可能的一个，遍历剩下的，找到答案后筛去重复答案

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& n) {
        if (n.size() < 3) return {};
        sort(n.begin(), n.end());
        vector<vector<int>> res;
        int l = n.size();
        for (int i = 0; i < l - 2; ++i) {
            int x = n[i];
            if (i && x == n[i - 1]) continue;
            if (x + n[i + 1] + n[i + 2] > 0) break;  // 向后不存在
            if (x + n[l - 1] + n[l - 2] < 0) continue;  // 不够等于0
            int j = i + 1, k = l - 1;
            while (j < k) {
                int s = x + n[j] + n[k];
                if (s > 0) {
                    --k;
                }
                else if (s < 0) {
                    ++j;
                }
                else {
                    res.push_back({ x, n[j], n[k] });
                    for (++j; j < k && n[j] == n[j - 1]; ++j);
                    for (--k; j < k && n[k] == n[k + 1]; --k);
                }
            }
        }
        return res;
    }
};