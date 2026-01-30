#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long countAlternatingSubarrays(vector<int>& nums) {
        long long ans = 0;
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i > 0 && nums[i] != nums[i - 1]) {
                cnt++;
            }
            else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
};