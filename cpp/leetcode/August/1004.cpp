#include<bits/stdc++.h>
using namespace std;



// 精妙绝伦
class Solution {
public:
    int longestOnes(vector<int>& n, int k) {
        int ans = 0, l = 0, c = 0;
        for (int r = 0; r < n.size(); ++r) {
            c += 1 - n[r];
            while (c > k) {
                c -= 1 - n[l++];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};