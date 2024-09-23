#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumTimeToInitialState(string s, int k) {
        int n = s.size();
        vector<int> z(n);
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            if (i % k == 0 && z[i] >= n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1;
    }
};