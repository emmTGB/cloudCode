#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxRemovals(string source, string pattern, vector<int>& targetIndices) {
        int m = pattern.length();
        vector<int> f(m + 1, INT_MIN);
        f[0] = 0;
        int k = 0;
        for (int i = 0; i < source.length(); i++) {
            if (k < targetIndices.size() && targetIndices[k] < i) {
                k++;
            }
            int is_del = k < targetIndices.size() && targetIndices[k] == i;
            for (int j = min(i, m - 1); j >= 0; j--) {
                f[j + 1] += is_del;
                if (source[i] == pattern[j]) {
                    f[j + 1] = max(f[j + 1], f[j]);
                }
            }
            f[0] += is_del;
        }
        return f[m];
    }
};
