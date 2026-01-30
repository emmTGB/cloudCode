#include<bits/stdc++.h>
using namespace std;


// 同1456
class Solution {
public:
    int longestSubarray(vector<int>& n) {
        int ans = 0, l = 0, c = 0;
        for (int r = 0; r < n.size(); ++r) {
            c += 1 - n[r];
            while (c > 1) {
                c -= 1 - n[l++];
            }
            ans = max(ans, r - l);
        }
        return ans;
    }
};