#include<bits/stdc++.h>
#include<unordered_map>
using namespace std;

class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        int ans = cnt[1] - 1 | 1; // 奇数
        cnt.erase(1);
        for (auto& [num, _] : cnt) {
            int res = 0;
            long long x = num;
            for (; cnt.contains(x) && cnt[x] > 1; x *= x) {
                res += 2;
            }
            ans = max(ans, res + (cnt.contains(x) ? 1 : -1)); // 保证 res 是奇数
        }
        return ans;
    }
};